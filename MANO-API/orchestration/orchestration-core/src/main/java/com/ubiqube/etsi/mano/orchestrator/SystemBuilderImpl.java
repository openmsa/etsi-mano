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

import java.util.Arrays;
import java.util.List;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.orchestrator.nodes.ConnectivityEdge;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWork;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SystemBuilderImpl<U> implements SystemBuilder<U> {
	private final ListenableGraph<UnitOfWork<U>, ConnectivityEdge<UnitOfWork<U>>> g = GraphTools.createGraph();
	private UnitOfWork<U> single = null;

	public static <U> SystemBuilder<U> of(final UnitOfWork<U> vt) {
		final SystemBuilderImpl<U> ib = new SystemBuilderImpl<>();
		ib.single = vt;
		return ib;
	}

	public static <U> SystemBuilder<U> of(final UnitOfWork<U> left, final UnitOfWork<U> right) {
		final SystemBuilderImpl<U> ib = new SystemBuilderImpl<>();
		ib.g.addVertex(left);
		ib.g.addVertex(right);
		ib.g.addEdge(left, right);
		return ib;
	}

	public SystemBuilder<U> edge(final UnitOfWork<U> left, final UnitOfWork<U> right) {
		g.addVertex(left);
		g.addVertex(right);
		g.addEdge(left, right);
		return this;
	}

	@Override
	public List<ConnectivityEdge<UnitOfWork<U>>> getEdges() {
		return g.edgeSet().stream().toList();
	}

	@Override
	public UnitOfWork<U> getSingle() {
		return single;
	}

	public List<UnitOfWork<U>> getVertex() {
		return g.vertexSet().stream().toList();
	}

	@Override
	public List<UnitOfWork<U>> getIncomingVertex() {
		if (null != single) {
			return Arrays.asList(single);
		}
		return g.vertexSet().stream().filter(x -> g.incomingEdgesOf(x).isEmpty()).toList();
	}

	@Override
	public List<UnitOfWork<U>> getOutgoingVertex() {
		if (null != single) {
			return Arrays.asList(single);
		}
		return g.vertexSet().stream().filter(x -> g.outgoingEdgesOf(x).isEmpty()).toList();
	}

	@Override
	public void add(final UnitOfWork<U> src, final UnitOfWork<U> dest) {
		g.addVertex(src);
		g.addVertex(dest);
		g.addEdge(src, dest);
	}
}
