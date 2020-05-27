package com.ubiqube.etsi.mano.service.graph;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.nio.dot.DOTExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedMonitoring;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiationLevels;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.NsInstanceService;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfPackageService;

@Service
public class ExecutionPlanner {

	private static final Logger LOG = LoggerFactory.getLogger(ExecutionPlanner.class);

	private final VnfPackageRepository vnfPackageRepository;

	private final VnfPackageService vnfPackageService;

	private final VnfInstanceService vnfInstanceService;

	private final VduNamingStrategy vduNamingStrategy;

	private final NsInstanceService nsInstanceService;

	public ExecutionPlanner(final VnfPackageRepository _vnfPackageRepository, final VnfInstanceService _vnfInstanceService, final VnfPackageService _vnfPackageService, final VduNamingStrategy _vduNamingStrategy, final NsInstanceService _nsInstanceService) {
		vnfPackageRepository = _vnfPackageRepository;
		vnfInstanceService = _vnfInstanceService;
		vnfPackageService = _vnfPackageService;
		vduNamingStrategy = _vduNamingStrategy;
		nsInstanceService = _nsInstanceService;
	}

	private static ListenableGraph<UnitOfWork, ConnectivityEdge> createGraph() {
		// Vertex everyThing
		final ListenableGraph<UnitOfWork, ConnectivityEdge> g = new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(ConnectivityEdge.class));
		g.addGraphListener(new EdgeListener<UnitOfWork>());
		return g;
	}

	@NotNull
	public ListenableGraph<UnitOfWork, ConnectivityEdge> plan(@NotNull final VnfLcmOpOccs vnfLcmOpOccs, @NotNull final VnfPackage vnfPackage) {
		final ListenableGraph<UnitOfWork, ConnectivityEdge> g = createGraph();
		final MultiValueMap<String, UnitOfWork> vertex = buildVertex(g, vnfLcmOpOccs);
		// Connect LinkPort to VM
		vnfPackage.getVnfLinkPort().forEach(x -> {
			LOG.debug("LinkPort: {} -> {}", x.getVirtualLink(), x.getVirtualBinding());
			addEdge(g, vertex.get(x.getVirtualLink()), vertex.get(x.getVirtualBinding()));
		});

		// Connect Vdu
		vnfPackage.getVnfCompute().forEach(x -> {
			final Set<String> storages = x.getStorages();
			if (null != storages) {
				storages.forEach(y -> {
					LOG.debug("Storage link {} -> {}", y, x.getToscaName());
					addEdge(g, vertex.get(y), vertex.get(x.getToscaName()));
				});
			}
			// XXX do the same for swImages ?
			if ((null != x.getMonitoringParameters()) && !x.getMonitoringParameters().isEmpty()) {
				// XXX Move this.
				final VnfInstantiatedCompute instanceMonotor = new VnfInstantiatedCompute();
				instanceMonotor.setChangeType(ChangeType.ADDED);
				instanceMonotor.setVnfLcmOpOccs(vnfLcmOpOccs);
				instanceMonotor.setVnfCompute(x);
				instanceMonotor.setStatus(InstantiationStatusType.NOT_STARTED);
				final UnitOfWork uow = new MonitoringUow(instanceMonotor, x, makeUowMonitoringName(x));
				vertex.add(makeUowMonitoringName(x), uow);
				g.addVertex(uow);
				LOG.debug("Monitoring: {} -> {}", x.getToscaName(), uow.getName());
				addEdge(g, vertex.get(x.getToscaName()), Arrays.asList(uow));
			}
		});

		// XXX Ok to add to the plan, but only if we have a ExtCp from NS.
		vnfPackage.getVnfExtCp().forEach(x -> {
			LOG.debug("ExtCp_{}: {} -> {}", x.getToscaName(), x.getInternalVirtualLink(), x.getExternalVirtualLink());
			Optional.ofNullable(vertex.get(x.getToscaName()))
					.ifPresent(y -> addEdge(g, vertex.get(x.getInternalVirtualLink()), y));
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

	private static void addEdge(final ListenableGraph<UnitOfWork, ConnectivityEdge> g, final List<UnitOfWork> left, final List<UnitOfWork> right) {
		if ((null == left) || (null == right)) {
			LOG.debug("One or more end point are not in the plan {} <-> {}", left, right);
			return;
		}
		left.forEach(x -> right.forEach(y -> {
			LOG.info("  - Adding {} <-> {}", x, y);
			g.addEdge(x, y);
		}));
	}

	private MultiValueMap<String, UnitOfWork> buildVertex(final ListenableGraph<UnitOfWork, ConnectivityEdge> g, final VnfLcmOpOccs vnfLcmOpOccs) {
		final MultiValueMap<String, UnitOfWork> vertex = new LinkedMultiValueMap<>();

		vnfLcmOpOccs.getResourceChanges().getAffectedVirtualLinks().forEach(x -> {
			final VnfVl vlProtocol = vnfPackageService.findVirtualLnkById(x.getVnfVirtualLink().getId()).orElseThrow(() -> new NotFoundException("Unable to find Virtual Link resource " + x.getVduId()));
			final UnitOfWork uow = new VirtualLinkUow(x, vlProtocol.getVlProfileEntity().getVirtualLinkProtocolData().iterator().next(), vlProtocol.getToscaName());
			vertex.add(vlProtocol.getToscaName(), uow);
			g.addVertex(uow);
		});

		vnfLcmOpOccs.getResourceChanges().getAffectedVirtualStorages().forEach(x -> {
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

		vnfLcmOpOccs.getResourceChanges().getAffectedVnfcs().forEach(x -> {
			final VnfCompute compute = vnfPackageService.findComputeById(x.getVduId()).orElseThrow(() -> new NotFoundException("Unable to find Virtual Compute resource " + x.getVduId()));
			final UnitOfWork uow = new ComputeUow(x, compute);
			vertex.add(compute.getToscaName(), uow);
			g.addVertex(uow);
		});
		final Set<VnfInstantiatedExtCp> extCp = vnfLcmOpOccs.getResourceChanges().getAffectedExtCp();
		extCp.forEach(x -> {
			final VnfExtCp lextCp = vnfPackageService.findExtCpById(x.getVduId()).orElseThrow(() -> new NotFoundException("Unable to find ExtCp resource " + x.getVnfExtCp().getId()));
			final UnitOfWork uow = new VnfExtCpUow(x, lextCp);
			vertex.add(lextCp.getToscaName(), uow);
			g.addVertex(uow);
		});
		return vertex;
	}

	public void exportGraph(final ListenableGraph<UnitOfWork, ConnectivityEdge> g, @Nonnull final UUID _id, final VnfInstance vnfInstance, final String subName) {
		final DOTExporter<UnitOfWork, ConnectivityEdge> exporter = new DOTExporter<>(UnitOfWork::getName);
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		exporter.exportGraph(g, out);
		final byte[] res = out.toByteArray();
		final InputStream _stream = new ByteArrayInputStream(res);
		vnfPackageRepository.storeBinary(_id, subName + "-" + vnfInstance.getId() + ".dot", _stream);
	}

	private static String makeUowMonitoringName(final VnfCompute x) {
		return x.getToscaName() + "MON";
	}

	public ListenableGraph<UnitOfWork, ConnectivityEdge> revert(final ListenableGraph<UnitOfWork, ConnectivityEdge> g) {
		final ListenableGraph<UnitOfWork, ConnectivityEdge> gNew = createGraph();
		g.vertexSet().forEach(gNew::addVertex);
		g.edgeSet().forEach(x -> gNew.addEdge(x.getTarget(), x.getSource()));
		return gNew;
	}

	public void makePrePlan(final String instantiationLevelId, final VnfPackage vnfPakage, final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs) {
		// instantiationLevelId is aspectId
		final List<VnfInstantiationLevels> instantiationLevels = vnfPakage.getVnfInstantiationLevels().stream()
				.filter(x -> x.getLevelName().equals(instantiationLevelId))
				.collect(Collectors.toList());
		instantiationLevels.forEach(instantiationLevel -> {
			LOG.info("Pre-Planning {}", instantiationLevel.getScaleInfoName());
			vnfPakage.getVnfCompute().forEach(x -> {
				final List<VnfComputeAspectDelta> res = vnfPackageService.findAspectDeltaByAspectId(x, instantiationLevel.getScaleInfoName());
				Integer numInst = null;
				if (!res.isEmpty()) {
					if (res.size() <= instantiationLevel.getScaleInfoLevel()) {
						numInst = res.get(instantiationLevel.getScaleInfoLevel() - 1).getNumberOfInstances();
					} else if (res.isEmpty()) {
						numInst = x.getInitialNumberOfInstance();
					} else {
						numInst = res.get(res.size() - 1).getNumberOfInstances();
					}
					final int wantedNumInst = numInst.intValue();
					final int currentInst = vnfInstanceService.getNumberOfLiveInstance(vnfInstance, x);
					final VduInstantiationLevel vduInstantiationLevel = vnfPackageService.findByVnfComputeAndInstantiationLevel(x, instantiationLevel.getScaleInfoName());
					if (currentInst < wantedNumInst) {
						addVnfComputeInstance(lcmOpOccs, x, vnfPakage, vduInstantiationLevel, currentInst, wantedNumInst - currentInst);
					} else if (currentInst > wantedNumInst) {
						removeVnfComputeInstance(lcmOpOccs, vnfInstance, x, vduInstantiationLevel, currentInst - wantedNumInst);
					}
				} else {
					LOG.debug("Unknown aspect {} for Vdu {} ", instantiationLevel.getScaleInfoName(), x.getToscaName());
				}
			});
		});
		vnfPakage.getVnfVl().forEach(x -> {
			// XXX They should scale.
			final int num = vnfInstanceService.getNumberOfLiveVl(vnfInstance, x);
			if (num == 0) {
				final VnfInstantiatedVirtualLink aVl = new VnfInstantiatedVirtualLink();
				aVl.setChangeType(ChangeType.ADDED);
				aVl.setVnfVirtualLink(x);
				// XXX it's not a Vdu il.
				aVl.setInstantiationLevel(null);
				aVl.setVnfLcmOpOccs(lcmOpOccs);
				aVl.setVduId(x.getId());
				aVl.setAliasName(vduNamingStrategy.nameVdu(lcmOpOccs, x.getToscaName(), 0));
				// aVl = vnfInstanceService.save(aVl);
				lcmOpOccs.getResourceChanges().addAffectedVirtualLink(aVl);
			}
		});
		vnfPakage.getVnfStorage().stream().forEach(x -> {
			final int num = vnfInstanceService.getNumberOfLiveStorage(vnfInstance, x);
			if (num == 0) {
				final VnfInstantiatedStorage aVs = new VnfInstantiatedStorage();
				aVs.setChangeType(ChangeType.ADDED);
				aVs.setVnfVirtualStorage(x);
				// XXX it's not a Vdu il.
				aVs.setInstantiationLevel(null);
				aVs.setVnfLcmOpOccs(lcmOpOccs);
				aVs.setVduId(x.getId());
				aVs.setAliasName(vduNamingStrategy.nameVdu(lcmOpOccs, x.getToscaName(), 0));
				lcmOpOccs.getResourceChanges().addAffectedVirtualStorage(aVs);
			}
		});
		// No ExtCp when spawning.
		vnfPakage.getVnfExtCp().stream().forEach(x -> {
			final int num = vnfInstanceService.getNumberOfLiveExtCp(vnfInstance, x);
			if (num == 0) {
				final VnfInstantiatedExtCp aVs = new VnfInstantiatedExtCp();
				aVs.setChangeType(ChangeType.ADDED);
				aVs.setVnfExtCp(x);
				// XXX it's not a Vdu il.
				aVs.setInstantiationLevel(null);
				aVs.setVduId(x.getId());
				aVs.setVnfLcmOpOccs(lcmOpOccs);
				aVs.setAliasName(vduNamingStrategy.nameVdu(lcmOpOccs, x.getToscaName(), 0));
				lcmOpOccs.getResourceChanges().addAffectedExtCp(aVs);
			}
		});
	}

	private void addVnfComputeInstance(final VnfLcmOpOccs lcmOpOccs, final VnfCompute vnfCompute, final VnfPackage vnfPackage, final VduInstantiationLevel scaleLevel, final int currentCount, final int number) {
		for (int i = 0; i < number; i++) {
			final VnfInstantiatedCompute vnfInstantiedCompute = new VnfInstantiatedCompute();
			vnfInstantiedCompute.setChangeType(ChangeType.ADDED);
			vnfInstantiedCompute.setVduId(vnfCompute.getId());
			vnfInstantiedCompute.setVnfCompute(vnfCompute);
			vnfInstantiedCompute.setVnfLcmOpOccs(lcmOpOccs);
			vnfInstantiedCompute.setAliasName(vduNamingStrategy.nameVdu(lcmOpOccs, vnfCompute.getToscaName(), currentCount + i));
			// final VnfInstantiedCompute savedVnfInstantiedCompute =
			// vnfInstanceService.save(vnfInstantiedCompute);
			lcmOpOccs.getResourceChanges().addAffectedVnfcs(vnfInstantiedCompute);
			if ((null != vnfCompute.getMonitoringParameters()) && !vnfCompute.getMonitoringParameters().isEmpty()) {
				final VnfInstantiatedMonitoring instanceMonotor = new VnfInstantiatedMonitoring();
				instanceMonotor.setChangeType(ChangeType.ADDED);
				instanceMonotor.setVnfLcmOpOccs(lcmOpOccs);
				instanceMonotor.setVnfCompute(vnfCompute);
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
		final Deque<VnfInstantiatedCompute> instantiated = vnfInstanceService.getLiveComputeInstanceOf(vnfInstance, x);
		for (int i = 0; i < number; i++) {
			final VnfInstantiatedCompute instantiatedCompute = new VnfInstantiatedCompute();
			instantiatedCompute.setChangeType(ChangeType.REMOVED);
			instantiatedCompute.setStatus(InstantiationStatusType.NOT_STARTED);
			instantiatedCompute.setVduId(x.getId());
			final VnfInstantiatedCompute poped = instantiated.pop();
			instantiatedCompute.setRemovedInstantiated(poped.getId());
			instantiatedCompute.setResourceId(poped.getResourceId());
			instantiatedCompute.setInstantiationLevel(scaleLevel);
			instantiatedCompute.setVnfLcmOpOccs(lcmOpOccs);
			lcmOpOccs.getResourceChanges().addAffectedVnfcs(instantiatedCompute);
			x.getStorages().forEach(y -> {
				// XXX Delete Storage, but we need a vdu.
			});
		}
	}

	public void terminatePlan(final VnfLcmOpOccs lcmOpOccs) {
		final List<VnfInstantiatedCompute> instantiatedCompute = vnfInstanceService.getLiveComputeInstanceOf(lcmOpOccs.getVnfInstance());
		instantiatedCompute.forEach(x -> {
			final VnfInstantiatedCompute affectedCompute = copyInstantiedResource(x, new VnfInstantiatedCompute(), lcmOpOccs);
			affectedCompute.setVnfCompute(x.getVnfCompute());
			lcmOpOccs.getResourceChanges().addAffectedVnfcs(affectedCompute);
		});
		final List<VnfInstantiatedExtCp> instantiatedExtCps = vnfInstanceService.getLiveExtCpInstanceOf(lcmOpOccs.getVnfInstance());
		instantiatedExtCps.forEach(x -> {
			final VnfInstantiatedExtCp affectedCompute = copyInstantiedResource(x, new VnfInstantiatedExtCp(), lcmOpOccs);
			affectedCompute.setVnfExtCp(x.getVnfExtCp());
			lcmOpOccs.getResourceChanges().addAffectedExtCp(affectedCompute);
		});
		final List<VnfInstantiatedStorage> instantiatedStorages = vnfInstanceService.getLiveStorageInstanceOf(lcmOpOccs.getVnfInstance());
		instantiatedStorages.forEach(x -> {
			final VnfInstantiatedStorage affectedStorage = copyInstantiedResource(x, new VnfInstantiatedStorage(), lcmOpOccs);
			affectedStorage.setVnfVirtualStorage(x.getVnfVirtualStorage());
			lcmOpOccs.getResourceChanges().addAffectedVirtualStorage(affectedStorage);
		});
		final List<VnfInstantiatedVirtualLink> instantiatedVirtualLinks = vnfInstanceService.getLiveVirtualLinkInstanceOf(lcmOpOccs.getVnfInstance());
		instantiatedVirtualLinks.forEach(x -> {
			final VnfInstantiatedVirtualLink affectedVirtualLink = copyInstantiedResource(x, new VnfInstantiatedVirtualLink(), lcmOpOccs);
			affectedVirtualLink.setVnfVirtualLink(x.getVnfVirtualLink());
			lcmOpOccs.getResourceChanges().addAffectedVirtualLink(affectedVirtualLink);
		});
	}

	private static <T extends VnfInstantiatedBase> T copyInstantiedResource(final VnfInstantiatedBase source, final T inst, final VnfLcmOpOccs lcmOpOccs) {
		inst.setChangeType(ChangeType.REMOVED);
		inst.setStatus(InstantiationStatusType.STARTED);
		inst.setVduId(source.getVduId());
		inst.setRemovedInstantiated(source.getId());
		inst.setResourceId(source.getResourceId());
		inst.setVnfLcmOpOccs(lcmOpOccs);
		return inst;
	}

	public void makePrePlan(final NsdInstance nsInstance, final NsdPackage nsdInfo, final NsLcmOpOccs lcmOpOccs) {
		nsdInfo.getNsSaps().forEach(x -> {
			final int c = nsInstanceService.countLiveInstanceOfSap(x.getId());
			if (c == 0) {
				//
			}
		});
		nsdInfo.getNsVirtualLinks();
		nsdInfo.getNestedNsdInfoIds();
		nsdInfo.getVnfPkgIds();

	}
}
