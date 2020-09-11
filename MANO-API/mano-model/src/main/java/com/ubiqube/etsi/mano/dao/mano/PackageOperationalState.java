package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PackageOperationalState {

	ENABLED("ENABLED"),

	DISABLED("DISABLED");

	private String value;

	PackageOperationalState(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static PackageOperationalState fromValue(final String text) {
		for (final PackageOperationalState b : PackageOperationalState.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
