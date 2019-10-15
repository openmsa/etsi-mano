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
	protected RequirementDeserialization() {
		this(null);
	}

	public RequirementDeserialization(final Class<?> object) {
		super(object);
	}

	@Override
	public RequirementDefinition deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
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

	private Map<String, Requirement> handle(final ObjectNode value, final ObjectCodec objectCodec) {
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
