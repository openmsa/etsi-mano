package com.ubiqube.parser.tosca;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class LoadDefault {

	public ToscaRoot load() {
		final InputStream stream = this.getClass().getClassLoader().getResourceAsStream("TOSCA_definition_1_0.yaml");
		String content;
		try {
			content = IOUtils.toString(stream, Charset.defaultCharset());
		} catch (final IOException e) {
			throw new ParseException(e);
		}
		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		final SimpleModule module = new SimpleModule();
		mapper.registerModule(module);

		try {
			return mapper.readValue(content.getBytes(), ToscaRoot.class);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void resolvProperties(final Map<String, ToscaClass> map) {
		final Set<Entry<String, ToscaClass>> entries = map.entrySet();
		for (final Entry<String, ToscaClass> entry : entries) {
			final ToscaClass val = entry.getValue();
			final ToscaProperties props = val.getProperties();
			if (null != props) {
				final HashMap<String, ValueObject> vo = props.getProperties();
			}
		}
	}
}
