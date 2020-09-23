package com.ubiqube.parser.tosca;

import java.util.List;

import com.ubiqube.parser.tosca.constraints.Constraint;

public class DataType extends ToscaBaseEntity {
	private List<Constraint> constraints;
	private ToscaProperties properties;
	// 1.3
	private Object key_schema;
	// 1.3
	private Object entry_schema;

	public List<Constraint> getConstraints() {
		return constraints;
	}

	public void setConstraints(final List<Constraint> constraints) {
		this.constraints = constraints;
	}

	public ToscaProperties getProperties() {
		return properties;
	}

	public void setProperties(final ToscaProperties properties) {
		this.properties = properties;
	}

	public Object getKey_schema() {
		return key_schema;
	}

	public void setKey_schema(final Object key_schema) {
		this.key_schema = key_schema;
	}

	public Object getEntry_schema() {
		return entry_schema;
	}

	public void setEntry_schema(final Object entry_schema) {
		this.entry_schema = entry_schema;
	}

}
