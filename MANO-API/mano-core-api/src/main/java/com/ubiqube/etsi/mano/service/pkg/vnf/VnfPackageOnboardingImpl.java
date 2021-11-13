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
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import com.ubiqube.etsi.mano.dao.mano.AffinityRule;
import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.dao.mano.PkgChecksum;
import com.ubiqube.etsi.mano.dao.mano.ScalingAspect;
import com.ubiqube.etsi.mano.dao.mano.SecurityGroup;
import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiationLevels;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mano.service.pkg.PackageDescriptor;
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
 */
@Service
public class VnfPackageOnboardingImpl {

	private static final Logger LOG = LoggerFactory.getLogger(VnfPackageOnboardingImpl.class);

	private final EventManager eventManager;

	private final VnfPackageManager packageManager;

	private final MapperFacade mapper;

	private final VnfPackageService vnfPackageService;

	private final VnfPackageRepository vnfPackageRepository;

	public VnfPackageOnboardingImpl(final VnfPackageRepository vnfPackageRepository, final EventManager eventManager, final VnfPackageManager packageManager, final MapperFacade _mapper, final VnfPackageService _vnfPackageService) {
		this.vnfPackageRepository = vnfPackageRepository;
		this.eventManager = eventManager;
		this.packageManager = packageManager;
		mapper = _mapper;
		vnfPackageService = _vnfPackageService;
	}

	public VnfPackage vnfPackagesVnfPkgIdPackageContentPut(@Nonnull final String vnfPkgId) {
		final byte[] data = vnfPackageRepository.getBinary(UUID.fromString(vnfPkgId), "vnfd");
		VnfPackage vnfPpackage = vnfPackageService.findById(UUID.fromString(vnfPkgId));
		vnfPpackage = startOnboarding(vnfPpackage);
		return uploadAndFinishOnboarding(vnfPpackage, data);
	}

	public VnfPackage vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(@Nonnull final String vnfPkgId, final String url) {
		final VnfPackage vnfPackage = vnfPackageService.findById(UUID.fromString(vnfPkgId));
		startOnboarding(vnfPackage);
		LOG.info("Async. Download of {}", url);
		final byte[] data = getUrlContent(url);
		return uploadAndFinishOnboarding(vnfPackage, data);
	}

	private VnfPackage uploadAndFinishOnboarding(final VnfPackage vnfPackage, final byte[] data) {
		VnfPackage ret = vnfPackage;
		try {
			vnfPackage.setChecksum(getChecksum(data));
			vnfPackageRepository.storeBinary(vnfPackage.getId(), "vnfd", new ByteArrayInputStream(data));
			final PackageDescriptor<VnfPackageReader> packageProvider = packageManager.getProviderFor(data);
			if (null != packageProvider) {
				mapVnfPackage(packageProvider.getNewReaderInstance(data), vnfPackage);

			}
			ret = finishOnboarding(vnfPackage);
			eventManager.sendNotification(NotificationEvent.VNF_PKG_ONBOARDING, vnfPackage.getId());
		} catch (final RuntimeException e) {
			LOG.error("", e);
			// XXX It is ERROR in
			final VnfPackage v2 = vnfPackageService.findById(vnfPackage.getId());
			v2.setOnboardingState(OnboardingStateType.CREATED);
			v2.setOnboardingFailureDetails(new FailureDetails(500, e.getMessage()));
			ret = vnfPackageService.save(v2);
		}
		return ret;
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
		final Map<String, String> userData = vnfPackage.getUserDefinedData();
		final Set<VnfCompute> cNodes = vnfPackageReader.getVnfComputeNodes(vnfPackage.getUserDefinedData());
		vnfPackage.setVnfCompute(cNodes);
		final Set<VnfStorage> vboNodes = vnfPackageReader.getVnfStorages(vnfPackage.getUserDefinedData());
		vnfPackage.setVnfStorage(vboNodes);
		final Set<VnfVl> vvlNodes = vnfPackageReader.getVnfVirtualLinks(vnfPackage.getUserDefinedData());
		vnfPackage.setVnfVl(vvlNodes);
		final Set<VnfLinkPort> vcNodes = vnfPackageReader.getVnfVduCp(vnfPackage.getUserDefinedData());
		vcNodes.stream().forEach(x -> x.setVnfPackage(vnfPackage));
		vnfPackage.setVnfLinkPort(vcNodes);
		remapNetworks(cNodes, vcNodes);
		vnfPackage.setAdditionalArtifacts(vnfPackageReader.getAdditionalArtefacts(vnfPackage.getUserDefinedData()));
		final Set<VnfExtCp> vnfExtCp = vnfPackageReader.getVnfExtCp(vnfPackage.getUserDefinedData());
		vnfPackage.setVnfExtCp(vnfExtCp);
		final Set<ScalingAspect> scalingAspects = vnfPackageReader.getScalingAspects(vnfPackage.getUserDefinedData());
		final List<InstantiationLevels> instantiationLevels = vnfPackageReader.getInstatiationLevels(vnfPackage.getUserDefinedData());
		final List<VduInstantiationLevels> vduInstantiationLevel = vnfPackageReader.getVduInstantiationLevels(vnfPackage.getUserDefinedData());
		final List<VduInitialDelta> vduInitialDeltas = vnfPackageReader.getVduInitialDelta(vnfPackage.getUserDefinedData());
		final List<VduScalingAspectDeltas> vduScalingAspectDeltas = vnfPackageReader.getVduScalingAspectDeltas(vnfPackage.getUserDefinedData());
		rebuildVduScalingAspects(vnfPackage, instantiationLevels, vduInstantiationLevel, vduInitialDeltas, vduScalingAspectDeltas, scalingAspects);
		final Set<SecurityGroupAdapter> sgAdapters = vnfPackageReader.getSecurityGroups(userData);
		handleSecurityGroups(sgAdapters, vnfPackage, vnfExtCp);

		final Set<AffinityRuleAdapater> ar = vnfPackageReader.getAffinityRules(vnfPackage.getUserDefinedData());
		handleAffinity(ar, vnfPackage);
	}

