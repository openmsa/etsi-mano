package com.ubiqube.etsi.mano.vnfm.v331.controller.lcmgrant;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.v331.model.grant.Grant;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.GrantRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class GrantsApiController implements GrantsApi {

	@Override
	public ResponseEntity<Grant> grantsGrantIdGet(final String grantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Grant> grantsPost(@Valid final GrantRequest body) {
		// TODO Auto-generated method stub
		return null;
	}
}
