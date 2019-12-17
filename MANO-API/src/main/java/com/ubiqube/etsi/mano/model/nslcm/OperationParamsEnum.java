package com.ubiqube.etsi.mano.model.nslcm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OperationParamsEnum {
	INSTANTIATE("INSTANTIATE"),

	SCALE("SCALE"),

	UPDATE("UPDATE"),

	HEAL("HEAL"),

	TERMINATE("TERMINATE");

	private final String value;

	OperationParamsEnum(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static OperationParamsEnum fromValue(final String text) {
		for (final OperationParamsEnum b : OperationParamsEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
