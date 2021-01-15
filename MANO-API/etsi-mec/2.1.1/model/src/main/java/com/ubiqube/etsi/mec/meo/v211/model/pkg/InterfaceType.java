package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of interface.
 */
public enum InterfaceType {
	TUNNEL("TUNNEL"),
	MAC("MAC"),
	IP("IP");

	private String value;

	InterfaceType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static InterfaceType fromValue(final String text) {
		for (final InterfaceType b : InterfaceType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
