package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ResourceTypeEnum {
	COMPUTE("COMPUTE"),

	VL("VL"),

	STORAGE("STORAGE"),

	LINKPORT("LINKPORT");

	private final String value;

	ResourceTypeEnum(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static ResourceTypeEnum fromValue(final String text) {
		for (final ResourceTypeEnum b : ResourceTypeEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
