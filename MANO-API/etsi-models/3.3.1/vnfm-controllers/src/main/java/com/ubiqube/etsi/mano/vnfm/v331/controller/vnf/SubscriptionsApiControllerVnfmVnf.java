package com.ubiqube.etsi.mano.vnfm.v331.controller.vnf;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-19T11:42:02.797+02:00")

@Controller("VnfSubscriptionsApiControllerVnfmVnf331")
public class SubscriptionsApiControllerVnfmVnf implements SubscriptionsApi {

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public SubscriptionsApiControllerVnfmVnf(final ObjectMapper objectMapper, final HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public Optional<ObjectMapper> getObjectMapper() {
		return Optional.ofNullable(objectMapper);
	}

	@Override
	public Optional<HttpServletRequest> getRequest() {
		return Optional.ofNullable(request);
	}

}
