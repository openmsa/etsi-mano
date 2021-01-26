package com.ubiqube.etsi.mec.meo.v211.controller.grant;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.dto.VnfGrantsRequest;
import com.ubiqube.etsi.mec.controller.grant.AppGrantController;
import com.ubiqube.etsi.mec.meo.v211.model.grant.Grant;
import com.ubiqube.etsi.mec.meo.v211.model.grant.GrantRequest;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class Grants211MepmApiController implements Grants211MepmApi {
	private final AppGrantController appGrantController;

	private final MapperFacade mapper;

	public Grants211MepmApiController(final AppGrantController _appGrantController, final MapperFacade _mapper) {
		appGrantController = _appGrantController;
		mapper = _mapper;
	}

	@Override
	public ResponseEntity<Grant> grantGET(final String grantId) {
		final GrantResponse grants = appGrantController.findById(UUID.fromString(grantId));
		if (!grants.getAvailable().equals(Boolean.TRUE)) {
			return ResponseEntity.accepted().build();
		}
		final Grant jsonGrant = mapper.map(grants, Grant.class);
		return ResponseEntity.ok(jsonGrant);
	}

	@Override
	public ResponseEntity<Grant> grantPOST(@Valid final GrantRequest grantRequest) {
		final VnfGrantsRequest obj = mapper.map(grantRequest, VnfGrantsRequest.class);
		final GrantResponse resp = appGrantController.post(obj);
		final URI location = linkTo(methodOn(Grants211MepmApi.class).grantGET(resp.getId().toString())).withSelfRel().toUri();
		return ResponseEntity.created(location).build();
	}

}
