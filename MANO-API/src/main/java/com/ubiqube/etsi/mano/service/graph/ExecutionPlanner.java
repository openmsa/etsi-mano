package com.ubiqube.etsi.mano.service.graph;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.nio.dot.DOTExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.AffectedCompute;
import com.ubiqube.etsi.mano.dao.mano.AffectedExtCp;
import com.ubiqube.etsi.mano.dao.mano.AffectedVl;
import com.ubiqube.etsi.mano.dao.mano.AffectedVs;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiationLevels;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfPackageService;

@Service
public class ExecutionPlanner {

	private static final Logger LOG = LoggerFactory.getLogger(ExecutionPlanner.class);

	private final VnfPackageRepository vnfPackageRepository;

	private final VnfPackageService vnfPackageService;

	private final VnfInstanceService vnfInstanceService;

	public ExecutionPlanner(final VnfPackageRepository _vnfPackageRepository, final VnfInstanceService _vnfInstanceService, final VnfPackageService _vnfPackageService) {
		vnfPackageRepository = _vnfPackageRepository;
		vnfInstanceService = _vnfInstanceService;
		vnfPackageService = _vnfPackageService;
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
		final Map<String, UnitOfWork> vertex = buildVertex(g, vnfLcmOpOccs);
		// Connect LinkPort to VM
		vnfPackage.getVnfLinkPort().forEach(x -> {
			LOG.debug("LinkPort: {} -> {}", x.getVirtualLink(), x.getVirtualBinding());
			g.addEdge(vertex.get(x.getVirtualLink()), vertex.get(x.getVirtualBinding()));
		});

		// Connect Vdu
		vnfPackage.getVnfCompute().forEach(x -> {
			final Set<String> storages = x.getStorages();
			if (null != storages) {
				storages.forEach(y -> {
					LOG.debug("Storage link {} -> {}", y, x.getToscaName());
					g.addEdge(vertex.get(y), vertex.get(x.getToscaName()));
				});
			}
			// XXX do the same for swImages ?
			if ((null != x.getMonitoringParameters()) && !x.getMonitoringParameters().isEmpty()) {
				final UnitOfWork uow = new MonitoringUow(new ResourceHandleEntity(), x, makeUowMonitoringName(x));
				vertex.put(makeUowMonitoringName(x), uow);
				g.addVertex(uow);
				LOG.debug("Monitoring: {} -> {}", x.getToscaName(), uow.getName());
				g.addEdge(vertex.get(x.getToscaName()), uow);
			}
		});

		// XXX Move this elsewhere.
		vnfPackage.getVnfExtCp().forEach(x -> {
			LOG.debug("ExtCp_{}: {} -> {}", x.getToscaName(), x.getInternalVirtualLink(), x.getExternalVirtualLink());
			Optional.ofNullable(vertex.get(x.getToscaName()))
					.ifPresent(y -> g.addEdge(vertex.get(x.getInternalVirtualLink()), y));
		});
		// Add start
		final UnitOfWork root = new StartUow();
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
		final UnitOfWork end = new EndUow(new ResourceHandleEntity());
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

	private Map<String, UnitOfWork> buildVertex(final ListenableGraph<UnitOfWork, ConnectivityEdge> g, final VnfLcmOpOccs vnfLcmOpOccs) {
		final Map<String, UnitOfWork> vertex = new HashMap<>();

		vnfLcmOpOccs.getResourceChanges().getAffectedVirtualLinks().forEach(x -> {
			final VnfVl vlProtocol = vnfPackageService.findVirtualLnkById(x.getVirtualLinkDesc().getId()).orElseThrow(() -> new NotFoundException("Unable to find Virtual Link resource " + x.getNetworkResource().getVduId()));
			final UnitOfWork uow = new VirtualLinkUow(x.getNetworkResource(), vlProtocol.getVlProfileEntity().getVirtualLinkProtocolData().iterator().next(), vlProtocol.getToscaName());
			vertex.put(vlProtocol.getToscaName(), uow);
			g.addVertex(uow);
		});

		vnfLcmOpOccs.getResourceChanges().getAffectedVirtualStorages().forEach(x -> {
			final VnfStorage vstorage = vnfPackageService.findVirtualStorageById(x.getVirtualStorageDesc().getId()).orElseThrow(() -> new NotFoundException("Unable to find Virtual Strorage resource " + x.getVirtualStorageDesc().getId()));
			UnitOfWork uow;
			if ("BLOCK".equals(vstorage.getType())) {
				uow = new StorageUow(x.getStorageResource(), vstorage);
			} else {
				uow = new ObjectStorageUow(x.getStorageResource(), vstorage, vstorage.getToscaName());
			}
			vertex.put(vstorage.getToscaName(), uow);
			g.addVertex(uow);
		});

		vnfLcmOpOccs.getResourceChanges().getAffectedVnfcs().forEach(x -> {
			final VnfCompute compute = vnfPackageService.findComputeById(x.getVduId()).orElseThrow(() -> new NotFoundException("Unable to find Virtual Compute resource " + x.getVduId()));
			final UnitOfWork uow = new ComputeUow(x.getVnfInstantiedCompute(), compute);
			vertex.put(compute.getToscaName(), uow);
			g.addVertex(uow);
		});
		final Set<AffectedExtCp> extCp = vnfLcmOpOccs.getResourceChanges().getAffectedExtCp();
		extCp.forEach(x -> {
			final VnfExtCp lextCp = vnfPackageService.findExtCpById(x.getStorageResource().getVduId()).orElseThrow(() -> new NotFoundException("Unable to find ExtCp resource " + x.getStorageResource().getVduId()));
			final UnitOfWork uow = new VnfExtCpUow(x.getStorageResource(), lextCp);
			vertex.put(lextCp.getToscaName(), uow);
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
		final VnfInstantiationLevels instantiationLevel = vnfPakage.getVnfInstantiationLevels().stream()
				.filter(x -> x.getLevelName().equals(instantiationLevelId))
				.findFirst().orElseThrow(() -> new NotFoundException("Aspectid not found: "));
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
					addVnfComputeInstance(lcmOpOccs, x, vnfPakage, vduInstantiationLevel, wantedNumInst - currentInst, vnfInstance);
				} else if (currentInst > wantedNumInst) {
					removeVnfComputeInstance(lcmOpOccs, vnfInstance, x, vduInstantiationLevel, currentInst - wantedNumInst);
				}
			} else {
				LOG.warn("Unknown aspect {} for Vdu {} ", instantiationLevel.getScaleInfoName(), x.getToscaName());
			}
		});
		vnfPakage.getVnfVl().forEach(x -> {
			// XXX They should scale.
			final int num = vnfInstanceService.getNumberOfLiveVl(vnfInstance, x);
			if (num == 0) {
				AffectedVl aVl = new AffectedVl();
				aVl.setChangeType(ChangeType.ADDED);
				aVl.setVirtualLinkDesc(x);
				aVl.setInstantiationLevel(instantiationLevel);
				aVl.setVnfLcmOpOccs(lcmOpOccs);
				ResourceHandleEntity networkResource = new ResourceHandleEntity();
				networkResource.setVduId(x.getId());
				networkResource.setVnfLcmOpOccs(lcmOpOccs);
				networkResource = this.vnfInstanceService.save(networkResource);
				aVl.setNetworkResource(networkResource);
				aVl = vnfInstanceService.save(aVl);
				lcmOpOccs.getResourceChanges().addAffectedVirtualLink(aVl);
			}
		});
		// No ExtCp when spawning.
	}

	private void addVnfComputeInstance(final VnfLcmOpOccs lcmOpOccs, final VnfCompute vnfCompute, final VnfPackage vnfPackage, final VduInstantiationLevel scaleLevel, final int number, final VnfInstance vnfInstance) {
		for (int i = 0; i < number; i++) {
			final AffectedCompute affectedCompute = new AffectedCompute();
			affectedCompute.setChangeType(ChangeType.ADDED);
			affectedCompute.setVduId(vnfCompute.getId());
			affectedCompute.setVnfCompute(vnfCompute);
			affectedCompute.setVnfLcmOpOccs(lcmOpOccs);
			VnfInstantiedCompute vnfInstantiedCompute = new VnfInstantiedCompute();
			vnfInstantiedCompute.setVduId(vnfCompute.getId());
			vnfInstantiedCompute.setVnfCompute(vnfCompute);
			vnfInstantiedCompute.setVnfLcmOpOccs(lcmOpOccs);

			ResourceHandleEntity networkResource = new ResourceHandleEntity();
			networkResource.setVduId(vnfCompute.getId());
			networkResource.setVnfLcmOpOccs(lcmOpOccs);
			networkResource = vnfInstanceService.save(networkResource);
			vnfInstantiedCompute.setCompResource(networkResource);

			vnfInstantiedCompute = vnfInstanceService.save(vnfInstantiedCompute);

			affectedCompute.setVnfInstantiedCompute(vnfInstantiedCompute);

			lcmOpOccs.getResourceChanges().addAffectedVnfcs(affectedCompute);
			vnfCompute.getStorages().forEach(y -> {
				// XX Add Storage
				affectedCompute.getAddedStorageResourceIds().add(y);
				affectedCompute.setInstantiationLevel(scaleLevel);
				final VnfStorage storage = vnfPackageService.findStorageByName(vnfPackage, y)
						.orElseThrow(() -> new NotFoundException("could not find: " + y));
				AffectedVs vs = new AffectedVs();
				vs.setInstantiationLevel(scaleLevel);
				vs.setChangeType(ChangeType.ADDED);
				vs.setVirtualStorageDesc(storage);
				ResourceHandleEntity vsResource = new ResourceHandleEntity();
				vsResource.setVduId(storage.getId());
				vsResource.setVnfLcmOpOccs(lcmOpOccs);
				vsResource = this.vnfInstanceService.save(vsResource);
				vs = vnfInstanceService.save(vs);
				lcmOpOccs.getResourceChanges().addAffectedVirtualStorage(vs);
			});
		}
	}

	private void removeVnfComputeInstance(final VnfLcmOpOccs lcmOpOccs, final VnfInstance vnfInstance, final VnfCompute x, final VduInstantiationLevel scaleLevel, final int number) {
		final Deque<VnfInstantiedCompute> instantied = vnfInstanceService.getLiveComputeInstanceOf(vnfInstance, x);
		for (int i = 0; i < number; i++) {
			final AffectedCompute affectedCompute = new AffectedCompute();
			affectedCompute.setChangeType(ChangeType.REMOVED);
			affectedCompute.setVduId(x.getId());
			affectedCompute.setVnfInstantiedCompute(instantied.pop());
			affectedCompute.setInstantiationLevel(scaleLevel);
			affectedCompute.setVnfLcmOpOccs(lcmOpOccs);
			lcmOpOccs.getResourceChanges().addAffectedVnfcs(affectedCompute);
			x.getStorages().forEach(y -> {
				// XXX Delete Storage, but we need a vdu.
			});
		}
	}

}
