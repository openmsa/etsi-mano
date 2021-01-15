package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 'Identifies the action of the MEC host data plane, when a packet matches the
 * trafficFilter.'
 */
public enum Action {
	DROP("DROP"),
	FORWARD_DECAPSULATED("FORWARD_DECAPSULATED"),
	FORWARD_AS_IS("FORWARD_AS_IS"),
	PASSTHROUGH("PASSTHROUGH"),
	DUPLICATED_DECAPSULATED("DUPLICATED_DECAPSULATED"),
	DUPLICATE_AS_IS("DUPLICATE_AS_IS");

	private String value;

	Action(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static Action fromValue(final String text) {
		for (final Action b : Action.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
