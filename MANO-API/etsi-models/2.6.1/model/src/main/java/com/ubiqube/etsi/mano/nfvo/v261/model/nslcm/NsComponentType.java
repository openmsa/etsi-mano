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
 * The enumeration NsComponentType represents the NS component type. It shall
 * comply with the provisions defined in Table 6.5.4.5-1. Value | Description
 * ------|------------ VNF | Represents the impacted NS component is a VNF. PNF
 * | Represents the impacted NS component is a PNF. NS | Represents the impacted
 * NS component is a nested NS.
 */
public enum NsComponentType {

	VNF("VNF"),

	PNF("PNF"),

	NS("NS");

	private String value;

	NsComponentType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static NsComponentType fromValue(final String text) {
		for (final NsComponentType b : NsComponentType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
