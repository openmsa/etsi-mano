package com.ubiqube.parser.tosca.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.ubiqube.parser.tosca.Occurrences;

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
		// TODO It could be a Range or a simple digit
		return null;
	}

}
