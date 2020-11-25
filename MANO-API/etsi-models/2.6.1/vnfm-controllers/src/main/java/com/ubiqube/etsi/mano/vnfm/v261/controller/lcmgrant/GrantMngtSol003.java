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
