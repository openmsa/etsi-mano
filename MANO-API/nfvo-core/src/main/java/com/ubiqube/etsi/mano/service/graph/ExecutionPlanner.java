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
package com.ubiqube.etsi.mano.service.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.ToscaEntity;
import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedDnsZone;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedMonitoring;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiationLevels;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmResourceChanges;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.DnsZoneTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.VirtualLinkUow;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;

@Service
public class ExecutionPlanner {

	private static final String MANO_VM = ".mano.vm.";

	private static final Logger LOG = LoggerFactory.getLogger(ExecutionPlanner.class);

	private final VnfPackageService vnfPackageService;

	private final VnfInstanceService vnfInstanceService;

	private final VduNamingStrategy vduNamingStrategy;

	public ExecutionPlanner(final VnfInstanceService _vnfInstanceService, final VnfPackageService _vnfPackageService, final VduNamingStrategy _vduNamingStrategy) {
		vnfInstanceService = _vnfInstanceService;
		vnfPackageService = _vnfPackageService;
		vduNamingStrategy = _vduNamingStrategy;
	}

	@NotNull
	public ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> plan(@NotNull final VnfLcmOpOccs vnfLcmOpOccs, @NotNull final VnfPackage vnfPackage, final ChangeType changeType) {
		final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g = GraphTools.createGraph();
		final MultiValueMap<String, UnitOfWork> vertex = buildVertex(g, vnfLcmOpOccs, vnfPackage, changeType);

		vnfLcmOpOccs.getResourceChanges().getDnsZones().forEach(x -> vertex.entrySet().stream()
				.flatMap(y -> y.getValue().stream())
				.filter(VirtualLinkUow.class::isInstance)
				.forEach(y -> GraphTools.addEdge(g, vertex.get("zone"), y)));
		// Connect LinkPort to VM
		vnfPackage.getVnfLinkPort().forEach(x -> {
			LOG.debug("LinkPort: {} -> {}", x.getVirtualLink(), x.getVirtualBinding());
			GraphTools.addEdge(g, vertex.get(x.getVirtualLink()), vertex.get(x.getVirtualBinding()));
		});

		// Connect Vdu
		vnfPackage.getVnfCompute().forEach(x -> {
			final Set<String> storages = x.getStorages();
			if (null != storages) {
				storages.forEach(y -> {
					LOG.debug("Storage link {} -> {}", y, x.getToscaName());
					GraphTools.addEdge(g, vertex.get(y), vertex.get(x.getToscaName()));
				});
			}
		});

		// XXX Ok to add to the plan, but only if we have a ExtCp from NS.
		vnfPackage.getVnfExtCp().forEach(x -> {
			LOG.debug("ExtCp_{}: {} -> {}", x.getToscaName(), x.getInternalVirtualLink(), x.getExternalVirtualLink());
			Optional.ofNullable(vertex.get(x.getToscaName()))
					.ifPresent(y -> GraphTools.addEdge(g, vertex.get(x.getInternalVirtualLink()), y));
		});
		final VnfInstantiatedCompute vnfInstantiedStart = new VnfInstantiatedCompute();
		vnfInstantiedStart.setChangeType(ChangeType.ADDED);
		vnfInstantiedStart.setVnfLcmOpOccs(vnfLcmOpOccs);
		vnfInstantiedStart.setStatus(InstantiationStatusType.NOT_STARTED);
		// Add start
		/*
		 * final UnitOfWork root = new StartUow(vnfInstantiedStart); g.addVertex(root);
		 * g.vertexSet().stream() .filter(key -> g.incomingEdgesOf(key).isEmpty())
		 * .forEach(key -> { if (key != root) { LOG.debug("Connecting root to {}",
		 * key.getName()); g.addEdge(root, key); } }); // And end Node final
		 * VnfInstantiatedCompute vnfInstantiedEnd = new VnfInstantiatedCompute();
		 * vnfInstantiedEnd.setChangeType(ChangeType.ADDED);
		 * vnfInstantiedEnd.setVnfLcmOpOccs(vnfLcmOpOccs);
		 * vnfInstantiedEnd.setStatus(InstantiationStatusType.NOT_STARTED); final
		 * UnitOfWork end = new EndUow(vnfInstantiedEnd); g.addVertex(end);
		 * g.vertexSet().stream() .filter(key -> g.outgoingEdgesOf(key).isEmpty())
		 * .forEach(key -> { if (key != end) { g.addEdge(key, end); } });
		 */
		return g;
	}

