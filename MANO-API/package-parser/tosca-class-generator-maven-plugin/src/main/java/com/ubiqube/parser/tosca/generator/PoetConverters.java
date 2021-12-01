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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.FieldSpec.Builder;
import com.ubiqube.parser.tosca.ParseException;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class PoetConverters {

	private static final Logger LOG = LoggerFactory.getLogger(PoetConverters.class);

	private PoetConverters() {
		// Nothing.
	}

	public static Object convert(final Object def, final Builder currentField) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("def={} jType={}", def, currentField);
		}
		final FieldSpec fs = currentField.build();
		// XXX to do.
		switch (fs.type.toString()) {
		case "java.lang.String":
			return "\"" + def + "\"";
		case "java.lang.Boolean":
			return def;
		case "java.lang.Integer":
			if (def.getClass().equals(Integer.class)) {
				return def;
			}
			return Integer.parseInt((String) def);
		default:
			throw new ParseException("Unknown type: " + fs.type);
		}
	}
}
