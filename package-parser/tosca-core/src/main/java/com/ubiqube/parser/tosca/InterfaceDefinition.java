package com.ubiqube.parser.tosca;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class InterfaceDefinition {
	@JsonAnySetter
	private Map<String, OperationDefinition> operations;
	private String derived_from;
	private String description;

	public Map<String, OperationDefinition> getOperations() {
		return operations;
	}

	public void setOperations(final Map<String, OperationDefinition> operations) {
		this.operations = operations;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getDerived_from() {
		return derived_from;
	}

	public void setDerived_from(final String derived_from) {
		this.derived_from = derived_from;
	}

}
