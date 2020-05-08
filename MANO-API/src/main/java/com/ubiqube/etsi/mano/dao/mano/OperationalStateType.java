package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OperationalStateType {

	STARTED("STARTED"),

	STOPPED("STOPPED");

	private String value;

	OperationalStateType(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static OperationalStateType fromValue(final String text) {
		for (final OperationalStateType b : OperationalStateType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
