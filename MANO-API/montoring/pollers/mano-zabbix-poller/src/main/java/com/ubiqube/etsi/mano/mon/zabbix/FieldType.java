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
package com.ubiqube.etsi.mano.mon.zabbix;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FieldType {

	INTEGER("INTEGER"),
	STRING("STRING"),
	HOST("HOST"),
	ITEM("ITEM"),
	ITEM_PROTOTYPE("ITEM_PROTOTYPE"),
	TYPE_GRAPH("GRAPH"),
	GRAPH_PROTOTYPE("GRAPH_PROTOTYPE");

	private final String value;

	FieldType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static FieldType fromValue(final String text) {
		for (final FieldType b : FieldType.values()) {
			if (b.value.equals(text)) {
				return b;
			}
		}
		throw new RuntimeException("" + text);
	}

}
