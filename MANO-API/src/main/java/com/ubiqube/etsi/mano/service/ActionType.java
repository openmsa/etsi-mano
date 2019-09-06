package com.ubiqube.etsi.mano.service;

import java.util.stream.Stream;

public enum ActionType {
	VNF_PKG_ONBOARD_FROM_URI(String.valueOf("VNF_PKG_ONBOARD_FROM_URI")),
	VNF_PKG_ONBOARD_FROM_BYTES(String.valueOf("VNF_PKG_ONBOARD_FROM_BYTES"));

	private String value;

	ActionType(final String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static ActionType fromValue(final String v) {
		return Stream.of(ActionType.values())
				.filter(x -> String.valueOf(x.value).equals(v))
				.findFirst()
				.orElse(null);
	}
}
