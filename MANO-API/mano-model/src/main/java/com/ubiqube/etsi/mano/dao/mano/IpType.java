package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum IpType {
	IPV4("IPV4"),

	IPV6("IPV6");

	private final String value;

	IpType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static IpType fromValue(final String text) {
		for (final IpType b : IpType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
