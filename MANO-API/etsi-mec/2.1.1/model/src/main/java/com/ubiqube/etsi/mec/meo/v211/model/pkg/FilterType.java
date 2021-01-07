package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Definition of filter type: per FLOW or PACKET
 */
public enum FilterType {
	FLOW("FLOW"),
	PACKET("PACKET");

	private String value;

	FilterType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static FilterType fromValue(final String text) {
		for (final FilterType b : FilterType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
