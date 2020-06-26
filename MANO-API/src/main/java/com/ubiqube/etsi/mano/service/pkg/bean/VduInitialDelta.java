package com.ubiqube.etsi.mano.service.pkg.bean;

import java.util.List;

public class VduInitialDelta {
	private VduLevel initialDelta;
	private List<String> targets;

	public VduLevel getInitialDelta() {
		return initialDelta;
	}

	public void setInitialDelta(final VduLevel initialDelta) {
		this.initialDelta = initialDelta;
	}

	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(final List<String> targets) {
		this.targets = targets;
	}

}
