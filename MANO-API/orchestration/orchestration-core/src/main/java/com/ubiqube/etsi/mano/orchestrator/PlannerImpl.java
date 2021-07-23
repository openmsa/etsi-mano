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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
public class PlannerImpl implements Planner {

	private static final Logger LOG = LoggerFactory.getLogger(PlannerImpl.class);

	Map<Class<? extends Node>, PlanContributor> contributors;

	ImplementationService implementationService;

	public PlannerImpl(final Map<Class<? extends Node>, PlanContributor> contributors, final ImplementationService implementationService) {
		super();
		this.contributors = contributors;
		this.implementationService = implementationService;
	}

	@Override
	public ListenableGraph<VirtualTask<?>, VirtualTaskConnectivity> makePlan(final Bundle bundle, final List<Class<? extends Node>> planConstituent) {
		final ListenableGraph<VirtualTask<?>, VirtualTaskConnectivity> gf = new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(VirtualTaskConnectivity.class));
		gf.addGraphListener(new VirtualTaskVertexListener());
		planConstituent.forEach(x -> {
			final PlanContributor conts = contributors.get(x);
			if (null == conts) {
				LOG.warn("Unable to find contributor for {}.", x.getSimpleName());
			} else {
				final List<? extends VirtualTask<?>> tasks = conts.contribute(bundle);
				tasks.forEach(gf::addVertex);
			}
		});
		// Rebuild connectivity.
		gf.vertexSet().forEach(x -> x.getNameDependencies().forEach(y -> {
			final VirtualTask<?> dep = findDependency(y, gf);
			gf.addEdge(dep, x);
		}));
		return gf;
	}

	@Override
	public ListenableGraph<UnitOfWork, UnitOfWorkConnectivity> implement(final ListenableGraph<VirtualTask<?>, VirtualTaskConnectivity> gf) {
		final ListenableGraph<UnitOfWork, UnitOfWorkConnectivity> ng = new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(UnitOfWorkConnectivity.class));
		ng.addGraphListener(new UnitOfWorkVertexListener());
		final Map<UnitOfWork, VirtualTask<?>> mapUowToVt = new HashMap<>();
		// First resolve implementation.
		gf.vertexSet().forEach(x -> {
			final SystemBuilder db = implementationService.getTaretSystem(x);
			x.setSystemBuilder(db);
			db.getIncomingVertex().forEach(y -> {
				ng.addVertex(y);
				mapUowToVt.put(y, x);
			});
		});
		// Connect everything.
		gf.edgeSet().forEach(x -> {
			final SystemBuilder ibSrc = x.getSource().getSystemBuilder();
			final SystemBuilder ibTgt = x.getTarget().getSystemBuilder();
			linkTo(ng, ibSrc, ibTgt);
		});
		return ng;
	}

	private static void linkTo(final ListenableGraph<UnitOfWork, UnitOfWorkConnectivity> g, final SystemBuilder ibSrc, final SystemBuilder ibTgt) {
		linkTo(g, ibSrc.getOutgoingVertex(), ibTgt.getIncomingVertex());
		defnieSubGraph(g, ibSrc);
		defnieSubGraph(g, ibTgt);
	}

	private static void defnieSubGraph(final ListenableGraph<UnitOfWork, UnitOfWorkConnectivity> g, final SystemBuilder systemBuilder) {
		if (null == systemBuilder.getSingle()) {
			return;
		}
		systemBuilder.getEdges().forEach(x -> g.addEdge(x.getSource(), x.getTarget()));
	}

	private static void linkTo(final ListenableGraph<UnitOfWork, UnitOfWorkConnectivity> g, final List<UnitOfWork> outgoingVertex, final List<UnitOfWork> incomingVertex) {
		outgoingVertex.forEach(x -> incomingVertex.forEach(y -> g.addEdge(x, y)));
	}

	private static VirtualTask<?> findDependency(final NamedDependency namedDependency, final ListenableGraph<? extends VirtualTask<?>, VirtualTaskConnectivity> gf) {
		return gf.vertexSet().stream().filter(x -> x.getNamedProduced().stream()
				.anyMatch(namedDependency::match))
				.findAny()
				.orElseThrow(() -> new OrchestrationException("Could not find named dependency " + namedDependency));
	}
}
