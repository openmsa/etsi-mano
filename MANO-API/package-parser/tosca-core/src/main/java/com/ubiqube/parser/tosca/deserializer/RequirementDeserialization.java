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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.Requirement;
import com.ubiqube.parser.tosca.RequirementDefinition;

public class RequirementDeserialization extends StdDeserializer<RequirementDefinition> {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	protected RequirementDeserialization() {
		this(null);
	}

	public RequirementDeserialization(final Class<?> object) {
		super(object);
	}

	@Override
	public RequirementDefinition deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
		final Map<String, Requirement> reqMap = new HashMap<>();
		final ArrayNode value = p.getCodec().readTree(p);
		final Iterator<JsonNode> ite = value.iterator();
		while (ite.hasNext()) {
			final ObjectNode node = (ObjectNode) ite.next();
			final Map<String, Requirement> req = handle(node, p.getCodec());
			reqMap.putAll(req);
		}

		return new RequirementDefinition(reqMap);
	}

	private static Map<String, Requirement> handle(final ObjectNode value, final ObjectCodec objectCodec) {
		final Map<String, Requirement> reqMap = new HashMap<>();
		final Iterator<Entry<String, JsonNode>> fields = value.fields();
		while (fields.hasNext()) {
			final Map.Entry<String, JsonNode> entry = fields.next();
			try {
				Requirement req;
				if (entry.getValue().isTextual()) {
					req = new Requirement();
					req.setCapability(entry.getValue().asText());
				} else {
					req = objectCodec.treeToValue(entry.getValue(), Requirement.class);
				}
				reqMap.put(entry.getKey(), req);
			} catch (final JsonProcessingException e) {
				throw new ParseException(e);
			}
		}
		return reqMap;
	}

}
