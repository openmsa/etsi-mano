package com.ubiqube.parser.tosca.constraints;

import com.fasterxml.jackson.databind.JsonNode;

public class GreaterOrEqual implements Constraint {
	private final String value;
	private final JsonNode node;

	public GreaterOrEqual(final JsonNode _value) {
		value = _value.asText();
		node = _value;
	}

}
