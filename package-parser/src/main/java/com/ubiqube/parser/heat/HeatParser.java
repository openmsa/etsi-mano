package com.ubiqube.parser.heat;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ubiqube.parser.tosca.ParseException;

public class HeatParser {

	public HeatRoot parse(final String filename) {
		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(new File(filename), HeatRoot.class);
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}
}
