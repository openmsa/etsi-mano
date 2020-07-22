package com.ubiqube.etsi.mano.common.v261.model.nslcm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The instantiation state of the VNF.
 */
public enum InstantiationStateEnum {
	NOT_INSTANTIATED("NOT_INSTANTIATED"),
	INSTANTIATED("INSTANTIATED");

	private final String value;

	InstantiationStateEnum(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static InstantiationStateEnum fromValue(final String text) {
		for (final InstantiationStateEnum b : InstantiationStateEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	public String value() {
		return value;
	}

}
