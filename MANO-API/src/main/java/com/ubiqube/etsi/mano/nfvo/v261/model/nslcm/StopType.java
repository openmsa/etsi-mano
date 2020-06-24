package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * * FORCEFUL: The VNFM will stop the VNF immediately after accepting the
 * request. * GRACEFUL: The VNFM will first arrange to take the VNF out of
 * service after accepting the request. Once that operation is successful or
 * once the timer value specified in the \"gracefulStopTimeout\" attribute
 * expires, the VNFM will stop the VNF.
 */
public enum StopType {

	FORCEFUL("FORCEFUL"),

	GRACEFUL("GRACEFUL");

	private String value;

	StopType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static StopType fromValue(final String text) {
		for (final StopType b : StopType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
