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
package com.ubiqube.etsi.mano.nfvo.v261.model.nsd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration NsdOnboardingStateType shall comply with the provisions
 * defined in Table 5.5.4.5-1 of GS NFV-SOL 005. It indicates the on-boarding
 * state of the NSD. CREATED = The NSD information object is created. UPLOADING
 * = The associated NSD content is being uploaded. PROCESSING = The associated
 * NSD content is being processed, e.g. validation. ONBOARDED = The associated
 * NSD content is on-boarded.
 */
public enum NsdOnboardingStateType {

	CREATED("CREATED"),

	UPLOADING("UPLOADING"),

	PROCESSING("PROCESSING"),

	ONBOARDED("ONBOARDED");

	private String value;

	NsdOnboardingStateType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static NsdOnboardingStateType fromValue(final String text) {
		for (final NsdOnboardingStateType b : NsdOnboardingStateType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
