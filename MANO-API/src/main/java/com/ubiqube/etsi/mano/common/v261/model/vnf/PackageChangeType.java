package com.ubiqube.etsi.mano.common.v261.model.vnf;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration PackageChangeType shall comply with the provisions defined in
 * Table 9.5.4.6-1. Permitted Values: - OP_STATE_CHANGE: The
 * \"operationalState\" attribute has been changed. - PKG_DELETE: The VNF
 * package has been deleted.
 */
public enum PackageChangeType {

	OP_STATE_CHANGE("OP_STATE_CHANGE"),

	PKG_DELETE("PKG_DELETE");

	private String value;

	PackageChangeType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static PackageChangeType fromValue(final String text) {
		for (final PackageChangeType b : PackageChangeType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
