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
package com.ubiqube.etsi.mano.orchestrator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.nio.dot.DOTExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.DefaultDexecutor;
import com.github.dexecutor.core.DexecutorConfig;
import com.github.dexecutor.core.ExecutionConfig;
import com.github.dexecutor.core.task.ExecutionResults;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.orchestrator.nodes.ConnectivityEdge;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.service.ImplementationService;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWork;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWorkVertexListener;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTaskConnectivity;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTaskVertexListener;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class PlannerImpl<P, U, W> implements Planner<P, U, W> {

	private static final Logger LOG = LoggerFactory.getLogger(PlannerImpl.class);

	private final Map<Class<? extends Node>, PlanContributor> contributors;

	private final ImplementationService implementationService;

	public PlannerImpl(final List<PlanContributor> contributorRaw, final ImplementationService implementationService) {
		super();
		this.contributors = contributorRaw.stream()
				.collect(Collectors.toMap(
						PlanContributor::getNode,
						Function.identity(),
						(u, v) -> v,
						LinkedHashMap::new));
		this.implementationService = implementationService;
	}

	@Override
	public PreExecutionGraph<W> makePlan(final Bundle bundle, final List<Class<? extends Node>> planConstituent, final P parameters) {
		final ListenableGraph<VirtualTask<U>, VirtualTaskConnectivity<U>> createGraph = (ListenableGraph) (Object) new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(VirtualTaskConnectivity.class));
		final ListenableGraph<VirtualTask<U>, VirtualTaskConnectivity<U>> deleteGraph = (ListenableGraph) (Object) new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(VirtualTaskConnectivity.class));
		createGraph.addGraphListener(new VirtualTaskVertexListener<>());
		deleteGraph.addGraphListener(new VirtualTaskVertexListener<>());
		planConstituent.forEach(x -> {
			final PlanContributor conts = contributors.get(x);
			if (null == conts) {
				LOG.warn("Unable to find contributor for {}.", x.getSimpleName());
			} else {
				final List<? extends VirtualTask<U>> tasks = conts.contribute(bundle, parameters);
				tasks.forEach(y -> {
					if (y.isDeleteTask()) {
						LOG.debug("Deleting: {}", y);
						deleteGraph.addVertex(y);
					} else {
						LOG.debug("Adding: {}", y);
						createGraph.addVertex(y);
					}
				});
			}
		});
		// Rebuild connectivity.
		rebuildConnectivity(createGraph);
		rebuildConnectivity(deleteGraph);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Create graph:");
			GraphTools.dumpVt(createGraph);
			LOG.debug("Remove graph:");
			GraphTools.dumpVt(deleteGraph);
		}
		return new PreExecutionGraphImpl(createGraph, deleteGraph);
	}

	private void rebuildConnectivity(final ListenableGraph<VirtualTask<U>, VirtualTaskConnectivity<U>> graph) {
		graph.vertexSet().forEach(x -> x.getNameDependencies().forEach(y -> {
			final VirtualTask<U> dep = findProducer(y, graph);
			if (null == dep) {
				LOG.info("Single(dep): {} ", x);
				graph.addVertex(x);
			} else {
				LOG.debug("Add edge(dep): {} <-> {}", dep, x);
				graph.addEdge(dep, x);
			}
		}));
	}

	@Override
	public ExecutionGraph implement(final PreExecutionGraph<U> g) {
		final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> ng = createImplementation(((PreExecutionGraphImpl) g).getCreateGraph());
		final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> nr = createImplementation(((PreExecutionGraphImpl) g).getDeleteGraph());
		if (LOG.isDebugEnabled()) {
			LOG.debug("Create graph:");
			GraphTools.dump(ng);
			LOG.debug("Remove graph:");
			GraphTools.dump(nr);
		}
		return new ExecutionGraphImpl<>(ng, nr);
	}

	private ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> createImplementation(final ListenableGraph<VirtualTask<U>, VirtualTaskConnectivity<U>> gf) {
		final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> ng = (ListenableGraph) (Object) new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(ConnectivityEdge.class));
		ng.addGraphListener(new UnitOfWorkVertexListener<>());
		// First resolve implementation.
		gf.vertexSet().forEach(x -> {
			final SystemBuilder<U> db = implementationService.getTargetSystem(x);
			x.setSystemBuilder(db);
			db.getIncomingVertex().forEach(ng::addVertex);
			db.getOutgoingVertex().forEach(ng::addVertex);
		});
		// Connect everything.
		gf.edgeSet().forEach(x -> {
			final SystemBuilder<U> ibSrc = x.getSource().getSystemBuilder();
			final SystemBuilder<U> ibTgt = x.getTarget().getSystemBuilder();
			linkTo(ng, ibSrc, ibTgt);
		});
		return ng;
	}

	private static <U> void linkTo(final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> g, final SystemBuilder<U> ibSrc, final SystemBuilder<U> ibTgt) {
		linkTo(g, ibSrc.getOutgoingVertex(), ibTgt.getIncomingVertex());
		defnieSubGraph(g, ibSrc);
		defnieSubGraph(g, ibTgt);
	}

	private static <U> void defnieSubGraph(final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> g, final SystemBuilder<U> systemBuilder) {
		if (null != systemBuilder.getSingle()) {
			return;
		}
		systemBuilder.getEdges().forEach(x -> g.addEdge(x.getSource(), x.getTarget()));
	}

	private static <U> void linkTo(final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> g, final List<UnitOfWork<U>> outgoingVertex, final List<UnitOfWork<U>> incomingVertex) {
		outgoingVertex.forEach(x -> incomingVertex.forEach(y -> g.addEdge(x, y)));
	}

	private static <U> VirtualTask<U> findProducer(final NamedDependency namedDependency, final ListenableGraph<? extends VirtualTask<U>, VirtualTaskConnectivity<U>> gf) {
		return gf.vertexSet().stream().filter(x -> x.getNamedProduced().stream()
				.anyMatch(namedDependency::match))
				.findAny()
				.orElse(null);
	}

	@Override
	public OrchExecutionResults<U> execute(final ExecutionGraph implementation, final Context context, final OrchExecutionListener<U> listener) {
		final ExecutionGraphImpl<U> impl = (ExecutionGraphImpl<U>) implementation;
		// Execute delete.
		final ExecutionResults<UnitOfWork<U>, String> deleteRes = execDelete(impl.getDeleteImplementation(), context, listener);
		final OrchExecutionResults<U> finalDelete = convertResults(deleteRes);
		if (!deleteRes.getErrored().isEmpty()) {
			return finalDelete;
		}
		// Execute create.
		exportGraph(impl.getCreateImplementation(), "vnf-added.dot");
		final ExecutionResults<UnitOfWork<U>, String> res = execCreate(impl.getCreateImplementation(), context, listener);
		finalDelete.addAll(convertResults(res));
		return finalDelete;
	}

	private OrchExecutionResults<U> convertResults(final ExecutionResults<UnitOfWork<U>, String> res) {
		final List<OrchExecutionResultImpl<U>> all = res.getAll().stream().map(x -> new OrchExecutionResultImpl<>(x.getId(), ResultType.valueOf(x.getStatus().toString()), x.getMessage())).toList();
		return new OrchExecutionResultsImpl<>(all);
	}

	private static <U> ExecutionResults<UnitOfWork<U>, String> execCreate(final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> g, final Context context, final OrchExecutionListener<U> listener) {
		return createExecutor(g, new CreateTaskProvider<>(context, listener));
	}

	private static <U> ExecutionResults<UnitOfWork<U>, String> execDelete(final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> g, final Context context, final OrchExecutionListener<U> listener) {
		final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> rev = GraphTools.revert(g);
		return createExecutor(rev, new DeleteTaskProvider<>(context, listener));
	}

	private static <U> ExecutionResults<UnitOfWork<U>, String> createExecutor(final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> g, final TaskProvider<UnitOfWork<U>, String> uowTaskProvider) {
		final ExecutorService executorService = Executors.newFixedThreadPool(10);
		final DexecutorConfig<UnitOfWork<U>, String> config = new DexecutorConfig<>(executorService, uowTaskProvider);
		// What about config setExecutionListener.
		final DefaultDexecutor<UnitOfWork<U>, String> executor = new DefaultDexecutor<>(config);
		addRoot(g, executor);
		g.edgeSet().forEach(x -> executor.addDependency(x.getSource(), x.getTarget()));

		final ExecutionResults<UnitOfWork<U>, String> res = executor.execute(ExecutionConfig.TERMINATING);
		executorService.shutdown();
		return res;
	}

	private static <U> void addRoot(final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> g, final DefaultDexecutor<UnitOfWork<U>, String> executor) {
		g.vertexSet().forEach(x -> {
			if (g.incomingEdgesOf(x).isEmpty() && g.outgoingEdgesOf(x).isEmpty()) {
				executor.addIndependent(x);
			}
		});
	}

	public static <U> void exportGraph(final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> g, final String fileName) {
		final DOTExporter<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> exporter = new DOTExporter<>(x -> "\"" + x.getTask().getName() + RandomStringUtils.random(5, true, true) + "\"");
		try (final FileOutputStream out = new FileOutputStream(fileName)) {
			exporter.exportGraph(g, out);
		} catch (final IOException e) {
			LOG.trace("Error in graph export", e);
		}
	}
}
