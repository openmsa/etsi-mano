package com.ubiqube.parser.tosca;

import java.util.List;
import java.util.Map;

public class PolicyType extends ToscaBaseEntity {
	private Map<String, Object> properties;
	private List<String> targets;
	// Map of Triggers definitions.
	private Object triggers;

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(final Map<String, Object> properties) {
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
