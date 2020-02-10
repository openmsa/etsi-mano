package com.ubiqube.parser.tosca;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ubiqube.parser.tosca.csar.CsarParser;

public class ToscaParser {
	Pattern zipMatcher = Pattern.compile("\\.(zip|csar)$");

	public ToscaContext parse(final String filename) {
		final ObjectMapper mapper = getMapper();
		CsarParser csar = null;
		IResolver resolver;
		if (isZip(filename)) {
			csar = new CsarParser(filename);
			resolver = csar.getResolver();
		} else {
			resolver = new Resolver();
		}
		try {
			final ToscaRoot root = loadToscaBase();
			final ToscaContext ctx = new ToscaContext(root, resolver);
			final ToscaRoot root2;
			if (isZip(filename)) {
				root2 = mapper.readValue(csar.getEntryDefinition(), ToscaRoot.class);
			} else {
				root2 = mapper.readValue(new File(filename), ToscaRoot.class);
			}
			ctx.addRoot(root2);
			ctx.resolvImports();
			ctx.resolvSymbols();
			return ctx;
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	public ToscaContext parseContent(final String content, final IResolver resolver) {
		final ObjectMapper mapper = getMapper();
		try {
			final ToscaRoot root = mapper.readValue(content, ToscaRoot.class);
			final ToscaContext ctx = new ToscaContext(root, resolver);
			ctx.resolvImports();
			return ctx;
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	private ToscaRoot loadToscaBase() {
		final InputStream stream = this.getClass().getClassLoader().getResourceAsStream("TOSCA_definition_1_0.yaml");
		String content;
		try {
			content = IOUtils.toString(stream, Charset.defaultCharset());
			final ObjectMapper mapper = getMapper();
			return mapper.readValue(content.getBytes(), ToscaRoot.class);
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	private static ObjectMapper getMapper() {
		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		final SimpleModule module = new SimpleModule();
		mapper.registerModule(module);

		return mapper;
	}

	private boolean isZip(final String fileName) {
		final Matcher res = zipMatcher.matcher(fileName);
		return res.find();
	}
}
