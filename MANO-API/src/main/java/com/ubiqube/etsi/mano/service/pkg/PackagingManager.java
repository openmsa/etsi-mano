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
import java.util.Map.Entry;
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
import com.ubiqube.etsi.mano.dao.mano.NsdPackageNsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
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
import com.ubiqube.etsi.mano.model.nsd.NsdOnboardingStateType;
import com.ubiqube.etsi.mano.model.vnf.PackageOnboardingStateType;
import com.ubiqube.etsi.mano.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mano.service.event.ProviderData;

import ma.glasnost.orika.MapperFacade;
import tosca.datatypes.nfv.VduLevel;
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

	private final VnfPackageService vnfPackageService;

	private final NsdRepository nsdRepository;

	private final NsdPackageJpa nsdPackageJpa;

	public PackagingManager(final VnfPackageRepository vnfPackageRepository, final EventManager eventManager, final PackageManager packageManager, final MapperFacade _mapper, final NsdRepository _nsdRepository, final NsdPackageJpa _nsdPackageJpa, final VnfPackageService _vnfPackageService) {
		super();
		this.vnfPackageRepository = vnfPackageRepository;
		this.eventManager = eventManager;
		this.packageManager = packageManager;
		mapper = _mapper;
		nsdRepository = _nsdRepository;
		nsdPackageJpa = _nsdPackageJpa;
		vnfPackageService = _vnfPackageService;
	}

	public void vnfPackagesVnfPkgIdPackageContentPut(@Nonnull final String vnfPkgId, final byte[] data) {
		final VnfPackage vnfPpackage = vnfPackageService.findById(UUID.fromString(vnfPkgId));
		startOnboarding(vnfPpackage);
		uploadAndFinishOnboarding(vnfPpackage, data);
	}

	public void vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(@Nonnull final String vnfPkgId, final String url) {
		final VnfPackage vnfPackage = vnfPackageService.findById(UUID.fromString(vnfPkgId));
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
			final ProviderData pd = packageProvider.getProviderPadata();
			vnfPackageService.findByDescriptorId(pd.getDescriptorId()).ifPresent(x -> {
				throw new GenericException("Package " + x.getDescriptorId() + " already onboarded in " + x.getId() + ".");
			});
			mapper.map(pd, vnfPackage);
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
			final List<InstantiationLevels> instantiationLevels = packageProvider.getInstatiationLevels(vnfPackage.getUserDefinedData());
			final List<VduInstantiationLevels> vduInstantiationLevel = packageProvider.getVduInstantiationLevels(vnfPackage.getUserDefinedData());
			final List<VduInitialDelta> vduInitialDeltas = packageProvider.getVduInitialDelta(vnfPackage.getUserDefinedData());
			final List<VduScalingAspectDeltas> vduScalingAspectDeltas = packageProvider.getVduScalingAspectDeltas(vnfPackage.getUserDefinedData());

			rebuildVduScalingAspects(vnfPackage, instantiationLevels, vduInstantiationLevel, vduInitialDeltas, vduScalingAspectDeltas, scalingAspects);
		}
		finishOnboarding(vnfPackage);
		eventManager.sendNotification(NotificationEvent.VNF_PKG_ONBOARDING, vnfPackage.getId());
	}

	private static void rebuildVduScalingAspects(final VnfPackage vnfPackage, final List<InstantiationLevels> instantiationLevels, final List<VduInstantiationLevels> vduInstantiationLevels, final List<VduInitialDelta> vduInitialDeltas, final List<VduScalingAspectDeltas> vduScalingAspectDeltas, final Set<ScalingAspect> scalingAspects) {
		// flattern the instantiation levels. levels(demo,premium) -> ScaleInfo(name,
		// scaleLevel)
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
				int level = 1;
				final ScalingAspect aspect = scalingAspects.stream().filter(z -> z.getName().equals(x.getAspect())).findFirst().orElse(new ScalingAspect());
				for (final Entry<String, VduLevel> delta : x.getDeltas().entrySet()) {
					vnfc.addScalingAspectDeltas(new VnfComputeAspectDelta(x.getAspect(), delta.getKey(), delta.getValue().getNumberOfInstances(), level++, aspect.getMaxScaleLevel(), y));
				}
			});
		});
		// Minimal instance at instantiate time.
		vduInitialDeltas.forEach(x -> {
			x.getTargets().forEach(y -> {
				final VnfCompute vnfc = findVnfCompute(vnfPackage, y);
				vnfc.setInitialNumberOfInstance(x.getInitialDelta().getNumberOfInstances());
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
		vnfPackageService.save(vnfPackage);
	}

	private void startOnboarding(final VnfPackage vnfPackage) {
		vnfPackage.setOnboardingState(PackageOnboardingStateType.PROCESSING);
		vnfPackageService.save(vnfPackage);
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
			final Set<NsdPackageVnfPackage> vnfds = packageProvider.getVnfd(userData).stream()
					.map(x -> {
						final VnfPackage vnfPackage = vnfPackageService.findByDescriptorId(x).orElseThrow(() -> new NotFoundException("Vnfd descriptor_id not found: " + x));
						final NsdPackageVnfPackage nsdPackageVnfPackage = new NsdPackageVnfPackage();
						nsdPackageVnfPackage.setNsdPackage(nsPackage);
						nsdPackageVnfPackage.setToscaName(x);
						nsdPackageVnfPackage.setVnfPackage(vnfPackage);
						vnfPackage.addNsdPackage(nsdPackageVnfPackage);
						vnfPackageService.save(vnfPackage);
						return nsdPackageVnfPackage;
					})
					.collect(Collectors.toSet());
			nsPackage.setVnfPkgIds(vnfds);
			final Set<NsdPackageNsdPackage> nsds = packageProvider.getNestedNsd(userData).stream()
					.map(x -> {
						final NsdPackage nestedNsd = nsdPackageJpa.findByNsdInvariantId(x).orElseThrow(() -> new NotFoundException("Nsd invariant_id not found: " + x));
						final NsdPackageNsdPackage nsdnsd = new NsdPackageNsdPackage();
						nsdnsd.setParent(nsPackage);
						nsdnsd.setChild(nestedNsd);
						nsdnsd.setToscaName(x);
						return nsdnsd;
					})
					.collect(Collectors.toSet());
			nsPackage.setNestedNsdInfoIds(nsds);
		}
		nsPackage.setNsdOnboardingState(NsdOnboardingStateType.ONBOARDED);
		nsPackage.setNsdOperationalState(PackageOperationalStateType.ENABLED);
		nsdPackageJpa.save(nsPackage);
		eventManager.sendNotification(NotificationEvent.NS_PKG_ONBOARDING, nsPackage.getId());
	}

}
