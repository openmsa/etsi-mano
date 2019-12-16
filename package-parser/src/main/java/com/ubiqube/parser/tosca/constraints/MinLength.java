package com.ubiqube.parser.tosca.constraints;

import com.fasterxml.jackson.databind.JsonNode;

public class MinLength extends SimpleValue implements Constraint {

	public MinLength(final JsonNode value) {
		super(value.asText());
	}

}
