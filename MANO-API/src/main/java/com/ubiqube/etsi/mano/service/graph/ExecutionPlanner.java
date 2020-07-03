package com.ubiqube.etsi.mano.service.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

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
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
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
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.vnfm.ComputeUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.EndUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.ObjectStorageUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.StartUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.StorageUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.VirtualLinkUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.VnfExtCpUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.ZoneUow;

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
		final UnitOfWork root = new StartUow(vnfInstantiedStart);
		g.addVertex(root);
		g.vertexSet().stream()
				.filter(key -> g.incomingEdgesOf(key).isEmpty())
				.forEach(key -> {
					if (key != root) {
						LOG.debug("Connecting root to {}", key.getName());
						g.addEdge(root, key);
					}
				});
		// And end Node
		final VnfInstantiatedCompute vnfInstantiedEnd = new VnfInstantiatedCompute();
		vnfInstantiedEnd.setChangeType(ChangeType.ADDED);
		vnfInstantiedEnd.setVnfLcmOpOccs(vnfLcmOpOccs);
		vnfInstantiedEnd.setStatus(InstantiationStatusType.NOT_STARTED);
		final UnitOfWork end = new EndUow(vnfInstantiedEnd);
		g.addVertex(end);
		g.vertexSet().stream()
				.filter(key -> g.outgoingEdgesOf(key).isEmpty())
				.forEach(key -> {
					if (key != end) {
						g.addEdge(key, end);
					}
				});
		return g;
	}

	private MultiValueMap<String, UnitOfWork> buildVertex(final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g, final VnfLcmOpOccs vnfLcmOpOccs, final VnfPackage vnfPackage, final ChangeType changeType) {
		final MultiValueMap<String, UnitOfWork> vertex = new LinkedMultiValueMap<>();
		vnfLcmOpOccs.getResourceChanges().getDnsZones().stream()
				.filter(x -> x.getChangeType() == changeType)
				.forEach(x -> {
					final UnitOfWork uow = new ZoneUow(x, x.getDomainName());
					vertex.add("zone", uow);
					g.addVertex(uow);
				});

		vnfLcmOpOccs.getResourceChanges().getAffectedVirtualLinks().stream()
				.filter(x -> x.getChangeType() == changeType)
				.forEach(x -> {
					final VnfVl vlProtocol = vnfPackageService.findVirtualLnkById(x.getVnfVirtualLink().getId()).orElseThrow(() -> new NotFoundException("Unable to find Virtual Link resource " + x.getVduId()));
					final UnitOfWork uow = new VirtualLinkUow(x, vlProtocol.getVlProfileEntity().getVirtualLinkProtocolData().iterator().next(), vlProtocol.getToscaName(), vnfLcmOpOccs.getVnfInstance().getId() + MANO_VM);
					vertex.add(vlProtocol.getToscaName(), uow);
					g.addVertex(uow);
				});

		vnfLcmOpOccs.getResourceChanges().getAffectedVirtualStorages().stream()
				.filter(x -> x.getChangeType() == changeType)
				.forEach(x -> {
					final VnfStorage vstorage = vnfPackageService.findVirtualStorageById(x.getVnfVirtualStorage().getId()).orElseThrow(() -> new NotFoundException("Unable to find Virtual Strorage resource " + x.getVnfVirtualStorage().getId()));
					UnitOfWork uow;
					if ("BLOCK".equals(vstorage.getType())) {
						uow = new StorageUow(x, vstorage);
					} else {
						uow = new ObjectStorageUow(x, vstorage, vstorage.getToscaName());
					}
					vertex.add(vstorage.getToscaName(), uow);
					g.addVertex(uow);
				});

		vnfLcmOpOccs.getResourceChanges().getAffectedVnfcs().stream()
				.filter(x -> x.getChangeType() == changeType)
				.forEach(x -> {
					final VnfCompute compute = vnfPackageService.findComputeById(x.getVduId()).orElseThrow(() -> new NotFoundException("Unable to find Virtual Compute resource " + x.getVduId()));
					final UnitOfWork uow = new ComputeUow(x, compute, vnfPackageService.findVnfVirtualLinks(vnfPackage));
					vertex.add(compute.getToscaName(), uow);
					g.addVertex(uow);
				});
		final Set<VnfInstantiatedExtCp> extCp = vnfLcmOpOccs.getResourceChanges().getAffectedExtCp();
		extCp.stream()
				.filter(x -> x.getChangeType() == changeType)
				.forEach(x -> {
					final VnfExtCp lextCp = vnfPackageService.findExtCpById(x.getVduId()).orElseThrow(() -> new NotFoundException("Unable to find ExtCp resource " + x.getVnfExtCp().getId()));
					final UnitOfWork uow = new VnfExtCpUow(x, lextCp);
					vertex.add(lextCp.getToscaName(), uow);
					g.addVertex(uow);
				});
		return vertex;
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
			// XXX They should scale.
			final int num = vnfInstanceService.getNumberOfLiveVl(vnfInstance, x);
			if (num == 0) {
				final VnfInstantiatedVirtualLink aVl = createInstantiated(new VnfInstantiatedVirtualLink(), x, lcmOpOccs);
				aVl.setVnfVirtualLink(x);
				lcmOpOccs.getResourceChanges().addAffectedVirtualLink(aVl);
			}
		});
		vnfPakage.getVnfStorage().stream().forEach(x -> {
			final int num = vnfInstanceService.getNumberOfLiveStorage(vnfInstance, x);
			if (num == 0) {
				final VnfInstantiatedStorage aVs = createInstantiated(new VnfInstantiatedStorage(), x, lcmOpOccs);
				aVs.setVnfVirtualStorage(x);
				lcmOpOccs.getResourceChanges().addAffectedVirtualStorage(aVs);
			}
		});
		// No ExtCp when spawning.
		vnfPakage.getVnfExtCp().stream().forEach(x -> {
			final int num = vnfInstanceService.getNumberOfLiveExtCp(vnfInstance, x);
			if (num == 0) {
				final VnfInstantiatedExtCp aVs = createInstantiated(new VnfInstantiatedExtCp(), x, lcmOpOccs);
				aVs.setVnfExtCp(x);
				lcmOpOccs.getResourceChanges().addAffectedExtCp(aVs);
			}
		});
	}

	private <U extends VnfInstantiatedBase> U createInstantiated(final U aVs, final ToscaEntity x, final VnfLcmOpOccs lcmOpOccs) {
		aVs.setChangeType(ChangeType.ADDED);
		// XXX it's not a Vdu il.
		aVs.setInstantiationLevel(null);
		aVs.setVduId(x.getId());
		aVs.setVnfLcmOpOccs(lcmOpOccs);
		aVs.setAliasName(vduNamingStrategy.nameVdu(lcmOpOccs, x.getToscaName(), 0));
		aVs.setToscaName(x.getToscaName());
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
			vnfInstantiedCompute.setVnfCompute(vnfCompute);
			vnfInstantiedCompute.setVnfLcmOpOccs(lcmOpOccs);
			vnfInstantiedCompute.setInstantiationLevel(scaleLevel);
			vnfInstantiedCompute.setAliasName(vduNamingStrategy.nameVdu(lcmOpOccs, vnfCompute.getToscaName(), currentCount + i));
			vnfInstantiedCompute.setToscaName(vnfCompute.getToscaName());
			// final VnfInstantiedCompute savedVnfInstantiedCompute =
			lcmOpOccs.getResourceChanges().addAffectedVnfcs(vnfInstantiedCompute);
			if ((null != vnfCompute.getMonitoringParameters()) && !vnfCompute.getMonitoringParameters().isEmpty()) {
				final VnfInstantiatedMonitoring instanceMonotor = new VnfInstantiatedMonitoring();
				instanceMonotor.setChangeType(ChangeType.ADDED);
				instanceMonotor.setVnfLcmOpOccs(lcmOpOccs);
				instanceMonotor.setVnfCompute(vnfCompute);
				instanceMonotor.setInstantiationLevel(scaleLevel);
				instanceMonotor.setStatus(InstantiationStatusType.NOT_STARTED);
				instanceMonotor.setVnfInstantiatedCompute(vnfInstantiedCompute);
				lcmOpOccs.getResourceChanges().addAffectedMonitoring(instanceMonotor);
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
				vs.setVnfVirtualStorage(storage);
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
			instantiatedCompute.setVnfCompute(x);
			instantiatedCompute.setToscaName(x.getToscaName());
			lcmOpOccs.getResourceChanges().addAffectedVnfcs(instantiatedCompute);
			x.getStorages().forEach(y -> {
				// XXX Delete Storage, but we need a vdu.
			});
		}
	}

	public void terminatePlan(final VnfLcmOpOccs lcmOpOccs, final VnfInstance vnfInstance) {
		final List<VnfInstantiatedDnsZone> instantiatedDnsZone = vnfInstanceService.getLiveDnsZoneInstanceOf(lcmOpOccs.getVnfInstance());
		instantiatedDnsZone.forEach(x -> {
			final VnfInstantiatedDnsZone affectedCompute = copyInstantiedResource(x, new VnfInstantiatedDnsZone(), lcmOpOccs);
			affectedCompute.setVnfCompute(x.getVnfCompute());
			affectedCompute.setDomainName(vnfInstance.getId() + MANO_VM);
			affectedCompute.setAliasName("dns-zone");
			affectedCompute.setToscaName("dns-zone");
			final VnfLiveInstance vnfLiveInstance = vnfInstanceService.findLiveInstanceByInstantiated(x.getId());
			affectedCompute.setRemovedInstantiated(vnfLiveInstance.getId());
			lcmOpOccs.getResourceChanges().addAffectedDnsZone(affectedCompute);
		});
		final List<VnfInstantiatedCompute> instantiatedCompute = vnfInstanceService.getLiveComputeInstanceOf(lcmOpOccs.getVnfInstance());
		instantiatedCompute.forEach(x -> {
			final VnfInstantiatedCompute affectedCompute = copyInstantiedResource(x, new VnfInstantiatedCompute(), lcmOpOccs);
			affectedCompute.setVnfCompute(x.getVnfCompute());
			final VnfLiveInstance vnfLiveInstance = vnfInstanceService.findLiveInstanceByInstantiated(x.getId());
			affectedCompute.setRemovedInstantiated(vnfLiveInstance.getId());
			lcmOpOccs.getResourceChanges().addAffectedVnfcs(affectedCompute);
		});
		final List<VnfInstantiatedExtCp> instantiatedExtCps = vnfInstanceService.getLiveExtCpInstanceOf(lcmOpOccs.getVnfInstance());
		instantiatedExtCps.forEach(x -> {
			final VnfInstantiatedExtCp affectedCompute = copyInstantiedResource(x, new VnfInstantiatedExtCp(), lcmOpOccs);
			affectedCompute.setVnfExtCp(x.getVnfExtCp());
			final VnfLiveInstance vnfLiveInstance = vnfInstanceService.findLiveInstanceByInstantiated(x.getId());
			affectedCompute.setRemovedInstantiated(vnfLiveInstance.getId());
			lcmOpOccs.getResourceChanges().addAffectedExtCp(affectedCompute);
		});
		final List<VnfInstantiatedStorage> instantiatedStorages = vnfInstanceService.getLiveStorageInstanceOf(lcmOpOccs.getVnfInstance());
		instantiatedStorages.forEach(x -> {
			final VnfInstantiatedStorage affectedStorage = copyInstantiedResource(x, new VnfInstantiatedStorage(), lcmOpOccs);
			affectedStorage.setVnfVirtualStorage(x.getVnfVirtualStorage());
			final VnfLiveInstance vnfLiveInstance = vnfInstanceService.findLiveInstanceByInstantiated(x.getId());
			affectedStorage.setRemovedInstantiated(vnfLiveInstance.getId());
			lcmOpOccs.getResourceChanges().addAffectedVirtualStorage(affectedStorage);
		});
		final List<VnfInstantiatedVirtualLink> instantiatedVirtualLinks = vnfInstanceService.getLiveVirtualLinkInstanceOf(lcmOpOccs.getVnfInstance());
		instantiatedVirtualLinks.forEach(x -> {
			final VnfInstantiatedVirtualLink affectedVirtualLink = copyInstantiedResource(x, new VnfInstantiatedVirtualLink(), lcmOpOccs);
			affectedVirtualLink.setVnfVirtualLink(x.getVnfVirtualLink());
			final VnfLiveInstance vnfLiveInstance = vnfInstanceService.findLiveInstanceByInstantiated(x.getId());
			affectedVirtualLink.setRemovedInstantiated(vnfLiveInstance.getId());
			lcmOpOccs.getResourceChanges().addAffectedVirtualLink(affectedVirtualLink);
		});
	}

	private static <T extends VnfInstantiatedBase> T copyInstantiedResource(final VnfInstantiatedBase source, final T inst, final VnfLcmOpOccs lcmOpOccs) {
		inst.setChangeType(ChangeType.REMOVED);
		inst.setStatus(InstantiationStatusType.STARTED);
		inst.setVduId(source.getVduId());
		inst.setRemovedInstantiated(source.getId());
		inst.setResourceId(source.getResourceId());
		inst.setInstantiationLevel(source.getInstantiationLevel());
		inst.setVnfLcmOpOccs(lcmOpOccs);
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
