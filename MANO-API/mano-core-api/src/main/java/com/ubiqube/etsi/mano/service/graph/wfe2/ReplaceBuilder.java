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
import java.util.List;

import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Network;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ReplaceBuilder {
	private final List<ConnectivityEdge<Class<? extends Node>>> edges = new ArrayList<>();
	private final Class<? extends Node> contributor;

	public ReplaceBuilder(final Class<? extends Node> contributor2) {
		contributor = contributor2;
	}

	public ReplaceBuilder connectTo(final Class<? extends Node> class1) {
		edges.add(new ConnectivityEdge<>(contributor, class1));
		return this;
	}

	public List<ConnectivityEdge<Class<? extends Node>>> getEdges() {
		return edges;
	}

	public ReplaceBuilder connectFrom(final Class<Network> class1) {
		edges.add(new ConnectivityEdge<>(class1, contributor));
		return this;
	}

}
