package com.ubiqube.etsi.mano.service.graph;

import java.util.ArrayList;
import java.util.List;

import com.ubiqube.etsi.mano.service.vim.node.Network;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.Start;

public class ConnectionStorage {

	public void getDefaultConnections() {
		final List<ConnectivityEdge<Node>> connections = new ArrayList<>();
		connections.add(new ConnectivityEdge<>(new Start(), new Network()));
	}
}
