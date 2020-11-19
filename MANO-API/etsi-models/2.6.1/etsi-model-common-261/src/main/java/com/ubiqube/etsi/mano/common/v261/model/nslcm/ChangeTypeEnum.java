/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
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