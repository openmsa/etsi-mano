package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration PnfdUsageStateType shall comply with the provisions defined
 * in Table 5.5.4.7-1 of GS NFV-SOL005. It indicates the usage state of the
 * resource.IN-USE = The resource is in use.NOT_IN_USE = The resource is
 * not-in-use.
 */
public enum PnfdUsageStateType {

	IN_USE("IN_USE"),

	NOT_IN_USE("NOT_IN_USE");

	private String value;

	PnfdUsageStateType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static PnfdUsageStateType fromValue(final String text) {
		for (final PnfdUsageStateType b : PnfdUsageStateType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
