package com.ubiqube.parser.tosca;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputBean {

	private String type;
	private String def;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	@JsonProperty("default")
	public String getDef() {
		return def;
	}

	public void setDef(final String def) {
		this.def = def;
	}

	@Override
	public String toString() {
		return "InputBean [type=" + type + ", def=" + def + "]";
	}

}
