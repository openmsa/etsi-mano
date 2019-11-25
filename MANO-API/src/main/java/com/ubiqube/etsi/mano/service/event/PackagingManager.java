package com.ubiqube.etsi.mano.service.event;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.io.ByteStreams;
import com.ubiqube.etsi.mano.Constants;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.vnf.sol005.Checksum;
import com.ubiqube.etsi.mano.model.vnf.sol005.PackageOnboardingStateType;
import com.ubiqube.etsi.mano.model.vnf.sol005.PackageOperationalStateType;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

@Service
public class PackagingManager {

	private static final Logger LOG = LoggerFactory.getLogger(PackagingManager.class);

	private final VnfPackageRepository vnfPackageRepository;
	private final EventManager eventManager;

	private final PackageManager packageManager;

	public PackagingManager(final VnfPackageRepository vnfPackageRepository, final EventManager eventManager, final PackageManager packageManager) {
		super();
		this.vnfPackageRepository = vnfPackageRepository;
		this.eventManager = eventManager;
		this.packageManager = packageManager;
	}

	public void vnfPackagesVnfPkgIdPackageContentPut(@Nonnull final String vnfPkgId, final byte[] data) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);
		startOnboarding(vnfPkgInfo);
		uploadAndFinishOnboarding(vnfPkgInfo, data);
	}

	public void vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(@Nonnull final String vnfPkgId, final String url) {
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

	private static Checksum getChecksum(final byte[] bytes) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(Constants.HASH_ALGORITHM);
		} catch (final NoSuchAlgorithmException e) {
			throw new GenericException(e);
		}
		final byte[] hashbytes = digest.digest(bytes);
		final String sha3_256hex = bytesToHex(hashbytes);
		final Checksum checksum = new Checksum();

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
		vnfPkgInfo.setOnboardingState(PackageOnboardingStateType.ONBOARDED);
		vnfPkgInfo.setOperationalState(PackageOperationalStateType.ENABLED);
		vnfPackageRepository.save(vnfPkgInfo);
	}

	private void startOnboarding(final VnfPkgInfo vnfPkgInfo) {
		vnfPkgInfo.setOnboardingState(PackageOnboardingStateType.PROCESSING);
		vnfPackageRepository.save(vnfPkgInfo);
	}

}
