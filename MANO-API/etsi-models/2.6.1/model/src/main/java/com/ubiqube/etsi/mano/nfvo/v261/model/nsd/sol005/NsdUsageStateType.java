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
 * The enumeration NsdUsageStateType shall comply with the provisions defined in
 * Table 5.5.4.4-1 of GS NFV-SOL 005. It indicates the usage state of the
 * resource.IN_USE = The resource is in use.NOT_IN_USE = The resource is
 * not-in-use.
 */
public enum NsdUsageStateType {

	IN_USE("IN_USE"),

	NOT_IN_USE("NOT_IN_USE");

	private String value;

	NsdUsageStateType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static NsdUsageStateType fromValue(final String text) {
		for (final NsdUsageStateType b : NsdUsageStateType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
