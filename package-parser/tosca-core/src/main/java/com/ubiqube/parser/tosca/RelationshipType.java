package com.ubiqube.parser.tosca;

import java.util.List;
import java.util.Map;

public class RelationshipType extends ToscaBaseEntity {
	private ToscaProperties properties;
	private Map<String, ValueObject> attributes;
	private Object interfaces;
	private List<String> valid_target_types;

	public ToscaProperties getProperties() {
		return properties;
	}

	public void setProperties(final ToscaProperties properties) {
		this.properties = properties;
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

	public List<String> getValid_target_types() {
		return valid_target_types;
	}

	public void setValid_target_types(final List<String> valid_target_types) {
		this.valid_target_types = valid_target_types;
	}

}
