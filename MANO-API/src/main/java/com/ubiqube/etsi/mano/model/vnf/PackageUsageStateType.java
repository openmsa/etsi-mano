package com.ubiqube.etsi.mano.model.vnf;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * \"The enumeration PackageUsageStateType shall comply with the provisions.
 * Acceptable values are: -IN_USE - VNF instances instantiated from this VNF
 * package exist. -NOT_IN_USE - No existing VNF instance is instantiated from
 * this VNF package\"
 */
public enum PackageUsageStateType {

	IN_USE("IN_USE"),

	NOT_IN_USE("NOT_IN_USE");

	private String value;

	PackageUsageStateType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static PackageUsageStateType fromValue(final String text) {
		for (final PackageUsageStateType b : PackageUsageStateType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
