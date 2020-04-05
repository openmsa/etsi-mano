package com.ubiqube.parser.tosca;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ubiqube.parser.tosca.deserializer.OperationDeserializer;

@JsonDeserialize(using = OperationDeserializer.class)
public class OperationDefinition {

	private String description;
	private Object implementation;
	private Map<String, Object> inputs;
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

	public Map<String, Object> getInputs() {
		return inputs;
	}

	public void setInputs(final Map<String, Object> inputs) {
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
