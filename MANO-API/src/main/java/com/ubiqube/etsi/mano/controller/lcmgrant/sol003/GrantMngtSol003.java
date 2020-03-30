package com.ubiqube.etsi.mano.controller.lcmgrant.sol003;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.lcmgrant.GrantManagement;
import com.ubiqube.etsi.mano.dao.mano.Grants;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.Grant;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;
import com.ubiqube.etsi.mano.service.rest.NfvoRest;

import ma.glasnost.orika.MapperFacade;

@Profile("NFVM")
@Service
public class GrantMngtSol003 implements GrantManagement {
	private final NfvoRest nfvoRest;
	private final MapperFacade mapper;

	public GrantMngtSol003(final NfvoRest _nfvoRest, final MapperFacade _mapper) {
		nfvoRest = _nfvoRest;
		mapper = _mapper;
	}

	@Override
	public Grants get(final UUID grantId) {
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("grantId", grantId);
		final URI uri = nfvoRest.uriBuilder()
				.pathSegment("grant/v1/grants/{grantId}")
				.buildAndExpand(uriVariables)
				.toUri();
		final Grant grants = nfvoRest.get(uri, Grant.class);
		return mapper.map(grants, Grants.class);
	}

	@Override
	public Grants post(final GrantRequest grant) {
		final URI uri = nfvoRest.uriBuilder()
				.pathSegment("grant/v1/grants")
				.build()
				.toUri();
		final Grant grantPost = nfvoRest.post(uri, grant, Grant.class);
		return mapper.map(grantPost, Grants.class);
	}

}
