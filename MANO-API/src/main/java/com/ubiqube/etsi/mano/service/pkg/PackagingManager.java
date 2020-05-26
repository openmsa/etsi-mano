package com.ubiqube.etsi.mano.service.pkg;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.ubiqube.etsi.mano.Constants;
import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.ScalingAspect;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiationLevels;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.common.Checksum;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.NsdPackageJpa;
import com.ubiqube.etsi.mano.jpa.VnfPackageJpa;
import com.ubiqube.etsi.mano.model.nsd.NsdOnboardingStateType;
import com.ubiqube.etsi.mano.model.vnf.PackageOnboardingStateType;
import com.ubiqube.etsi.mano.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mano.service.event.ProviderData;

import ma.glasnost.orika.MapperFacade;
import tosca.policies.nfv.InstantiationLevels;
import tosca.policies.nfv.VduInitialDelta;
import tosca.policies.nfv.VduInstantiationLevels;
import tosca.policies.nfv.VduScalingAspectDeltas;

@Service
public class PackagingManager {

	private static final Logger LOG = LoggerFactory.getLogger(PackagingManager.class);

	private final VnfPackageRepository vnfPackageRepository;

	private final EventManager eventManager;

	private final PackageManager packageManager;

	private final MapperFacade mapper;

	private final VnfPackageJpa vnfPackageJpa;

	private final NsdRepository nsdRepository;

	private final NsdPackageJpa nsdPackageJpa;

	public PackagingManager(final VnfPackageRepository vnfPackageRepository, final EventManager eventManager, final PackageManager packageManager, final MapperFacade _mapper, final VnfPackageJpa _vnfPackageJpa, final NsdRepository _nsdRepository, final NsdPackageJpa _nsdPackageJpa) {
		super();
		this.vnfPackageRepository = vnfPackageRepository;
		this.eventManager = eventManager;
		this.packageManager = packageManager;
		mapper = _mapper;
		vnfPackageJpa = _vnfPackageJpa;
		nsdRepository = _nsdRepository;
		nsdPackageJpa = _nsdPackageJpa;
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
		vnfPackageRepository.storeBinary(vnfPackage.getId(), "vnfd", new ByteArrayInputStream(data));
		final PackageProvider packageProvider = packageManager.getProviderFor(data);
		if (null != packageProvider) {
			final Set<VnfCompute> cNodes = packageProvider.getVnfComputeNodes(vnfPackage.getUserDefinedData());
			vnfPackage.setVnfCompute(cNodes);
			final Set<VnfStorage> vboNodes = packageProvider.getVnfStorages(vnfPackage.getUserDefinedData());
			vnfPackage.setVnfStorage(vboNodes);
			final Set<VnfVl> vvlNodes = packageProvider.getVnfVirtualLinks(vnfPackage.getUserDefinedData());
			vnfPackage.setVnfVl(vvlNodes);
			final Set<VnfLinkPort> vcNodes = packageProvider.getVnfVduCp(vnfPackage.getUserDefinedData());
			vnfPackage.setVnfLinkPort(vcNodes);
			remapNetworks(cNodes, vcNodes);
			vnfPackage.setAdditionalArtifacts(packageProvider.getAdditionalArtefacts(vnfPackage.getUserDefinedData()));
			final Set<VnfExtCp> vnfExtCp = packageProvider.getVnfExtCp(vnfPackage.getUserDefinedData());
			vnfPackage.setVnfExtCp(vnfExtCp);
			final Set<ScalingAspect> scalingAspects = packageProvider.getScalingAspects(vnfPackage.getUserDefinedData());
			vnfPackage.setScalingAspects(scalingAspects);
			// XXX Normally, Tosca object must not traverse this layer.
			final List<InstantiationLevels> instantiationLevels = packageProvider.getInstatiationLevels(vnfPackage.getUserDefinedData());
			final List<VduInstantiationLevels> vduInstantiationLevel = packageProvider.getVduInstantiationLevels(vnfPackage.getUserDefinedData());
			final List<VduInitialDelta> vduInitialDeltas = packageProvider.getVduInitialDelta(vnfPackage.getUserDefinedData());
			final List<VduScalingAspectDeltas> vduScalingAspectDeltas = packageProvider.getVduScalingAspectDeltas(vnfPackage.getUserDefinedData());

			rebuildScalingAspects(vnfPackage, instantiationLevels, vduInstantiationLevel, vduInitialDeltas, vduScalingAspectDeltas);
			final ProviderData pd = packageProvider.getProviderPadata();
			mapper.map(pd, vnfPackage);
		}
		finishOnboarding(vnfPackage);
		eventManager.sendNotification(NotificationEvent.VNF_PKG_ONBOARDING, vnfPackage.getId());
	}

