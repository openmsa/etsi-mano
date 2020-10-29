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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.ListenableGraph;
import org.jgrapht.nio.dot.DOTExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.service.graph.GraphTools;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.plan.contributors.PlanContributor;
import com.ubiqube.etsi.mano.service.vim.ConnectionStorage;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.Start;

@Service
public class Planner {

	private static final Logger LOG = LoggerFactory.getLogger(Planner.class);

	private final List<PlanContributor> planContributors;
	private final List<ConnectivityEdge<Node>> connections;

	public Planner(final List<PlanContributor> _planContributors) {
		planContributors = _planContributors;
		// XXX Some how it could depend on planContributors.
		connections = new ConnectionStorage().getConnections();
	}

	public void doPlan(final VnfPackage vnfPackage, final Blueprint plan, final Set<ScaleInfo> scaling) {
		final List<ConnectivityEdge<Node>> start = findSourceNodesByType(connections, Start.class);
		start.forEach(x -> doPlanInner(vnfPackage, plan, x.getTarget().getClass(), scaling, connections));
	}

	private void doPlanInner(final VnfPackage vnfPackage, final Blueprint plan, final Class<? extends Node> clazz, final Set<ScaleInfo> scaling, final List<ConnectivityEdge<Node>> connections) {
		final List<ConnectivityEdge<Node>> start = findSourceNodesByType(connections, clazz);
		if (!start.isEmpty()) {
			final ConnectivityEdge<Node> edge = start.get(0);
			contribute(vnfPackage, plan, scaling, edge.getSource().getClass());
			start.forEach(x -> doPlanInner(vnfPackage, plan, x.getTarget().getClass(), scaling, connections));
		} else {
			contribute(vnfPackage, plan, scaling, clazz);
		}
	}

	private void contribute(final VnfPackage vnfPackage, final Blueprint plan, final Set<ScaleInfo> scaling, final Class<? extends Node> node) {
		final List<PlanContributor> contributors = getContributors(node);
		LOG.debug("Contributors for node {} = {}", node, contributors);
		contributors.stream().forEach(x -> {
			final List<Task> tasks = x.contribute(vnfPackage, plan, scaling);
			plan.getTasks().addAll(tasks);
		});
	}

	private List<PlanContributor> getContributors(final Class<? extends Node> node) {
		return planContributors.stream().filter(x -> x.getContributionType() == node).collect(Collectors.toList());
	}

	private static List<ConnectivityEdge<Node>> findSourceNodesByType(final List<ConnectivityEdge<Node>> connections, final Class<? extends Node> class1) {
		return connections.stream().filter(x -> x.getSource().getClass() == class1).collect(Collectors.toList());
	}

	public ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> convertToExecution(final Blueprint plan) {
		final List<UnitOfWork> list = planContributors.stream().flatMap(x -> x.convertTasksToExecNode(plan.getTasks(), plan).stream()).collect(Collectors.toList());
		final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g = GraphTools.createGraph();
		list.forEach(g::addVertex);
		final Map<String, UnitOfWork> cache = list.stream().collect(Collectors.toMap(UnitOfWork::getToscaName, x -> x));
		list.forEach(x -> x.connect(g, cache));
		final DOTExporter<UnitOfWork, ConnectivityEdge<UnitOfWork>> exporter = new DOTExporter<>(x -> x.getName().replace('-', '_'));
		try (final FileOutputStream out = new FileOutputStream("out.dot")) {
			exporter.exportGraph(g, out);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return g;
	}
}
