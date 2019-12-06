package com.ubiqube.etsi.mano.model.nslcm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration NsLcmOpType represents those lifecycle operations that
 * trigger a NS lifecycle management operation occurrence notification. Value |
 * Description ------|------------ INSTANTIATE | Represents the \"Instantiate
 * NS\" LCM operation. SCALE | Represents the \"Scale NS\" LCM operation. UPDATE
 * | Represents the \"Update NS\" LCM operation. TERMINATE | Represents the
 * \"Terminate NS\" LCM operation. HEAL | Represents the \"Heal NS\" LCM
 * operation.
 */
public enum NsLcmOpType {

	INSTANTIATE("INSTANTIATE"),

	SCALE("SCALE"),

	UPDATE("UPDATE"),

	TERMINATE("TERMINATE"),

	HEAL("HEAL");

	private String value;

	NsLcmOpType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static NsLcmOpType fromValue(final String text) {
		for (final NsLcmOpType b : NsLcmOpType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
