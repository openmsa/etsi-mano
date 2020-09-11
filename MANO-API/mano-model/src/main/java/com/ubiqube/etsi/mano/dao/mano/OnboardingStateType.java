package com.ubiqube.etsi.mano.dao.mano;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OnboardingStateType {

	CREATED("CREATED"),

	UPLOADING("UPLOADING"),

	PROCESSING("PROCESSING"),

	ONBOARDED("ONBOARDED");

	private String value;

	OnboardingStateType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static OnboardingStateType fromValue(final String text) {
		for (final OnboardingStateType b : OnboardingStateType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
