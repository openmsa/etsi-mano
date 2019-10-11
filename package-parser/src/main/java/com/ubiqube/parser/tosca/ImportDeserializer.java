package com.ubiqube.parser.tosca;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

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
		final JsonNode node = p.getCodec().readTree(p);
		return null;
	}

}
