package com.ubiqube.etsi.mano;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
public class Application {
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		final ObjectMapper objectMapper = new ObjectMapper();
		LOG.info("Initializing JSON Object mapper.");
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		objectMapper.setSerializationInclusion(Include.NON_NULL);

		return objectMapper;
	}

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.serializationInclusion(JsonInclude.Include.NON_NULL);
		builder.indentOutput(true);
		return builder;
	}
}
