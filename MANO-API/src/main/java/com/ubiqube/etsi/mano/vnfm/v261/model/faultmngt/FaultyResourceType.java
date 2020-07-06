package com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration FaultyResourceType represents those types of faulty resource.
 */
public enum FaultyResourceType {

	COMPUTE("COMPUTE"),

	STORAGE("STORAGE"),

	NETWORK("NETWORK");

	private String value;

	FaultyResourceType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static FaultyResourceType fromValue(final String text) {
		for (final FaultyResourceType b : FaultyResourceType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
