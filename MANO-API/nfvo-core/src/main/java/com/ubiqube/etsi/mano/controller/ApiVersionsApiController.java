package com.ubiqube.etsi.mano.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.model.ApiVersionInformation;
import com.ubiqube.etsi.mano.model.ApiVersionInformationApiVersions;

import ma.glasnost.orika.MapperFacade;



@Controller
public class ApiVersionsApiController implements ApiVersionsApi {

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	private final MapperFacade mapper;

	private final List<EtsiImplementation> implementations;

	public ApiVersionsApiController(final ObjectMapper objectMapper, final HttpServletRequest request, final List<EtsiImplementation> _implementations, final MapperFacade _mapper) {
		this.objectMapper = objectMapper;
		this.request = request;
		implementations = _implementations;
		mapper = _mapper;
	}

	@Override
	public Optional<ObjectMapper> getObjectMapper() {
		return Optional.ofNullable(objectMapper);
	}

	@Override
	public Optional<HttpServletRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	@Override
	public ResponseEntity<ApiVersionInformation> apiVersionsGet(final String version) {
		final ApiVersionInformation apiVersionInformation = new ApiVersionInformation();
		apiVersionInformation.setApiVersions(mapper.mapAsList(implementations, ApiVersionInformationApiVersions.class));
		return ResponseEntity.ok(apiVersionInformation);
	}

}
