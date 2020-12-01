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
package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.Start;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Compute;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.DnsHost;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.DnsZone;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Monitoring;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Network;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.ObjectStorage;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Storage;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.SubNetwork;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.VnfExtCp;

public class VnfConnections {
	private final List<ConnectivityEdge<Node>> connections;

	public VnfConnections() {
		connections = new ArrayList<>();
		connections.add(new ConnectivityEdge<>(new Start(), new DnsZone()));
		connections.add(new ConnectivityEdge<>(new Start(), new ObjectStorage()));
		// Network.
		connections.add(new ConnectivityEdge<>(new DnsZone(), new Network()));
		connections.add(new ConnectivityEdge<>(new Network(), new Storage()));
		connections.add(new ConnectivityEdge<>(new Network(), new SubNetwork()));
		// Compute Join
		connections.add(new ConnectivityEdge<>(new Storage(), new Compute()));
		connections.add(new ConnectivityEdge<>(new SubNetwork(), new Compute()));
		// Monitoring.
		connections.add(new ConnectivityEdge<>(new Compute(), new DnsHost()));
		connections.add(new ConnectivityEdge<>(new Compute(), new Monitoring()));
		connections.add(new ConnectivityEdge<>(new DnsHost(), new VnfExtCp()));
	}

	public void insertAfter(final Class<? extends Node> origin, final Node toInsert) {
		final List<ConnectivityEdge<Node>> incoming = connections.stream().filter(x -> x.getSource().getClass() == origin).collect(Collectors.toList());
		if (!incoming.isEmpty()) {
			incoming.forEach(x -> {
				final Node oldTarget = x.getTarget();
				x.setTarget(toInsert);
				connections.add(new ConnectivityEdge<>(toInsert, oldTarget));
			});
		}
	}

	public List<ConnectivityEdge<Node>> getConnections() {
		return Collections.unmodifiableList(connections);
	}
}