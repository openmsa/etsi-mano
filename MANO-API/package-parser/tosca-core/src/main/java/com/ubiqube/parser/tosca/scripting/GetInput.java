package com.ubiqube.parser.tosca.scripting;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.ubiqube.parser.tosca.ParseException;

public class GetInput extends ScriptingValue {

	private List<String> properties = new ArrayList<>();

	public GetInput(final JsonNode value) {
		if (value.isTextual()) {
			properties.add(value.asText());
		} else {
			throw new ParseException("Unable to parse GetInput value : " + value);
		}
	}

	public List<String> getProperties() {
		return properties;
	}

	public void setProperties(final List<String> properties) {
		this.properties = properties;
	}

}
