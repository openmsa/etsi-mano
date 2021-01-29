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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.Start;
import com.ubiqube.etsi.mano.service.vim.node.nfvo.NsdNode;
import com.ubiqube.etsi.mano.service.vim.node.nfvo.SapNode;
import com.ubiqube.etsi.mano.service.vim.node.nfvo.VnfNode;

public class NsConnections {

	private final List<ConnectivityEdge<Class<? extends Node>>> connections;

	public NsConnections() {
		connections = new ArrayList<>();
		connections.add(new ConnectivityEdge<>(Start.class, VnfNode.class));
		connections.add(new ConnectivityEdge<>(Start.class, NsdNode.class));

		connections.add(new ConnectivityEdge<>(VnfNode.class, SapNode.class));
		connections.add(new ConnectivityEdge<>(NsdNode.class, SapNode.class));
	}

	public List<ConnectivityEdge<Class<? extends Node>>> getConnections() {
		return Collections.unmodifiableList(connections);
	}
}
