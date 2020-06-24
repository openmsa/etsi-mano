package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NsdChangeType {
	ADD("ADD"),

	REMOVE("REMOVE"),

	INSTANTIATE("INSTANTIATE"),

	TERMINATE("TERMINATE"),

	SCALE("SCALE"),
	SCALE_TO_LEVEL("SCALE_TO_LEVEL"),

	CHANGE_FLAVOUR("CHANGE_FLAVOUR"),

	HEAL("HEAL"),

	OPERATE("OPERATE"),

	MODIFY_INFORMATION("MODIFY_INFORMATION"),

	CHANGE_EXT_CONN("CHANGE_EXT_CONN"),
	CHANGE_EXTERNAL_VNF_CONNECTIVITY("CHANGE_EXTERNAL_VNF_CONNECTIVITY");
	private final String value;

	NsdChangeType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static NsdChangeType fromValue(final String text) {
		for (final NsdChangeType b : NsdChangeType.values()) {
			if (b.value.equals(text)) {
				return b;
			}
		}
		return null;
	}

}
