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
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWorkConnectivity;
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
public class PlannerImpl<U> implements Planner<U> {

	private static final Logger LOG = LoggerFactory.getLogger(PlannerImpl.class);

	Map<Class<? extends Node>, PlanContributor> contributors;

	ImplementationService implementationService;

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
	public <V> PreExecutionGraph<V> makePlan(final Bundle bundle, final List<Class<? extends Node>> planConstituent, final U parameters) {
		final ListenableGraph<VirtualTask<?>, VirtualTaskConnectivity> createGraph = new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(VirtualTaskConnectivity.class));
		final ListenableGraph<VirtualTask<?>, VirtualTaskConnectivity> deleteGraph = new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(VirtualTaskConnectivity.class));
		createGraph.addGraphListener(new VirtualTaskVertexListener());
		deleteGraph.addGraphListener(new VirtualTaskVertexListener());
		planConstituent.forEach(x -> {
			final PlanContributor conts = contributors.get(x);
			if (null == conts) {
				LOG.warn("Unable to find contributor for {}.", x.getSimpleName());
			} else {
				final List<? extends VirtualTask<?>> tasks = conts.contribute(bundle, parameters);
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

	private static void rebuildConnectivity(final ListenableGraph<VirtualTask<?>, VirtualTaskConnectivity> graph) {
		graph.vertexSet().forEach(x -> x.getNameDependencies().forEach(y -> {
			final VirtualTask<?> dep = findProducer(y, graph);
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
	public ExecutionGraph implement(final PreExecutionGraph<?> g) {
		final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> ng = createImplementation(((PreExecutionGraphImpl) g).getCreateGraph());
		final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> nr = createImplementation(((PreExecutionGraphImpl) g).getDeleteGraph());
		if (LOG.isDebugEnabled()) {
			LOG.debug("Create graph:");
			GraphTools.dump(ng);
			LOG.debug("Remove graph:");
			GraphTools.dump(nr);
		}
		return new ExecutionGraphImpl(ng, nr);
	}

	private ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> createImplementation(final ListenableGraph<VirtualTask<?>, VirtualTaskConnectivity> gf) {
		final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> ng = new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(UnitOfWorkConnectivity.class));
		ng.addGraphListener(new UnitOfWorkVertexListener());
		// First resolve implementation.
		gf.vertexSet().forEach(x -> {
			final SystemBuilder db = implementationService.getTargetSystem(x);
			x.setSystemBuilder(db);
			db.getIncomingVertex().forEach(ng::addVertex);
			db.getOutgoingVertex().forEach(ng::addVertex);
		});
		// Connect everything.
		gf.edgeSet().forEach(x -> {
			final SystemBuilder ibSrc = x.getSource().getSystemBuilder();
			final SystemBuilder ibTgt = x.getTarget().getSystemBuilder();
			linkTo(ng, ibSrc, ibTgt);
		});
		return ng;
	}

	private static void linkTo(final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> g, final SystemBuilder ibSrc, final SystemBuilder ibTgt) {
		linkTo(g, ibSrc.getOutgoingVertex(), ibTgt.getIncomingVertex());
		defnieSubGraph(g, ibSrc);
		defnieSubGraph(g, ibTgt);
	}

	private static void defnieSubGraph(final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> g, final SystemBuilder systemBuilder) {
		if (null != systemBuilder.getSingle()) {
			return;
		}
		systemBuilder.getEdges().forEach(x -> g.addEdge(x.getSource(), x.getTarget()));
	}

	private static void linkTo(final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> g, final List<UnitOfWork<?>> outgoingVertex, final List<UnitOfWork<?>> incomingVertex) {
		outgoingVertex.forEach(x -> incomingVertex.forEach(y -> g.addEdge(x, y)));
	}

	private static VirtualTask<?> findProducer(final NamedDependency namedDependency, final ListenableGraph<? extends VirtualTask<?>, VirtualTaskConnectivity> gf) {
		return gf.vertexSet().stream().filter(x -> x.getNamedProduced().stream()
				.anyMatch(namedDependency::match))
				.findAny()
				.orElse(null);
	}

	@Override
	public OrchExecutionResults<?> execute(final ExecutionGraph implementation, final Context context, final OrchExecutionListener<?> listener) {
		final ExecutionGraphImpl impl = (ExecutionGraphImpl) implementation;
		// Execute delete.
		final ExecutionResults<UnitOfWork<?>, String> deleteRes = execDelete(impl.getDeleteImplementation(), context, listener);
		final OrchExecutionResults<?> finalDelete = convertResults(deleteRes);
		if (!deleteRes.getErrored().isEmpty()) {
			return finalDelete;
		}
		// Execute create.
		exportGraph(impl.getCreateImplementation(), "vnf-added.dot");
		final ExecutionResults<UnitOfWork<?>, String> res = execCreate(impl.getCreateImplementation(), context, listener);
		finalDelete.addAll(convertResults(res));
		return finalDelete;
	}

	private static OrchExecutionResults<?> convertResults(final ExecutionResults<UnitOfWork<?>, String> res) {
		final List<OrchExecutionResultImpl> all = res.getAll().stream().map(x -> new OrchExecutionResultImpl(x.getId(), ResultType.valueOf(x.getStatus().toString()), x.getMessage())).toList();
		return new OrchExecutionResultsImpl(all);
	}

	private static ExecutionResults<UnitOfWork<?>, String> execCreate(final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> g, final Context context, final OrchExecutionListener<?> listener) {
		return createExecutor(g, new CreateTaskProvider(context, listener));
	}

	private static ExecutionResults<UnitOfWork<?>, String> execDelete(final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> g, final Context context, final OrchExecutionListener<?> listener) {
		final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> rev = GraphTools.revert(g);
		return createExecutor(rev, new DeleteTaskProvider(context, listener));
	}

	private static ExecutionResults<UnitOfWork<?>, String> createExecutor(final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> g, final TaskProvider<UnitOfWork<?>, String> uowTaskProvider) {
		final ExecutorService executorService = Executors.newFixedThreadPool(10);
		final DexecutorConfig<UnitOfWork<?>, String> config = new DexecutorConfig<>(executorService, uowTaskProvider);
		// What about config setExecutionListener.
		final DefaultDexecutor<UnitOfWork<?>, String> executor = new DefaultDexecutor<>(config);
		addRoot(g, executor);
		g.edgeSet().forEach(x -> executor.addDependency(x.getSource(), x.getTarget()));

		final ExecutionResults<UnitOfWork<?>, String> res = executor.execute(ExecutionConfig.TERMINATING);
		executorService.shutdown();
		return res;
	}

	private static void addRoot(final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> g, final DefaultDexecutor<UnitOfWork<?>, String> executor) {
		g.vertexSet().forEach(x -> {
			if (g.incomingEdgesOf(x).isEmpty() && g.outgoingEdgesOf(x).isEmpty()) {
				executor.addIndependent(x);
			}
		});
	}

	public static <U extends UnitOfWork> void exportGraph(final ListenableGraph g, final String fileName) {
		final DOTExporter<U, ConnectivityEdge<U>> exporter = new DOTExporter<>(x -> "\"" + x.getTask().getName() + RandomStringUtils.random(5, true, true) + "\"");
		try (final FileOutputStream out = new FileOutputStream(fileName)) {
			exporter.exportGraph(g, out);
		} catch (final IOException e) {
			LOG.trace("Error in graph export", e);
		}
	}
}
