package com.ubiqube.etsi.mano.dao.mano.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PlanOperationType {
	INSTANTIATE("INSTANTIATE"),

	TERMINATE("TERMINATE"),

	SCALE("SCALE"),
	SCALE_TO_LEVEL("SCALE_TO_LEVEL"),

	CHANGE_FLAVOUR("CHANGE_FLAVOUR"),

	HEAL("HEAL"),
	UPDATE("UPDATE"),
	OPERATE("OPERATE"),

	MODIFY_INFORMATION("MODIFY_INFORMATION"),
	MODIFY_INFO("MODIFY_INFO"),

	CHANGE_EXT_CONN("CHANGE_EXT_CONN"),
	CHANGE_EXTERNAL_VNF_CONNECTIVITY("CHANGE_EXTERNAL_VNF_CONNECTIVITY");

	private final String value;

	PlanOperationType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static PlanOperationType fromValue(final String text) {
		for (final PlanOperationType b : PlanOperationType.values()) {
			if (b.value.equals(text)) {
				return b;
			}
		}
		return null;
	}

}