	private MultiValueMap<String, UnitOfWork> buildVertex(final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g, final VnfLcmOpOccs vnfLcmOpOccs, final VnfPackage vnfPackage, final ChangeType changeType) {
		final MultiValueMap<String, UnitOfWork> vertex = new LinkedMultiValueMap<>();
		/*
		 * vnfLcmOpOccs.getResourceChanges().getDnsZones().stream() .filter(x ->
		 * x.getChangeType() == changeType) .forEach(x -> { final UnitOfWork uow = new
		 * ZoneUow(x, x.getDomainName()); vertex.add("zone", uow); g.addVertex(uow); });
		 * 
		 * vnfLcmOpOccs.getResourceChanges().getAffectedVirtualLinks().stream()
		 * .filter(x -> x.getChangeType() == changeType) .forEach(x -> { final VnfVl
		 * vnfVl =
		 * vnfPackageService.findVirtualLnkById(x.getManoResourceId()).orElseThrow(() ->
		 * new NotFoundException("Unable to find Virtual Link resource " +
		 * x.getVduId())); final UnitOfWork uow = new VirtualLinkUow(x,
		 * vnfVl.getVlProfileEntity().getVirtualLinkProtocolData().iterator().next(),
		 * vnfVl.getToscaName()); addVertex(vertex, vnfVl, uow, g); });
		 * 
		 * vnfLcmOpOccs.getResourceChanges().getAffectedVirtualStorages().stream()
		 * .filter(x -> x.getChangeType() == changeType) .forEach(x -> { final
		 * VnfStorage vstorage =
		 * vnfPackageService.findVirtualStorageById(x.getManoResourceId()).orElseThrow((
		 * ) -> new NotFoundException("Unable to find Virtual Strorage resource " +
		 * x.getManoResourceId())); UnitOfWork uow; if
		 * ("BLOCK".equals(vstorage.getType())) { uow = new StorageUow(x, vstorage); }
		 * else { uow = new ObjectStorageUow(x, vstorage, vstorage.getToscaName()); }
		 * addVertex(vertex, vstorage, uow, g); });
		 * 
		 * vnfLcmOpOccs.getResourceChanges().getAffectedVnfcs().stream() .filter(x ->
		 * x.getChangeType() == changeType) .forEach(x -> { final VnfCompute compute =
		 * vnfPackageService.findComputeById(x.getVduId()).orElseThrow(() -> new
		 * NotFoundException("Unable to find Virtual Compute resource " +
		 * x.getVduId())); final UnitOfWork uow = new ComputeUow(x, compute,
		 * vnfPackageService.findVnfVirtualLinks(vnfPackage)); addVertex(vertex,
		 * compute, uow, g); });
		 * vnfLcmOpOccs.getResourceChanges().getAffectedExtCp().stream() .filter(x ->
		 * x.getChangeType() == changeType) .forEach(x -> { final VnfExtCp lextCp =
		 * vnfPackageService.findExtCpById(x.getVduId()).orElseThrow(() -> new
		 * NotFoundException("Unable to find ExtCp resource " + x.getManoResourceId()));
		 * final UnitOfWork uow = new VnfExtCpUow(x, lextCp); addVertex(vertex, lextCp,
		 * uow, g); });
		 */
		return vertex;
	}

	private static <U extends UnitOfWork> void addVertex(final MultiValueMap<String, U> vertex, final ToscaEntity entity, final U uow, final Graph<U, ConnectivityEdge<U>> graph) {
		vertex.add(entity.getToscaName(), uow);
		graph.addVertex(uow);
	}

