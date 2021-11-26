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
package com.ubiqube.etsi.mano.json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

public class WantedSerializer extends BeanSerializerModifier {
	private final Set<String> list;

	public WantedSerializer(final Set<String> inList) {
		list = new HashSet<>();
		for (final String string : inList) {
			final String[] part = string.split("\\.");
			list.addAll(Arrays.asList(part));
		}
	}

	@Override
	public List<BeanPropertyWriter> changeProperties(final SerializationConfig config, final BeanDescription beanDesc, final List<BeanPropertyWriter> beanProperties) {
		final List<BeanPropertyWriter> result = new ArrayList<>();

		for (final BeanPropertyWriter beanPropertyWriter : beanProperties) {
			final String partName = beanPropertyWriter.getName();
			if (list.contains(partName)) {
				result.add(beanPropertyWriter);
			}
		}
		return result;
	}

}
