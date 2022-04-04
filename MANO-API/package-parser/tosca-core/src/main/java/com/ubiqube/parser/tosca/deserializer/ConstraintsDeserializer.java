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
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.constraints.Constraint;
import com.ubiqube.parser.tosca.constraints.Equal;
import com.ubiqube.parser.tosca.constraints.GreaterOrEqual;
import com.ubiqube.parser.tosca.constraints.GreaterThan;
import com.ubiqube.parser.tosca.constraints.InRange;
import com.ubiqube.parser.tosca.constraints.LessOrEqual;
import com.ubiqube.parser.tosca.constraints.LessThan;
import com.ubiqube.parser.tosca.constraints.MaxLength;
import com.ubiqube.parser.tosca.constraints.MinLength;
import com.ubiqube.parser.tosca.constraints.Pattern;
import com.ubiqube.parser.tosca.constraints.ValidValues;

public class ConstraintsDeserializer extends StdDeserializer<Constraint> {
	protected ConstraintsDeserializer() {
		this(null);
	}

	public ConstraintsDeserializer(final Class<?> object) {
		super(object);
	}

	@Override
	public Constraint deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
		final ObjectNode value = p.getCodec().readTree(p);
		final Iterator<Entry<String, JsonNode>> fields = value.fields();
		while (fields.hasNext()) {
			final Map.Entry<String, JsonNode> entry = fields.next();
			final String key = entry.getKey();
			// One of 3.5.2.1 Operator keynames
			if ("greater_or_equal".equals(key)) {
				return new GreaterOrEqual(entry.getValue());
			}
			if ("valid_values".equals(key)) {
				return new ValidValues((ArrayNode) entry.getValue());
			}
			if ("min_length".equals(key)) {
				return new MinLength(entry.getValue());
			} else if ("max_length".equals(key)) {
				return new MaxLength(entry.getValue());
			} else if ("equal".equals(key)) {
				return new Equal(entry.getKey());
			} else if ("greater_than".equals(key)) {
				return new GreaterThan(entry.getValue());
			} else if ("less_than".equals(key)) {
				return new LessThan(entry.getKey());
			} else if ("in_range".equals(key)) {
				return new InRange((ArrayNode) entry.getValue());
			} else if ("pattern".equals(key)) {
				return new Pattern(entry.getValue());
			} else if ("less_or_equal".equals(key) || "length".equals(key)) {
				return new LessOrEqual(entry.getValue().asText());
			} else {
				throw new ParseException("Unknow Constraint: " + key);
			}
		}
		return null;
	}

}
