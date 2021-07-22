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

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.DefaultDexecutor;
import com.github.dexecutor.core.DexecutorConfig;
import com.github.dexecutor.core.ExecutionConfig;
import com.github.dexecutor.core.task.ExecutionResults;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.orchestrator.exceptions.OrchestrationException;
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
		this.contributors = contributorRaw.stream().collect(Collectors.toMap(PlanContributor::getNode, Function.identity()));
		this.implementationService = implementationService;
	}

	@Override
	public <V> PreExecutionGraph<V> makePlan(final Bundle bundle, final List<Class<? extends Node>> planConstituent, final U parameters) {
		final ListenableGraph<VirtualTask<?>, VirtualTaskConnectivity> gf = new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(VirtualTaskConnectivity.class));
		final ListenableGraph<VirtualTask<?>, VirtualTaskConnectivity> gr = new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(VirtualTaskConnectivity.class));
		gf.addGraphListener(new VirtualTaskVertexListener());
		gr.addGraphListener(new VirtualTaskVertexListener());
		planConstituent.forEach(x -> {
			final PlanContributor conts = contributors.get(x);
			if (null == conts) {
				LOG.warn("Unable to find contributor for {}.", x.getSimpleName());
			} else {
				final List<? extends VirtualTask<?>> tasks = conts.contribute(bundle, parameters);
				tasks.forEach(y -> {
					if (y.isDeleteTask()) {
						LOG.debug("Deleting: {}", y);
						gr.addVertex(y);
					} else {
						LOG.debug("Adding: {}", y);
						gf.addVertex(y);
					}
				});
			}
		});
		// Rebuild connectivity.
		gf.vertexSet().forEach(x -> x.getNameDependencies().forEach(y -> {
			final VirtualTask<?> dep = findDependency(y, gf);
			LOG.debug("Add edge : {} <-> {}", dep, x);
			gf.addEdge(dep, x);
		}));
		if (LOG.isDebugEnabled()) {
			LOG.debug("Create graph:");
			GraphTools.dumpVt(gf);
			LOG.debug("Remove graph:");
			GraphTools.dumpVt(gr);
		}
		return new PreExecutionGraphImpl(gf, gr);
	}

	@Override
	public ExecutionGraph implement(final PreExecutionGraph g, final Object parameters) {
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
			final SystemBuilder db = implementationService.getTaretSystem(x);
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

	private static VirtualTask<?> findDependency(final NamedDependency namedDependency, final ListenableGraph<? extends VirtualTask<?>, VirtualTaskConnectivity> gf) {
		return gf.vertexSet().stream().filter(x -> x.getNamedProduced().stream()
				.anyMatch(namedDependency::match))
				.findAny()
				.orElseThrow(() -> new OrchestrationException("Could not find named dependency " + namedDependency));
	}

	@Override
	public OrchExecutionResults execute(final ExecutionGraph implementation, final OrchExecutionListener listener) {
		final ExecutionGraphImpl impl = (ExecutionGraphImpl) implementation;
		final Context context = new ContextImpl();
		// Execute delete.
		ExecutionResults<UnitOfWork<?>, String> res = execDelete(impl.getDeleteImplementation(), context, listener);
		if (!res.getErrored().isEmpty()) {
			return convertResults(res);
		}
		// Execute create.
		res = execCreate(impl.getCreateImplementation(), context, listener);
		return convertResults(res);
	}

	private static OrchExecutionResults convertResults(final ExecutionResults<UnitOfWork<?>, String> res) {
		final List<OrchExecutionResultImpl> all = res.getAll().stream().map(x -> new OrchExecutionResultImpl(x.getId(), x.getResult(), x.getMessage())).collect(Collectors.toList());
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
		g.edgeSet().forEach(x -> executor.addDependency(x.getSource(), x.getTarget()));

		final ExecutionResults<UnitOfWork<?>, String> res = executor.execute(ExecutionConfig.TERMINATING);
		executorService.shutdown();
		return res;
	}

}
