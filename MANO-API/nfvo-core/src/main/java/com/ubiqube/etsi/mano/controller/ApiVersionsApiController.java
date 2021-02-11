/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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
		apiVersionInformation.setUriPrefix("v1/");
		return ResponseEntity.ok(apiVersionInformation);
	}

}
