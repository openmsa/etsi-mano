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

import com.ubiqube.etsi.mano.orchestrator.nodes.ConnectivityEdge;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWork;
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

	public static <U> ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> createGraph() {
		// Vertex everyThing
		final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> g = (ListenableGraph) (Object) new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(ConnectivityEdge.class));
		g.addGraphListener(new UnitOfWorkVertexListener<>());
		return g;
	}

	public static <U> ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> revert(final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> g) {
		final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> gNew = createGraph();
		g.vertexSet().forEach(gNew::addVertex);
		g.edgeSet().forEach(x -> gNew.addEdge(x.getTarget(), x.getSource()));
		return gNew;
	}

	public static <U> void dump(final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> g) {
		g.vertexSet().forEach(x -> LOG.debug("v: {}", x.getTask().getName()));
		g.edgeSet().forEach(x -> LOG.debug("e: {} => {}", x.getSource().getTask().getName(), x.getTarget().getTask().getName()));
	}

	public static <U> void dumpVt(final ListenableGraph<VirtualTask<U>, VirtualTaskConnectivity<U>> g) {
		g.edgeSet().forEach(x -> LOG.debug("vt: {} => {}", x.getSource().getName(), x.getTarget().getName()));
	}
}
