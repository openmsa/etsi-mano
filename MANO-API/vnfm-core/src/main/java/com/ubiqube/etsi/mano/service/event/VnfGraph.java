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
package com.ubiqube.etsi.mano.service.event;

import java.util.Set;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.VnfParameters;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;

public class VnfGraph {
	private final ListenableGraph<UnitOfWork<VnfTask, VnfParameters>, ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> graph;

	public VnfGraph(final ListenableGraph<UnitOfWork<VnfTask, VnfParameters>, ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> _graph) {
		graph = _graph;
	}

	public ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>> addEdge(final UnitOfWork<VnfTask, VnfParameters> sourceVertex, final UnitOfWork<VnfTask, VnfParameters> targetVertex) {
		return graph.addEdge(sourceVertex, targetVertex);
	}

	public boolean addEdge(final UnitOfWork<VnfTask, VnfParameters> sourceVertex, final UnitOfWork<VnfTask, VnfParameters> targetVertex, final ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>> e) {
		return graph.addEdge(sourceVertex, targetVertex, e);
	}

	public Set<ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> incomingEdgesOf(final UnitOfWork<VnfTask, VnfParameters> vertex) {
		return graph.incomingEdgesOf(vertex);
	}

	public Set<ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> outgoingEdgesOf(final UnitOfWork<VnfTask, VnfParameters> vertex) {
		return graph.outgoingEdgesOf(vertex);
	}

	public ListenableGraph<UnitOfWork<VnfTask, VnfParameters>, ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> getGraph() {
		return graph;
	}

}
