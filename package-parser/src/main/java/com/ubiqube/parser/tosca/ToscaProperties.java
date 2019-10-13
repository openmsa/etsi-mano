package com.ubiqube.parser.tosca;

import java.util.HashMap;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = PropertyDeserializer.class)
public class ToscaProperties {
	private HashMap<String, ValueObject> properties = new HashMap<>();

	public void setProperties(final HashMap<String, ValueObject> properties) {
		this.properties = properties;
	}

	public HashMap<String, ValueObject> getProperties() {
		return properties;
	}

}
