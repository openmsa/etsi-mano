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

package com.ubiqube.etsi.mano.nfvo.v261.controller.lcmgrant;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mano.common.v261.model.lcmgrant.Grant;
import com.ubiqube.etsi.mano.controller.lcmgrant.GrantManagement;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.dto.GrantsRequest;
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
		final GrantResponse resp = grantManagement.post(mapper.map(grantRequest, GrantsRequest.class));
		final Grant grant = mapper.map(resp, Grant.class);
		final URI location = linkTo(methodOn(LcmGrants.class).grantsGrantIdGet(grant.getId(), "2.6.1")).withSelfRel().toUri();
		return ResponseEntity.created(location).build();
	}

}
