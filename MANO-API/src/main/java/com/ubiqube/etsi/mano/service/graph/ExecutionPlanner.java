package com.ubiqube.etsi.mano.service.graph;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.nio.dot.DOTExporter;
import org.jgrapht.traverse.TopologicalOrderIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;

@Service
public class ExecutionPlanner {

	private static final Logger LOG = LoggerFactory.getLogger(ExecutionPlanner.class);

	private final VnfInstancesRepository vnfInstancesRepository;

	public ExecutionPlanner(final VnfInstancesRepository _vnfInstancesRepository) {
		vnfInstancesRepository = _vnfInstancesRepository;
	}

	public List<UnitOfWork> plan(final VnfPackage vnfPackage, final String vnfInstanceId) {
		final Map<String, UnitOfWork> vertex = new HashMap<>();
		// Vertex everyThing
		final ListenableGraph<UnitOfWork, ConnectivityEdge> g = new DefaultListenableGraph<>(new DirectedAcyclicGraph(ConnectivityEdge.class));
		g.addGraphListener(new EdgeListener<UnitOfWork>());
		vnfPackage.getVnfVl().forEach(x -> {
			x.getVlProfileEntity().getVirtualLinkProtocolData().forEach(y -> {
				final UnitOfWork uow = new VirtualLinkUow(y, String.format("%s_%04d", x.getToscaName(), 0));
				vertex.put(x.getToscaName(), uow);
				g.addVertex(uow);
			});

		});

		vnfPackage.getVnfStorage().forEach(x -> {
			final UnitOfWork uow = new StorageUow(x);
			vertex.put(x.getToscaName(), uow);
			g.addVertex(uow);
		});

		vnfPackage.getVnfCompute().forEach(x -> {
			final UnitOfWork uow = new ComputeUow(x);
			vertex.put(x.getToscaName(), uow);
			g.addVertex(uow);
		});

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
			// do the same for swImages ?
			// Do the same for Monitoring.
			if ((null != x.getMonitoringParameters()) && !x.getMonitoringParameters().isEmpty()) {
				final UnitOfWork uow = new MonitoringUow(x, makeUowMonitoringName(x));
				vertex.put(makeUowMonitoringName(x), uow);
				g.addVertex(uow);
				LOG.debug("Monitoring: {} -> {}", x.getToscaName(), uow.getName());
				g.addEdge(vertex.get(x.getToscaName()), uow);
			}
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
		final UnitOfWork end = new EndUow();
		g.addVertex(end);
		g.vertexSet().stream()
				.filter(key -> g.outgoingEdgesOf(key).isEmpty())
				.forEach(key -> {
					if (key != end) {
						g.addEdge(key, end);
					}
				});
		exportGraph(g, vnfInstanceId);
		final TopologicalOrderIterator<UnitOfWork, ConnectivityEdge> orderIterator = new TopologicalOrderIterator<>(g);
		final List<UnitOfWork> ret = new ArrayList<>();
		orderIterator.forEachRemaining(ret::add);
		if (LOG.isInfoEnabled()) {
			dumpPlan(ret);
		}
		return ret;
	}

	private void exportGraph(final ListenableGraph<UnitOfWork, ConnectivityEdge> g, @NotNull final String _id) {
		final DOTExporter<UnitOfWork, ConnectivityEdge> exporter = new DOTExporter<>(UnitOfWork::getName);
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		exporter.exportGraph(g, out);
		final byte[] res = out.toByteArray();
		final InputStream _stream = new ByteArrayInputStream(res);
		vnfInstancesRepository.storeBinary(_id, "plan.dot", _stream);
	}

	private static void dumpPlan(final List<UnitOfWork> ret) {
		LOG.info("Dumping plan:");
		ret.forEach(x -> LOG.info("\t{}", x.getName()));
	}

	private static String makeUowMonitoringName(final VnfCompute x) {
		return x.getToscaName() + "MON";
	}

}
