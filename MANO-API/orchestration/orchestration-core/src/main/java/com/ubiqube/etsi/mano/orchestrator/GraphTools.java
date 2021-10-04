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

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWork;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWorkConnectivity;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWorkVertexListener;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTaskConnectivity;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class GraphTools {

	private static final Logger LOG = LoggerFactory.getLogger(GraphTools.class);

	private GraphTools() {
		// Nothing.
	}

	public static ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> createGraph() {
		// Vertex everyThing
		final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> g = new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(UnitOfWorkConnectivity.class));
		g.addGraphListener(new UnitOfWorkVertexListener());
		return g;
	}

	public static ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> revert(final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> g) {
		final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> gNew = createGraph();
		g.vertexSet().forEach(gNew::addVertex);
		g.edgeSet().forEach(x -> gNew.addEdge(x.getTarget(), x.getSource()));
		return gNew;
	}

	public static void dump(final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> g) {
		g.edgeSet().forEach(x -> LOG.debug(" {} => {}", x.getSource(), x.getTarget()));
	}

	public static void dumpVt(final ListenableGraph<VirtualTask<?>, VirtualTaskConnectivity> g) {
		g.edgeSet().forEach(x -> LOG.debug(" {} => {}", x.getSource(), x.getTarget()));
	}
}
