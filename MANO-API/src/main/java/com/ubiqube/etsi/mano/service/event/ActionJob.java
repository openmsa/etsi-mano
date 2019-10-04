package com.ubiqube.etsi.mano.service.event;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.google.common.io.ByteStreams;
import com.ubiqube.etsi.mano.Constants;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstance.NsStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.OperationStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoChecksum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.UsageStateEnum;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;
import com.ubiqube.etsi.mano.repository.NsLcmOpOccsRepository;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.MsaExecutor;
import com.ubiqube.etsi.mano.service.VnfmInterface;

/**
 * this class handle job reception.
 *
 * TODO: - This class is currently too big. - Does too many things - VNF Methods
 * are allmost all the same.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ActionJob extends QuartzJobBean {
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(ActionJob.class);
	private final VnfInstancesRepository vnfInstancesRepository;
	private final VnfPackageRepository vnfPackageRepository;
	private final EventManager eventManager;
	private final NsInstanceRepository nsInstanceRepository;
	private final NsdRepository nsdRepository;
	private final MsaExecutor msaExecutor;
	private final VnfmInterface vnfm;
	private final NsLcmOpOccsRepository lcmOpOccsRepository;
	private final VnfLcmOpOccsRepository vnfLcmOpOccsRepository;

	public ActionJob(final VnfPackageRepository vnfPackageRepository, final EventManager _eventManager, final NsInstanceRepository _nsInstanceRepository, final NsdRepository _nsdRepository, final MsaExecutor _msaExecutor, final VnfmInterface _vnfm, final NsLcmOpOccsRepository _lcmOpOccsRepository, final VnfInstancesRepository _vnfInstancesRepository, final VnfLcmOpOccsRepository _vnfLcmOpOccsRepository) {
		super();
		this.vnfPackageRepository = vnfPackageRepository;
		eventManager = _eventManager;
		nsInstanceRepository = _nsInstanceRepository;
		nsdRepository = _nsdRepository;
		msaExecutor = _msaExecutor;
		vnfm = _vnfm;
		lcmOpOccsRepository = _lcmOpOccsRepository;
		vnfInstancesRepository = _vnfInstancesRepository;
		vnfLcmOpOccsRepository = _vnfLcmOpOccsRepository;
	}

	@Override
	protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
		final JobDataMap jobDataMap = context.getMergedJobDataMap();
		final ActionType eventType = ActionType.valueOf(jobDataMap.getString("eventType"));
		final String objectId = jobDataMap.getString("objectId");

		dispatch(eventType, objectId, jobDataMap);
	}

	private void dispatch(final ActionType eventType, final String objectId, final JobDataMap jobDataMap) {
		switch (eventType) {
		case VNF_PKG_ONBOARD_FROM_URI:
			vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(objectId, jobDataMap.getString("url"));
			break;
		case VNF_PKG_ONBOARD_FROM_BYTES:
			vnfPackagesVnfPkgIdPackageContentPut(objectId, (byte[]) jobDataMap.get("data"));
			break;
		case VNF_INSTANTIATE:
			vnfInstantiate(objectId);
			break;
		case NS_INSTANTIATE:
			nsInstantiate(objectId);
			break;
		case NS_TERMINATE:
			nsTerminate(objectId);
			break;
		default:
			LOG.warn("Unknown event: {}", eventType);
			break;
		}
	}

	private void vnfInstantiate(final String vnfInstanceId) {
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

		final List<VnfLcmOpOcc> vnfLcmOpOccss = new ArrayList<>();
		vnfLcmOpOccss.add(lcmOpOccs);
		waitForCompletion(vnfLcmOpOccss);

		vnfPackageRepository.updateState(lcmOpOccs, lcmOpOccs.getOperationState());
		vnfInstance.setInstantiationState(InstantiationStateEnum.INSTANTIATED);
		vnfInstancesRepository.save(vnfInstance);

		vnfPkg.setUsageState(UsageStateEnum.IN_USE);
		vnfPackageRepository.save(vnfPkg);
	}

	private void nsTerminate(final String nsInstanceId) {
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = nsInstanceRepository.createLcmOpOccs(nsInstanceId, LcmOperationTypeEnum.TERMINATE);
		final NsInstancesNsInstance nsInstance = nsInstanceRepository.get(nsInstanceId);

		final String nsdId = nsInstance.getNsdId();
		final NsDescriptorsNsdInfo nsdInfo = nsdRepository.get(nsdId);
		// Delete VNF
		final List<String> vnfs = nsdInfo.getVnfPkgIds();
		List<VnfLcmOpOcc> vnfLcmOpOccsIds = new ArrayList<>();
		for (final String vnfId : vnfs) {
			final VnfLcmOpOcc vnfLcmOpOccs = vnfm.VnfTerminate(nsInstanceId, vnfId);
			vnfLcmOpOccsIds.add(vnfLcmOpOccs);
		}
		waitForCompletion(vnfLcmOpOccsIds);
		vnfLcmOpOccsIds = refreshVnfLcmOpOccsIds(vnfLcmOpOccsIds);
		vnfLcmOpOccsRepository.save(vnfLcmOpOccsIds);
		final OperationStateEnum status = computeStatus(vnfLcmOpOccsIds);
		if (OperationStateEnum.COMPLETED != status) {
			lcmOpOccs.setOperationState(status);
			lcmOpOccsRepository.save(lcmOpOccs);
			eventManager.sendNotification(NotificationEvent.NS_TERMINATE, nsInstanceId);
			return;
		}
		// Release the NS.
		msaExecutor.onNsInstanceTerminate(nsdInfo.getUserDefinedData());
		nsInstance.setNsState(NsStateEnum.NOT_INSTANTIATED);
		nsInstanceRepository.save(nsInstance);
	}

	private void nsInstantiate(final String nsInstanceId) {
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = nsInstanceRepository.createLcmOpOccs(nsInstanceId, LcmOperationTypeEnum.INSTANTIATE);
		final NsInstancesNsInstance nsInstance = nsInstanceRepository.get(nsInstanceId);

		final String nsdId = nsInstance.getNsdId();
		final NsDescriptorsNsdInfo nsdInfo = nsdRepository.get(nsdId);
		changeNsdUpdateState(nsdInfo, NsdUsageStateEnum.IN_USE);
		// Create Ns.
		final Map<String, Object> userData = nsdInfo.getUserDefinedData();
		final String processId = msaExecutor.onNsInstantiate(nsdId, userData);
		LOG.info("Creating a MSA Job: {}", processId);
		// Save Process Id with lcm
		nsInstanceRepository.attachProcessIdToLcmOpOccs(lcmOpOccs.getId(), processId);
		// Wait it's done.
		msaExecutor.waitForProcess(processId);
		// Instantiate each VNF.
		final List<String> vnfPkgIds = nsdInfo.getVnfPkgIds();
		List<VnfLcmOpOcc> vnfLcmOpOccsIds = new ArrayList<>();
		for (final String vnfId : vnfPkgIds) {
			final VnfLcmOpOcc vnfLcmOpOccs = vnfm.VnfInstatiate(nsInstanceId, vnfId);
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
		lcmOpOccs.setStateEnteredTime(new Date());
		lcmOpOccs.setOperationState(status);
		lcmOpOccsRepository.save(lcmOpOccs);
		// event->create (we have lcm op occs.)
		eventManager.sendNotification(NotificationEvent.NS_INSTANTIATE, nsInstanceId);

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
			try {
				Thread.sleep(5 * 60 * 1000);
			} catch (final InterruptedException e) {
				LOG.warn("Interrupted exception.", e);
				Thread.currentThread().interrupt();
			}
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

	private void changeNsdUpdateState(@NotNull final NsDescriptorsNsdInfo nsdInfo, final NsdUsageStateEnum inUse) {
		nsdInfo.setNsdUsageState(NsdUsageStateEnum.IN_USE);
		nsdRepository.save(nsdInfo);
	}

	private void vnfPackagesVnfPkgIdPackageContentPut(final String vnfPkgId, final byte[] data) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);
		startOnboarding(vnfPkgInfo);
		try {
			vnfPkgInfo.setChecksum(getChecksum(data));
			vnfPackageRepository.storeBinary(vnfPkgId, new ByteArrayInputStream(data), "vnfd");
			finishOnboarding(vnfPkgInfo);
		} catch (final NoSuchAlgorithmException e) {
			throw new GenericException(e);
		}
		eventManager.sendNotification(NotificationEvent.VNF_PKG_ONBOARDING, vnfPkgId);
	}

	private void vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(final String vnfPkgId, final String url) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);
		startOnboarding(vnfPkgInfo);
		LOG.info("Async. Download of {}", url);
		final byte[] content = getUrlContent(url);
		try {
			vnfPkgInfo.setChecksum(getChecksum(content));
			vnfPackageRepository.storeBinary(vnfPkgId, new ByteArrayInputStream(content), "vnfd");
			finishOnboarding(vnfPkgInfo);
		} catch (final NoSuchAlgorithmException e) {
			throw new GenericException(e);
		}
		eventManager.sendNotification(NotificationEvent.VNF_PKG_ONBOARDING, vnfPkgId);
	}

	private static byte[] getUrlContent(final String uri) {
		URL url;
		try {
			url = new URL(uri);
			return ByteStreams.toByteArray((InputStream) url.getContent());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private static VnfPackagesVnfPkgInfoChecksum getChecksum(final byte[] bytes) throws NoSuchAlgorithmException {
		final MessageDigest digest = MessageDigest.getInstance(Constants.HASH_ALGORITHM);
		final byte[] hashbytes = digest.digest(bytes);
		final String sha3_256hex = bytesToHex(hashbytes);
		final VnfPackagesVnfPkgInfoChecksum checksum = new VnfPackagesVnfPkgInfoChecksum();

		checksum.algorithm(Constants.HASH_ALGORITHM).hash(sha3_256hex);
		return checksum;
	}

	private static String bytesToHex(final byte[] hash) {
		final StringBuilder hexString = new StringBuilder();
		for (final byte element : hash) {
			final String hex = Integer.toHexString(0xff & element);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	private void finishOnboarding(final VnfPkgInfo vnfPkgInfo) {
		vnfPkgInfo.setOnboardingState(OnboardingStateEnum.ONBOARDED);
		vnfPkgInfo.setOperationalState(OperationalStateEnum.ENABLED);
		vnfPackageRepository.save(vnfPkgInfo);
	}

	private void startOnboarding(final VnfPkgInfo vnfPkgInfo) {
		vnfPkgInfo.setOnboardingState(OnboardingStateEnum.PROCESSING);
		vnfPackageRepository.save(vnfPkgInfo);
	}

}
