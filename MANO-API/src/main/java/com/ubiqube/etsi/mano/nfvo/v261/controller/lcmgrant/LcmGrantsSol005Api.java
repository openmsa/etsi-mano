package com.ubiqube.etsi.mano.nfvo.v261.controller.lcmgrant;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mano.controller.lcmgrant.GrantManagement;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant.Grant;
import com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant.GrantRequest;

import ma.glasnost.orika.MapperFacade;

@Profile({ "!VNFM" })
@Controller
public class LcmGrantsSol005Api implements LcmGrants {
	private final GrantManagement grantManagement;
	private final MapperFacade mapper;

	public LcmGrantsSol005Api(final GrantManagement _grantManagement, final MapperFacade _mapper) {
		grantManagement = _grantManagement;
		mapper = _mapper;
	}

	@Override
	public ResponseEntity<Grant> grantsGrantIdGet(final String grantId, final String version) {
		final GrantResponse grants = grantManagement.get(UUID.fromString(grantId));
		if (!grants.getAvailable().equals(Boolean.TRUE)) {
			return ResponseEntity.noContent().build();
		}
		final Grant jsonGrant = mapper.map(grants, Grant.class);
		return ResponseEntity.ok(jsonGrant);
	}

	@Override
	public ResponseEntity<Grant> grantsPost(@Valid final GrantRequest grantRequest, final String contentType, final String version) {
		grantManagement.post(grantRequest);
		return ResponseEntity.accepted().build();
	}

}
