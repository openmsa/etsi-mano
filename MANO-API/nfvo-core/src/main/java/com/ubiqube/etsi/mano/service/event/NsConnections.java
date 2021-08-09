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

import com.ubiqube.etsi.mano.orchestrator.nodes.NodeConnectivity;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.NsVlNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.NsdNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.SapNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.VnfNode;
import com.ubiqube.etsi.mano.service.vim.node.Start;

public class NsConnections {

	private final List<NodeConnectivity> connections;

	public NsConnections() {
		connections = new ArrayList<>();
		connections.add(new NodeConnectivity(Start.class, NsVlNode.class));
		connections.add(new NodeConnectivity(NsVlNode.class, VnfNode.class));
		connections.add(new NodeConnectivity(NsVlNode.class, NsdNode.class));

		connections.add(new NodeConnectivity(VnfNode.class, SapNode.class));
		connections.add(new NodeConnectivity(NsdNode.class, SapNode.class));
	}

	public List<NodeConnectivity> getConnections() {
		return Collections.unmodifiableList(connections);
	}
}
