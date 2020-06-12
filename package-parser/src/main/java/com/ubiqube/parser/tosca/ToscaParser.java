package com.ubiqube.parser.tosca;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class ToscaParser {

	public ToscaRoot parse(final String filename) {
		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(new File(filename), ToscaRoot.class);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
