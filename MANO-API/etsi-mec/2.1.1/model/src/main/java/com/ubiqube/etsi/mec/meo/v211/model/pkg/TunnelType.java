package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of tunnel.
 */
public enum TunnelType {
	GTP_U("GTP-U"),
	GRE("GRE");

	private String value;

	TunnelType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static TunnelType fromValue(final String text) {
		for (final TunnelType b : TunnelType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
