/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.service.pkg.vnf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.Constants;
import com.ubiqube.etsi.mano.dao.mano.AffinityRule;
import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.dao.mano.PkgChecksum;
import com.ubiqube.etsi.mano.dao.mano.ScalingAspect;
import com.ubiqube.etsi.mano.dao.mano.SecurityGroup;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiationLevels;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.dao.mano.common.ListKeyPair;
import com.ubiqube.etsi.mano.dao.mano.pkg.UploadUriParameters;
import com.ubiqube.etsi.mano.dao.mano.vnfi.VimCapability;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.model.NotificationEvent;
import com.ubiqube.etsi.mano.repository.ManoResource;
import com.ubiqube.etsi.mano.repository.ManoUrlResource;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.pkg.FileEntry;
import com.ubiqube.etsi.mano.service.pkg.PackageDescriptor;
import com.ubiqube.etsi.mano.service.pkg.ToscaException;
import com.ubiqube.etsi.mano.service.pkg.bean.AffinityRuleAdapater;
import com.ubiqube.etsi.mano.service.pkg.bean.InstantiationLevels;
import com.ubiqube.etsi.mano.service.pkg.bean.ProviderData;
import com.ubiqube.etsi.mano.service.pkg.bean.SecurityGroupAdapter;
import com.ubiqube.etsi.mano.service.pkg.bean.VduInitialDelta;
import com.ubiqube.etsi.mano.service.pkg.bean.VduInstantiationLevels;
import com.ubiqube.etsi.mano.service.pkg.bean.VduLevel;
import com.ubiqube.etsi.mano.service.pkg.bean.VduScalingAspectDeltas;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 *
 *         This class is need in VNFM, when the NFVO onboard a package, we will
 *         receive a notification, then we download the new package, and onboard
 *         it.
 */
@Service
public class VnfPackageOnboardingImpl {

	private static final Logger LOG = LoggerFactory.getLogger(VnfPackageOnboardingImpl.class);

	private final EventManager eventManager;

	private final VnfPackageManager packageManager;

	private final MapperFacade mapper;

	private final VnfPackageService vnfPackageService;

	private final VnfPackageRepository vnfPackageRepository;

	public VnfPackageOnboardingImpl(final VnfPackageRepository vnfPackageRepository, final EventManager eventManager, final VnfPackageManager packageManager,
			final MapperFacade mapper, final VnfPackageService vnfPackageService) {
		this.vnfPackageRepository = vnfPackageRepository;
		this.eventManager = eventManager;
		this.packageManager = packageManager;
		this.mapper = mapper;
		this.vnfPackageService = vnfPackageService;
	}

	public VnfPackage vnfPackagesVnfPkgIdPackageContentPut(@Nonnull final String vnfPkgId) {
		final ManoResource data = vnfPackageRepository.getBinary(UUID.fromString(vnfPkgId), Constants.REPOSITORY_FILENAME_PACKAGE);
		VnfPackage vnfPpackage = vnfPackageService.findById(UUID.fromString(vnfPkgId));
		vnfPpackage = startOnboarding(vnfPpackage);
		return uploadAndFinishOnboarding(vnfPpackage, data);
	}

