package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Specifies the IP address type
 */
public enum IpAddressType {
	V6("IP_V6"),
	V4("IP_V4");

	private String value;

	IpAddressType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static IpAddressType fromValue(final String text) {
		for (final IpAddressType b : IpAddressType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
