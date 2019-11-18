package com.ubiqube.etsi.mano.service.event;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.io.ByteStreams;
import com.ubiqube.etsi.mano.Constants;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoChecksum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

/**
 * this class handle job reception.
 *
 * TODO: I keep Package here for the momment.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Transactional
public class ActionJob extends QuartzJobBean {
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(ActionJob.class);

	private final VnfPackageRepository vnfPackageRepository;
	private final EventManager eventManager;
	private final VnfmActions vnfmActions;
	private final NfvoActions nfvoActions;
	private final PackageManager packageManager;

	public ActionJob(final VnfPackageRepository vnfPackageRepository, final EventManager _eventManager, final VnfmActions _vnfmActions, final NfvoActions _nfvoActions, final PackageManager _packageProvider) {
		super();
		this.vnfPackageRepository = vnfPackageRepository;
		eventManager = _eventManager;
		vnfmActions = _vnfmActions;
		nfvoActions = _nfvoActions;
		packageManager = _packageProvider;
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
			vnfmActions.vnfInstantiate(objectId);
			break;
		case NS_INSTANTIATE:
			nfvoActions.nsInstantiate(objectId);
			break;
		case NS_TERMINATE:
			nfvoActions.nsTerminate(objectId);
			break;
		default:
			LOG.warn("Unknown event: {}", eventType);
			break;
		}
	}

	private void vnfPackagesVnfPkgIdPackageContentPut(final String vnfPkgId, final byte[] data) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);
		startOnboarding(vnfPkgInfo);
		uploadAndFinishOnboarding(vnfPkgInfo, data);
	}

	private void vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(final String vnfPkgId, final String url) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);
		startOnboarding(vnfPkgInfo);
		LOG.info("Async. Download of {}", url);
		final byte[] data = getUrlContent(url);
		uploadAndFinishOnboarding(vnfPkgInfo, data);
	}

	private void uploadAndFinishOnboarding(final VnfPkgInfo vnfPkgInfo, final byte[] data) {
		vnfPkgInfo.setChecksum(getChecksum(data));
		vnfPackageRepository.storeBinary(vnfPkgInfo.getId(), "vnfd", new ByteArrayInputStream(data));
		final PackageProvider packageProvider = packageManager.getProviderFor(data);
		if (null != packageProvider) {
			vnfPkgInfo.setSoftwareImages(packageProvider.getSoftwareImages());
		}
		finishOnboarding(vnfPkgInfo);
		eventManager.sendNotification(NotificationEvent.VNF_PKG_ONBOARDING, vnfPkgInfo.getId());
	}

	private static byte[] getUrlContent(final String uri) {
		try {
			final URL url = new URL(uri);
			return ByteStreams.toByteArray((InputStream) url.getContent());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private static VnfPackagesVnfPkgInfoChecksum getChecksum(final byte[] bytes) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(Constants.HASH_ALGORITHM);
		} catch (final NoSuchAlgorithmException e) {
			throw new GenericException(e);
		}
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
