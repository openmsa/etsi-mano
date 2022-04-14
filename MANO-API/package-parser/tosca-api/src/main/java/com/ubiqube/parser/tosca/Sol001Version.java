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
package com.ubiqube.parser.tosca;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public enum Sol001Version {
	SOL001_2_5_1("2.5.1"),
	SOL001_2_6_1("2.6.1"),
	SOL001_2_7_1("2.7.1"),
	SOL001_2_8_1("2.8.1"),
	SOL001_3_3_1("3.3.1"),
	SOL001_3_5_1("3.5.1"),
	SOL001_3_6_1("3.6.1"),
	SOL001_4_2_1("4_2.1");

	private final String value;

	Sol001Version(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static Sol001Version fromValue(final String text) {
		for (final Sol001Version b : Sol001Version.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

}
