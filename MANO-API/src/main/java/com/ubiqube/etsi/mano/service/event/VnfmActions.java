package com.ubiqube.etsi.mano.service.event;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantedLcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.VnfInstanceInstantiatedVnfInfo;
import com.ubiqube.etsi.mano.model.nslcm.VnfOperationalStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.Vim;

@Service
public class VnfmActions {
	private static final Logger LOG = LoggerFactory.getLogger(VnfmActions.class);

	private final VnfInstancesRepository vnfInstancesRepository;

	private final Vim msaExecutor;

	private final VnfPackageRepository vnfPackageRepository;

	private final EventManager eventManager;

	private final VnfLcmOpOccsRepository vnfLcmOpOccsRepository;

	public VnfmActions(final VnfInstancesRepository _vnfInstancesRepository, final Vim _vim, final VnfPackageRepository _vnfPackageRepository, final EventManager _eventManager, final VnfLcmOpOccsRepository _vnfLcmOpOccsRepository) {
		super();
		vnfInstancesRepository = _vnfInstancesRepository;
		msaExecutor = _vim;
		vnfPackageRepository = _vnfPackageRepository;
		eventManager = _eventManager;
		vnfLcmOpOccsRepository = _vnfLcmOpOccsRepository;
	}

	public void vnfInstantiate(final String vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		// Maybe e need additional parameters to say STARTING / PROCESSING ...
		final VnfLcmOpOcc lcmOpOccs = vnfLcmOpOccsRepository.createLcmOpOccs(vnfInstanceId, LcmOperationType.INSTANTIATE);
		eventManager.sendNotification(NotificationEvent.VNF_INSTANTIATE, vnfInstance.getId().toString());
		// Send Grant.
		final GrantRequest grant = createGrant(vnfInstance, lcmOpOccs, null);
		// Send processing notification.
		vnfLcmOpOccsRepository.updateState(lcmOpOccs, LcmOperationStateType.PROCESSING);
		final String vnfPkgId = vnfInstance.getVnfPkg().getId().toString();
		final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
		final Map<String, Object> userData = vnfPkg.getUserDefinedData();
		if (null == userData.get("vimId")) {
			throw new GenericException("No vim information for VNF Instance: " + vnfInstanceId);
		}

		final String processId = msaExecutor.onVnfInstantiate(vnfPkgId, userData);
		LOG.info("New MSA VNF Create job: {}", processId);
		vnfInstance.setProcessId(processId);
		vnfInstancesRepository.save(vnfInstance);
		final LcmOperationStateType status = msaExecutor.waitForCompletion(processId, 1 * 60);
		vnfLcmOpOccsRepository.updateState(lcmOpOccs, status);

		if (status == LcmOperationStateType.COMPLETED) {
			vnfInstance.setInstantiationState(InstantiationStateEnum.INSTANTIATED);
			vnfPkg.setUsageState(PackageUsageStateType.IN_USE);
			final VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo = new VnfInstanceInstantiatedVnfInfo();
			instantiatedVnfInfo.setVnfState(VnfOperationalStateType.STARTED);
			vnfInstance.setInstantiatedVnfInfo(instantiatedVnfInfo);
			vnfLcmOpOccsRepository.updateState(lcmOpOccs, LcmOperationStateType.COMPLETED);
		} else {
			vnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
			vnfLcmOpOccsRepository.updateState(lcmOpOccs, LcmOperationStateType.FAILED);
		}
		vnfInstancesRepository.save(vnfInstance);
		vnfPackageRepository.save(vnfPkg);
	}

	private GrantRequest createGrant(final VnfInstance vnfInstance, final VnfLcmOpOcc lcmOpOccs, final VnfPackage vnfPackage) {
		final GrantRequest grant = new GrantRequest();
		grant.setVnfInstanceId(vnfInstance.getId().toString());
		grant.setVnfLcmOpOccId(lcmOpOccs.getId().toString());
		grant.setVnfdId(vnfInstance.getVnfdId());
		grant.setFlavourId(vnfPackage.getFlavorId());
		grant.setIsAutomaticInvocation(false);
		grant.setOperation(GrantedLcmOperationType.INSTANTIATE);
		/// XXX: Have a closer look on lcm_operations_configuration or vnf_profile.
		grant.setInstantiationLevelId("0");
		return grant;
	}

}
