package com.ubiqube.etsi.mano.service.pkg.bean;

import java.util.List;
import java.util.Map;

public class InstantiationLevels {
	/**
	 * The default instantiation level for this flavour.
	 *
	 */
	private String defaultLevel;
	/**
	 * Describes the various levels of resources that can be used to instantiate the
	 * VNF using this flavour.
	 *
	 */
	private Map<String, InstantiationLevel> levels;

	private List<String> targets;

	public String getDefaultLevel() {
		return defaultLevel;
	}

	public void setDefaultLevel(final String defaultLevel) {
		this.defaultLevel = defaultLevel;
	}

	public Map<String, InstantiationLevel> getLevels() {
		return levels;
	}

	public void setLevels(final Map<String, InstantiationLevel> levels) {
		this.levels = levels;
	}

	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(final List<String> targets) {
		this.targets = targets;
	}

}
