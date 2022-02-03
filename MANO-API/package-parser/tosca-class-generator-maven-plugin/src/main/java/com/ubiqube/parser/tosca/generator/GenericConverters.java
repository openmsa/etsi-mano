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
package com.ubiqube.parser.tosca.generator;

import java.time.ZonedDateTime;

import com.ubiqube.parser.tosca.scalar.Frequency;
import com.ubiqube.parser.tosca.scalar.Range;
import com.ubiqube.parser.tosca.scalar.Size;
import com.ubiqube.parser.tosca.scalar.Time;
import com.ubiqube.parser.tosca.scalar.Version;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public final class GenericConverters {
	private GenericConverters() {
		// Nothing.
	}

	public static Class<?> convert(final String type) {
		if ("integer".equals(type)) {
			return Integer.class;
		}
		if ("scalar-unit.size".equals(type)) {
			return Size.class;
		}
		if ("scalar-unit.frequency".equals(type)) {
			return Frequency.class;
		}
		if ("scalar-unit.time".equals(type)) {
			return Time.class;
		}
		if ("string".equals(type)) {
			return String.class;
		}
		if ("range".equals(type)) {
			return Range.class;
		}
		if ("boolean".equals(type)) {
			return Boolean.class;
		}
		if ("float".equals(type)) {
			return Float.class;
		}
		if ("version".equals(type)) {
			return Version.class;
		}
		if ("timestamp".equals(type)) {
			return ZonedDateTime.class;
		}

		return null;
	}

}
