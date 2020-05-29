package com.ubiqube.etsi.mano.service.graph.nfvo;

import java.util.concurrent.Future;

public class NsConnectivityEdge {
	private NsUnitOfWork source;
	private NsUnitOfWork target;
	private Future<String> future;
	private boolean ended = false;

	public NsUnitOfWork getSource() {
		return source;
	}

	public void setSource(final NsUnitOfWork source) {
		this.source = source;
	}

	public NsUnitOfWork getTarget() {
		return target;
	}

	public void setTarget(final NsUnitOfWork target) {
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
