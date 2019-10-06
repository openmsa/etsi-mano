package com.ubiqube.etsi.mano.service.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance.NsStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.OperationStateEnum;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;
import com.ubiqube.etsi.mano.repository.NsLcmOpOccsRepository;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;
import com.ubiqube.etsi.mano.service.Vim;
import com.ubiqube.etsi.mano.service.VnfmInterface;

@Service
public class NfvoActions {

	private static final Logger LOG = LoggerFactory.getLogger(NfvoActions.class);

	private final NsLcmOpOccsRepository lcmOpOccsRepository;
	private final VnfLcmOpOccsRepository vnfLcmOpOccsRepository;
	private final NsInstanceRepository nsInstanceRepository;
	private final NsdRepository nsdRepository;
	private final VnfmInterface vnfm;
	private final Vim msaExecutor;
	private final EventManager eventManager;

	public NfvoActions(final NsLcmOpOccsRepository _lcmOpOccsRepository, final VnfLcmOpOccsRepository _vnfLcmOpOccsRepository, final NsInstanceRepository _nsInstanceRepository, final NsdRepository _nsdRepository, final VnfmInterface _vnfm, final Vim _msaExecutor, final EventManager _eventManager) {
		super();
		lcmOpOccsRepository = _lcmOpOccsRepository;
		vnfLcmOpOccsRepository = _vnfLcmOpOccsRepository;
		nsInstanceRepository = _nsInstanceRepository;
		nsdRepository = _nsdRepository;
		vnfm = _vnfm;
		msaExecutor = _msaExecutor;
		eventManager = _eventManager;
	}

	public void nsTerminate(final String nsInstanceId) {
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = nsInstanceRepository.createLcmOpOccs(nsInstanceId, LcmOperationTypeEnum.TERMINATE);
		final NsInstance nsInstance = nsInstanceRepository.get(nsInstanceId);

		final String nsdId = nsInstance.getNsdId();
		final NsDescriptorsNsdInfo nsdInfo = nsdRepository.get(nsdId);
		// Delete VNF
		final List<String> vnfs = nsdInfo.getVnfPkgIds();
		// Correct if talking with a Mano VNFM ( can we pass nsInstanceId ?)
		List<VnfLcmOpOcc> vnfLcmOpOccsIds = new ArrayList<>();
		for (final String vnfId : vnfs) {
			final VnfLcmOpOcc vnfLcmOpOccs = vnfm.vnfTerminate(nsInstanceId, vnfId);
			vnfLcmOpOccsIds.add(vnfLcmOpOccs);
		}
		waitForCompletion(vnfLcmOpOccsIds);
		vnfLcmOpOccsIds = refreshVnfLcmOpOccsIds(vnfLcmOpOccsIds);
		vnfLcmOpOccsRepository.save(vnfLcmOpOccsIds);
		final OperationStateEnum status = computeStatus(vnfLcmOpOccsIds);
		if (OperationStateEnum.COMPLETED != status) {
			updateOperationState(lcmOpOccs, status);
			eventManager.sendNotification(NotificationEvent.NS_TERMINATE, nsInstanceId);
			return;
		}
		// Release the NS.
		final String processId = msaExecutor.onNsInstanceTerminate(nsdInfo.getUserDefinedData());
		msaExecutor.waitForCompletion(processId, 5 * 60);

		nsInstanceRepository.changeNsdUpdateState(nsInstance, NsStateEnum.NOT_INSTANTIATED);
	}

	private static OperationStateEnum computeStatus(final List<VnfLcmOpOcc> vnfLcmOpOccsIds) {
		for (final VnfLcmOpOcc vnfLcmOpOcc : vnfLcmOpOccsIds) {
			if (LcmOperationStateType.COMPLETED != vnfLcmOpOcc.getOperationState()) {
				return convertToNsLcmOpOccsState(vnfLcmOpOcc.getOperationState());
			}
		}
		return OperationStateEnum.COMPLETED;
	}

	private static OperationStateEnum convertToNsLcmOpOccsState(@NotNull final LcmOperationStateType operationState) {
		// No starting convertion ?
		switch (operationState) {
		case COMPLETED:
			return OperationStateEnum.COMPLETED;
		case FAILED:
			return OperationStateEnum.FAILED;
		case FAILED_TEMP:
			return OperationStateEnum.FAILED_TEMP;
		case PROCESSING:
			return OperationStateEnum.PROCESSING;
		case ROLLED_BACK:
			return OperationStateEnum.ROLLED_BACK;
		case ROLLING_BACK:
			return OperationStateEnum.ROLLING_BACK;
		default:
			throw new GenericException("Unknown operation state : " + operationState);
		}
	}

