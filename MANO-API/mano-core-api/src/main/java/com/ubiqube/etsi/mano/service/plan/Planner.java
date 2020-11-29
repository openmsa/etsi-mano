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

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.ListenableGraph;
import org.jgrapht.nio.dot.DOTExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.NoopTask;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.service.graph.GraphTools;
import com.ubiqube.etsi.mano.service.graph.vnfm.StartUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.plan.contributors.PlanContributor;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.Start;

public class Planner<U extends Task, P, PA, B extends Blueprint<U>> {

	private static final Logger LOG = LoggerFactory.getLogger(Planner.class);

	private final List<PlanContributor<P, B, U, PA>> planContributors;

	public Planner(final List<PlanContributor<P, B, U, PA>> _planContributors) {
		planContributors = _planContributors;
	}

	public void doPlan(final P bundle, final B blueprint, final Set<ScaleInfo> scaling, final List<ConnectivityEdge<Node>> conns) {
		final List<ConnectivityEdge<Node>> start = findSourceNodesByType(conns, Start.class);
		final Set<String> cache = new HashSet<>();
		start.forEach(x -> doPlanInner(bundle, blueprint, x.getTarget().getClass(), scaling, conns, cache));
	}

	private void doPlanInner(final P bundle, final B blueprint, final Class<? extends Node> clazz, final Set<ScaleInfo> scaling, final List<ConnectivityEdge<Node>> connections, final Set<String> cache) {
		final List<ConnectivityEdge<Node>> start = findSourceNodesByType(connections, clazz);
		if (!start.isEmpty()) {
			final ConnectivityEdge<Node> edge = start.get(0);
			if (!cache.contains(edge.getSource().getClass().getName())) {
				contribute(bundle, blueprint, scaling, edge.getSource().getClass());
				cache.add(edge.getSource().getClass().getName());
			}
			start.forEach(x -> doPlanInner(bundle, blueprint, x.getTarget().getClass(), scaling, connections, cache));
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

	private static List<ConnectivityEdge<Node>> findSourceNodesByType(final List<ConnectivityEdge<Node>> connections, final Class<? extends Node> class1) {
		return connections.stream().filter(x -> x.getSource().getClass() == class1).collect(Collectors.toList());
	}

	public ListenableGraph<UnitOfWork<U, PA>, ConnectivityEdge<UnitOfWork<U, PA>>> convertToExecution(final B blueprint, final ChangeType changeType) {
		final Set<U> tasks = blueprint.getTasks().stream().filter(x -> x.getChangeType() == changeType).collect(Collectors.toSet());
		final List<UnitOfWork<U, PA>> list = planContributors.stream().flatMap(x -> x.convertTasksToExecNode(tasks, blueprint).stream()).collect(Collectors.toList());
		final ListenableGraph<UnitOfWork<U, PA>, ConnectivityEdge<UnitOfWork<U, PA>>> g = GraphTools.createGraph();
		list.forEach(g::addVertex);
		final Map<String, UnitOfWork<U, PA>> cache = list.stream()
				.collect(
						Collectors.toMap(
								x -> x.getTaskEntity().getAlias(), x -> x,
								(x, y) -> {
									LOG.warn("Duplicate key: {}", x.getName());
									return x;
								}));
		// Add start
		final UnitOfWork<U, PA> root = new StartUow(new NoopTask());
		g.addVertex(root);
		g.vertexSet().stream()
				.filter(key -> g.incomingEdgesOf(key).isEmpty())
				.forEach(key -> {
					if (key != root) {
						LOG.debug("Connecting root to {}", key.getName());
						g.addEdge(root, key);
					}
				});
		list.forEach(x -> x.connect(g, cache));
		final DOTExporter<UnitOfWork<U, PA>, ConnectivityEdge<UnitOfWork<U, PA>>> exporter = new DOTExporter<>(x -> x.getName().replace('-', '_'));
		try (final FileOutputStream out = new FileOutputStream("out.dot")) {
			exporter.exportGraph(g, out);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return g;
	}

}
