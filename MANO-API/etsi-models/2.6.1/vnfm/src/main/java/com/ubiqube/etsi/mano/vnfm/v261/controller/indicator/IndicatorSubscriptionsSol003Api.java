package com.ubiqube.etsi.mano.vnfm.v261.controller.indicator;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping("/sol003/vnfind/v1")
public class IndicatorSubscriptionsSol003Api implements IndicatorSubscriptionsSol003 {

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public IndicatorSubscriptionsSol003Api(final ObjectMapper objectMapper, final HttpServletRequest request) {
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
