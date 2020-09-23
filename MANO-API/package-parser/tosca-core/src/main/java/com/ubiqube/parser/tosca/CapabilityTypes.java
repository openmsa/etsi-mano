package com.ubiqube.parser.tosca;

import java.util.List;
import java.util.Map;

public class CapabilityTypes extends ToscaBaseEntity {
	private ToscaProperties properties;
	private Map<String, ValueObject> attributes;
	private List<String> valid_source_types;

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

	public List<String> getValid_source_types() {
		return valid_source_types;
	}

	public void setValid_source_types(final List<String> valid_source_types) {
		this.valid_source_types = valid_source_types;
	}

}
