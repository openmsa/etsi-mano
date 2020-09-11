package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CancelModeTypeEnum {

	GRACEFUL("GRACEFUL"),

	FORCEFUL("FORCEFUL");

	private String value;

	CancelModeTypeEnum(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static CancelModeTypeEnum fromValue(final String text) {
		for (final CancelModeTypeEnum b : CancelModeTypeEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
