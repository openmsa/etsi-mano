package com.ubiqube.etsi.mano.utils;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonJsonProviderAtRest extends JacksonJaxbJsonProvider {

	private final static Logger LOG = LoggerFactory.getLogger(JacksonJsonProviderAtRest.class);
	private static ObjectMapper objectMapperAtRest = new ObjectMapper();

	static {
		objectMapperAtRest.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapperAtRest.configure(SerializationFeature.INDENT_OUTPUT, true);
		objectMapperAtRest.setSerializationInclusion(Include.NON_NULL);
	}

	public JacksonJsonProviderAtRest() {
		super();
		setMapper(objectMapperAtRest);
		LOG.error(">>>>>>>>>>>>>>>>>>>>>>>>> INIT DONE. <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
}
