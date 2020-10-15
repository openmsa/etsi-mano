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
package com.ubiqube.etsi.mano.dao.mano.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PlanOperationType {
	INSTANTIATE("INSTANTIATE"),

	TERMINATE("TERMINATE"),

	SCALE("SCALE"),
	SCALE_TO_LEVEL("SCALE_TO_LEVEL"),

	CHANGE_FLAVOUR("CHANGE_FLAVOUR"),

	HEAL("HEAL"),
	UPDATE("UPDATE"),
	OPERATE("OPERATE"),

	MODIFY_INFORMATION("MODIFY_INFORMATION"),
	MODIFY_INFO("MODIFY_INFO"),

	CHANGE_EXT_CONN("CHANGE_EXT_CONN"),
	CHANGE_EXTERNAL_VNF_CONNECTIVITY("CHANGE_EXTERNAL_VNF_CONNECTIVITY");

	private final String value;

	PlanOperationType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static PlanOperationType fromValue(final String text) {
		for (final PlanOperationType b : PlanOperationType.values()) {
			if (b.value.equals(text)) {
				return b;
			}
		}
		return null;
	}

}
