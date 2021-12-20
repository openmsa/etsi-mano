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
package com.ubiqube.etsi.mano.nfvo.service.pkg.ns;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageNsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.dao.mano.nsd.VnffgDescriptor;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsScalingLevelMapping;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsScalingStepMapping;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsVlLevelMapping;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsVlStepMapping;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.StepMapping;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.VlBitRate;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.VnfScalingLevelMapping;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.VnfScalingStepMapping;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLink;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.nfvo.jpa.NsdPackageJpa;
import com.ubiqube.etsi.mano.nfvo.service.pkg.PackageVersion;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mano.service.pkg.ToscaException;
import com.ubiqube.etsi.mano.service.pkg.bean.NsInformations;
import com.ubiqube.etsi.mano.service.pkg.bean.SecurityGroupAdapter;
import com.ubiqube.etsi.mano.service.pkg.bean.nsscaling.NsScaling;
import com.ubiqube.etsi.mano.service.pkg.bean.nsscaling.RootLeaf;
import com.ubiqube.etsi.mano.service.pkg.ns.NsPackageProvider;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NsPackageOnboardingImpl {

	private static final Logger LOG = LoggerFactory.getLogger(NsPackageOnboardingImpl.class);

	private final EventManager eventManager;

	private final NsPackageManager packageManager;

	private final MapperFacade mapper;

	private final VnfPackageService vnfPackageService;

	private final NsdRepository nsdRepository;

	private final NsdPackageJpa nsdPackageJpa;

	public NsPackageOnboardingImpl(final EventManager eventManager, final NsPackageManager packageManager, final MapperFacade mapper, final NsdRepository nsdRepository,
			final NsdPackageJpa nsdPackageJpa, final VnfPackageService vnfPackageService) {
		super();
		this.eventManager = eventManager;
		this.packageManager = packageManager;
		this.mapper = mapper;
		this.nsdRepository = nsdRepository;
		this.nsdPackageJpa = nsdPackageJpa;
		this.vnfPackageService = vnfPackageService;
	}

	public void nsOnboarding(@NotNull final UUID objectId) {
		final NsdPackage nsPackage = nsdPackageJpa.findById(objectId).orElseThrow(() -> new NotFoundException("NS Package " + objectId + " Not found."));
		try {
			nsOnboardingInternal(nsPackage);
			nsPackage.setNsdOnboardingState(OnboardingStateType.ONBOARDED);
			nsPackage.setNsdOperationalState(PackageOperationalState.ENABLED);
			nsPackage.setOnboardingFailureDetails(new FailureDetails());
			nsdPackageJpa.save(nsPackage);
		} catch (final RuntimeException e) {
			LOG.error("NSD error", e);
			// XXX: ERROR on 2.6.1+
			final NsdPackage v2 = nsdPackageJpa.findById(nsPackage.getId()).orElseThrow();
			v2.setNsdOnboardingState(OnboardingStateType.CREATED);
			v2.setNsdOperationalState(PackageOperationalState.DISABLED);
			v2.setOnboardingFailureDetails(new FailureDetails(500, e.getMessage()));
			nsdPackageJpa.save(v2);
		}
		eventManager.sendNotification(NotificationEvent.NS_PKG_ONBOARDING, nsPackage.getId());
	}

	public void nsOnboardingInternal(@NotNull final NsdPackage nsPackage) {
		final byte[] data = nsdRepository.getBinary(nsPackage.getId(), "nsd");
		final NsPackageProvider packageProvider = packageManager.getProviderFor(data);
		if (null != packageProvider) {
			mapNsPackage(packageProvider, nsPackage);
		}
	}

	private void mapNsPackage(final NsPackageProvider packageProvider, final NsdPackage nsPackage) {
		final Map<String, String> userData = nsPackage.getUserDefinedData();
		final NsInformations nsInformations = packageProvider.getNsInformations(userData);
		mapper.map(nsInformations, nsPackage);
		final Set<NsSap> nsSap = packageProvider.getNsSap(userData);
		nsPackage.setNsSaps(nsSap);
		final Set<NsVirtualLink> nsVirtualLink = packageProvider.getNsVirtualLink(userData);
		nsPackage.setNsVirtualLinks(nsVirtualLink);
		final Set<SecurityGroupAdapter> sgAdapters = packageProvider.getSecurityGroups(userData);
		// Security groups are only applicable to SAP.
		sgAdapters.forEach(x -> nsPackage.getNsSaps().stream()
				.filter(y -> x.getTargets().contains(y.getToscaName()))
				.forEach(y -> y.addSecurityGroups(x.getSecurityGroup())));
		final Set<NsdPackageVnfPackage> vnfds = packageProvider.getVnfd(userData).stream()
				.map(x -> {
					nsInformations.getFlavorId();
					final Optional<VnfPackage> optPackage = getVnfPackage(x.getVnfdId());
					final VnfPackage vnfPackage = optPackage.orElseThrow(() -> new NotFoundException("Vnfd descriptor_id not found: " + x.getVnfdId()));
					final NsdPackageVnfPackage nsdPackageVnfPackage = new NsdPackageVnfPackage();
					nsdPackageVnfPackage.setNsdPackage(nsPackage);
					nsdPackageVnfPackage.setToscaName(x.getName());
					nsdPackageVnfPackage.setVnfPackage(vnfPackage);
					nsdPackageVnfPackage.addVirtualLink(x.getVirtualLink());
					vnfPackage.addNsdPackage(nsdPackageVnfPackage);
					vnfPackageService.save(vnfPackage);
					return nsdPackageVnfPackage;
				})
				.collect(Collectors.toSet());
		nsPackage.setVnfPkgIds(vnfds);
		final Set<NsdPackageNsdPackage> nsds = packageProvider.getNestedNsd(userData).stream()
				.map(x -> {
					final NsdPackage nestedNsd = nsdPackageJpa.findByNsdInvariantId(x.getNsdId()).orElseThrow(() -> new NotFoundException("Nsd invariant_id not found: " + x.getNsdId()));
					final NsdPackageNsdPackage nsdnsd = new NsdPackageNsdPackage();
					nsdnsd.setParent(nsPackage);
					nsdnsd.setChild(nestedNsd);
					nsdnsd.setToscaName(x.getName());
					nsdnsd.addVirtualLink(x.getVirtulaLink());
					return nsdnsd;
				})
				.collect(Collectors.toSet());
		final Set<VnffgDescriptor> vnffg = packageProvider.getVnffg(userData);
		nsPackage.setAutoHealEnabled(packageProvider.isAutoHealEnabled());
		nsPackage.setVnffgs(vnffg);
		rebuildConnectivity(vnffg, nsPackage);
		nsPackage.setNestedNsdInfoIds(nsds);
		final NsScaling nsScaling = packageProvider.getNsScaling(userData);
		remapScaling(nsPackage, nsScaling);
		verifyCircularDependencies(nsPackage, new ArrayDeque<>());
	}

	private void verifyCircularDependencies(final NsdPackage nsPackage, final Deque<UUID> stack) {
		stack.push(nsPackage.getId());
		nsPackage.getNestedNsdInfoIds().forEach(x -> {
			if (stack.contains(x.getId())) {
				throw new GenericException("Circular dependency detected, trying to include " + x.getId() + ", chain: " + stack);
			}
			final NsdPackage p = nsdPackageJpa.findById(x.getChild().getId()).orElseThrow();
			verifyCircularDependencies(p, stack);
		});
		stack.pop();
	}

	private static void remapScaling(final NsdPackage nsPackage, final NsScaling nsScaling) {
		nsScaling.getNsStepMapping().forEach(x -> x.target().forEach(y -> {
			final NsdPackageNsdPackage nsd = findNesteedNs(nsPackage, y);
			final NsScalingStepMapping scaling = new NsScalingStepMapping(mapStepMapping(x.mapping()), x.aspectId());
			x.mapping().entrySet().forEach(z -> nsd.addStepMapping(scaling));
		}));
		nsScaling.getVnfStepMapping().forEach(x -> x.target().forEach(y -> {
			final NsdPackageVnfPackage nsd = findVnf(nsPackage, y);
			final VnfScalingStepMapping scaling = new VnfScalingStepMapping(mapStepMapping(x.mapping()), x.aspectId());
			x.mapping().entrySet().forEach(z -> nsd.addStepMapping(scaling));
		}));
		nsScaling.getVlStepMapping().forEach(x -> x.targets().forEach(y -> {
			final NsVirtualLink vl = findVl(nsPackage, y);
			final NsVlStepMapping mapping = new NsVlStepMapping(mapVlStep(x.mapping()), x.aspectId());
			vl.addStepMapping(mapping);
		}));

		nsScaling.getNsLevelMapping().forEach(x -> x.target().forEach(y -> {
			final NsdPackageNsdPackage nsd = findNesteedNs(nsPackage, y);
			x.mapping().entrySet().forEach(z -> {
				final NsScalingLevelMapping mapping = new NsScalingLevelMapping(z.getKey(), x.aspectId(), z.getValue());
				nsd.addLevelMapping(mapping);
			});
		}));
		nsScaling.getVnfLevelMapping().forEach(x -> x.target().forEach(y -> {
			final NsdPackageVnfPackage nsd = findVnf(nsPackage, y);
			x.mapping().entrySet().forEach(z -> {
				final VnfScalingLevelMapping mapping = new VnfScalingLevelMapping(z.getKey(), x.aspectId(), z.getValue());
				nsd.addLevelMapping(mapping);
			});
		}));
		nsScaling.getVlLevelMapping().forEach(x -> x.targets().forEach(y -> {
			final NsVirtualLink vl = findVl(nsPackage, y);
			x.mapping().entrySet().forEach(z -> {
				final NsVlLevelMapping mapping = new NsVlLevelMapping(z.getKey(), z.getValue().root(), z.getValue().leaf());
				vl.addLevelMapping(mapping);
			});
		}));
	}

	private static Set<StepMapping> mapStepMapping(final Map<Integer, Integer> mapping) {
		return mapping.entrySet().stream().map(x -> new StepMapping(x.getKey(), x.getValue())).collect(Collectors.toSet());
	}

	private static Set<VlBitRate> mapVlStep(final Map<Integer, RootLeaf> mapping) {
		return mapping.entrySet().stream()
				.map(x -> new VlBitRate(null, x.getKey(), x.getValue().root(), x.getValue().leaf()))
				.collect(Collectors.toSet());
	}

	private static NsdPackageVnfPackage findVnf(final NsdPackage nsPackage, final String y) {
		return nsPackage.getVnfPkgIds().stream().filter(x -> x.getToscaName().equals(y)).findAny().orElseThrow(() -> new GenericException("Could not find a VNFD named: " + y));
	}

	private static NsdPackageNsdPackage findNesteedNs(final NsdPackage nsPackage, final String y) {
		return nsPackage.getNestedNsdInfoIds().stream().filter(x -> x.getToscaName().equals(y)).findAny().orElseThrow(() -> new GenericException("Could not find a NSD named: " + y));
	}

	private static void rebuildConnectivity(final Set<VnffgDescriptor> vnffg, final NsdPackage nsPackage) {
		vnffg.stream().forEach(x -> {
			final NsVirtualLink vl = findVl(nsPackage, x.getVirtualLinkId());
			vl.addVnffg(x.getName());
			x.getPairs().forEach(y -> assignVnnfg(x.getName(), nsPackage));
		});
	}

	private static void assignVnnfg(final String name, final NsdPackage nsPackage) {
		final Optional<NsdPackageNsdPackage> oNsd = nsPackage.getNestedNsdInfoIds().stream().filter(x -> x.getToscaName().equals(name)).findFirst();
		if (oNsd.isPresent()) {
			oNsd.get().addVirtualLink(name);
			return;
		}
		final Optional<NsSap> oSap = nsPackage.getNsSaps().stream().filter(x -> x.getToscaName().equals(name)).findFirst();
		if (oSap.isPresent()) {
			oSap.get().addVirtualLink(name);
			return;
		}
		final Optional<NsdPackageVnfPackage> oVnfPkg = nsPackage.getVnfPkgIds().stream().filter(x -> x.getToscaName().equals(name)).findFirst();
		if (oVnfPkg.isPresent()) {
			oVnfPkg.get().addVirtualLink(name);
			return;
		}
		final Optional<PnfDescriptor> oPnf = nsPackage.getPnfdInfoIds().stream().filter(x -> x.getPnfdName().equals(name)).findFirst();
		if (oPnf.isPresent()) {
			oPnf.get().addVirtualLink(name);
			return;
		}
		throw new GenericException("Could not find any reference on " + name);
	}

	private static NsVirtualLink findVl(final NsdPackage nsPackage, final String virtualLinkId) {
		return nsPackage.getNsVirtualLinks().stream().filter(x -> x.getToscaName().equals(virtualLinkId)).findFirst().orElseThrow(() -> new ToscaException("Could not find VL named: " + virtualLinkId));
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
		}
		if (part == 2) {
			return vnfPackageService.findByDescriptorIdAndSoftwareVersion(descriptorId, version);
		}
		if (part == 3) {
			return vnfPackageService.findByDescriptorIdFlavorIdVnfdVersion(descriptorId, flavor, version);
		}
		throw new GenericException("Unknown version " + part);
	}

	private Optional<VnfPackage> getVnfPackage(final String x) {
		final PackageVersion pv = new PackageVersion(x);
		return getVnfPackage(pv.getFlavorId(), pv.getName(), pv.getVersion());
	}
}
