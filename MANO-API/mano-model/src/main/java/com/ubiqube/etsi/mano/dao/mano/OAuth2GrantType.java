package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OAuth2GrantType {
	PASSWORD("password"),
	CLIENT_CREDENTIAL("client_credentials");

	private final String value;

	OAuth2GrantType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static OAuth2GrantType fromValue(final String text) {
		for (final OAuth2GrantType b : OAuth2GrantType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
