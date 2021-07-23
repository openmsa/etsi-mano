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
package com.ubiqube.etsi.mano.service.plan;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.ListenableGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.Instance;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.orchestrator.nodes.ConnectivityEdge;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.NodeConnectivity;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.wfe2.WfConfiguration;
import com.ubiqube.etsi.mano.service.plan.contributors.PlanContributor;
import com.ubiqube.etsi.mano.service.vim.node.Start;

public abstract class Planner<U extends Task, P, PA, B extends Blueprint<U, ? extends Instance>> {

	private static final Logger LOG = LoggerFactory.getLogger(Planner.class);

	private final List<? extends PlanContributor<P, B, U, PA>> planContributors;

	public Planner(final List<? extends PlanContributor<P, B, U, PA>> contributor) {
		planContributors = contributor;
	}

	public void doPlan(final P bundle, final B blueprint, final Set<ScaleInfo> scaling, final List<NodeConnectivity> conns) {
		final List<NodeConnectivity> start = findSourceNodesByType(conns, Start.class);
		final Set<String> cache = new HashSet<>();
		start.forEach(x -> doPlanInner(bundle, blueprint, x.getTarget(), scaling, conns, cache));
	}

	private void doPlanInner(final P bundle, final B blueprint, final Class<? extends Node> clazz, final Set<ScaleInfo> scaling, final List<NodeConnectivity> connections, final Set<String> cache) {
		final List<NodeConnectivity> start = findSourceNodesByType(connections, clazz);
		if (!start.isEmpty()) {
			start.forEach(edge -> {
				LOG.debug(" - {} ", edge.getSource());
				if (!cache.contains(edge.getSource().getName())) {
					contribute(bundle, blueprint, scaling, edge.getSource());
					cache.add(edge.getSource().getName());
				}
			});
			start.forEach(x -> doPlanInner(bundle, blueprint, x.getTarget(), scaling, connections, cache));
		} else {
			if (!cache.contains(clazz.getName())) {
				contribute(bundle, blueprint, scaling, clazz);
				cache.add(clazz.getName());
			}
		}
	}

	private void contribute(final P bundle, final B blueprint, final Set<ScaleInfo> scaling, final Class<? extends Node> node) {
		final List<PlanContributor<P, B, U, PA>> contributors = getContributors(node);
		LOG.debug("Contributors for node {} = {}", node, contributors);
		blueprint.getTasks().addAll(contributors.stream()
				.flatMap(x -> x.contribute(bundle, blueprint, scaling).stream())
				.collect(Collectors.toList()));
	}

	private List<PlanContributor<P, B, U, PA>> getContributors(final Class<? extends Node> node) {
		return planContributors.stream().filter(x -> x.getContributionType() == node).collect(Collectors.toList());
	}

	private static List<NodeConnectivity> findSourceNodesByType(final List<NodeConnectivity> connections, final Class<? extends Node> class1) {
		return connections.stream().filter(x -> x.getSource() == class1).collect(Collectors.toList());
	}

	public ListenableGraph<UnitOfWork<U, PA>, ConnectivityEdge<UnitOfWork<U, PA>>> convertToExecution(final B blueprint, final ChangeType changeType) {
		final Set<U> tasks = blueprint.getTasks().stream().filter(x -> x.getChangeType() == changeType).collect(Collectors.toSet());
		final List<UnitOfWork<U, PA>> list = planContributors.stream().flatMap(x -> x.convertTasksToExecNode(tasks, blueprint).stream()).collect(Collectors.toList());
		final WfConfiguration wfConfiguration = new WfConfiguration(planContributors);
		wfConfiguration.getConfigurationGraph();
		final ListenableGraph<UnitOfWork<U, PA>, ConnectivityEdge<UnitOfWork<U, PA>>> g = wfConfiguration.autoConnect(list);
		// Add start
		final UnitOfWork<U, PA> root = getStartNode();
		g.addVertex(root);
		g.vertexSet().stream()
				.filter(key -> g.incomingEdgesOf(key).isEmpty())
				.forEach(key -> {
					if (key != root) {
						LOG.debug("Connecting root to {}", key.getName());
						g.addEdge(root, key);
					}
				});
		return g;
	}

	protected abstract UnitOfWork<U, PA> getStartNode();
}
