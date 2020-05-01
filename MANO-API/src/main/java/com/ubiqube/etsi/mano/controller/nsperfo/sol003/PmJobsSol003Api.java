package com.ubiqube.etsi.mano.controller.nsperfo.sol003;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-01T16:01:09.127+02:00")

@Controller
public class PmJobsSol003Api implements PmJobsSol003 {

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public PmJobsSol003Api(final ObjectMapper objectMapper, final HttpServletRequest request) {
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
