package com.ubiqube.parser.tosca.constraints;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class ValidValues implements Constraint {
	private final List<String> values = new ArrayList<>();

	public ValidValues(final ArrayNode value) {
		final Iterator<JsonNode> ite = value.iterator();
		while (ite.hasNext()) {
			final JsonNode node = ite.next();
			values.add(node.asText());
		}
	}

}
