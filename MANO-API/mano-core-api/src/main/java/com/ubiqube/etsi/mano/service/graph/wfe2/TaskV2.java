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
package com.ubiqube.etsi.mano.service.graph.wfe2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.service.graph.GraphTools;
import com.ubiqube.etsi.mano.service.plan.contributors.PlanContributor;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.Start;

@Service
public class TaskV2 {
	private static final Logger LOG = LoggerFactory.getLogger(TaskV2.class);
	List<Node> dependencies;

	List<Node> produces;

	private final List<PlanContributor> planContributors;
	final Map<Class<? extends Node>, ReplaceBuilder> replacements = new HashMap<>();

	public TaskV2(final List<PlanContributor> _planContributors) {
		planContributors = _planContributors;
	}

	public void test() {
		final List<ConnectivityEdge<Class<? extends Node>>> edges = new ArrayList<>();
		planContributors.forEach(x -> {
			LOG.debug("Getting contributor: {}.", x.getContributionType());
			final DependencyBuilder dependencyBuilder = new DependencyBuilder(x);
			x.getDependencies(dependencyBuilder);
			final Map<Class<? extends Node>, ReplaceBuilder> replacement = dependencyBuilder.getReplacement();
			replacement.entrySet().forEach(y -> {
				if (replacements.containsKey(y.getKey())) {
					LOG.warn("Duplicate replacement: {}", y.getKey());
				} else {
					replacements.put(y.getKey(), y.getValue());
				}
			});
			edges.addAll(dependencyBuilder.getEdges());
		});
		final ListenableGraph<Class<? extends Node>, ConnectivityEdge<Class<? extends Node>>> g = (ListenableGraph<Class<? extends Node>, ConnectivityEdge<Class<? extends Node>>>) (Object) GraphTools.createGraph();
		final Set<Class<? extends Node>> vertex = new HashSet<>();
		edges.forEach(x -> {
			vertex.add(x.getSource());
			vertex.add(x.getTarget());
		});
		replacements.entrySet().stream()
				.flatMap(x -> x.getValue().getEdges().stream())
				.forEach(x -> {
					vertex.add(x.getSource());
					vertex.add(x.getTarget());
				});
		vertex.add(Start.class);
		vertex.stream().forEach(g::addVertex);
		replacements.entrySet().stream()
				.flatMap(x -> x.getValue().getEdges().stream())
				.forEach(x -> {
					LOG.debug("RPL Link: {} -> {}", x.getSource(), x.getTarget());
					g.addEdge(x.getSource(), x.getTarget());
				});
		edges.forEach(x -> {
			// Link B <- me.
			if (replacements.containsKey(x.getSource())) {
				complexLink(g, replacements.get(x.getSource()), x.getTarget());
			} else {
				if (replacements.containsKey(x.getTarget())) {
					complexLink(g, x.getSource(), replacements.get(x.getTarget()));
				} else {
					LOG.debug("Link: {} -> {}", x.getSource(), x.getTarget());
					g.addEdge(x.getSource(), x.getTarget());
				}
			}
		});
		optimze(g);
		GraphTools.exportGraph(g, "test.out");
	}

	private static void optimze(final ListenableGraph<Class<? extends Node>, ConnectivityEdge<Class<? extends Node>>> g) {
		final AllDirectedPaths<Class<? extends Node>, ConnectivityEdge<Class<? extends Node>>> dp = new AllDirectedPaths<>(g);
		g.vertexSet().forEach(x -> {
			g.edgesOf(x).stream().forEach(y -> {
				if (x != y.getTarget()) {
					final List<GraphPath<Class<? extends Node>, ConnectivityEdge<Class<? extends Node>>>> paths = dp.getAllPaths(x, y.getTarget(), false, 5);
					if (paths.size() > 1) {
						paths.forEach(z -> {
							if (z.getLength() == 1) {
								LOG.debug("Removing: {}", z);
								g.removeEdge(z.getEdgeList().get(0));
							}
						});
					}
				}
			});
		});
	}

	private static void complexLink(final ListenableGraph<Class<? extends Node>, ConnectivityEdge<Class<? extends Node>>> g, final Class<? extends Node> source, final ReplaceBuilder replaceBuilder) {
		replaceBuilder.getEdges().forEach(x -> {
			LOG.debug("CPLX SRC Link: {} -> {}", source, x.getSource());
			g.addEdge(source, x.getSource());
		});
	}

	private static void complexLink(final ListenableGraph<Class<? extends Node>, ConnectivityEdge<Class<? extends Node>>> g, final ReplaceBuilder replaceBuilder, final Class<? extends Node> target) {
		replaceBuilder.getEdges().forEach(x -> {
			LOG.debug("CPLX TGT Link: {} -> {}", x.getTarget(), target);
			g.addEdge(x.getTarget(), target);
		});
	}

}
