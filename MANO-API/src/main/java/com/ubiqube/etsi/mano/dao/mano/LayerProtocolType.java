package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LayerProtocolType {
	IP_OVER_ETHERNET("IP_OVER_ETHERNET");

	private final String value;

	LayerProtocolType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static LayerProtocolType fromValue(final String text) {
		for (final LayerProtocolType b : LayerProtocolType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
