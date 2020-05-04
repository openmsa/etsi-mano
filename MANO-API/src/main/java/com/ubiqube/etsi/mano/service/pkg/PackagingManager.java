package com.ubiqube.etsi.mano.service.pkg;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.ubiqube.etsi.mano.Constants;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.common.Checksum;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.VnfPackageJpa;
import com.ubiqube.etsi.mano.model.vnf.PackageOnboardingStateType;
import com.ubiqube.etsi.mano.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mano.service.event.ProviderData;

import ma.glasnost.orika.MapperFacade;

@Service
public class PackagingManager {

	private static final Logger LOG = LoggerFactory.getLogger(PackagingManager.class);

	private final VnfPackageRepository vnfPackageRepository;

	private final EventManager eventManager;

	private final PackageManager packageManager;

	private final MapperFacade mapper;

	private final VnfPackageJpa vnfPackageJpa;

	public PackagingManager(final VnfPackageRepository vnfPackageRepository, final EventManager eventManager, final PackageManager packageManager, final MapperFacade _mapper, final VnfPackageJpa _vnfPackageJpa) {
		super();
		this.vnfPackageRepository = vnfPackageRepository;
		this.eventManager = eventManager;
		this.packageManager = packageManager;
		mapper = _mapper;
		vnfPackageJpa = _vnfPackageJpa;
	}

	public void vnfPackagesVnfPkgIdPackageContentPut(@Nonnull final String vnfPkgId, final byte[] data) {
		final Optional<VnfPackage> vnfPackageOpt = vnfPackageJpa.findById(UUID.fromString(vnfPkgId));
		final VnfPackage vnfPpackage = vnfPackageOpt.orElseThrow(() -> new NotFoundException("VNF Package " + vnfPkgId + " Not found."));
		startOnboarding(vnfPpackage);
		uploadAndFinishOnboarding(vnfPpackage, data);
	}

	public void vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(@Nonnull final String vnfPkgId, final String url) {
		final Optional<VnfPackage> vnfPackageOpt = vnfPackageJpa.findById(UUID.fromString(vnfPkgId));
		final VnfPackage vnfPackage = vnfPackageOpt.orElseThrow(() -> new NotFoundException("VNF Package " + vnfPkgId + " Not found."));
		startOnboarding(vnfPackage);
		LOG.info("Async. Download of {}", url);
		final byte[] data = getUrlContent(url);
		uploadAndFinishOnboarding(vnfPackage, data);
	}

	private void uploadAndFinishOnboarding(final VnfPackage vnfPackage, final byte[] data) {
		vnfPackage.setChecksum(getChecksum(data));
		vnfPackageRepository.storeBinary(vnfPackage.getId().toString(), "vnfd", new ByteArrayInputStream(data));
		final PackageProvider packageProvider = packageManager.getProviderFor(data);
		if (null != packageProvider) {
			final Set<VnfCompute> cNodes = packageProvider.getVnfComputeNodes();
			vnfPackage.setVnfCompute(cNodes);
			final Set<VnfStorage> vboNodes = packageProvider.getVnfStorages();
			vnfPackage.setVnfStorage(vboNodes);
			final Set<VnfVl> vvlNodes = packageProvider.getVnfVirtualLinks();
			vnfPackage.setVnfVl(vvlNodes);
			final Set<VnfLinkPort> vcNodes = packageProvider.getVnfVduCp();
			vnfPackage.setVnfLinkPort(vcNodes);
			remapNetworks(cNodes, vcNodes);
			vnfPackage.setAdditionalArtifacts(packageProvider.getAdditionalArtefacts());
			final Set<VnfExtCp> vnfExtCp = packageProvider.getVnfExtCp();
			vnfPackage.setVnfExtCp(vnfExtCp);
			final ProviderData pd = packageProvider.getProviderPadata();
			mapper.map(pd, vnfPackage);
		}
		finishOnboarding(vnfPackage);
		eventManager.sendNotification(NotificationEvent.VNF_PKG_ONBOARDING, vnfPackage.getId().toString());
	}

	private static void remapNetworks(final Set<VnfCompute> cNodes, final Set<VnfLinkPort> vcNodes) {
		cNodes.forEach(x -> {
			final Set<String> nodes = filter(vcNodes, x.getToscaName());
			x.setNetworks(nodes);
		});
	}

	private static Set<String> filter(final Set<VnfLinkPort> vcNodes, final String toscaName) {
		return vcNodes.stream()
				.filter(x -> x.getVirtualBinding().equals(toscaName))
				.map(x -> x.getVirtualLink())
				.collect(Collectors.toSet());
	}

	private static void verifyImagePath(final Set<SoftwareImage> softwareImages) {
		softwareImages.forEach(x -> {
			if (null != x.getImagePath()) {
				final File file = new File(x.getImagePath());
				if (!file.exists()) {
					throw new NotFoundException("File " + x.getImagePath() + " Could not be found");
				}
			}
		});
	}

	private static byte[] getUrlContent(final String uri) {
		try {
			final URL url = new URL(uri);
			return StreamUtils.copyToByteArray((InputStream) url.getContent());
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

		checksum.setAlgorithm(Constants.HASH_ALGORITHM);
		checksum.setHash(sha3_256hex);
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

	private void finishOnboarding(final VnfPackage vnfPackage) {
		vnfPackage.setOnboardingState(PackageOnboardingStateType.ONBOARDED);
		vnfPackage.setOperationalState(PackageOperationalStateType.ENABLED);
		vnfPackageJpa.save(vnfPackage);
	}

	private void startOnboarding(final VnfPackage vnfPackage) {
		vnfPackage.setOnboardingState(PackageOnboardingStateType.PROCESSING);
		vnfPackageJpa.save(vnfPackage);
	}

}
