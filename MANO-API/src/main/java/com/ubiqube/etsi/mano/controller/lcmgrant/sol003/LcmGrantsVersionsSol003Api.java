package com.ubiqube.etsi.mano.controller.lcmgrant.sol003;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.ApiVersionInformation;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-30T10:47:24.034+02:00")
@Profile({ "!NFVO" })
@Controller
public class LcmGrantsVersionsSol003Api implements LcmGrantsVersionsSol003 {

	private static final Logger log = LoggerFactory.getLogger(LcmGrantsVersionsSol003Api.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public LcmGrantsVersionsSol003Api(final ObjectMapper objectMapper, final HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public ResponseEntity<ApiVersionInformation> apiVersionsGet(@ApiParam(value = "Version of the API requested to use when responding to this request. ") @RequestHeader(value = "Version", required = false) final String version) {
		final String accept = request.getHeader("Accept");
		if ((accept != null) && accept.contains("application/json")) {
			try {
				return new ResponseEntity<>(objectMapper.readValue("{  \"apiVersions\" : [ {    \"isDeprecated\" : true,    \"retirementDate\" : { },    \"version\" : \"version\"  }, {    \"isDeprecated\" : true,    \"retirementDate\" : { },    \"version\" : \"version\"  } ],  \"uriPrefix\" : \"uriPrefix\"}", ApiVersionInformation.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (final IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}
