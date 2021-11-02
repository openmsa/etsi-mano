package com.ubiqube.etsi.mano.dao.mano.nfvo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ArchiveSecurityOptionEnumType {
	_1("OPTION_1"),

	_2("OPTION_2");

	private final String value;

	ArchiveSecurityOptionEnumType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static ArchiveSecurityOptionEnumType fromValue(final String text) {
		for (final ArchiveSecurityOptionEnumType b : ArchiveSecurityOptionEnumType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
