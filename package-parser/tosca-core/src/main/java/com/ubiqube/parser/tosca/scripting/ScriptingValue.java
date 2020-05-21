package com.ubiqube.parser.tosca.scripting;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ubiqube.parser.tosca.deserializer.ScriptingDeserializer;

@JsonDeserialize(using = ScriptingDeserializer.class)
public class ScriptingValue {
	private String type;
	private Boolean required = Boolean.TRUE;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(final Boolean required) {
		this.required = required;
	}

}
