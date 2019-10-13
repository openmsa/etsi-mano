package com.ubiqube.parser.tosca;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class ToscaParser {

	public ToscaContext parse(final String filename) {
		final ObjectMapper mapper = getMapper();
		try {
			final ToscaRoot root = mapper.readValue(new File(filename), ToscaRoot.class);
			final ToscaContext ctx = new ToscaContext(root);
			ctx.resolvImports();
			ctx.resolvSymbols();
			return ctx;
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	public ToscaContext parseContent(final String content) {
		final ObjectMapper mapper = getMapper();
		try {
			final ToscaRoot root = mapper.readValue(content, ToscaRoot.class);
			final ToscaContext ctx = new ToscaContext(root);
			ctx.resolvImports();
			ctx.resolvSymbols();
			return ctx;
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	private ObjectMapper getMapper() {
		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		final SimpleModule module = new SimpleModule();
		mapper.registerModule(module);

		module.addDeserializer(Imports.class, new ImportDeserializer());
		module.addDeserializer(ToscaProperties.class, new PropertyDeserializer());

		return mapper;
	}
}
