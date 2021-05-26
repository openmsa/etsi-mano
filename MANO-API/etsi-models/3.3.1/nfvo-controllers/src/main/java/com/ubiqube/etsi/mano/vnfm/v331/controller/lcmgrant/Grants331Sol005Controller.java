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
package com.ubiqube.etsi.mano.vnfm.v331.controller.lcmgrant;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.lcmgrant.LcmGrantsFrontController;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.Grant;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.GrantRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.Link;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class Grants331Sol005Controller implements Grants331Sol005Api {
	private final LcmGrantsFrontController lcmGrantsFrontController;

	public Grants331Sol005Controller(final LcmGrantsFrontController lcmGrantsFrontController) {
		super();
		this.lcmGrantsFrontController = lcmGrantsFrontController;
	}

	@Override
	public ResponseEntity<Grant> grantsGrantIdGet(final String grantId) {
		return lcmGrantsFrontController.grantsGrantIdGet(grantId, Grant.class, Grants331Sol005Controller::makeSelfLinks);
	}

	@Override
	public ResponseEntity<Grant> grantsPost(@Valid final GrantRequest grantRequest) {
		return lcmGrantsFrontController.grantsPost(grantRequest, Grant.class, Grants331Sol005Controller::getSelfLink);
	}

	private static void makeSelfLinks(final Grant jsonGrant) {
		final Link link = new Link();
		link.setHref(linkTo(methodOn(Grants331Sol005Api.class).grantsGrantIdGet(jsonGrant.getId())).withSelfRel().getHref());
		jsonGrant.getLinks().setSelf(link);
	}

	private static String getSelfLink(final Grant grant) {
		return linkTo(methodOn(Grants331Sol005Api.class).grantsGrantIdGet(grant.getId())).withSelfRel().getHref();
	}
}
