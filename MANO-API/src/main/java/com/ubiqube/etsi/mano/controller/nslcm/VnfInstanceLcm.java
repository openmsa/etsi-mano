package com.ubiqube.etsi.mano.controller.nslcm;

import static com.ubiqube.etsi.mano.Constants.ensureInstantiated;
import static com.ubiqube.etsi.mano.Constants.ensureIsEnabled;
import static com.ubiqube.etsi.mano.Constants.ensureIsOnboarded;
import static com.ubiqube.etsi.mano.Constants.ensureNotInstantiated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.ScaleVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.ScaleVnfToLevelRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest.TerminationTypeEnum;
import com.ubiqube.etsi.mano.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfLcmService;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;

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

	public VnfInstanceLcm(final VnfInstancesRepository vnfInstancesRepository, final VnfPackageRepository vnfPackageRepository, final EventManager _eventManager, final MapperFacade _mapper, final VnfLcmService _vnfLcmService, final VnfInstanceService _vnfInstanceService) {
		super();
		this.vnfInstancesRepository = vnfInstancesRepository;
		this.vnfPackageRepository = vnfPackageRepository;
		eventManager = _eventManager;
		mapper = _mapper;
		vnfLcmService = _vnfLcmService;
		vnfInstanceService = _vnfInstanceService;
	}

	public List<com.ubiqube.etsi.mano.model.nslcm.VnfInstance> get(final Map<String, String> queryParameters, final LcmLinkable links) {
		final String filter = queryParameters.get("filter");
		final List<VnfInstance> result = vnfInstancesRepository.query(filter);
		return result.stream()
				.map(x -> {
					final com.ubiqube.etsi.mano.model.nslcm.VnfInstance v = mapper.map(x, com.ubiqube.etsi.mano.model.nslcm.VnfInstance.class);
					v.setLinks(links.getLinks(x.getId().toString()));
					return v;
				})
				.collect(Collectors.toList());
	}

	public VnfLcmOpOccs get(final UUID id) {
		return vnfLcmService.findById(id);
	}

	public com.ubiqube.etsi.mano.model.nslcm.VnfInstance post(final CreateVnfRequest createVnfRequest) {
		final String vnfId = createVnfRequest.getVnfdId();
		final VnfPackage vnfPkgInfo = vnfPackageRepository.get(UUID.fromString(vnfId));
		ensureIsOnboarded(vnfPkgInfo);
		ensureIsEnabled(vnfPkgInfo);
		VnfInstance vnfInstance = LcmFactory.createVnfInstance(createVnfRequest, vnfPkgInfo);
		mapper.map(vnfPkgInfo, vnfInstance);
		// VnfIdentifierCreationNotification NFVO + EM
		vnfInstance = vnfInstancesRepository.save(vnfInstance);
		eventManager.sendNotification(NotificationEvent.VNF_INSTANCE_CREATE, vnfInstance.getId());
		return mapper.map(vnfInstance, com.ubiqube.etsi.mano.model.nslcm.VnfInstance.class);
	}

	public void delete(@Nonnull final UUID vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		ensureNotInstantiated(vnfInstance);

		if (vnfInstancesRepository.isInstantiate(vnfInstance.getVnfPkg().getId())) {
			final VnfPackage vnfPkg = vnfPackageRepository.get(vnfInstance.getVnfPkg().getId());
			vnfPkg.setUsageState(PackageUsageStateType.NOT_IN_USE);
			vnfPackageRepository.save(vnfPkg);
		}
		vnfInstanceService.delete(vnfInstanceId);
		// VnfIdentitifierDeletionNotification NFVO + EM
	}

	public VnfLcmOpOccs instantiate(@Nonnull final UUID vnfInstanceId, final InstantiateVnfRequest instantiateVnfRequest) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		ensureNotInstantiated(vnfInstance);

		final UUID vnfPkgId = vnfInstance.getVnfPkg().getId();
		final VnfPackage vnfPkg = vnfPackageRepository.get(vnfPkgId);
		ensureIsEnabled(vnfPkg);

		VnfLcmOpOccs lcmOpOccs = vnfLcmService.createIntatiateOpOcc(vnfInstance);
		final VnfInstantiatedInfo ivf = mapper.map(instantiateVnfRequest, com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedInfo.class);
		lcmOpOccs.setVnfInstantiatedInfo(ivf);
		lcmOpOccs = vnfLcmService.save(lcmOpOccs);
		eventManager.sendAction(ActionType.VNF_INSTANTIATE, lcmOpOccs.getId(), new HashMap<>());
		LOG.info("VNF Instantiation Event Sucessfully sent.");
		return lcmOpOccs;
	}

	public VnfLcmOpOccs terminate(@Nonnull final UUID vnfInstanceId, final TerminateVnfRequest terminateVnfRequest) {
		if (terminateVnfRequest.getTerminationType() != TerminationTypeEnum.FORCEFUL) {
			LOG.warn("Terminaison should be set to FORCEFULL.");
		}
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		ensureInstantiated(vnfInstance);
		final VnfLcmOpOccs lcmOpOccs = vnfLcmService.createTerminateOpOcc(vnfInstance);
		eventManager.sendAction(ActionType.VNF_TERMINATE, lcmOpOccs.getId(), new HashMap<String, Object>());
		LOG.info("Terminate sent for instancce: {}", vnfInstanceId);
		return lcmOpOccs;
	}

	public VnfLcmOpOccs scaleToLevel(final UUID uuid, final ScaleVnfToLevelRequest scaleVnfToLevelRequest) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(uuid);
		final VnfLcmOpOccs lcmOpOccs = vnfLcmService.createScaleToLevelOpOcc(vnfInstance, scaleVnfToLevelRequest.getInstantiationLevelId());
		ensureInstantiated(vnfInstance);
		eventManager.sendAction(ActionType.VNF_SCALE_TO_LEVEL, lcmOpOccs.getId(), new HashMap<String, Object>());
		return lcmOpOccs;
	}

	public VnfLcmOpOccs scale(final UUID uuid, final ScaleVnfRequest scaleVnfRequest) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(uuid);
		final VnfLcmOpOccs lcmOpOccs = vnfLcmService.createScaleOpOcc(vnfInstance, scaleVnfRequest);
		ensureInstantiated(vnfInstance);
		eventManager.sendAction(ActionType.VNF_SCALE_TO_LEVEL, lcmOpOccs.getId(), new HashMap<String, Object>());
		return lcmOpOccs;
	}

}
