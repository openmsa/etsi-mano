package com.ubiqube.etsi.mano.service.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.factory.VnfInstanceFactory;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceVnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;
import com.ubiqube.etsi.mano.repository.NsLcmOpOccsRepository;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
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
	private final VnfPackageRepository vnfPackageRepository;

	public NfvoActions(final NsLcmOpOccsRepository _lcmOpOccsRepository, final VnfLcmOpOccsRepository _vnfLcmOpOccsRepository, final NsInstanceRepository _nsInstanceRepository, final NsdRepository _nsdRepository, final VnfmInterface _vnfm, final Vim _msaExecutor, final EventManager _eventManager, final VnfPackageRepository _vnfPackageRepository) {
		super();
		lcmOpOccsRepository = _lcmOpOccsRepository;
		vnfLcmOpOccsRepository = _vnfLcmOpOccsRepository;
		nsInstanceRepository = _nsInstanceRepository;
		nsdRepository = _nsdRepository;
		vnfm = _vnfm;
		msaExecutor = _msaExecutor;
		eventManager = _eventManager;
		vnfPackageRepository = _vnfPackageRepository;
	}

	public void nsTerminate(final String nsInstanceId) {
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = nsdRepository.createLcmOpOccs(nsInstanceId, LcmOperationTypeEnum.TERMINATE);
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
		final LcmOperationStateType status = computeStatus(vnfLcmOpOccsIds);
		if (LcmOperationStateType.COMPLETED != status) {
			updateOperationState(lcmOpOccs, status);
			eventManager.sendNotification(NotificationEvent.NS_TERMINATE, nsInstanceId);
			return;
		}
		// Release the NS.
		final String processId = msaExecutor.onNsInstanceTerminate(nsdInfo.getUserDefinedData());
		msaExecutor.waitForCompletion(processId, 5 * 60);

		nsInstanceRepository.changeNsdUpdateState(nsInstance, InstantiationStateEnum.NOT_INSTANTIATED);
	}

	private static LcmOperationStateType computeStatus(final List<VnfLcmOpOcc> vnfLcmOpOccsIds) {
		for (final VnfLcmOpOcc vnfLcmOpOcc : vnfLcmOpOccsIds) {
			if (LcmOperationStateType.COMPLETED != vnfLcmOpOcc.getOperationState()) {
				return vnfLcmOpOcc.getOperationState();
			}
		}
		return LcmOperationStateType.COMPLETED;
	}

	private void updateOperationState(final NsLcmOpOccsNsLcmOpOcc lcmOpOccs, final LcmOperationStateType status) {
		lcmOpOccs.setOperationState(status);
		lcmOpOccs.setStateEnteredTime(new Date());
		lcmOpOccsRepository.save(lcmOpOccs);
	}

	public void nsInstantiate(final String nsInstanceId) {
		final NsInstance nsInstance = nsInstanceRepository.get(nsInstanceId);
		final String nsdId = nsInstance.getNsdId();
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = nsdRepository.createLcmOpOccs(nsdId, LcmOperationTypeEnum.INSTANTIATE);

		final NsDescriptorsNsdInfo nsdInfo = nsdRepository.get(nsdId);
		// Create Ns.
		final Map<String, Object> userData = nsdInfo.getUserDefinedData();
		final String processId = msaExecutor.onNsInstantiate(nsdId, userData);
		LOG.info("Creating a MSA Job: {}", processId);
		// Save Process Id with lcm
		nsdRepository.attachProcessIdToLcmOpOccs(lcmOpOccs.getId(), processId);
		LcmOperationStateType status = msaExecutor.waitForCompletion(processId, 1 * 60);
		if (status != LcmOperationStateType.COMPLETED) {
			// update Lcm OpOccs
			// send Notification.
			LOG.warn("Instance #{} => {}", nsInstance.getId(), status);
			return;
		}
		nsdRepository.changeNsdUpdateState(nsdInfo, NsdUsageStateEnum.IN_USE);
		// Instantiate each VNF.
		final List<String> vnfPkgIds = nsdInfo.getVnfPkgIds();
		List<VnfLcmOpOcc> vnfLcmOpOccsIds = new ArrayList<>();
		for (final String vnfId : vnfPkgIds) {
			NsInstancesNsInstanceVnfInstance nsVnfInstance = nsInstance.getVnfInstance().stream().filter(x -> x.getVnfPkgId().equals(vnfId)).findFirst().orElse(null);
			if (null == nsVnfInstance) {
				final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfId);
				final VnfInstance vnfInstance = vnfm.createVnfInstance(vnfPkgInfo, "", "Sub-instance " + nsInstanceId);
				nsVnfInstance = VnfInstanceFactory.createNsInstancesNsInstanceVnfInstance(vnfInstance, "vimId?");
				nsInstance.addVnfInstanceItem(nsVnfInstance);
				nsInstanceRepository.save(nsInstance);
			}
			final VnfLcmOpOcc vnfLcmOpOccs = vnfm.vnfInstatiate(nsVnfInstance.getId(), vnfId);
			vnfLcmOpOccsIds.add(vnfLcmOpOccs);
		}
		// Link VNF lcm OP OCCS to this operation.
		vnfLcmOpOccsRepository.save(vnfLcmOpOccsIds);
		// wait for completion
		waitForCompletion(vnfLcmOpOccsIds);
		// update lcm op occs
		vnfLcmOpOccsIds = refreshVnfLcmOpOccsIds(vnfLcmOpOccsIds);
		vnfLcmOpOccsRepository.save(vnfLcmOpOccsIds);
		status = computeStatus(vnfLcmOpOccsIds);
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

	private void sleepSeconds(final long seconds) {
		try {
			Thread.sleep(seconds * 1000L);
		} catch (final InterruptedException e) {
			LOG.warn("Interrupted exception.", e);
			Thread.currentThread().interrupt();
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
