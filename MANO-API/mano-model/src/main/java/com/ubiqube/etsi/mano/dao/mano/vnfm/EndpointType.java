package com.ubiqube.etsi.mano.dao.mano.vnfm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public enum EndpointType {
	MGMT("MGMT"),

	VNF("VNF");

	private String value;

	EndpointType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static EndpointType fromValue(final String text) {
		for (final EndpointType b : EndpointType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
