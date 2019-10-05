package com.ubiqube.etsi.mano.controller.nslcm;

import static com.ubiqube.etsi.mano.Constants.ensureInstantiated;
import static com.ubiqube.etsi.mano.Constants.ensureIsEnabled;
import static com.ubiqube.etsi.mano.Constants.ensureIsOnboarded;
import static com.ubiqube.etsi.mano.Constants.ensureNotInstantiated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest.TerminationTypeEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgIndex;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgInstance;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgOperation;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.UsageStateEnum;
import com.ubiqube.etsi.mano.repository.NsLcmOpOccsRepository;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.Vim;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;

/**
 * NFVO+VNFM & VNFM Implementation.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Profile({ "default", "VNFM" })
@Service
public class VnfInstanceLcm {

	private static final Logger LOG = LoggerFactory.getLogger(VnfInstanceLcm.class);

	private final VnfInstancesRepository vnfInstancesRepository;
	private final VnfPackageRepository vnfPackageRepository;
	private final Vim msaExecutor;
	private final NsLcmOpOccsRepository lcmOpOccsMsa;
	private final EventManager eventManager;

	public VnfInstanceLcm(final VnfInstancesRepository vnfInstancesRepository, final VnfPackageRepository vnfPackageRepository, final Vim _msaExecutor, final NsLcmOpOccsRepository _lcmOpOccsRepository, final EventManager _eventManager) {
		super();
		this.vnfInstancesRepository = vnfInstancesRepository;
		this.vnfPackageRepository = vnfPackageRepository;
		msaExecutor = _msaExecutor;
		lcmOpOccsMsa = _lcmOpOccsRepository;
		eventManager = _eventManager;
	}

	public List<VnfInstance> get(final Map<String, String> queryParameters, final LcmLinkable links) {
		final String filter = queryParameters.get("filter");
		final List<VnfInstance> result = vnfInstancesRepository.query(filter);
		result.stream().forEach(x -> x.setLinks(links.getLinks(x.getId())));
		return result;
	}

	public VnfInstance post(final CreateVnfRequest createVnfRequest) {
		final String id = UUID.randomUUID().toString();
		final String vnfId = createVnfRequest.getVnfdId();
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfId);
		ensureIsOnboarded(vnfPkgInfo);
		ensureIsEnabled(vnfPkgInfo);
		final VnfInstance vnfInstance = LcmFactory.createVnfInstance(createVnfRequest);

		vnfInstance.setId(id);
		// VnfIdentifierCreationNotification NFVO + EM
		vnfInstancesRepository.save(vnfInstance);
		final VnfPkgIndex vnfPkgIndex = vnfPackageRepository.loadObject(vnfId, VnfPkgIndex.class, "indexes.json");
		vnfPkgIndex.addVnfPkgInstance(new VnfPkgInstance(vnfInstance.getId()));
		vnfPackageRepository.storeObject(vnfId, vnfPkgIndex, "indexes.json");

		return vnfInstance;
	}

	public void delete(@Nonnull final String vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		ensureNotInstantiated(vnfInstance);
		// Clean LCM Repository.
		final String vnfPkgId = vnfInstance.getVnfPkgId();
		final VnfPkgIndex vnfPkgIndex = vnfPackageRepository.loadObject(vnfPkgId, VnfPkgIndex.class, "indexes.json");
		final VnfPkgInstance instance = vnfPkgIndex.getVnfPkgInstance(vnfInstanceId);
		instance.getOperations().values().stream().forEach(x -> lcmOpOccsMsa.delete(x.getId()));
		lcmOpOccsMsa.delete(vnfInstanceId);
		vnfPkgIndex.remove(instance);
		vnfPackageRepository.storeObject(vnfPkgId, vnfPkgIndex, "indexes.json");

		if (vnfPkgIndex.isEmpty()) {
			final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
			vnfPkg.setUsageState(UsageStateEnum.NOT_IN_USE);
			vnfPackageRepository.save(vnfPkg);
		}
		vnfInstancesRepository.delete(vnfInstanceId);
		// VnfIdentitifierDeletionNotification NFVO + EM
	}

	public void instantiate(@Nonnull final String vnfInstanceId, final InstantiateVnfRequest instantiateVnfRequest, @Nonnull final LcmLinkable links) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		ensureNotInstantiated(vnfInstance);

		final String vnfPkgId = vnfInstance.getVnfPkgId();
		final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
		ensureIsEnabled(vnfPkg);
		eventManager.sendAction(ActionType.VNF_INSTANTIATE, vnfInstanceId, new HashMap<String, Object>());

		vnfInstance.setLinks(links.getLinks(vnfInstanceId));
	}

	public void terminate(@Nonnull final String vnfInstanceId, final TerminateVnfRequest terminateVnfRequest) {
		if (terminateVnfRequest.getTerminationType() != TerminationTypeEnum.FORCEFUL) {
			LOG.warn("Terminaison should be set to FORCEFULL.");
		}
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		ensureInstantiated(vnfInstance);
		eventManager.sendAction(ActionType.VNF_TERMINATE, vnfInstanceId, new HashMap<String, Object>());

		final String vnfPkgId = vnfInstance.getVnfPkgId();
		vnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);

		final VnfPkgIndex vnfPkgIndex = vnfPackageRepository.loadObject(vnfInstance.getVnfPkgId(), VnfPkgIndex.class, "indexes.json");
		final VnfPkgInstance instance = vnfPkgIndex.getVnfPkgInstance(vnfInstanceId);

		instance.getOperations().values().forEach(x -> lcmOpOccsMsa.delete(x.getId()));
		instance.getOperations().clear();

		final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
		final Map<String, Object> userData = vnfPkg.getUserDefinedData();
		final String processId = msaExecutor.onVnfInstanceTerminate(userData);
		userData.put("msaTerminateServiceId", processId);
		vnfPackageRepository.storeObject(vnfPkg.getId(), vnfPkgIndex, "indexes.json");
		addVnfOperation(vnfPkgId, processId, vnfInstanceId, LcmOperationTypeEnum.TERMINATE);
		vnfPackageRepository.save(vnfPkg);
		vnfInstancesRepository.save(vnfInstance);
	}

	private NsLcmOpOccsNsLcmOpOcc addVnfOperation(final String _vnfPkgId, final String _processId, final String _vnfInstanceId, final LcmOperationTypeEnum _lcmOperationType) {
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = LcmFactory.createNsLcmOpOccsNsLcmOpOcc(_vnfInstanceId, _lcmOperationType);
		lcmOpOccsMsa.save(lcmOpOccs);
		final VnfPkgIndex vnfPkgIndex = vnfPackageRepository.loadObject(_vnfPkgId, VnfPkgIndex.class, "indexes.json");
		final VnfPkgOperation VnfPkgOperation = new VnfPkgOperation(lcmOpOccs.getId(), _processId);
		final VnfPkgInstance instance = vnfPkgIndex.getVnfPkgInstance(_vnfInstanceId);
		instance.addOperation(VnfPkgOperation);

		vnfPackageRepository.storeObject(_vnfPkgId, vnfPkgIndex, "indexes.json");
		return lcmOpOccs;
	}

}
