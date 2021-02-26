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
package com.ubiqube.parser.tosca.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.ubiqube.parser.tosca.Occurrences;
import com.ubiqube.parser.tosca.ParseException;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class OccurrencesDeserializer extends StdDeserializer<Occurrences> {
	/** serial. */
	private static final long serialVersionUID = 1L;

	public OccurrencesDeserializer() {
		this(null);
	}

	public OccurrencesDeserializer(final Class<?> vc) {
		super(vc);
	}

	@Override
	public Occurrences deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
		final JsonNode value = p.getCodec().readTree(p);
		if (value.isArray()) {
			final ArrayNode arr = (ArrayNode) value;
			if (arr.size() > 2) {
				throw new ParseException("To many element in occurence : " + value);
			}
			final int min = parseMinMax(arr.get(0), Integer.MIN_VALUE);
			final int max = parseMinMax(arr.get(1), Integer.MAX_VALUE);
			return new Occurrences(min, max);
		} else if (value.isNumber()) {
			return new Occurrences(value.intValue(), value.intValue());
		}
		return new Occurrences(0, Integer.MAX_VALUE);
	}

	private static int parseMinMax(final JsonNode node, final int minMaxValue) {
		if (node.isNumber()) {
			return node.asInt();
		} else if (node.isTextual()) {
			final String str = node.asText();
			if (!"UNBOUNDED".equals(str)) {
				throw new ParseException("Could not parse value of occurence : " + node);
			}
			return minMaxValue;
		}
		throw new ParseException("Could not parse value of occurence : " + node);
	}
}
