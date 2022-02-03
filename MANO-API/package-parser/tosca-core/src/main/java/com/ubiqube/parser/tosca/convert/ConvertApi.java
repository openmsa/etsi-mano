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
package com.ubiqube.parser.tosca.convert;

import java.util.HashMap;
import java.util.Map;

import com.ubiqube.parser.tosca.ParseException;

public class ConvertApi {

	private final Map<String, Converter<?>> converters = new HashMap<>();

	public void register(final String target, final Converter<?> converter) {
		converters.put(target, converter);
	}

	public <T> T map(final String clazz, final Object value) {
		final Converter<?> conv = converters.get(clazz);
		if (null == conv) {
			throw new ParseException("Unable to convert " + clazz);
		}
		return (T) conv.convert(value);
	}
}
