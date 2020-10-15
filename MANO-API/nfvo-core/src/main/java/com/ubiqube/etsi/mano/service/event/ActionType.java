package com.ubiqube.etsi.mano.service.event;

import java.util.stream.Stream;

/**
 * Enum for all asynchronous event.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public enum ActionType {
	VNF_PKG_ONBOARD_FROM_URI(String.valueOf("VNF_PKG_ONBOARD_FROM_URI")),
	VNF_PKG_ONBOARD_FROM_BYTES(String.valueOf("VNF_PKG_ONBOARD_FROM_BYTES")),
	NSD_PKG_ONBOARD_FROM_BYTES(String.valueOf("NSD_PKG_ONBOARD_FROM_BYTES")),
	NS_INSTANTIATE("NS_INSTANTIATE"),
	NS_TERMINATE("NS_TERMINATE"),
	VNF_INSTANTIATE("VNF_INSTANTIATE"),
	VNF_TERMINATE("VNF_TERMINATE"),
	VNF_OPERATE("VNF_OPERATE"),
	VNF_SCALE_TO_LEVEL("VNF_SCALE_TO_LEVEL"),
	GRANT_REQUEST("GRANT_REQUEST"),
	UNKNOW("UNKNOWN"),
	;

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
