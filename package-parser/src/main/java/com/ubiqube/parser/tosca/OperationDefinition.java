package com.ubiqube.parser.tosca;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.JsonNode;

public class OperationDefinition {

	private String description;
	private Object implementation;
	List<ToscaProperties> inputs;
	private String type;

	@JsonAnySetter
	private Map<String, JsonNode> operations;

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Object getImplementation() {
		return implementation;
	}

	public void setImplementation(final Object implementation) {
		this.implementation = implementation;
	}

	public List<ToscaProperties> getInputs() {
		return inputs;
	}

	public void setInputs(final List<ToscaProperties> inputs) {
		this.inputs = inputs;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public Map<String, JsonNode> getOperations() {
		return operations;
	}

	public void setOperations(final Map<String, JsonNode> operations) {
		this.operations = operations;
	}

}
