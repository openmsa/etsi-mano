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
package com.ubiqube.etsi.mano.tosca;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public enum ToscaVersion {
	TOSCA_SIMPLE_YAML_1_0("tosca_simple_yaml_1_0"),
	TOSCA_SIMPLE_YAML_1_1("tosca_simple_yaml_1_1"),
	TOSCA_SIMPLE_YAML_1_2("tosca_simple_yaml_1_2"),
	TOSCA_SIMPLE_YAML_1_3("tosca_simple_yaml_1_3"),
	TOSCA_2_0("tosca_2_0");

	private final String value;

	ToscaVersion(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static ToscaVersion fromValue(final String text) {
		for (final ToscaVersion b : ToscaVersion.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
