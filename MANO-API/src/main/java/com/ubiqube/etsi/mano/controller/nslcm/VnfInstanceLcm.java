package com.ubiqube.etsi.mano.controller.nslcm;

import static com.ubiqube.etsi.mano.Constants.ensureInstantiated;
import static com.ubiqube.etsi.mano.Constants.ensureIsEnabled;
import static com.ubiqube.etsi.mano.Constants.ensureIsOnboarded;
import static com.ubiqube.etsi.mano.Constants.ensureNotInstantiated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmResourceChanges;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfLcmService;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mano.service.vim.VimManager;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.OperateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.ScaleVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.ScaleVnfToLevelRequest;

import ma.glasnost.orika.MapperFacade;

/**
 * NFVO+VNFM & VNFM Implementation. TODO: Make terminate Async and this will be
 * generic again.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
//@Profile({ "!NFVO" })
@Service
public class VnfInstanceLcm {

	private static final Logger LOG = LoggerFactory.getLogger(VnfInstanceLcm.class);

	private final VnfInstancesRepository vnfInstancesRepository;

	private final VnfPackageRepository vnfPackageRepository;

	private final EventManager eventManager;

	private final MapperFacade mapper;

	private final VnfLcmService vnfLcmService;

	private final VnfInstanceService vnfInstanceService;

	private final VimManager vimManager;

	public VnfInstanceLcm(final VnfInstancesRepository vnfInstancesRepository, final VnfPackageRepository vnfPackageRepository, final EventManager _eventManager, final MapperFacade _mapper, final VnfLcmService _vnfLcmService, final VnfInstanceService _vnfInstanceService, final VimManager _vimManager) {
		super();
		this.vnfInstancesRepository = vnfInstancesRepository;
		this.vnfPackageRepository = vnfPackageRepository;
		eventManager = _eventManager;
		mapper = _mapper;
		vnfLcmService = _vnfLcmService;
		vnfInstanceService = _vnfInstanceService;
		vimManager = _vimManager;
	}

	public List<VnfInstance> get(final Map<String, String> queryParameters) {
		final String filter = queryParameters.get("filter");
		return vnfInstancesRepository.query(filter);
	}

	public VnfLcmOpOccs get(final UUID id) {
		final VnfLcmOpOccs lcm = vnfLcmService.findById(id);
		final VnfLcmResourceChanges instInfo = lcm.getResourceChanges();
		instInfo.setAffectedExtCp(instInfo.getAffectedExtCp().stream().filter(x -> x.getResourceId() != null).collect(Collectors.toSet()));
		instInfo.setAffectedVirtualLinks(instInfo.getAffectedVirtualLinks().stream().filter(x -> x.getResourceId() != null).collect(Collectors.toSet()));
		instInfo.setAffectedVirtualStorages(instInfo.getAffectedVirtualStorages().stream().filter(x -> x.getResourceId() != null).collect(Collectors.toSet()));
		instInfo.setAffectedVnfcs(instInfo.getAffectedVnfcs().stream().filter(x -> x.getResourceId() != null).collect(Collectors.toSet()));
		return lcm;
	}

	public VnfInstance post(final String vnfdId, final String vnfInstanceName, final String vnfInstanceDescription) {
		final VnfPackage vnfPkgInfo = vnfPackageRepository.get(UUID.fromString(vnfdId));
		ensureIsOnboarded(vnfPkgInfo);
		ensureIsEnabled(vnfPkgInfo);
		VnfInstance vnfInstance = LcmFactory.createVnfInstance(vnfdId, vnfInstanceName, vnfInstanceDescription, vnfPkgInfo);
		mapper.map(vnfPkgInfo, vnfInstance);
		// VnfIdentifierCreationNotification NFVO + EM
		vnfInstance = vnfInstancesRepository.save(vnfInstance);
		eventManager.sendNotification(NotificationEvent.VNF_INSTANCE_CREATE, vnfInstance.getId());
		return vnfInstance;
	}

	public void delete(@Nonnull final UUID vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		ensureNotInstantiated(vnfInstance);

		if (vnfInstancesRepository.isInstantiate(vnfInstance.getVnfPkg().getId())) {
			final VnfPackage vnfPkg = vnfPackageRepository.get(vnfInstance.getVnfPkg().getId());
			vnfPkg.setUsageState(PackageUsageState.NOT_IN_USE);
			vnfPackageRepository.save(vnfPkg);
		}
		vnfInstanceService.delete(vnfInstanceId);
		// VnfIdentitifierDeletionNotification NFVO + EM
	}

	public VnfLcmOpOccs instantiate(@Nonnull final UUID vnfInstanceId, final VnfInstantiate instantiateVnfRequest) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		ensureNotInstantiated(vnfInstance);

		final UUID vnfPkgId = vnfInstance.getVnfPkg().getId();
		final VnfPackage vnfPkg = vnfPackageRepository.get(vnfPkgId);
		ensureIsEnabled(vnfPkg);

		if (null != instantiateVnfRequest.getVimConnectionInfo()) {
			final List<VimConnectionInformation> vimconnections = mapper.mapAsList(instantiateVnfRequest.getVimConnectionInfo(), VimConnectionInformation.class);
			final Set<VimConnectionInformation> vimSet = vimconnections.stream()
					.map(x -> {
						if (null != x.getId()) {
							return vimManager.findVimById(x.getId());
						}
						final Optional<VimConnectionInformation> optVim = vimManager.findOptionalVimByVimId(x.getVimId());
						if (optVim.isPresent()) {
							return optVim.get();
						}
						return vimManager.save(x);
					}).collect(Collectors.toSet());
			vnfInstance.setVimConnectionInfo(vimSet);
			vnfInstanceService.save(vnfInstance);
		}
		final VnfInstantiatedInfo ivf = mapper.map(instantiateVnfRequest, com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedInfo.class);
		VnfLcmOpOccs lcmOpOccs = vnfLcmService.createIntatiateOpOcc(vnfInstance);
		lcmOpOccs.setVnfInstantiatedInfo(ivf);
		lcmOpOccs = vnfLcmService.save(lcmOpOccs);
		eventManager.sendAction(ActionType.VNF_INSTANTIATE, lcmOpOccs.getId(), new HashMap<>());
		LOG.info("VNF Instantiation Event Sucessfully sent.");
		return lcmOpOccs;
	}

	public VnfLcmOpOccs terminate(@Nonnull final UUID vnfInstanceId, final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		ensureInstantiated(vnfInstance);
		final VnfLcmOpOccs lcmOpOccs = vnfLcmService.createTerminateOpOcc(vnfInstance);
		eventManager.sendAction(ActionType.VNF_TERMINATE, lcmOpOccs.getId(), new HashMap<String, Object>());
		LOG.info("Terminate sent for instancce: {}", vnfInstanceId);
		return lcmOpOccs;
	}

	public VnfLcmOpOccs scaleToLevel(final UUID uuid, final ScaleVnfToLevelRequest scaleVnfToLevelRequest) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(uuid);
		ensureInstantiated(vnfInstance);
		final VnfLcmOpOccs lcmOpOccs = vnfLcmService.createScaleToLevelOpOcc(vnfInstance, scaleVnfToLevelRequest);
		eventManager.sendAction(ActionType.VNF_SCALE_TO_LEVEL, lcmOpOccs.getId(), new HashMap<String, Object>());
		return lcmOpOccs;
	}

	public VnfLcmOpOccs scale(final UUID uuid, final ScaleVnfRequest scaleVnfRequest) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(uuid);
		ensureInstantiated(vnfInstance);
		final VnfScaleRequest req = mapper.map(scaleVnfRequest, VnfScaleRequest.class);
		final VnfLcmOpOccs lcmOpOccs = vnfLcmService.createScaleOpOcc(vnfInstance, req);
		eventManager.sendAction(ActionType.VNF_SCALE_TO_LEVEL, lcmOpOccs.getId(), new HashMap<String, Object>());
		return lcmOpOccs;
	}

	public VnfLcmOpOccs operate(final UUID uuid, final OperateVnfRequest operateVnfRequest) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(uuid);
		ensureInstantiated(vnfInstance);
		final VnfOperateRequest req = mapper.map(operateVnfRequest, VnfOperateRequest.class);
		final VnfLcmOpOccs lcmOpOccs = vnfLcmService.createOperateOpOcc(vnfInstance, req);
		eventManager.sendAction(ActionType.VNF_OPERATE, lcmOpOccs.getId(), new HashMap<String, Object>());
		return lcmOpOccs;
	}

}