	public static int getNumberOfInstance(final Set<VnfInstantiationLevels> vnfInstantiationLevels, final VnfCompute vnfCompute, final String instantiationLevel, final ScaleInfo myscaling) {
		if (null == instantiationLevel) {
			return Optional.ofNullable(vnfCompute.getInitialNumberOfInstance()).orElse(Integer.valueOf(1)).intValue();
		}
		// Get base level or 1 instance.
		final VduInstantiationLevel il = vnfCompute.getInstantiationLevel().stream()
				.filter(x -> x.getLevelName().equals(instantiationLevel))
				.findFirst()
				.orElse(new VduInstantiationLevel(1));
		final int base = il.getNumberOfInstances();
		// Get Delta per levels.
		final List<VnfComputeAspectDelta> vnfComputeAspectDeltas = new ArrayList<>();
		for (final VnfComputeAspectDelta vnfComputeAspectDelta : vnfCompute.getScalingAspectDeltas()) {
			final List<VnfInstantiationLevels> instLev = vnfInstantiationLevels.stream()
					.filter(y -> vnfComputeAspectDelta.getAspectName().equals(y.getScaleInfoName()))
					.collect(Collectors.toList());
			if (instLev.isEmpty()) {
				continue;
			}
			vnfComputeAspectDeltas.add(vnfComputeAspectDelta);
		}
		int cnt = 0;
		int apply = 0;
		// Apply delta.
		VnfComputeAspectDelta last = new VnfComputeAspectDelta("", "", 1, 1, 1, null);
		for (final VnfComputeAspectDelta vnfComputeAspectDelta : vnfComputeAspectDeltas) {
			if (vnfComputeAspectDelta.getLevel() <= myscaling.getScaleLevel()) {
				cnt += vnfComputeAspectDelta.getNumberOfInstances();
				last = vnfComputeAspectDelta;
				apply++;
			}
		}
		final int maxLevel = Math.min(myscaling.getScaleLevel(), last.getMaxScaleLevel());
		for (int i = apply; i < maxLevel; i++) {
			cnt += last.getNumberOfInstances();
		}
		return base + cnt;
	}

	public static Set<VnfInstantiationLevels> resolvLevelName(final String instantiationLevel, final int level, final Set<VnfInstantiationLevels> vnfInstantiationLevels) {
		return vnfInstantiationLevels.stream()
				.filter(x -> instantiationLevel.equals(x.getLevelName()))
				.filter(x -> x.getScaleInfoLevel() <= level)
				.sorted(Comparator.comparingInt(VnfInstantiationLevels::getScaleInfoLevel))
				.collect(Collectors.toSet());
	}

	public void makePrePlan2(final String instantiationLevelId, final VnfPackage vnfPakage, final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs, final Set<ScaleInfo> scaling) {
		final Blueprint plan = new Blueprint();
		plan.setVnfInstance(vnfInstance);
		if (lcmOpOccs.getOperation() == NsdChangeType.INSTANTIATE) {
			final DnsZoneTask dnsZoneTask = new DnsZoneTask();
			dnsZoneTask.setChangeType(ChangeType.ADDED);
			dnsZoneTask.setDomainName(vnfInstance.getId() + MANO_VM);
			plan.add(dnsZoneTask);
		}
		vnfPakage.getVnfCompute().forEach(x -> {

		});
	}

