package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration PnfdOnboardingStateType shall comply with the provisions
 * defined in Table 5.5.4.6-1 of GS-NFV SOL005. It indicates the on-boarding
 * state of the individual PNF descriptor resource. CREATED = The PNF descriptor
 * resource is created. UPLOADING = The associated PNFD content is being
 * uploaded. PROCESSING = The associated PNFD content is being processed, e.g.
 * validation. ONBOARDED = The associated PNFD content is on-boarded.
 */
public enum PnfdOnboardingStateType {

	CREATED("CREATED"),

	UPLOADING("UPLOADING"),

	PROCESSING("PROCESSING"),

	ONBOARDING("ONBOARDING");

	private String value;

	PnfdOnboardingStateType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static PnfdOnboardingStateType fromValue(final String text) {
		for (final PnfdOnboardingStateType b : PnfdOnboardingStateType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
