package com.ubiqube.etsi.mano.vnfm.v351.controller.grant;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.lcmgrant.LcmGrantsFrontController;
import com.ubiqube.etsi.mano.vnfm.v351.model.grant.Grant;
import com.ubiqube.etsi.mano.vnfm.v351.model.grant.GrantLinks;
import com.ubiqube.etsi.mano.vnfm.v351.model.grant.GrantRequest;
import com.ubiqube.etsi.mano.vnfm.v351.model.grant.Link;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class LcmGrants351Sol003Controller implements LcmGrants351Sol003Api {
	private final LcmGrantsFrontController lcmGrantsFrontController;

	public LcmGrants351Sol003Controller(final LcmGrantsFrontController lcmGrantsFrontController) {
		super();
		this.lcmGrantsFrontController = lcmGrantsFrontController;
	}

	@Override
	public ResponseEntity<Grant> grantsGrantIdGet(final String grantId) {
		return lcmGrantsFrontController.grantsGrantIdGet(grantId, Grant.class, LcmGrants351Sol003Controller::makeSelfLinks);
	}

	@Override
	public ResponseEntity<Grant> grantsPost(@Valid final GrantRequest grantRequest) {
		return lcmGrantsFrontController.grantsPost(grantRequest, Grant.class, LcmGrants351Sol003Controller::getSelfLink);
	}

	private static void makeSelfLinks(final Grant jsonGrant) {
		final GrantLinks grantLinks = new GrantLinks();
		final Link link = new Link();
		link.setHref(linkTo(methodOn(LcmGrants351Sol003Api.class).grantsGrantIdGet(jsonGrant.getId())).withSelfRel().getHref());
		grantLinks.setSelf(link);
		jsonGrant.setLinks(grantLinks);
	}

	private static String getSelfLink(final Grant grant) {
		return linkTo(methodOn(LcmGrants351Sol003Api.class).grantsGrantIdGet(grant.getId())).withSelfRel().getHref();
	}

}