	public void makePrePlan(final String instantiationLevelId, final VnfPackage vnfPakage, final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs, final Set<ScaleInfo> scaling) {
		if (lcmOpOccs.getOperation() == NsdChangeType.INSTANTIATE) {
			// Create DNS
			final VnfInstantiatedDnsZone aVs = new VnfInstantiatedDnsZone();
			aVs.setChangeType(ChangeType.ADDED);
			aVs.setResourceProviderId("MANO-DNS");
			aVs.setVnfLcmOpOccs(lcmOpOccs);
			aVs.setDomainName(vnfInstance.getId() + MANO_VM);
			aVs.setAliasName("dns-zone");
			aVs.setToscaName("dns-zone");
			lcmOpOccs.getResourceChanges().addAffectedDnsZone(aVs);
		}
		// instantiationLevelId is aspectId
		vnfPakage.getVnfCompute().forEach(x -> {
			Set<VnfInstantiationLevels> instantiationLevels = vnfPakage.getVnfInstantiationLevels();
			if (null != instantiationLevelId) {
				// Get Instantiation levels or baseLine levels..
				instantiationLevels = resolvLevelName(instantiationLevelId, 0, vnfPakage.getVnfInstantiationLevels());
				// filter using tis vnfc.
				instantiationLevels = instantiationLevels.stream()
						.filter(y -> match(x, y))
						.collect(Collectors.toSet());
			}
			// Filter myScaling.
			ScaleInfo myscaling = new ScaleInfo("whatEver", 0);
			if (null != scaling) {
				final Set<ScaleInfo> myscalings = scaling.stream()
						.filter(y -> match(x, y))
						.collect(Collectors.toSet());
				if (myscalings.size() > 1) {
					throw new GenericException("VDU " + x.getToscaName() + " have multiple scalings.");
				} else if (!myscalings.isEmpty()) {
					myscaling = myscalings.iterator().next();
				}
			}
			if (!instantiationLevels.isEmpty()) {
				final int currentInst = vnfInstanceService.getNumberOfLiveInstance(vnfInstance, x);
				final int wantedNumInst = getNumberOfInstance(instantiationLevels, x, instantiationLevelId, myscaling);
				LOG.info("{}: Actual currentInst={} wantedInst={}", x.getToscaName(), currentInst, wantedNumInst);

				if (currentInst < wantedNumInst) {
					addVnfComputeInstance(lcmOpOccs, x, vnfPakage, null, currentInst, wantedNumInst - currentInst);
				} else if (currentInst > wantedNumInst) {
					removeVnfComputeInstance(lcmOpOccs, vnfInstance, x, null, currentInst - wantedNumInst);
				}
			} else {
				LOG.warn("Node {} Doesn't have scale information.", x.getToscaName());
				final int currentInst = vnfInstanceService.getNumberOfLiveInstance(vnfInstance, x);
				if (currentInst == 0) {
					addVnfComputeInstance(lcmOpOccs, x, vnfPakage, null, currentInst, 1);
				}

			}
		});
		vnfPakage.getVnfVl().forEach(x -> {
			final int num = vnfInstanceService.getNumberOfLiveVl(vnfInstance, x);
			if (num == 0) {
				copyInstantiatedToLcm(lcmOpOccs, x, VnfInstantiatedVirtualLink::new, VnfLcmResourceChanges::addAffectedVirtualLink);
			}
		});
		vnfPakage.getVnfStorage().stream().forEach(x -> {
			final int num = vnfInstanceService.getNumberOfLiveStorage(vnfInstance, x);
			if (num == 0) {
				copyInstantiatedToLcm(lcmOpOccs, x, VnfInstantiatedStorage::new, VnfLcmResourceChanges::addAffectedVirtualStorage);
			}
		});
		// No ExtCp when spawning.
		vnfPakage.getVnfExtCp().stream().forEach(x -> {
			final int num = vnfInstanceService.getNumberOfLiveExtCp(vnfInstance, x);
			if (num == 0) {
				copyInstantiatedToLcm(lcmOpOccs, x, VnfInstantiatedExtCp::new, VnfLcmResourceChanges::addAffectedExtCp);
			}
		});
	}

	private <U extends ToscaEntity, V extends VnfInstantiatedBase> void copyInstantiatedToLcm(final VnfLcmOpOccs lcmOpOccs, final U u, final Supplier<V> newInstance, final BiConsumer<VnfLcmResourceChanges, V> func) {
		final V aVs = createInstantiated(newInstance.get(), u, lcmOpOccs);
		func.accept(lcmOpOccs.getResourceChanges(), aVs);
	}

	private <U extends VnfInstantiatedBase> U createInstantiated(final U aVs, final ToscaEntity x, final VnfLcmOpOccs lcmOpOccs) {
		aVs.setChangeType(ChangeType.ADDED);
		// XXX it's not a Vdu il.
		aVs.setInstantiationLevel(null);
		aVs.setVduId(x.getId());
		aVs.setVnfLcmOpOccs(lcmOpOccs);
		aVs.setAliasName(vduNamingStrategy.nameVdu(lcmOpOccs, x.getToscaName(), 0));
		aVs.setToscaName(x.getToscaName());
		aVs.setManoResourceId(x.getId());
		return aVs;
	}

	private static boolean match(final VnfCompute vnfCompute, final VnfInstantiationLevels vil) {
		if (null == vnfCompute.getScalingAspectDeltas()) {
			return false;
		}
		return !vnfCompute.getScalingAspectDeltas().stream()
				.filter(x -> x.getAspectName().equals(vil.getScaleInfoName()))
				.collect(Collectors.toList()).isEmpty();
	}

	private static boolean match(final VnfCompute vnfCompute, final ScaleInfo scaleInfo) {
		return !vnfCompute.getScalingAspectDeltas().stream()
				.filter(x -> x.getAspectName().equals(scaleInfo.getAspectId()))
				.collect(Collectors.toList()).isEmpty();
	}

