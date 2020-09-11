package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ChangeType {
	ADDED("ADDED"),
	REMOVED("REMOVED"),
	MODIFIED("MODIFIED"),
	LINK_PORT_ADDED("LINK_PORT_ADDED"),
	LINK_PORT_REMOVED("LINK_PORT_REMOVED"),
	TEMPORARY("TEMPORARY");

	private final String value;

	ChangeType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static ChangeType fromValue(final String text) {
		for (final ChangeType b : ChangeType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
