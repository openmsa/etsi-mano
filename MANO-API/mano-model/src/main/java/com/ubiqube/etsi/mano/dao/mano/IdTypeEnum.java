package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum IdTypeEnum {
	RES_MGMT("RES_MGMT"),

	GRANT("GRANT");

	private final String value;

	IdTypeEnum(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static IdTypeEnum fromValue(final String text) {
		for (final IdTypeEnum b : IdTypeEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
