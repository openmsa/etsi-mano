package com.ubiqube.etsi.mano.service.graph;

public class ConnectivityEdge<U> {
	private U source;

	private U target;

	public ConnectivityEdge() {
		// Nothing.
	}

	public ConnectivityEdge(final U _source, final U _target) {
		source = _source;
		target = _target;
	}

	public U getSource() {
		return source;
	}

	public void setSource(final U source) {
		this.source = source;
	}

	public U getTarget() {
		return target;
	}

	public void setTarget(final U target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "ConnectivityEdge [source=" + source + ", target=" + target + "]";
	}

}
