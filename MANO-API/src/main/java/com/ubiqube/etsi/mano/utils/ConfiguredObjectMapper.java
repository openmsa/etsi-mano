package com.ubiqube.etsi.mano.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Just a single point for configuration of mappers. in MANO API null removal is
 * important.
 *
 * @author ovi@ubiqube.com
 *
 */
public class ConfiguredObjectMapper {
	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		mapper.setSerializationInclusion(Include.NON_NULL);
	}

	private ConfiguredObjectMapper() {

	}

	public static ObjectMapper getMapper() {
		return mapper;
	}
}
