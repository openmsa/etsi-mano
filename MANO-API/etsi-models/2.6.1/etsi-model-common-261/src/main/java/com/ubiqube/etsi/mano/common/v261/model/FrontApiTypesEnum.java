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
package com.ubiqube.etsi.mano.common.v261.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum FrontApiTypesEnum {
	SOL003(String.valueOf("SOL003")),
	SOL005(String.valueOf("SOL005"));

	private final String value;

	FrontApiTypesEnum(final String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static FrontApiTypesEnum fromValue(final String v) {
		for (final FrontApiTypesEnum b : FrontApiTypesEnum.values()) {
			if (String.valueOf(b.value).equals(v)) {
				return b;
			}
		}
		return null;
	}

}
