/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.vnfm.v261.controller.lcmgrant;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.common.v261.model.lcmgrant.Grant;
import com.ubiqube.etsi.mano.controller.lcmgrant.GrantManagement;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.dto.GrantsRequest;
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
	public GrantResponse get(final UUID grantId) {
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("grantId", grantId);
		final URI uri = nfvoRest.uriBuilder()
				.pathSegment("grant/v1/grants/{grantId}")
				.buildAndExpand(uriVariables)
				.toUri();
		final Grant grants = nfvoRest.get(uri, Grant.class);
		return mapper.map(grants, GrantResponse.class);
	}

	@Override
	public GrantResponse post(final GrantsRequest grant) {
		final URI uri = nfvoRest.uriBuilder()
				.pathSegment("grant/v1/grants")
				.build()
				.toUri();
		// XXX Elect version, and map.
		final Grant grantPost = nfvoRest.post(uri, grant, Grant.class);
		return mapper.map(grantPost, GrantResponse.class);
	}

}
