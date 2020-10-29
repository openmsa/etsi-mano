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
