package com.ubiqube.parser.tosca.constraints;

import com.fasterxml.jackson.databind.JsonNode;

public class GreaterOrEqual extends SimpleValue implements Constraint {

	public GreaterOrEqual(final JsonNode _value) {
		super(_value.asText());
	}

}
