package com.ubiqube.etsi.mano.service.graph.vnfm;

import org.jgrapht.event.GraphEdgeChangeEvent;
import org.jgrapht.event.GraphListener;
import org.jgrapht.event.GraphVertexChangeEvent;

import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;

public class EdgeListener<V> implements GraphListener<V, ConnectivityEdge<V>> {

	@Override
	public void vertexAdded(final GraphVertexChangeEvent<V> e) {
		// Nothng.
	}

	@Override
	public void vertexRemoved(final GraphVertexChangeEvent<V> e) {
		// Nothng.
	}

	@Override
	public void edgeAdded(final GraphEdgeChangeEvent<V, ConnectivityEdge<V>> e) {
		final ConnectivityEdge edge = e.getEdge();
		edge.setSource(e.getEdgeSource());
		edge.setTarget(e.getEdgeTarget());
	}

	@Override
	public void edgeRemoved(final GraphEdgeChangeEvent<V, ConnectivityEdge<V>> e) {
		// Nothng.
	}

}
