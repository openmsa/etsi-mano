package com.ubiqube.parser.tosca.constraints;

import com.fasterxml.jackson.databind.JsonNode;

public class GreaterThan extends SimpleValue implements Constraint {

	public GreaterThan(final JsonNode jsonNode) {
		super(jsonNode.asText());
	}

}
