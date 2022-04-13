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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;

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

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ConstraintsDeserializer extends StdDeserializer<Constraint> {
	private static final long serialVersionUID = 1L;
	private final Map<String, Function<Map.Entry<String, JsonNode>, ? extends Constraint>> table = new HashMap<>();

	protected ConstraintsDeserializer() {
		this(null);
		// One of 3.5.2.1 Operator keynames
		table.put("greater_or_equal", x -> new GreaterOrEqual(x.getValue()));
		table.put("valid_values", x -> new ValidValues((ArrayNode) x.getValue()));
		table.put("min_length", x -> new MinLength(x.getValue()));
		table.put("max_length", x -> new MaxLength(x.getValue()));
		table.put("equal", x -> new Equal(x.getKey()));
		table.put("greater_than", x -> new GreaterThan(x.getValue()));
		table.put("less_than", x -> new LessThan(x.getKey()));
		table.put("in_range", x -> new InRange((ArrayNode) x.getValue()));
		table.put("pattern", x -> new Pattern(x.getValue()));
		table.put("less_or_equal", x -> new LessOrEqual(x.getValue().asText()));
		table.put("length", x -> new LessOrEqual(x.getValue().asText()));
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
			final Function<Entry<String, JsonNode>, ? extends Constraint> creator = Optional.ofNullable(table.get(key)).orElseThrow(() -> new ParseException("Unknow Constraint: " + key));
			return creator.apply(entry);
		}
		return null;
	}

}
