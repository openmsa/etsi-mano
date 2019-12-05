package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * STARTED - The VNF instance is up and running. STOPPED - The VNF instance has
 * been shut down.
 */
public enum OperationalStates {

	STARTED("STARTED"),

	STOPPED("STOPPED");

	private String value;

	OperationalStates(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static OperationalStates fromValue(final String text) {
		for (final OperationalStates b : OperationalStates.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
