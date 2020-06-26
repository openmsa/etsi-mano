package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ScaleTypeEnum {
	OUT("SCALE_OUT"),

	IN("SCALE_IN");

	private final String value;

	ScaleTypeEnum(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static ScaleTypeEnum fromValue(final String text) {
		for (final ScaleTypeEnum b : ScaleTypeEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
