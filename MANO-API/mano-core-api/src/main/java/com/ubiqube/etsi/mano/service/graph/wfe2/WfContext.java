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

import java.util.List;
import java.util.Set;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.service.graph.WfProduce;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;

public class WfContext<U extends Task, P> {
	private final ListenableGraph<UnitOfWork<U, P>, ConnectivityEdge<UnitOfWork<U, P>>> g;
	private final UnitOfWork<U, P> origin;

	public WfContext(final UnitOfWork<U, P> _origin, final ListenableGraph<UnitOfWork<U, P>, ConnectivityEdge<UnitOfWork<U, P>>> _g) {
		origin = _origin;
		g = _g;
	}

	public String getProducedValue(final Class<?> node) {
		final Set<ConnectivityEdge<UnitOfWork<U, P>>> edges = g.incomingEdgesOf(origin);
		for (final ConnectivityEdge<UnitOfWork<U, P>> connectivityEdge : edges) {
			final List<WfProduce> produced = connectivityEdge.getSource().getProduce();
			for (final WfProduce produce : produced) {
				if (produce.getNode() == node) {
					return connectivityEdge.getSource().getTaskEntity().getVimResourceId();
				}
			}
		}
		return null;
	}
}
