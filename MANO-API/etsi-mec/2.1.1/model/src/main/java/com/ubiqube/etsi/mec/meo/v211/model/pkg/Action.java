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
package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 'Identifies the action of the MEC host data plane, when a packet matches the
 * trafficFilter.'
 */
public enum Action {
	DROP("DROP"),
	FORWARD_DECAPSULATED("FORWARD_DECAPSULATED"),
	FORWARD_AS_IS("FORWARD_AS_IS"),
	PASSTHROUGH("PASSTHROUGH"),
	DUPLICATED_DECAPSULATED("DUPLICATED_DECAPSULATED"),
	DUPLICATE_AS_IS("DUPLICATE_AS_IS");

	private String value;

	Action(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static Action fromValue(final String text) {
		for (final Action b : Action.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
