/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Indicates the relative level of urgency for operator attention. * CRITICAL:
 * The Critical severity level indicates that a service affecting condition has
 * occurred and an immediate corrective action is required. Such a severity can
 * be reported, for example, when a managed object becomes totally out of
 * service and its capability needs to be restored (ITU-T Recommendation X.733).
 * * MAJOR: The Major severity level indicates that a service affecting
 * condition has developed and an urgent corrective action is required. Such a
 * severity can be reported, for example, when there is a severe degradation in
 * the capability of the managed object and its full capability needs to be
 * restored (ITU-T Recommendation X.733). * MINOR: The Minor severity level
 * indicates the existence of a non-service affecting fault condition and that
 * corrective action should be taken in order to prevent a more serious (for
 * example, service affecting) fault. Such a severity can be reported, for
 * example, when the detected alarm condition is not currently degrading the
 * capacity of the managed object (ITU-T Recommendation X.733). * WARNING: The
 * Warning severity level indicates the detection of a potential or impending
 * service affecting fault, before any significant effects have been felt.
 * Action should be taken to further diagnose (if necessary) and correct the
 * problem in order to prevent it from becoming a more serious service affecting
 * fault (ITU-T Recommendation X.733). * INDETERMINATE: The Indeterminate
 * severity level indicates that the severity level cannot be determined (ITU-T
 * Recommendation X.733). * CLEARED: The Cleared severity level indicates the
 * clearing of one or more previously reported alarms. This alarm clears all
 * alarms for this managed object that have the same Alarm type, Probable cause
 * and Specific problems (if given) (ITU-T Recommendation X.733).
 */
public enum PerceivedSeverityType {

	CRITICAL("CRITICAL"),

	MAJOR("MAJOR"),

	MINOR("MINOR"),

	WARNING("WARNING"),

	INDETERMINATE("INDETERMINATE"),

	CLEARED("CLEARED");

	private String value;

	PerceivedSeverityType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static PerceivedSeverityType fromValue(final String text) {
		for (final PerceivedSeverityType b : PerceivedSeverityType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
