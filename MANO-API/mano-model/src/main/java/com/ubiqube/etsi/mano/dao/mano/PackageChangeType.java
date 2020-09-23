package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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
