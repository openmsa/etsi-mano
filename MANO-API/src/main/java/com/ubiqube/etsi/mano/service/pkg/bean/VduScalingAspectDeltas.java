package com.ubiqube.etsi.mano.service.pkg.bean;

import java.util.List;
import java.util.Map;

public class VduScalingAspectDeltas {
	/**
	 * Represents the scaling aspect to which this policy applies
	 * 
	 */
	private String aspect;
	/**
	 * Describes the Vdu.Compute scaling deltas to be applied for every scaling
	 * steps of a particular aspect.
	 * 
	 */
	private Map<String, VduLevel> deltas;
	private List<String> targets;

	public String getAspect() {
		return aspect;
	}

	public void setAspect(final String aspect) {
		this.aspect = aspect;
	}

	public Map<String, VduLevel> getDeltas() {
		return deltas;
	}

	public void setDeltas(final Map<String, VduLevel> deltas) {
		this.deltas = deltas;
	}

	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(final List<String> targets) {
		this.targets = targets;
	}

}
