package com.ubiqube.parser.tosca.scripting;

public class SimpleString extends ScriptingValue {
	private String value;

	public SimpleString(final String key) {
		value = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

}
