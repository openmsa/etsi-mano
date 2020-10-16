package com.ubiqube.parser.tosca;

import java.util.List;

public class PolicyType extends ToscaBaseEntity {
	private ToscaProperties properties;
	private List<String> targets;
	// Map of Triggers definitions.
	private Object triggers;

	public ToscaProperties getProperties() {
		return properties;
	}

	public void setProperties(final ToscaProperties properties) {
		this.properties = properties;
	}

	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(final List<String> targets) {
		this.targets = targets;
	}

	public Object getTriggers() {
		return triggers;
	}

	public void setTriggers(final Object triggers) {
		this.triggers = triggers;
	}

}
