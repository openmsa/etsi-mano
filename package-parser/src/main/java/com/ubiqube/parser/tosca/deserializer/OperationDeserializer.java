package com.ubiqube.parser.tosca.deserializer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.ubiqube.parser.tosca.OperationDefinition;

public class OperationDeserializer extends StdDeserializer<OperationDefinition> {
	private static final Logger LOG = LoggerFactory.getLogger(OperationDeserializer.class);

	protected OperationDeserializer() {
		this(null);
	}

	public OperationDeserializer(final Class<?> object) {
		super(object);
	}

	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Override
	public OperationDefinition deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
		final Object value = p.getCodec().readTree(p);
		LOG.info("value {}<=>{}", value.getClass(), value);
		if (value instanceof TextNode) {
			final OperationDefinition od = new OperationDefinition();
			od.setImplementation(value);
			return od;
		} else {
			return p.getCodec().treeToValue(p.getCodec().readTree(p), OperationDefinition.class);
		}
	}

}
