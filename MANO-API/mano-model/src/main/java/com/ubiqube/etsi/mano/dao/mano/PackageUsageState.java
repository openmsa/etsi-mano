package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PackageUsageState {

	IN_USE("IN_USE"),

	NOT_IN_USE("NOT_IN_USE");

	private final String value;

	PackageUsageState(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static PackageUsageState fromValue(final String text) {
		for (final PackageUsageState b : PackageUsageState.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
