package com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration EventType represents those types of events that trigger an
 * alarm. * COMMUNICATIONS_ALARM: An alarm of this type is associated with the
 * procedure and/or process required conveying information from one point to
 * another (ITU-T Recommendation X.733). * PROCESSING_ERROR_ALARM: An alarm of
 * this type is associated with a software or processing fault (ITU-T
 * Recommendation X.733). * ENVIRONMENTAL_ALARM: An alarm of this type is
 * associated with a condition related to an enclosure in which the equipment
 * resides (ITU-T Recommendation X.733). * QOS_ALARM: An alarm of this type is
 * associated with degradation in the quality of a service (ITU-T Recommendation
 * X.733). * EQUIPMENT_ALARM: An alarm of this type is associated with an
 * equipment fault (ITU-T Recommendation X.733).
 */
public enum EventType {

	COMMUNICATIONS_ALARM("COMMUNICATIONS_ALARM"),

	PROCESSING_ERROR_ALARM("PROCESSING_ERROR_ALARM"),

	ENVIRONMENTAL_ALARM("ENVIRONMENTAL_ALARM"),

	QOS_ALARM("QOS_ALARM"),

	EQUIPMENT_ALARM("EQUIPMENT_ALARM");

	private String value;

	EventType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static EventType fromValue(final String text) {
		for (final EventType b : EventType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
