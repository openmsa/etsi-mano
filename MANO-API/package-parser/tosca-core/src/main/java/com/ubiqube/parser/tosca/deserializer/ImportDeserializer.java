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
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ubiqube.parser.tosca.Import;
import com.ubiqube.parser.tosca.Imports;

public class ImportDeserializer extends StdDeserializer<Imports> {
	/** serial. */
	private static final long serialVersionUID = 1L;

	public ImportDeserializer() {
		this(null);
	}

	public ImportDeserializer(final Class<?> vc) {
		super(vc);
	}

	@Override
	public Imports deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
		final Map<String, Import> imports = new HashMap<>();
		final ArrayNode value = p.getCodec().readTree(p);
		for (final JsonNode jsonNode : value) {
			if (jsonNode.isObject()) {
				final ObjectNode node = (ObjectNode) jsonNode;
				if (1 == node.size()) {
					final String key = node.fields().next().getKey();
					final String val = node.fields().next().getValue().asText();
					final Import imprt = new Import(key, val);
					imports.put(key, imprt);
				} else {
					final Import imprt = p.getCodec().treeToValue(node, Import.class);
					imports.put(UUID.randomUUID().toString(), imprt);
				}
			} else if (jsonNode.isTextual()) {
				final String key = UUID.randomUUID().toString();
				final Import imprt = new Import(key, jsonNode.asText());
				imports.put(key, imprt);
			}
		}

		return new Imports(imports);
	}

}
