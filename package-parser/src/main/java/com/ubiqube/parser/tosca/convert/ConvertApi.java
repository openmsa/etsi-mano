package com.ubiqube.parser.tosca.convert;

import java.util.HashMap;
import java.util.Map;

import com.ubiqube.parser.tosca.ParseException;

public class ConvertApi {

	private final Map<String, Converter> converters = new HashMap<>();

	public void register(final String target, final Converter converter) {
		converters.put(target, converter);
	}

	public <T> T map(final String clazz, final Object value) {
		final Converter conv = converters.get(clazz);
		if (null == conv) {
			throw new ParseException("Unable to convert " + clazz);
		}
		return (T) conv.convert(value);
	}
}
