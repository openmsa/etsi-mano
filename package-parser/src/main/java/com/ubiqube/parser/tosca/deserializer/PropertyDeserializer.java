package com.ubiqube.parser.tosca.deserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ubiqube.parser.tosca.ToscaProperties;
import com.ubiqube.parser.tosca.ValueObject;

public class PropertyDeserializer extends StdDeserializer<ToscaProperties> {

	public PropertyDeserializer() {
		this(null);
	}

	public PropertyDeserializer(final Class<?> object) {
		super(object);
	}

	/** serial. */
	private static final long serialVersionUID = 1L;

	@Override
	public ToscaProperties deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
		final HashMap<String, ValueObject> props = new HashMap<>();
		final ObjectNode value = p.getCodec().readTree(p);
		final Iterator<Entry<String, JsonNode>> fields = value.fields();
		while (fields.hasNext()) {
			final Map.Entry<String, JsonNode> entry = fields.next();
			final JsonNode val = entry.getValue();
			ValueObject vo;
			// val.isContainerNode
			vo = p.getCodec().treeToValue(entry.getValue(), ValueObject.class);
			props.put(entry.getKey(), vo);
		}
		final ToscaProperties toscaProperties = new ToscaProperties();
		toscaProperties.setProperties(props);
		return toscaProperties;
	}

}
