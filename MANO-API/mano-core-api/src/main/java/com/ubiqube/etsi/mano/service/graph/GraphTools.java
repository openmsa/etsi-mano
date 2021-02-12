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
package com.ubiqube.etsi.mano.service.graph;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.RandomStringUtils;
import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.nio.dot.DOTExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.BaseEntity;
import com.ubiqube.etsi.mano.repository.BinaryRepository;
import com.ubiqube.etsi.mano.service.graph.vnfm.EdgeListener;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;

public class GraphTools {

	private static final Logger LOG = LoggerFactory.getLogger(GraphTools.class);

	private GraphTools() {
		// Nothing.
	}

	public static <U> ListenableGraph<U, ConnectivityEdge<U>> createGraph() {
		final Class<ConnectivityEdge<U>> t = (Class<ConnectivityEdge<U>>) (Object) ConnectivityEdge.class;
		// Vertex everyThing
		final ListenableGraph<U, ConnectivityEdge<U>> g = new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(t));
		g.addGraphListener(new EdgeListener<>());
		return g;
	}

	public static <U extends UnitOfWorkBase> void addEdge(final ListenableGraph<U, ConnectivityEdge<U>> g, final List<U> left, final List<U> right) {
		if ((null == left) || (null == right)) {
			LOG.debug("One or more end point are not in the plan {} <-> {}", left, right);
			return;
		}
		left.forEach(x -> right.forEach(y -> {
			LOG.info("  - Adding {} <-> {}", x, y.getName());
			g.addEdge(x, y);
		}));
	}

	public static <U extends UnitOfWorkBase> void exportGraph(final ListenableGraph<U, ConnectivityEdge<U>> g, @Nonnull final UUID _id, final BaseEntity vnfInstance, final String subName, final BinaryRepository repo) {
		final DOTExporter<U, ConnectivityEdge<U>> exporter = new DOTExporter<>(x -> x.getName().replace('-', '_'));
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		exporter.exportGraph(g, out);
		final byte[] res = out.toByteArray();
		final InputStream stream = new ByteArrayInputStream(res);
		repo.storeBinary(_id, subName + "-" + vnfInstance.getId() + ".dot", stream);
	}

	public static <U extends UnitOfWorkBase> void exportGraph(final ListenableGraph g, final String fileName) {
		final DOTExporter<U, ConnectivityEdge<U>> exporter = new DOTExporter<>(x -> x.getName().replace('-', '_') + "_" + RandomStringUtils.random(5, true, true));
		try (final FileOutputStream out = new FileOutputStream(fileName)) {
			exporter.exportGraph(g, out);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static <U> ListenableGraph<U, ConnectivityEdge<U>> revert(final ListenableGraph<U, ConnectivityEdge<U>> g) {
		final ListenableGraph<U, ConnectivityEdge<U>> gNew = GraphTools.createGraph();
		g.vertexSet().forEach(gNew::addVertex);
		g.edgeSet().forEach(x -> gNew.addEdge(x.getTarget(), x.getSource()));
		return gNew;
	}

	public static <V, U extends UnitOfWorkBase> void addEdge(final ListenableGraph<U, ConnectivityEdge<U>> g, final List<U> left, final U right) {
		if ((null == left) || (null == right)) {
			LOG.debug("One or more end point are not in the plan {} <-> {}", left, right);
			return;
		}
		left.forEach(x -> {
			LOG.info("  - Adding {} <-> {}", x, right.getName());
			g.addEdge(x, right);
		});
	}
}
