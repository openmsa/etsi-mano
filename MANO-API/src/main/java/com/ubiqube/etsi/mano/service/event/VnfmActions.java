package com.ubiqube.etsi.mano.service.event;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.UsageStateEnum;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.Vim;

@Service
public class VnfmActions {

	private static final Logger LOG = LoggerFactory.getLogger(VnfmActions.class);

	private final VnfInstancesRepository vnfInstancesRepository;
	private final Vim msaExecutor;
	private final VnfPackageRepository vnfPackageRepository;
	private final EventManager eventManager;

	public VnfmActions(final VnfInstancesRepository _vnfInstancesRepository, final Vim _vim, final VnfPackageRepository _vnfPackageRepository, final EventManager _eventManager) {
		super();
		vnfInstancesRepository = _vnfInstancesRepository;
		msaExecutor = _vim;
		vnfPackageRepository = _vnfPackageRepository;
		eventManager = _eventManager;
	}

	public void vnfInstantiate(final String vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		// Maybe e need additional parameters to say STARTING / PROCESSING ...
		final VnfLcmOpOcc lcmOpOccs = vnfPackageRepository.createLcmOpOccs(vnfInstanceId, LcmOperationType.INSTANTIATE);
		eventManager.sendNotification(NotificationEvent.VNF_INSTANTIATE, vnfInstance.getId());
		// Send Grant.
		// Send processing notification.
		vnfPackageRepository.updateState(lcmOpOccs, LcmOperationStateType.PROCESSING);
		final String vnfPkgId = vnfInstance.getVnfPkgId();
		final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
		final Map<String, Object> userData = vnfPkg.getUserDefinedData();
		if (null == userData.get("vimId")) {
			throw new GenericException("No vim information for VNF Instance: " + vnfInstanceId);
		}

		final String processId = msaExecutor.onVnfInstantiate(vnfPkgId, userData);
		LOG.info("New MSA VNF Create job: {}", processId);
		vnfPackageRepository.attachProcessIdToLcmOpOccs(lcmOpOccs.getId(), processId);
		msaExecutor.waitForCompletion(processId, 5 * 60);

		vnfPackageRepository.updateState(lcmOpOccs, lcmOpOccs.getOperationState());
		vnfInstance.setInstantiationState(InstantiationStateEnum.INSTANTIATED);
		vnfInstancesRepository.save(vnfInstance);

		vnfPkg.setUsageState(UsageStateEnum.IN_USE);
		vnfPackageRepository.save(vnfPkg);
	}

}
