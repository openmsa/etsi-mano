package com.ubiqube.etsi.mano.model.nsd.sol005.notification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration NsdOperationalStateType shall comply with the provisions
 * defined in Table 5.5.4.3-1 of GS NFV_SOL 005. It indicates the operational
 * state of the resource. ENABLED = The operational state of the resource is
 * enabled. DISABLED = The operational state of the resource is disabled.
 */
public enum NsdOperationalStateType {

	ENABLED("ENABLED"),

	DISABLED("DISABLED");

	private String value;

	NsdOperationalStateType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static NsdOperationalStateType fromValue(final String text) {
		for (final NsdOperationalStateType b : NsdOperationalStateType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
