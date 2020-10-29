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
package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration LcmOpOccStatusForChangeNotificationType represents the status
 * of the lifecycle management operation occurrence that impacts the NS
 * component and triggers an NS change notification. It shall comply with the
 * provisions defined in Table 6.5.4.7-1. Value | Description
 * ------|------------ START | The impact on the NS component is identified.
 * COMPLETED | The impact on the NS component stops and related lifecycle
 * operation completes successfully. PARTIALLY_COMPLETED | The impact on the NS
 * component stops and related lifecycle operation partially completes.
 * Inconsistency state may exist on the NS component. FAILED | The impact on the
 * NS component stops and related lifecycle operation fails. Inconsistency state
 * may exist for the NS component. ROLLED_BACK | The impact on the NS component
 * stops and related lifecycle operation is rolled back.
 */
public enum LcmOpOccStatusForChangeNotificationType {

	START("START"),

	COMPLETED("COMPLETED"),

	PARTIALLY_COMPLETED("PARTIALLY_COMPLETED"),

	FAILED("FAILED"),

	ROLLED_BACK("ROLLED_BACK");

	private String value;

	LcmOpOccStatusForChangeNotificationType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static LcmOpOccStatusForChangeNotificationType fromValue(final String text) {
		for (final LcmOpOccStatusForChangeNotificationType b : LcmOpOccStatusForChangeNotificationType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
