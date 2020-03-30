package com.ubiqube.etsi.mano.controller.lcmgrant.sol005;

import javax.validation.Valid;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mano.controller.lcmgrant.LcmGrants;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.Grant;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;

@Profile({ "!NFVO" })
@Controller
public class LcmGrantsSol005Api implements LcmGrants {

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
