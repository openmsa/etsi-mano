package com.ubiqube.etsi.mano.service.graph.nfvo;

import org.jgrapht.event.GraphEdgeChangeEvent;
import org.jgrapht.event.GraphListener;
import org.jgrapht.event.GraphVertexChangeEvent;

public class NsEdgeListener<V> implements GraphListener<V, NsConnectivityEdge> {

	@Override
	public void vertexAdded(final GraphVertexChangeEvent<V> e) {
		// Nothng.
	}

	@Override
	public void vertexRemoved(final GraphVertexChangeEvent<V> e) {
		// Nothng.
	}

	@Override
	public void edgeAdded(final GraphEdgeChangeEvent<V, NsConnectivityEdge> e) {
		final NsConnectivityEdge edge = e.getEdge();
		edge.setSource((NsUnitOfWork) e.getEdgeSource());
		edge.setTarget((NsUnitOfWork) e.getEdgeTarget());
	}

	@Override
	public void edgeRemoved(final GraphEdgeChangeEvent<V, NsConnectivityEdge> e) {
		// Nothng.
	}

}
