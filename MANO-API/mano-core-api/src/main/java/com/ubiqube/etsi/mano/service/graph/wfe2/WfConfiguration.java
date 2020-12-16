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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;
import org.jgrapht.ListenableGraph;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.service.graph.GraphTools;
import com.ubiqube.etsi.mano.service.graph.WfProduce;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.plan.contributors.PlanContributor;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.Start;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class WfConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(WfConfiguration.class);

	final Map<Class<? extends Node>, ReplaceBuilder> replacements = new HashMap<>();
	private final List<PlanContributor> planContributors;

	public WfConfiguration(final List<PlanContributor> _planContributors) {
		planContributors = _planContributors;
	}

	public ListenableGraph<Class<? extends Node>, ConnectivityEdge<Class<? extends Node>>> getConfigurationGraph() {
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
		createVertex(g, edges);
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
		return g;
	}

	private void createVertex(final ListenableGraph<Class<? extends Node>, ConnectivityEdge<Class<? extends Node>>> g, final List<ConnectivityEdge<Class<? extends Node>>> edges) {
		final Set<Class<? extends Node>> vertex = new HashSet<>();
		edges.forEach(x -> vertexAdd(vertex, x));
		replacements.entrySet().stream()
				.flatMap(x -> x.getValue().getEdges().stream())
				.forEach(x -> vertexAdd(vertex, x));
		vertex.add(Start.class);
		vertex.stream().forEach(g::addVertex);
	}

	private static void vertexAdd(final Set<Class<? extends Node>> vertex, final ConnectivityEdge<Class<? extends Node>> x) {
		vertex.add(x.getSource());
		vertex.add(x.getTarget());
	}

	public <U extends Task, P> ListenableGraph<UnitOfWork<U, P>, ConnectivityEdge<UnitOfWork<U, P>>> autoConnect(final List<UnitOfWork<U, P>> uows) {
		final ListenableGraph<UnitOfWork<U, P>, ConnectivityEdge<UnitOfWork<U, P>>> g = GraphTools.createGraph();
		final Map<Class<? extends Node>, MultiValueMap<String, UnitOfWork<U, P>>> dependencyCache = buildDependencyCache(uows);
		final Map<Class<? extends Node>, MultiValueMap<String, UnitOfWork<U, P>>> produceCache = buildproduceCache(uows);
		final List<DeferedConnection<U, P>> deferedConnetion = new ArrayList<>();
		uows.forEach(g::addVertex);
		dependencyCache.entrySet().forEach(x -> x.getValue().entrySet().forEach(y -> {
			LOG.debug("Deps of {}/{} => {}", x.getKey().getSimpleName(), y.getKey(), y.getValue());
			Optional.ofNullable(produceCache.get(x.getKey()))
					.map(z -> z.get(y.getKey()))
					.ifPresentOrElse(z -> connect(g, x.getKey(), z, y.getValue(), deferedConnetion), () -> LOG.warn("Unable to link {}", y.getKey()));
		}));
		handleDeferedConnections(g, deferedConnetion);
		return g;
	}

	private <U extends Task, P> void handleDeferedConnections(final ListenableGraph<UnitOfWork<U, P>, ConnectivityEdge<UnitOfWork<U, P>>> g, final List<DeferedConnection<U, P>> deferedConnetion) {
		// Allow connection of replacements
		final List<DeferedConnection<U, P>> deferedFinal = new ArrayList<>();
		final List<DeferedConnection<U, P>> implemented = new ArrayList<>();
		deferedConnetion.forEach(x -> {
			final ReplaceBuilder repl = replacements.get(x.getNode());
			if (isInReplacementFunction(repl, x)) {
				g.addEdge(x.getFrom(), x.getTo());
				implemented.add(x);
			} else {
				deferedFinal.add(x);
			}
		});
		// List of net => compute
		deferedFinal.forEach(x -> {
			final ReplaceBuilder repl = replacements.get(x.getNode());
			final List<ConnectivityEdge<Class<? extends Node>>> nodes = findOutterVertex(repl);
			// Net => Subnet
			// for each source node, map the implementation one.
			// find Original Node and get produce/node/name matching dependency/node/name
			final Set<ConnectivityEdge<UnitOfWork<U, P>>> out = g.outgoingEdgesOf(x.getFrom());
			final List<UnitOfWork<U, P>> matchingNodes = getAllMatchingNodes(out, nodes);
			matchingNodes.forEach(y -> {
				LOG.debug(" - Defered link: {} => {}", y, x.getTo());
				g.addEdge(y, x.getTo());
			});
		});
	}

	private static <U extends Task, P> List<UnitOfWork<U, P>> getAllMatchingNodes(final Set<ConnectivityEdge<UnitOfWork<U, P>>> edges, final List<ConnectivityEdge<Class<? extends Node>>> nodes) {
		return edges.stream()
				.filter(x -> have(nodes, x.getTarget().getProduce()))
				.map(ConnectivityEdge::getTarget)
				.collect(Collectors.toList());
	}

	private static boolean have(final List<ConnectivityEdge<Class<? extends Node>>> nodes, final List<WfProduce> l) {
		for (final WfProduce wfProduce : l) {
			if (nodes.stream().anyMatch(x -> x.getTarget() == wfProduce.getNode())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * The concept is an ending node is a node that doesn't appear in a source node.
	 * for example : A -> B -> C
	 *
	 * @param repl A replace builder instance.
	 * @return list of ending Nodes.
	 */
	private static List<ConnectivityEdge<Class<? extends Node>>> findOutterVertex(final ReplaceBuilder repl) {
		return repl.getEdges().stream()
				.filter(x -> dontHaveSource(repl, x.getTarget()))
				.collect(Collectors.toList());
	}

	private static boolean dontHaveSource(final ReplaceBuilder repl, final Class<? extends Node> target) {
		return repl.getEdges().stream().noneMatch(x -> x.getSource() == target);
	}

	private static <U extends Task, P> boolean isInReplacementFunction(final ReplaceBuilder repl, final DeferedConnection<U, P> defered) {
		final List<ConnectivityEdge<Class<? extends Node>>> edges = repl.getEdges();
		for (final ConnectivityEdge<Class<? extends Node>> connectivityEdge : edges) {
			// Net -> sub
			if (connectivityEdge.getSource() != defered.getNode()) {
				continue;
			}
			final List<WfProduce> deps = defered.getTo().getProduce();
			final List<WfProduce> list = getAllOf(deps, connectivityEdge.getTarget());
			if (!list.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	private static List<WfProduce> getAllOf(final List<WfProduce> deps, final Class<? extends Node> target) {
		return deps.stream().filter(x -> x.getNode() == target).collect(Collectors.toList());
	}

	private <U extends Task, P> void connect(final ListenableGraph<UnitOfWork<U, P>, ConnectivityEdge<UnitOfWork<U, P>>> g, final Class<? extends Node> k, final List<UnitOfWork<U, P>> from, final List<UnitOfWork<U, P>> to, final List<DeferedConnection<U, P>> deferedConnetion) {
		if (from.size() == 1) {
			for (final UnitOfWork<U, P> element : to) {
				if (LOG.isInfoEnabled()) {
					LOG.info(" - {} -> {}", uowToString(from.get(0)), uowToString(element));
				}
				makeConnection(g, k, from.get(0), element, deferedConnetion);
			}
		} else {
			LOG.debug("Multimode: {} ========> {}", from, to);
			for (int i = 0; i < from.size(); i++) {
				makeConnection(g, k, from.get(i), to.get(i), deferedConnetion);
			}
		}
	}

	private <U extends Task, P> void makeConnection(final ListenableGraph<UnitOfWork<U, P>, ConnectivityEdge<UnitOfWork<U, P>>> g, final Class<? extends Node> k, final UnitOfWork<U, P> from, final UnitOfWork<U, P> to, final List<DeferedConnection<U, P>> deferedConnetion) {
		//
		final ReplaceBuilder repl = replacements.get(k);
		if (null == repl) {
			g.addEdge(from, to);
			return;
		}
		LOG.debug("  - Defered connection: {} -> {} due to {}", from, to, k);
		deferedConnetion.add(new DeferedConnection<>(k, from, to));
	}

	private static <U extends Task, P> String uowToString(final UnitOfWork<U, P> unitOfWork) {
		return unitOfWork.getName() + "." + unitOfWork.getTaskEntity().getId();
	}

	private static <U extends Task, P> Map<Class<? extends Node>, MultiValueMap<String, UnitOfWork<U, P>>> buildproduceCache(final List<UnitOfWork<U, P>> uows) {
		final Map<Class<? extends Node>, MultiValueMap<String, UnitOfWork<U, P>>> ret = new HashMap<>();
		for (final UnitOfWork<U, P> unitOfWork : uows) {
			unitOfWork.getProduce().forEach(x -> {
				MultiValueMap<String, UnitOfWork<U, P>> node = ret.get(x.getNode());
				if (null == node) {
					node = new LinkedMultiValueMap<>();
					node.add(x.getName(), unitOfWork);
					ret.put(x.getNode(), node);
				} else {
					node.add(x.getName(), unitOfWork);
				}
			});
		}
		return ret;
	}

	private static <U extends Task, P> Map<Class<? extends Node>, MultiValueMap<String, UnitOfWork<U, P>>> buildDependencyCache(final List<UnitOfWork<U, P>> uows) {
		final Map<Class<? extends Node>, MultiValueMap<String, UnitOfWork<U, P>>> ret = new HashMap<>();
		for (final UnitOfWork<U, P> unitOfWork : uows) {
			unitOfWork.getDependencies().forEach(x -> {
				MultiValueMap<String, UnitOfWork<U, P>> node = ret.get(x.getNode());
				if (null == node) {
					node = new LinkedMultiValueMap<>();
					node.add(x.getName(), unitOfWork);
					ret.put(x.getNode(), node);
				} else {
					node.add(x.getName(), unitOfWork);
				}
			});
		}
		return ret;
	}

	private static void optimze(final ListenableGraph<Class<? extends Node>, ConnectivityEdge<Class<? extends Node>>> g) {
		final AllDirectedPaths<Class<? extends Node>, ConnectivityEdge<Class<? extends Node>>> dp = new AllDirectedPaths<>(g);
		g.vertexSet().forEach(x -> g.edgesOf(x).stream().forEach(y -> {
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
		}));
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

	static class DeferedConnection<U extends Task, P> {
		private final Class<? extends Node> node;
		private final UnitOfWork<U, P> from;
		private final UnitOfWork<U, P> to;

		public DeferedConnection(final Class<? extends Node> node, final UnitOfWork<U, P> from, final UnitOfWork<U, P> to) {
			super();
			this.node = node;
			this.from = from;
			this.to = to;
		}

		public Class<? extends Node> getNode() {
			return node;
		}

		public UnitOfWork<U, P> getFrom() {
			return from;
		}

		public UnitOfWork<U, P> getTo() {
			return to;
		}

	}
}