	public VnfPackage vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(@Nonnull final String vnfPkgId) {
		final VnfPackage vnfPackage = vnfPackageService.findById(UUID.fromString(vnfPkgId));
		startOnboarding(vnfPackage);
		final UploadUriParameters params = vnfPackage.getUploadUriParameters();
		LOG.info("Async. Download of {}", params);
		try (FluxRequestor requestor = new FluxRequestor(vnfPackage.getUploadUriParameters())) {
			final ManoResource data = new ManoUrlResource(0, vnfPackage.getUploadUriParameters().getAddressInformation(), requestor);
			return uploadAndFinishOnboarding(vnfPackage, data);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private VnfPackage uploadAndFinishOnboarding(final VnfPackage vnfPackage, final ManoResource data) {
		VnfPackage ret = vnfPackage;
		try {
			final PackageDescriptor<VnfPackageReader> packageProvider = packageManager.getProviderFor(data);
			mapVnfPackage(vnfPackage, data, packageProvider);
			ret = finishOnboarding(vnfPackage);
			buildChecksum(ret, data);
			eventManager.sendNotification(NotificationEvent.VNF_PKG_ONBOARDING, vnfPackage.getId(), Map.of());
		} catch (final RuntimeException | NoSuchAlgorithmException | IOException e) {
			LOG.error("", e);
			final VnfPackage v2 = vnfPackageService.findById(vnfPackage.getId());
			v2.setOnboardingState(OnboardingStateType.ERROR);
			v2.setOnboardingFailureDetails(new FailureDetails(500, e.getMessage()));
			ret = vnfPackageService.save(v2);
		}
		return ret;
	}

	private VnfPackage buildChecksum(final VnfPackage vnfPackage, final ManoResource data) throws NoSuchAlgorithmException, IOException {
		final DigestInputStream dis = new DigestInputStream(data.getInputStream(), MessageDigest.getInstance(Constants.HASH_ALGORITHM));
		try (InputStream stream = data.getInputStream()) {
			stream.readAllBytes();
			vnfPackage.setChecksum(getChecksum(dis));
		}
		return vnfPackageService.save(vnfPackage);
	}

	private void mapVnfPackage(final VnfPackage vnfPackage, final ManoResource data, final PackageDescriptor<VnfPackageReader> packageProvider) {
		if (null == packageProvider) {
			return;
		}
		vnfPackage.setPackageProvider(packageProvider.getProviderName());
		try (InputStream stream = data.getInputStream();
				final VnfPackageReader reader = packageProvider.getNewReaderInstance(stream, vnfPackage.getId())) {
			mapVnfPackage(reader, vnfPackage);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private void mapVnfPackage(final VnfPackageReader vnfPackageReader, final VnfPackage vnfPackage) {
		final ProviderData pd = vnfPackageReader.getProviderPadata();
		if (null == pd.getVnfdId()) {
			throw new GenericException("VNFD cannot be null.");
		}
		final Optional<VnfPackage> optPackage = getVnfPackage(pd);
		optPackage.ifPresent(x -> {
			throw new GenericException("Package " + x.getDescriptorId() + " already onboarded in " + x.getId() + ".");
		});
		mapper.map(pd, vnfPackage);
		additionalMapping(pd, vnfPackage);
		final Map<String, String> userData = vnfPackage.getUserDefinedData();
		final Set<VnfCompute> cNodes = extractComputeNode(vnfPackageReader, vnfPackage);
		extractStorage(vnfPackageReader, vnfPackage);
		extractVl(vnfPackageReader, vnfPackage);
		final Set<VnfLinkPort> vcNodes = extractLinkPort(vnfPackageReader, vnfPackage);
		remapNetworks(cNodes, vcNodes);
		vnfPackage.setAdditionalArtifacts(vnfPackageReader.getAdditionalArtefacts(vnfPackage.getUserDefinedData()));
		final Set<VnfExtCp> vnfExtCp = extractVnfExtCp(vnfPackageReader, vnfPackage);
		final Set<ScalingAspect> scalingAspects = vnfPackageReader.getScalingAspects(vnfPackage.getUserDefinedData());
		final List<InstantiationLevels> instantiationLevels = vnfPackageReader.getInstatiationLevels(vnfPackage.getUserDefinedData());
		final List<VduInstantiationLevels> vduInstantiationLevel = vnfPackageReader.getVduInstantiationLevels(vnfPackage.getUserDefinedData());
		final List<VduInitialDelta> vduInitialDeltas = vnfPackageReader.getVduInitialDelta(vnfPackage.getUserDefinedData());
		final List<VduScalingAspectDeltas> vduScalingAspectDeltas = vnfPackageReader.getVduScalingAspectDeltas(vnfPackage.getUserDefinedData());
		rebuildVduScalingAspects(vnfPackage, instantiationLevels, vduInstantiationLevel, vduInitialDeltas, vduScalingAspectDeltas, scalingAspects);
		final Set<SecurityGroupAdapter> sgAdapters = vnfPackageReader.getSecurityGroups(userData);
		handleSecurityGroups(sgAdapters, vnfPackage, vnfExtCp);
		fixExternalPoint(vnfPackage, vnfExtCp);
		final Set<AffinityRuleAdapater> ar = vnfPackageReader.getAffinityRules(vnfPackage.getUserDefinedData());
		mapVlToCp(vnfPackage);
		handleAffinity(ar, vnfPackage);
		setMandatoryVimCapabilities(vnfPackage);
		extractVnfd(vnfPackageReader, vnfPackage);
		extractedManifest(vnfPackageReader, vnfPackage);
		buildSoftwareImage(vnfPackage);
		onboardCnfElements(vnfPackageReader, vnfPackage);
	}

	private static void onboardCnfElements(final VnfPackageReader vnfPackageReader, final VnfPackage vnfPackage) {
		vnfPackage.setOsContainer(vnfPackageReader.getOsContainer(vnfPackage.getUserDefinedData()));
		vnfPackage.setOsContainerDeployableUnits(vnfPackageReader.getOsContainerDeployableUnit(vnfPackage.getUserDefinedData()));
		vnfPackage.setVirtualCp(vnfPackageReader.getVirtualCp(vnfPackage.getUserDefinedData()));
	}

	private static void buildSoftwareImage(final VnfPackage vnfPackage) {
		final Map<ImageKey, SoftwareImage> mvnf = vnfPackage.getVnfCompute().stream().map(VnfCompute::getSoftwareImage).filter(Objects::nonNull).collect(Collectors.toMap(ImageKey::new, x -> x));
		final Map<ImageKey, SoftwareImage> msto = vnfPackage.getVnfStorage().stream().map(VnfStorage::getSoftwareImage).filter(Objects::nonNull).collect(Collectors.toMap(ImageKey::new, x -> x));
		final HashMap<ImageKey, SoftwareImage> mall = new HashMap<>(mvnf);
		mall.putAll(msto);
		vnfPackage.setSoftwareImages(mall.entrySet().stream().map(Entry::getValue).collect(Collectors.toSet()));
	}

	private static Set<VnfExtCp> extractVnfExtCp(final VnfPackageReader vnfPackageReader, final VnfPackage vnfPackage) {
		final Set<VnfExtCp> vnfExtCp = vnfPackageReader.getVnfExtCp(vnfPackage.getUserDefinedData());
		vnfPackage.setVnfExtCp(vnfExtCp);
		return vnfExtCp;
	}

	private static Set<VnfLinkPort> extractLinkPort(final VnfPackageReader vnfPackageReader, final VnfPackage vnfPackage) {
		final Set<VnfLinkPort> vcNodes = vnfPackageReader.getVnfVduCp(vnfPackage.getUserDefinedData());
		vnfPackage.setVnfLinkPort(vcNodes);
		return vcNodes;
	}

	private static void extractVl(final VnfPackageReader vnfPackageReader, final VnfPackage vnfPackage) {
		final Set<VnfVl> vvlNodes = vnfPackageReader.getVnfVirtualLinks(vnfPackage.getUserDefinedData());
		vnfPackage.setVnfVl(vvlNodes);
	}

	private static void extractStorage(final VnfPackageReader vnfPackageReader, final VnfPackage vnfPackage) {
		final Set<VnfStorage> vboNodes = vnfPackageReader.getVnfStorages(vnfPackage.getUserDefinedData());
		vnfPackage.setVnfStorage(vboNodes);
	}

	private static Set<VnfCompute> extractComputeNode(final VnfPackageReader vnfPackageReader, final VnfPackage vnfPackage) {
		final Set<VnfCompute> cNodes = vnfPackageReader.getVnfComputeNodes(vnfPackage.getUserDefinedData());
		vnfPackage.setVnfCompute(cNodes);
		return cNodes;
	}

	private void extractVnfd(final VnfPackageReader vnfPackageReader, final VnfPackage vnfPackage) {
		final List<String> imports = vnfPackageReader.getImports();
		if (imports.isEmpty()) {
			return;
		}
		final List<FileEntry> ret = convertToFileEntry(vnfPackageReader, imports);
		if (ret.size() == 1) {
			final FileEntry fileEntry = ret.get(0);
			final ByteArrayInputStream stream = new ByteArrayInputStream(fileEntry.content());
			vnfPackageRepository.storeBinary(vnfPackage.getId(), Constants.REPOSITORY_FILENAME_VNFD, stream);
			vnfPackage.setVnfdContentType("text/plain");
			return;
		}
		final byte[] newZip = buildZip(ret);
		final ByteArrayInputStream stream = new ByteArrayInputStream(newZip);
		vnfPackageRepository.storeBinary(vnfPackage.getId(), Constants.REPOSITORY_FILENAME_VNFD, stream);
		vnfPackage.setVnfdContentType("application/zip");
	}

	private static byte[] buildZip(final List<FileEntry> ret) {
		try (final ByteArrayOutputStream fos = new ByteArrayOutputStream();
				final ZipOutputStream zipOut = new ZipOutputStream(fos)) {
			for (final FileEntry srcFile : ret) {
				try (final ByteArrayInputStream fis = new ByteArrayInputStream(srcFile.content())) {
					final ZipEntry zipEntry = new ZipEntry(srcFile.fileName());
					zipOut.putNextEntry(zipEntry);
					zipOut.write(srcFile.content());
				}
			}
			zipOut.finish();
			return fos.toByteArray();
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private static List<FileEntry> convertToFileEntry(final VnfPackageReader vnfPackageReader, final List<String> imports) {
		return imports.stream().map(x -> new FileEntry(x, vnfPackageReader.getFileContent(x))).toList();
	}

	private void extractedManifest(final VnfPackageReader vnfPackageReader, final VnfPackage vnfPackage) {
		final String manifest = vnfPackageReader.getManifestContent();
		if (null == manifest) {
			return; // XXX: It will pose a problem, maybe we can reconstituate manifest in Sol004
					// module when missing.
		}
		vnfPackageRepository.storeBinary(vnfPackage.getId(), Constants.REPOSITORY_FILENAME_MANIFEST, new ByteArrayInputStream(manifest.getBytes()));
	}

	private static void setMandatoryVimCapabilities(final VnfPackage vnfPackage) {
		final Set<VimCapability> vimCapabilities = new HashSet<>();
		vnfPackage.setVimCapabilities(vimCapabilities);
		addIfNeeded(VimCapability.HAVE_NET_MTU, vimCapabilities, vnfPackage, x -> x.getL2ProtocolData().getMtu(), Objects::nonNull);
		addIfNeeded(VimCapability.HAVE_VLAN_TRANSPARENT, vimCapabilities, vnfPackage, x -> x.getL2ProtocolData().getVlanTransparent(), x -> !x.booleanValue());
		addIfNeeded(VimCapability.HAVE_VXNET, vimCapabilities, vnfPackage, x -> x.getL2ProtocolData().getNetworkType(), x -> !x.equalsIgnoreCase("vxlan"));
	}

	private static <U> void addIfNeeded(final VimCapability vc, final Set<VimCapability> svc, final VnfPackage vnfPackage, final Function<VlProtocolData, U> tr, final Predicate<U> p) {
		vnfPackage.getVnfVl().stream()
				.flatMap(x -> x.getVlProfileEntity().getVirtualLinkProtocolData().stream())
				.map(tr)
				.filter(Objects::nonNull)
				.filter(p)
				.findFirst()
				.ifPresent(x -> svc.add(vc));
	}

	private static void mapVlToCp(final VnfPackage vnfPackage) {
		vnfPackage.getVnfCompute().stream()
				.flatMap(x -> x.getPorts().stream())
				.filter(x -> x.getVirtualLink() == null)
				.forEach(x -> x.setVirtualLink(findVl(x.getToscaName(), vnfPackage.getVirtualLinks())));
	}

	private static String findVl(final String toscaName, final Set<ListKeyPair> virtualLinks) {
		final ListKeyPair vl = virtualLinks.stream()
				.filter(x -> x.getValue() != null)
				.filter(x -> x.getValue().equals(toscaName))
				.findFirst()
				.orElseThrow(() -> new ToscaException("Could not find VL named " + toscaName + " in " + virtualLinks));
		return vlToString(vl);
	}

	private static String vlToString(final ListKeyPair vl) {
		if (0 == vl.getIdx()) {
			return "virtual_link";
		}
		return "virtual_link_" + vl.getIdx();
	}

	private static void additionalMapping(final ProviderData pd, final VnfPackage vnfPackage) {
		vnfPackage.addVirtualLink(pd.getVirtualLinkReq());
		vnfPackage.addVirtualLink(pd.getVirtualLink1Req());
		vnfPackage.addVirtualLink(pd.getVirtualLink2Req());
		vnfPackage.addVirtualLink(pd.getVirtualLink3Req());
		vnfPackage.addVirtualLink(pd.getVirtualLink4Req());
		vnfPackage.addVirtualLink(pd.getVirtualLink5Req());
		vnfPackage.addVirtualLink(pd.getVirtualLink6Req());
		vnfPackage.addVirtualLink(pd.getVirtualLink7Req());
		vnfPackage.addVirtualLink(pd.getVirtualLink8Req());
		vnfPackage.addVirtualLink(pd.getVirtualLink9Req());
		vnfPackage.addVirtualLink(pd.getVirtualLink10Req());
		final Set<ListKeyPair> nl = vnfPackage.getVirtualLinks().stream().filter(x -> x.getValue() != null).collect(Collectors.toSet());
		vnfPackage.setVirtualLinks(nl);
	}

	private static void fixExternalPoint(final VnfPackage vnfPackage, final Set<VnfExtCp> vnfExtCp) {
		vnfExtCp.forEach(x -> {
			if (isComputeNode(vnfPackage, x.getInternalVirtualLink())) {
				x.setComputeNode(true);
			}
		});
	}

	private static boolean isComputeNode(final VnfPackage vnfPackage, final String internalVirtualLink) {
		final Optional<VnfCompute> res = vnfPackage.getVnfCompute().stream().filter(x -> x.getToscaName().equals(internalVirtualLink)).findFirst();
		return res.isPresent();
	}

	private void handleAffinity(final Set<AffinityRuleAdapater> ar, final VnfPackage vnfPackage) {
		ar.forEach(x -> {
			vnfPackage.getVnfCompute().stream()
					.filter(y -> x.getTargets().contains(y.getToscaName()))
					.forEach(y -> y.addAffinity(x.getAffinityRule().getToscaName()));
			vnfPackage.getVnfVl().stream()
					.filter(y -> x.getTargets().contains(y.getToscaName()))
					.forEach(y -> y.addAffinity(x.getAffinityRule().getToscaName()));
			// Placement group.
		});
		final Set<AffinityRule> res = ar.stream().map(x -> mapper.map(x.getAffinityRule(), AffinityRule.class)).collect(Collectors.toSet());
		vnfPackage.setAffinityRules(res);
	}

	private void handleSecurityGroups(final Set<SecurityGroupAdapter> sgAdapters, final VnfPackage vnfPackage, final Set<VnfExtCp> vnfExtCp) {
		sgAdapters.forEach(x -> {
			vnfPackage.getVnfCompute().stream()
					.filter(y -> x.getTargets().contains(y.getToscaName()))
					.forEach(y -> y.addSecurityGroups(x.getSecurityGroup().getToscaName()));
			vnfExtCp.stream()
					.filter(y -> x.getTargets().contains(y.getToscaName()))
					.forEach(y -> y.addSecurityGroup(x.getSecurityGroup().getToscaName()));
		});
		final Set<SecurityGroup> res = sgAdapters.stream().map(x -> mapper.map(x.getSecurityGroup(), SecurityGroup.class)).collect(Collectors.toSet());
		vnfPackage.setSecurityGroups(res);
	}

	@SuppressWarnings("boxing")
	private static void rebuildVduScalingAspects(final VnfPackage vnfPackage, final List<InstantiationLevels> instantiationLevels, final List<VduInstantiationLevels> vduInstantiationLevels,
			final List<VduInitialDelta> vduInitialDeltas, final List<VduScalingAspectDeltas> vduScalingAspectDeltas, final Set<ScalingAspect> scalingAspects) {
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
		vduScalingAspectDeltas.forEach(x -> x.getTargets().forEach(y -> {
			final VnfCompute vnfc = findVnfCompute(vnfPackage, y);
			final VduInitialDelta init = findVduInitialDelta(vduInitialDeltas, y);
			int level = 1;
			int numInst = init.getInitialDelta().getNumberOfInstances();
			final ScalingAspect aspect = scalingAspects.stream().filter(z -> z.getName().equals(x.getAspect())).findFirst().orElse(new ScalingAspect());
			vnfc.addScalingAspectDeltas(new VnfComputeAspectDelta(x.getAspect(), "initial_delta", init.getInitialDelta().getNumberOfInstances(), 0, aspect.getMaxScaleLevel(), y, numInst));
			// Missing 0 => initial inst level.
			for (final String delta : aspect.getStepDeltas()) {
				final VduLevel step = x.getDeltas().get(delta);
				numInst += step.getNumberOfInstances();
				vnfc.addScalingAspectDeltas(new VnfComputeAspectDelta(x.getAspect(), delta, step.getNumberOfInstances(), level++, aspect.getMaxScaleLevel(), y, numInst));
			}
		}));
		// Minimal instance at instantiate time.
		vduInitialDeltas.forEach(x -> x.getTargets().forEach(y -> {
			final VnfCompute vnfc = findVnfCompute(vnfPackage, y);
			vnfc.setInitialNumberOfInstance(x.getInitialDelta().getNumberOfInstances());
		}));
	}

	private static VduInitialDelta findVduInitialDelta(final List<VduInitialDelta> vduInitialDeltas, final String y) {
		return vduInitialDeltas.stream().filter(x -> x.getTargets().contains(y)).findFirst().orElseThrow(() -> new GenericException("Could not find initial level for vdu " + y));
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
			final Set<VnfLinkPort> nodes = filter(vcNodes, x.getToscaName());
			if (nodes.isEmpty()) {
				LOG.warn("Node {} have no network.", x.getToscaName());
			}
			x.setNetworks(nodes.stream().map(VnfLinkPort::getVirtualLink).collect(Collectors.toSet()));
			x.setPorts(nodes);
		});
	}

	private static Set<VnfLinkPort> filter(final Set<VnfLinkPort> vcNodes, final String toscaName) {
		return vcNodes.stream()
				.filter(x -> x.getVirtualBinding().equals(toscaName))
				.collect(Collectors.toSet());
	}

	private static PkgChecksum getChecksum(final DigestInputStream digest) {
		final byte[] hashbytes = digest.getMessageDigest().digest();
		final String sha3_256hex = bytesToHex(hashbytes);
		final PkgChecksum checksum = new PkgChecksum();

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

	private VnfPackage finishOnboarding(final VnfPackage vnfPackage) {
		vnfPackage.setOnboardingState(OnboardingStateType.ONBOARDED);
		vnfPackage.setOperationalState(PackageOperationalState.ENABLED);
		vnfPackage.setOnboardingFailureDetails(new FailureDetails());
		return vnfPackageService.save(vnfPackage);
	}

	private VnfPackage startOnboarding(final VnfPackage vnfPackage) {
		vnfPackage.setOnboardingState(OnboardingStateType.PROCESSING);
		return vnfPackageService.save(vnfPackage);
	}

	private Optional<VnfPackage> getVnfPackage(final ProviderData pd) {
		return getVnfPackage(pd.getFlavorId(), pd.getDescriptorId(), pd.getVnfdVersion());
	}

	private Optional<VnfPackage> getVnfPackage(final String flavor, final String descriptorId, final String version) {
		int part = 0;
		if (flavor != null) {
			part++;
		}
		if (descriptorId != null) {
			part++;
		}
		if (version != null) {
			part++;
		}
		switch (part) {
		case 0:
			return Optional.empty();
		case 1:
			return vnfPackageService.findByDescriptorId(descriptorId);
		case 2:
			return vnfPackageService.findByDescriptorIdAndSoftwareVersion(descriptorId, version);
		case 3:
			return vnfPackageService.findByDescriptorIdFlavorIdVnfdVersion(descriptorId, flavor, version);
		default:
			break;
		}
		throw new GenericException("Unknown version " + part);
	}

	private static class ImageKey {
		SoftwareImage si;

		public ImageKey(final SoftwareImage si) {
			this.si = si;
		}

		@Override
		public int hashCode() {
			if (si.getChecksum() != null || null != si.getChecksum().getHash()) {
				return Objects.hash(si.getChecksum().getHash());
			}
			return Objects.hash(si.getName());
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || !(obj instanceof final ImageKey ik)) {
				return false;
			}
			final SoftwareImage si2 = ik.si;
			if (si2.getChecksum() == null || si.getChecksum() == null) {
				return si.getName().equals(si2.getName());
			}
			return Objects.equals(si.getChecksum().getHash(), si2.getChecksum().getHash());
		}
	}
}
