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
import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.dao.mano.PkgChecksum;
import com.ubiqube.etsi.mano.dao.mano.ScalingAspect;
import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiationLevels;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mano.service.pkg.bean.InstantiationLevels;
import com.ubiqube.etsi.mano.service.pkg.bean.ProviderData;
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
		final VnfPackageProvider packageProvider = packageManager.getProviderFor(data);
		if (null != packageProvider) {
			final ProviderData pd = packageProvider.getProviderPadata();
			final Optional<VnfPackage> optPackage = getVnfPackage(pd);
			optPackage.ifPresent(x -> {
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
			vcNodes.stream().forEach(x -> x.setVnfPackage(vnfPackage));
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

	private void finishOnboarding(final VnfPackage vnfPackage) {
		vnfPackage.setOnboardingState(OnboardingStateType.ONBOARDED);
		vnfPackage.setOperationalState(PackageOperationalState.ENABLED);
		vnfPackageService.save(vnfPackage);
	}

	private void startOnboarding(final VnfPackage vnfPackage) {
		vnfPackage.setOnboardingState(OnboardingStateType.PROCESSING);
		vnfPackageService.save(vnfPackage);
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
		if (part == 0) {
			return Optional.empty();
		}
		if (part == 1) {
			return vnfPackageService.findByDescriptorId(descriptorId);
		} else if (part == 2) {
			return vnfPackageService.findByDescriptorIdAndSoftwareVersion(descriptorId, version);
		} else if (part == 3) {
			return vnfPackageService.findByDescriptorIdFlavorIdVnfdVersion(descriptorId, flavor, version);
		}
		throw new GenericException("Unknown version " + part);
	}

}
