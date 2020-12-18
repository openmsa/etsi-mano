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
package com.ubiqube.etsi.mano.nfvo.v271.controller.vnf;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.ubiqube.etsi.mano.controller.FrontApiTypesEnum;
import com.ubiqube.etsi.mano.model.v271.sol003.vnf.PkgmSubscriptionLinks;
import com.ubiqube.etsi.mano.model.v271.sol003.vnf.VnfPkgInfoLinks;
import com.ubiqube.etsi.mano.nfvo.v271.model.Link;

public class Sol003Linkable implements Linkable {

	@Override
	public VnfPkgInfoLinks getVnfLinks(final String vnfPkgId) {
		final VnfPkgInfoLinks links = new VnfPkgInfoLinks();

		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfPackagesApiSol003.class).vnfPackagesVnfPkgIdGet(vnfPkgId, null, null)).withSelfRel().getHref());
		links.self(self);

		final Link vnfd = new Link();
		vnfd.setHref(linkTo(methodOn(VnfPackagesApiSol003.class).vnfPackagesVnfPkgIdVnfdGet(vnfPkgId, null, null, null)).withSelfRel().getHref());
		links.setVnfd(vnfd);

		final Link packageContent = new Link();
		packageContent.setHref(linkTo(methodOn(VnfPackagesApiSol003.class).vnfPackagesVnfPkgIdPackageContentGet(vnfPkgId, "", null)).withSelfRel().getHref());
		links.setPackageContent(packageContent);
		return links;
	}

	@Override
	public PkgmSubscriptionLinks createSubscriptionsPkgmSubscriptionLinks(final String _subscriptionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FrontApiTypesEnum getApi() {
		return FrontApiTypesEnum.SOL003;
	}

}
