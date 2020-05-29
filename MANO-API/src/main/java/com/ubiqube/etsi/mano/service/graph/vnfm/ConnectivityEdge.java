package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.concurrent.Future;

public class ConnectivityEdge {
	private UnitOfWork source;
	private UnitOfWork target;
	private Future<String> future;
	private boolean ended = false;

	public UnitOfWork getSource() {
		return source;
	}

	public void setSource(final UnitOfWork source) {
		this.source = source;
	}

	public UnitOfWork getTarget() {
		return target;
	}

	public void setTarget(final UnitOfWork target) {
		this.target = target;
	}

	public Future<String> getFuture() {
		return future;
	}

	public void setFuture(final Future<String> future) {
		this.future = future;
	}

	@Override
	public String toString() {
		return "ConnectivityEdge [source=" + source + ", target=" + target + "]";
	}

	public boolean isEnded() {
		return ended;
	}

	public void setEnded(final boolean ended) {
		this.ended = ended;
	}

}
