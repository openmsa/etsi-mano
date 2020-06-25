package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LinkPortType {
	VNFC_CP("VNFC_CP"),

	EXT_CP("EXT_CP");

	private final String value;

	LinkPortType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static LinkPortType fromValue(final String text) {
		for (final LinkPortType b : LinkPortType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
