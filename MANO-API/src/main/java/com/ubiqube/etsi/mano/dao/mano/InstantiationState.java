package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum InstantiationState {
	NOT_INSTANTIATED("NOT_INSTANTIATED"),
	INSTANTIATED("INSTANTIATED");

	private final String value;

	InstantiationState(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static InstantiationState fromValue(final String text) {
		for (final InstantiationState b : InstantiationState.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	public String value() {
		return value;
	}

}
