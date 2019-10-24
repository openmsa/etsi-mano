package com.ubiqube.etsi.mano.model.nslcm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED *
 * TEMPORARY For a temporary resource, an AffectedVnfc structure exists as long
 * as the temporary resource exists.
 */
public enum ChangeTypeEnum {
	ADDED("ADDED"),

	REMOVED("REMOVED"),

	MODIFIED("MODIFIED"),

	TEMPORARY("TEMPORARY");

	private final String value;

	ChangeTypeEnum(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static ChangeTypeEnum fromValue(final String text) {
		for (final ChangeTypeEnum b : ChangeTypeEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}