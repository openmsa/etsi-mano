package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PackageOperationalStateType {

	ENABLED("ENABLED"),

	DISABLED("DISABLED");

	private String value;

	PackageOperationalStateType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static PackageOperationalStateType fromValue(final String text) {
		for (final PackageOperationalStateType b : PackageOperationalStateType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
