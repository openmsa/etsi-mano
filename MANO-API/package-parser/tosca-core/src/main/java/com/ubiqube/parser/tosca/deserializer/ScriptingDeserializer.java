package com.ubiqube.parser.tosca.deserializer;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.scripting.GetInput;
import com.ubiqube.parser.tosca.scripting.ScriptingValue;
import com.ubiqube.parser.tosca.scripting.SimpleString;

public class ScriptingDeserializer extends StdDeserializer<ScriptingValue> {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	public ScriptingDeserializer() {
		this(null);
	}

	protected ScriptingDeserializer(final Class<?> vc) {
		super(vc);
	}

	@Override
	public ScriptingValue deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
		final JsonNode value = p.getCodec().readTree(p);
		if (value.isObject()) {
			// This is a scripting object
			return handle((ObjectNode) value);
		}
		return new SimpleString(value.asText());
	}

	private static ScriptingValue handle(final ObjectNode value) {
		final Iterator<Entry<String, JsonNode>> fields = value.fields();
		while (fields.hasNext()) {
			final Map.Entry<String, JsonNode> entry = fields.next();
			if (entry.getKey().equals("get_input")) {
				return new GetInput(entry.getValue());
			}
			throw new ParseException("Unknown operator: " + entry.getKey());
		}
		return null;
	}

}