	private void addVnfComputeInstance(final VnfLcmOpOccs lcmOpOccs, final VnfCompute vnfCompute, final VnfPackage vnfPackage, final VduInstantiationLevel scaleLevel, final int currentCount, final int number) {
		for (int i = 0; i < number; i++) {
			final VnfInstantiatedCompute vnfInstantiedCompute = new VnfInstantiatedCompute();
			vnfInstantiedCompute.setChangeType(ChangeType.ADDED);
			vnfInstantiedCompute.setVduId(vnfCompute.getId());
			vnfInstantiedCompute.setManoResourceId(vnfCompute.getId());
			vnfInstantiedCompute.setVnfLcmOpOccs(lcmOpOccs);
			vnfInstantiedCompute.setInstantiationLevel(scaleLevel);
			vnfInstantiedCompute.setAliasName(vduNamingStrategy.nameVdu(lcmOpOccs, vnfCompute.getToscaName(), currentCount + i));
			vnfInstantiedCompute.setToscaName(vnfCompute.getToscaName());
			// final VnfInstantiedCompute savedVnfInstantiedCompute =
			lcmOpOccs.getResourceChanges().addAffectedVnfcs(vnfInstantiedCompute);
			if ((null != vnfCompute.getMonitoringParameters()) && !vnfCompute.getMonitoringParameters().isEmpty()) {
				final VnfInstantiatedMonitoring instanceMonitor = new VnfInstantiatedMonitoring();
				instanceMonitor.setChangeType(ChangeType.ADDED);
				instanceMonitor.setVnfLcmOpOccs(lcmOpOccs);
				instanceMonitor.setManoResourceId(vnfCompute.getId());
				instanceMonitor.setInstantiationLevel(scaleLevel);
				instanceMonitor.setStatus(InstantiationStatusType.NOT_STARTED);
				lcmOpOccs.getResourceChanges().addAffectedMonitoring(instanceMonitor);
			}
			final int ci = i;
			vnfCompute.getStorages().forEach(y -> {
				// XX Add Storage.
				vnfInstantiedCompute.getAddedStorageResourceIds().add(y);
				vnfInstantiedCompute.setInstantiationLevel(scaleLevel);
				final VnfStorage storage = vnfPackageService.findStorageByName(vnfPackage, y)
						.orElseThrow(() -> new NotFoundException("could not find: " + y));
				final VnfInstantiatedStorage vs = new VnfInstantiatedStorage();
				vs.setInstantiationLevel(scaleLevel);
				vs.setChangeType(ChangeType.ADDED);
				vs.setManoResourceId(storage.getId());
				vs.setAliasName(vduNamingStrategy.nameVdu(lcmOpOccs, vnfCompute.getToscaName() + "-storage", currentCount + ci));

				lcmOpOccs.getResourceChanges().addAffectedVirtualStorage(vs);
			});
		}
	}

	private void removeVnfComputeInstance(final VnfLcmOpOccs lcmOpOccs, final VnfInstance vnfInstance, final VnfCompute x, final VduInstantiationLevel scaleLevel, final int number) {
		final Deque<VnfLiveInstance> instantiated = vnfInstanceService.getLiveComputeInstanceOf(vnfInstance, x);
		for (int i = 0; i < number; i++) {
			final VnfInstantiatedCompute instantiatedCompute = new VnfInstantiatedCompute();
			instantiatedCompute.setChangeType(ChangeType.REMOVED);
			instantiatedCompute.setStatus(InstantiationStatusType.NOT_STARTED);
			instantiatedCompute.setVduId(x.getId());
			final VnfLiveInstance poped = instantiated.pop();
			LOG.info("Removing VNF Compute instance {}", poped.getId());
			instantiatedCompute.setRemovedInstantiated(poped.getId());
			instantiatedCompute.setResourceId(poped.getResourceId());
			instantiatedCompute.setInstantiationLevel(scaleLevel);
			instantiatedCompute.setVnfLcmOpOccs(lcmOpOccs);
			instantiatedCompute.setManoResourceId(x.getId());
			instantiatedCompute.setToscaName(x.getToscaName());
			lcmOpOccs.getResourceChanges().addAffectedVnfcs(instantiatedCompute);
			x.getStorages().forEach(y -> {
				// XXX Delete Storage, but we need a vdu.
			});
		}
	}

