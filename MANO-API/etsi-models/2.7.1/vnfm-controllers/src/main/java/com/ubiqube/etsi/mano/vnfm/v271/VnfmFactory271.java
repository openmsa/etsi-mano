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
package com.ubiqube.etsi.mano.vnfm.v271;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.em.v271.controller.vnflcm.VnfInstances271Sol002Api;
import com.ubiqube.etsi.mano.em.v271.controller.vnflcm.VnfInstances271Sol002Controller;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.Link;
import com.ubiqube.etsi.mano.model.v271.sol003.lcmgrant.GrantRequest;
import com.ubiqube.etsi.mano.model.v271.sol003.lcmgrant.GrantRequestLinks;
import com.ubiqube.etsi.mano.v271.services.VnfmFactory;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfmFactory271 implements VnfmFactory {
	@Override
	public void makeGrantRequestLink(final GrantRequest manoGrant) {
		final GrantRequestLinks links = new GrantRequestLinks();
		Link link = new Link();
		link.setHref(VnfInstances271Sol002Controller.getSelfLink(manoGrant.getVnfInstanceId()));
		links.setVnfInstance(link);

		link = new Link();
		link.setHref(linkTo(methodOn(VnfInstances271Sol002Api.class).vnfInstancesVnfInstanceIdGet(manoGrant.getVnfLcmOpOccId())).withSelfRel().getHref());
		links.setVnfLcmOpOcc(link);
	}

}
