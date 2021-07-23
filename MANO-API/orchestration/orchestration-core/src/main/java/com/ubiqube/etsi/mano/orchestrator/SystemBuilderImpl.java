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
import java.util.stream.Collectors;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedAcyclicGraph;

import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWork;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWorkConnectivity;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SystemBuilderImpl implements SystemBuilder {
	private final ListenableGraph<UnitOfWork, UnitOfWorkConnectivity> g = new DefaultListenableGraph<>(new DirectedAcyclicGraph<>(UnitOfWorkConnectivity.class));
	private UnitOfWork single = null;

	public static SystemBuilder of(final UnitOfWork vt) {
		final SystemBuilderImpl ib = new SystemBuilderImpl();
		ib.single = vt;
		return ib;
	}

	public static SystemBuilder of(final UnitOfWork left, final UnitOfWork right) {
		final SystemBuilderImpl ib = new SystemBuilderImpl();
		ib.g.addVertex(left);
		ib.g.addVertex(right);
		ib.g.addEdge(left, right);
		return ib;
	}

	public SystemBuilder edge(final UnitOfWork left, final UnitOfWork right) {
		g.addVertex(left);
		g.addVertex(right);
		g.addEdge(left, right);
		return this;
	}

	public List<UnitOfWorkConnectivity> getEdges() {
		return g.edgeSet().stream().collect(Collectors.toList());
	}

	@Override
	public UnitOfWork getSingle() {
		return single;
	}

	public List<UnitOfWork> getVertex() {
		return g.vertexSet().stream().collect(Collectors.toList());
	}

	@Override
	public List<UnitOfWork> getIncomingVertex() {
		if (null != single) {
			return Arrays.asList(single);
		}
		return g.vertexSet().stream().filter(x -> g.incomingEdgesOf(x).isEmpty()).collect(Collectors.toList());
	}

	@Override
	public List<UnitOfWork> getOutgoingVertex() {
		if (null != single) {
			return Arrays.asList(single);
		}
		return g.vertexSet().stream().filter(x -> g.outgoingEdgesOf(x).isEmpty()).collect(Collectors.toList());
	}
}