	private void handleAffinity(final Set<AffinityRuleAdapater> ar, final VnfPackage vnfPackage) {
		ar.forEach(x -> {
			vnfPackage.getVnfCompute().stream()
					.filter(y -> x.getTargets().contains(x.getAffinityRule().getToscaName()))
					.forEach(y -> y.addAffinity(x.getAffinityRule().getToscaName()));
			vnfPackage.getVnfVl().stream()
					.filter(y -> x.getTargets().contains(x.getAffinityRule().getToscaName()))
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
		vduScalingAspectDeltas.forEach(x -> x.getTargets().forEach(y -> {
			final VnfCompute vnfc = findVnfCompute(vnfPackage, y);
			int level = 1;
			final ScalingAspect aspect = scalingAspects.stream().filter(z -> z.getName().equals(x.getAspect())).findFirst().orElse(new ScalingAspect());
			for (final Entry<String, VduLevel> delta : x.getDeltas().entrySet()) {
				vnfc.addScalingAspectDeltas(new VnfComputeAspectDelta(x.getAspect(), delta.getKey(), delta.getValue().getNumberOfInstances(), level++, aspect.getMaxScaleLevel(), y));
			}
		}));
		// Minimal instance at instantiate time.
		vduInitialDeltas.forEach(x -> x.getTargets().forEach(y -> {
			final VnfCompute vnfc = findVnfCompute(vnfPackage, y);
			vnfc.setInitialNumberOfInstance(x.getInitialDelta().getNumberOfInstances());
		}));
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
			if (nodes.isEmpty()) {
				throw new GenericException("Node " + x.getToscaName() + " must have a network.");
			}
			x.setNetworks(nodes);
		});
	}

	private static Set<String> filter(final Set<VnfLinkPort> vcNodes, final String toscaName) {
		return vcNodes.stream()
				.filter(x -> x.getVirtualBinding().equals(toscaName))
				.map(VnfLinkPort::getVirtualLink)
				.collect(Collectors.toSet());
	}

	private static byte[] getUrlContent(final String uri) {
		try {
			final URL url = new URL(uri);
			return StreamUtils.copyToByteArray((InputStream) url.getContent());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private static PkgChecksum getChecksum(final byte[] bytes) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(Constants.HASH_ALGORITHM);
		} catch (final NoSuchAlgorithmException e) {
			throw new GenericException(e);
		}
		final byte[] hashbytes = digest.digest(bytes);
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

}