	public void terminatePlan(final VnfLcmOpOccs lcmOpOccs, final VnfInstance vnfInstance) {
		final List<VnfInstantiatedDnsZone> instantiatedDnsZone = vnfInstanceService.getLiveDnsZoneInstanceOf(vnfInstance);
		instantiatedDnsZone.forEach(x -> {
			final VnfInstantiatedDnsZone affectedCompute = copyInstantiedResource(x, new VnfInstantiatedDnsZone(), lcmOpOccs);
			affectedCompute.setDomainName(vnfInstance.getId() + MANO_VM);
			affectedCompute.setAliasName("dns-zone");
			affectedCompute.setToscaName("dns-zone");
			lcmOpOccs.getResourceChanges().addAffectedDnsZone(affectedCompute);
		});
		final List<VnfInstantiatedCompute> instantiatedCompute = vnfInstanceService.getLiveComputeInstanceOf(vnfInstance);
		addAllToResourceChange(lcmOpOccs, instantiatedCompute, VnfInstantiatedCompute::new, VnfLcmResourceChanges::addAffectedVnfcs);

		final List<VnfInstantiatedExtCp> instantiatedExtCps = vnfInstanceService.getLiveExtCpInstanceOf(vnfInstance);
		addAllToResourceChange(lcmOpOccs, instantiatedExtCps, VnfInstantiatedExtCp::new, VnfLcmResourceChanges::addAffectedExtCp);

		final List<VnfInstantiatedStorage> instantiatedStorages = vnfInstanceService.getLiveStorageInstanceOf(vnfInstance);
		addAllToResourceChange(lcmOpOccs, instantiatedStorages, VnfInstantiatedStorage::new, VnfLcmResourceChanges::addAffectedVirtualStorage);

		final List<VnfInstantiatedVirtualLink> instantiatedVirtualLinks = vnfInstanceService.getLiveVirtualLinkInstanceOf(vnfInstance);
		addAllToResourceChange(lcmOpOccs, instantiatedVirtualLinks, VnfInstantiatedVirtualLink::new, VnfLcmResourceChanges::addAffectedVirtualLink);
	}

	private <U extends VnfInstantiatedBase> void addAllToResourceChange(final VnfLcmOpOccs lcmOpOccs, final List<U> u, final Supplier<U> newInstance, final BiConsumer<VnfLcmResourceChanges, U> func) {
		u.forEach(x -> {
			copyInstantiatedToLcm(lcmOpOccs, x, newInstance, func);
		});
	}

	private <U extends VnfInstantiatedBase> void copyInstantiatedToLcm(final VnfLcmOpOccs lcmOpOccs, final VnfInstantiatedBase source, final Supplier<U> newInstance, final BiConsumer<VnfLcmResourceChanges, U> func) {
		final U affectedVirtualLink = copyInstantiedResource(source, newInstance.get(), lcmOpOccs);
		func.accept(lcmOpOccs.getResourceChanges(), affectedVirtualLink);
	}

	private <T extends VnfInstantiatedBase> T copyInstantiedResource(final VnfInstantiatedBase source, @Nonnull final T inst, final VnfLcmOpOccs lcmOpOccs) {
		inst.setChangeType(ChangeType.REMOVED);
		inst.setStatus(InstantiationStatusType.STARTED);
		inst.setVduId(source.getVduId());
		inst.setRemovedInstantiated(source.getId());
		inst.setResourceId(source.getResourceId());
		inst.setInstantiationLevel(source.getInstantiationLevel());
		inst.setVnfLcmOpOccs(lcmOpOccs);
		inst.setManoResourceId(source.getManoResourceId());
		final VnfLiveInstance vnfLiveInstance = vnfInstanceService.findLiveInstanceByInstantiated(source.getId());
		inst.setRemovedInstantiated(vnfLiveInstance.getId());
		return inst;
	}

	public ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> planForRemoval(@Nonnull final VnfLcmOpOccs localLcmOpOccs, @Nonnull final VnfPackage vnfPkg) {
		final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> plan = plan(localLcmOpOccs, vnfPkg, ChangeType.REMOVED);
		return GraphTools.revert(plan);
	}

	public ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> planForCreation(@Nonnull final VnfLcmOpOccs localLcmOpOccs, @Nonnull final VnfPackage vnfPkg) {
		return plan(localLcmOpOccs, vnfPkg, ChangeType.ADDED);
	}
}
