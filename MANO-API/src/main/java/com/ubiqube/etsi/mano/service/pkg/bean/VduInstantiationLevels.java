package com.ubiqube.etsi.mano.service.pkg.bean;

import java.util.List;
import java.util.Map;

public class VduInstantiationLevels {
	private Map<String, VduLevel> levels;

	private List<String> targets;

	private String internalName;

	public Map<String, VduLevel> getLevels() {
		return levels;
	}

	public void setLevels(final Map<String, VduLevel> levels) {
		this.levels = levels;
	}

	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(final List<String> targets) {
		this.targets = targets;
	}

	public String getInternalName() {
		return internalName;
	}

	public void setInternalName(final String internalName) {
		this.internalName = internalName;
	}

}
