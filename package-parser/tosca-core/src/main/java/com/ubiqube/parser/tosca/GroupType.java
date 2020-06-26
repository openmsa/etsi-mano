package com.ubiqube.parser.tosca;

import java.util.List;
import java.util.Map;

public class GroupType extends ToscaBaseEntity {
	private ToscaProperties properties;
	private List<String> members;
	private Map<String, ValueObject> attributes;
	// In V1.0, no more in V1.3
	private Object interfaces;

	public ToscaProperties getProperties() {
		return properties;
	}

	public void setProperties(final ToscaProperties properties) {
		this.properties = properties;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(final List<String> members) {
		this.members = members;
	}

	public Map<String, ValueObject> getAttributes() {
		return attributes;
	}

	public void setAttributes(final Map<String, ValueObject> attributes) {
		this.attributes = attributes;
	}

	public Object getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(final Object interfaces) {
		this.interfaces = interfaces;
	}

}
