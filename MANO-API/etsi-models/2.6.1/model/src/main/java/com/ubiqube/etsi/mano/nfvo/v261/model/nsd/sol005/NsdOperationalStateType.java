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
package com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The enumeration NsdOperationalStateType shall comply with the provisions
 * defined in Table 5.5.4.3-1 of GS NFV_SOL 005. It indicates the operational
 * state of the resource. ENABLED = The operational state of the resource is
 * enabled. DISABLED = The operational state of the resource is disabled.
 */
public enum NsdOperationalStateType {

	ENABLED("ENABLED"),

	DISABLED("DISABLED");

	private String value;

	NsdOperationalStateType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static NsdOperationalStateType fromValue(final String text) {
		for (final NsdOperationalStateType b : NsdOperationalStateType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
