package com.ubiqube.etsi.mano.controller.lcmgrant.sol005;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.ubiqube.etsi.mano.controller.lcmgrant.LcmGrantsSol003;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.Grant;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;

public class LcmGrantsSol005Api implements LcmGrantsSol003 {

	@Override
	public ResponseEntity<Grant> grantsGrantIdGet(final String grantId, final String version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Grant> grantsPost(@Valid final GrantRequest grantRequest, final String contentType, final String version) {
		// TODO Auto-generated method stub
		return null;
	}

}
