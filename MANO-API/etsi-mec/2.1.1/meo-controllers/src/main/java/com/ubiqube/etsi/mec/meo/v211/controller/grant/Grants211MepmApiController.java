package com.ubiqube.etsi.mec.meo.v211.controller.grant;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mec.meo.v211.model.grant.Grant;
import com.ubiqube.etsi.mec.meo.v211.model.grant.GrantRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class Grants211MepmApiController implements Grants211MepmApi {

	@Override
	public ResponseEntity<Grant> grantGET(final String grantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Grant> grantPOST(@Valid final GrantRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

}
