package com.ubiqube.parser.tosca.constraints;

import com.fasterxml.jackson.databind.JsonNode;

public class Pattern extends SimpleValue implements Constraint {

	public Pattern(final JsonNode value) {
		super(value.asText());
	}

}