	private static void rebuildScalingAspects(final VnfPackage vnfPackage, final List<InstantiationLevels> instantiationLevels, final List<VduInstantiationLevels> vduInstantiationLevels, final List<VduInitialDelta> vduInitialDeltas, final List<VduScalingAspectDeltas> vduScalingAspectDeltas) {
		instantiationLevels.stream()
				.forEach(x -> {
					vnfPackage.setDefaultInstantiationLevel(x.getDefaultLevel());
					x.getLevels().entrySet().forEach(y -> {
						final String levelId = y.getKey();
						y.getValue().getScaleInfo().entrySet().forEach(z -> {
							final String aspectId = z.getKey();

							final VnfInstantiationLevels il = new VnfInstantiationLevels(levelId, aspectId, z.getValue().getScaleLevel());
							vnfPackage.addInstantiationLevel(il);
						});
					});
				});
		vduInstantiationLevels.forEach(x -> {
			x.getInternalName();
			final Set<VduInstantiationLevel> ils = x.getLevels().entrySet().stream().map(y -> {
				final VduInstantiationLevel vduInstantiationLevel = new VduInstantiationLevel();
				vduInstantiationLevel.setLevelName(y.getKey());
				vduInstantiationLevel.setNumberOfInstances(y.getValue().getNumberOfInstances().intValue());
				return vduInstantiationLevel;
			}).collect(Collectors.toSet());

			x.getTargets().forEach(y -> {
				final VnfCompute vnfCompute = findVnfCompute(vnfPackage, y);
				ils.forEach(z -> z.setVnfCompute(vnfCompute));
				vnfCompute.setInstantiationLevel(ils);
			});
		});
		vduScalingAspectDeltas.forEach(x -> {
			x.getTargets().forEach(y -> {
				final VnfCompute vnfc = findVnfCompute(vnfPackage, y);
				x.getDeltas().entrySet().forEach(z -> {
					vnfc.addScalingAspectDeltas(new VnfComputeAspectDelta(x.getAspect(), z.getKey(), z.getValue().getNumberOfInstances()));
				});
			});
		});

	}

	@Nonnull
	private static VnfCompute findVnfCompute(final VnfPackage vnfPackage, final String y) {
		return vnfPackage.getVnfCompute().stream()
				.filter(x -> x.getToscaName().equals(y))
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Unable to find VDU: " + y));
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

	public void nsOnboarding(@NotNull final UUID objectId) {
		final NsdPackage nsPackage = nsdPackageJpa.findById(objectId).orElseThrow(() -> new NotFoundException("NS Package " + objectId + " Not found."));
		final Map<String, String> userData = nsPackage.getUserDefinedData();
		final byte[] data = nsdRepository.getBinary(objectId, "nsd");
		final PackageProvider packageProvider = packageManager.getProviderFor(data);
		if (null != packageProvider) {
			final NsInformations nsInformations = packageProvider.getNsInformations(userData);
			mapper.map(nsInformations, nsPackage);
			final Set<NsSap> nsSap = packageProvider.getNsSap(userData);
			nsPackage.setNsSaps(nsSap);
			final Set<NsVirtualLink> nsVirtualLink = packageProvider.getNsVirtualLink(userData);
			nsPackage.setNsVirtualLinks(nsVirtualLink);
			final Set<SecurityGroupAdapter> sgAdapters = packageProvider.getSecurityGroups(userData);
			sgAdapters.forEach(x -> nsPackage.getNsSaps().stream()
					.filter(y -> x.getTargets().contains(y.getToscaName()))
					.forEach(y -> y.addSecurityGroups(x.getSecurityGroup())));
		}
		nsPackage.setNsdOnboardingState(NsdOnboardingStateType.ONBOARDED);
		nsPackage.setNsdOperationalState(PackageOperationalStateType.ENABLED);
		nsdPackageJpa.save(nsPackage);
		eventManager.sendNotification(NotificationEvent.NS_PKG_ONBOARDING, nsPackage.getId());
	}

}
