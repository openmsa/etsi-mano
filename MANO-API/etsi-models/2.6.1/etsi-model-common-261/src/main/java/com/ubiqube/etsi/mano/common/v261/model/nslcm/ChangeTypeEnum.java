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
package com.ubiqube.etsi.mano.common.v261.model.nslcm;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED *
 * TEMPORARY For a temporary resource, an AffectedVnfc structure exists as long
 * as the temporary resource exists.
 */
public enum ChangeTypeEnum {
	ADDED("ADDED"),

	REMOVED("REMOVED"),

	MODIFIED("MODIFIED"),

	TEMPORARY("TEMPORARY");

	private final String value;

	ChangeTypeEnum(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static ChangeTypeEnum fromValue(final String text) {
		for (final ChangeTypeEnum b : ChangeTypeEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}