package com.ubiqube.etsi.mano.service.graph.vnfm;

import org.jgrapht.event.GraphEdgeChangeEvent;
import org.jgrapht.event.GraphListener;
import org.jgrapht.event.GraphVertexChangeEvent;

public class EdgeListener<V> implements GraphListener<V, ConnectivityEdge> {

	@Override
	public void vertexAdded(final GraphVertexChangeEvent<V> e) {
		// Nothng.
	}

	@Override
	public void vertexRemoved(final GraphVertexChangeEvent<V> e) {
		// Nothng.
	}

	@Override
	public void edgeAdded(final GraphEdgeChangeEvent<V, ConnectivityEdge> e) {
		final ConnectivityEdge edge = e.getEdge();
		edge.setSource((UnitOfWork) e.getEdgeSource());
		edge.setTarget((UnitOfWork) e.getEdgeTarget());
	}

	@Override
	public void edgeRemoved(final GraphEdgeChangeEvent<V, ConnectivityEdge> e) {
		// Nothng.
	}

}
