package com.ubiqube.etsi.mano.controller.nslcm;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mano.controller.EtsiImplementation;
import com.ubiqube.etsi.mano.model.ApiVersionInformation;
import com.ubiqube.etsi.mano.model.ApiVersionInformationApiVersions;

import ma.glasnost.orika.MapperFacade;

@Controller
public class LcmApiVersionsApiController implements LcmApiVersionsApi {

	private final MapperFacade mapper;

	private final List<EtsiImplementation> implementations;

	public LcmApiVersionsApiController(final List<EtsiImplementation> _implementations, final MapperFacade _mapper) {
		implementations = _implementations;
		mapper = _mapper;
	}

	@Override
	public ResponseEntity<ApiVersionInformation> apiVersionsGet(final String version) {
		final ApiVersionInformation apiVersionInformation = new ApiVersionInformation();
		apiVersionInformation.setApiVersions(mapper.mapAsList(implementations, ApiVersionInformationApiVersions.class));
		return ResponseEntity.ok(apiVersionInformation);
	}

	@Override
	public ResponseEntity<ApiVersionInformation> apiVersionsV1Get(final String version) {
		final ApiVersionInformation apiVersionInformation = new ApiVersionInformation();
		apiVersionInformation.setApiVersions(mapper.mapAsList(implementations, ApiVersionInformationApiVersions.class));
		return ResponseEntity.ok(apiVersionInformation);
	}

}
