package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * \"The enumeration PackageOperationalStateType shall comply with the
 * provisions defined in Table 9.5.4.4-1.\" Acceptable values are: - ENABLED -
 * The VNF package is enabled, i.e. it can be used for instantiation of new VNF
 * instances. - DISABLED - The VNF package is disabled, i.e. it cannot be used
 * for further VNF instantiation requests (unless and until the VNF package is
 * re-enabled).
 */
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