	private void updateOperationState(final NsLcmOpOccsNsLcmOpOcc lcmOpOccs, final OperationStateEnum status) {
		lcmOpOccs.setOperationState(status);
		lcmOpOccs.setStateEnteredTime(new Date());
		lcmOpOccsRepository.save(lcmOpOccs);
	}

	public void nsInstantiate(final String nsInstanceId) {
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = nsInstanceRepository.createLcmOpOccs(nsInstanceId, LcmOperationTypeEnum.INSTANTIATE);
		final NsInstance nsInstance = nsInstanceRepository.get(nsInstanceId);

		final String nsdId = nsInstance.getNsdId();
		final NsDescriptorsNsdInfo nsdInfo = nsdRepository.get(nsdId);
		nsdRepository.changeNsdUpdateState(nsdInfo, NsdUsageStateEnum.IN_USE);
		// Create Ns.
		final Map<String, Object> userData = nsdInfo.getUserDefinedData();
		final String processId = msaExecutor.onNsInstantiate(nsdId, userData);
		LOG.info("Creating a MSA Job: {}", processId);
		// Save Process Id with lcm
		nsInstanceRepository.attachProcessIdToLcmOpOccs(lcmOpOccs.getId(), processId);
		msaExecutor.waitForCompletion(processId, 5 * 60);
		// Instantiate each VNF.
		final List<String> vnfPkgIds = nsdInfo.getVnfPkgIds();
		List<VnfLcmOpOcc> vnfLcmOpOccsIds = new ArrayList<>();
		for (final String vnfId : vnfPkgIds) {
			final VnfLcmOpOcc vnfLcmOpOccs = vnfm.vnfInstatiate(nsInstanceId, vnfId);
			vnfLcmOpOccsIds.add(vnfLcmOpOccs);
		}
		// Link VNF lcm OP OCCS to this operation.
		vnfLcmOpOccsRepository.save(vnfLcmOpOccsIds);
		// wait for completion
		waitForCompletion(vnfLcmOpOccsIds);
		// update lcm op occs
		vnfLcmOpOccsIds = refreshVnfLcmOpOccsIds(vnfLcmOpOccsIds);
		vnfLcmOpOccsRepository.save(vnfLcmOpOccsIds);
		final OperationStateEnum status = computeStatus(vnfLcmOpOccsIds);
		updateOperationState(lcmOpOccs, status);
		// event->create (we have lcm op occs.)
		eventManager.sendNotification(NotificationEvent.NS_INSTANTIATE, nsInstanceId);
	}

	private List<VnfLcmOpOcc> refreshVnfLcmOpOccsIds(final List<VnfLcmOpOcc> vnfLcmOpOccsIds) {
		final List<VnfLcmOpOcc> res = new ArrayList<>();
		for (final VnfLcmOpOcc vnfLcmOpOcc : vnfLcmOpOccsIds) {
			final VnfLcmOpOcc newLcmOpOc = vnfm.getVnfLcmOpOccs(vnfLcmOpOcc.getId());
			res.add(newLcmOpOc);
		}
		return res;
	}

	private void waitForCompletion(@Nonnull final List<VnfLcmOpOcc> vnfLcmOpOccss) {
		List<VnfLcmOpOcc> ret = new ArrayList<>(vnfLcmOpOccss);
		while (true) {
			ret = vnfCycle(ret);
			if (ret.isEmpty()) {
				break;
			}
			sleepSeconds(5 * 60);
		}
	}

	private void sleepSeconds(final int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (final InterruptedException e) {
			LOG.warn("Interrupted exception.", e);
			Thread.currentThread().interrupt();
		}
	}

	private void waitForCompletion(final VnfLcmOpOcc vnfLcmOpOcc) {
		while (true) {
			final VnfLcmOpOcc res = vnfm.getVnfLcmOpOccs(vnfLcmOpOcc.getId());
			if (res.getOperationState() == LcmOperationStateType.PROCESSING) {
				return;
			}
			sleepSeconds(5 * 60);
		}
	}

	private List<VnfLcmOpOcc> vnfCycle(final List<VnfLcmOpOcc> vnfLcmOpOccss) {
		final List<VnfLcmOpOcc> ret = new ArrayList<>(vnfLcmOpOccss);
		for (final VnfLcmOpOcc vnfLcmOpOcc : vnfLcmOpOccss) {
			final VnfLcmOpOcc res = vnfm.getVnfLcmOpOccs(vnfLcmOpOcc.getId());
			if (res.getOperationState() == LcmOperationStateType.PROCESSING) {
				ret.add(vnfLcmOpOcc);
			}
		}
		return ret;
	}

}
