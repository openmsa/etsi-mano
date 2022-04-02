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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ubiqube.parser.tosca.RequirementMapping;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class RequirementMappingDeserializer extends StdDeserializer<Map<String, RequirementMapping>> {
	public RequirementMappingDeserializer() {
		this(null);
	}

	protected RequirementMappingDeserializer(final Class<?> vc) {
		super(vc);
	}

	@Override
	public Map<String, RequirementMapping> deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JacksonException {
		final LinkedHashMap<String, RequirementMapping> ret = new LinkedHashMap<>();
		final ObjectNode value = p.getCodec().readTree(p);
		final Iterator<Entry<String, JsonNode>> fields = value.fields();
		while (fields.hasNext()) {
			final RequirementMapping rq = new RequirementMapping();
			final Map.Entry<String, JsonNode> entry = fields.next();
			rq.setRequirementName(entry.getKey());
			final ArrayNode v = (ArrayNode) entry.getValue();
			rq.setNodeTemplateName(v.get(0).asText());
			rq.setNodeTemplateRequirementName(v.get(1).asText());
			ret.put(entry.getKey(), rq);
		}
		return ret;
	}

}
