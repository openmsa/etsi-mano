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

package com.ubiqube.etsi.mano.nfvo.v261.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.UUID;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmLinks;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscription;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscriptionLinks;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfoLinks;
import com.ubiqube.etsi.mano.common.v261.services.Linkable;
import com.ubiqube.etsi.mano.controller.FrontApiTypesEnum;
import com.ubiqube.etsi.mano.nfvo.v261.controller.vnf.VnfPackage261Sol003Api;
import com.ubiqube.etsi.mano.nfvo.v261.controller.vnf.VnfSubscription261Sol003Api;

public class Sol003Linkable implements Linkable {

	public static VnfPkgInfoLinks getVnfLinks(final String vnfPkgId) {
		final VnfPkgInfoLinks links = new VnfPkgInfoLinks();

		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfPackage261Sol003Api.class).vnfPackagesVnfPkgIdGet(vnfPkgId)).withSelfRel().getHref());
		links.self(self);

		final Link vnfd = new Link();
		vnfd.setHref(linkTo(methodOn(VnfPackage261Sol003Api.class).vnfPackagesVnfPkgIdVnfdGet(vnfPkgId, null)).withSelfRel().getHref());
		links.setVnfd(vnfd);

		final Link packageContent = new Link();
		packageContent.setHref(linkTo(methodOn(VnfPackage261Sol003Api.class).vnfPackagesVnfPkgIdPackageContentGet(vnfPkgId)).withSelfRel().getHref());
		links.setPackageContent(packageContent);
		return links;
	}

	@Override
	public PkgmLinks createNotificationLink(final UUID _vnfPkgId, final UUID _subscriptionId) {
		final PkgmLinks ret = new PkgmLinks();
		final Link subscription = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfSubscription261Sol003Api.class).subscriptionsSubscriptionIdGet(_subscriptionId.toString())).withSelfRel().getHref());
		ret.setSubscription(subscription);

		final Link vnfPackage = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfPackage261Sol003Api.class).vnfPackagesVnfPkgIdGet(_vnfPkgId.toString())).withSelfRel().getHref());
		ret.setVnfPackage(vnfPackage);
		return ret;
	}

	@Override
	public PkgmLinks createVnfPackageOnboardingNotificationLinks(final UUID _vnfPkgId, final UUID _subscriptionId) {
		final PkgmLinks vnfPackageOnboardingNotificationLinks = new PkgmLinks();
		final Link subscription = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfSubscription261Sol003Api.class).subscriptionsSubscriptionIdGet(_subscriptionId.toString())).withSelfRel().getHref());
		vnfPackageOnboardingNotificationLinks.setSubscription(subscription);

		final Link vnfPackage = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfPackage261Sol003Api.class).vnfPackagesVnfPkgIdGet(_vnfPkgId.toString())).withSelfRel().getHref());
		vnfPackageOnboardingNotificationLinks.setVnfPackage(vnfPackage);

		return null;
	}

	private static Link createVnfPackagesVnfPkgInfoLinksSelf(final String _href) {
		final Link link = new Link();
		link.setHref(_href);
		return link;
	}

	@Override
	public PkgmSubscriptionLinks createSubscriptionsPkgmSubscriptionLinks(final String _subscriptionId) {
		final PkgmSubscriptionLinks subscriptionsPkgmSubscriptionLinks = new PkgmSubscriptionLinks();
		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfSubscription261Sol003Api.class).subscriptionsSubscriptionIdGet(_subscriptionId)).withSelfRel().getHref());
		subscriptionsPkgmSubscriptionLinks.setSelf(self);
		return subscriptionsPkgmSubscriptionLinks;
	}

	@Override
	public void makeSubscriptionLink(final PkgmSubscription pkgmSubscription) {
		pkgmSubscription.setLinks(createSubscriptionsPkgmSubscriptionLinks(pkgmSubscription.getId()));
	}

	@Override
	public FrontApiTypesEnum getApi() {
		return FrontApiTypesEnum.SOL003;
	}

	@Override
	public void makeLinks(final VnfPkgInfo _vnfPkgInfo) {
		_vnfPkgInfo.setLinks(getVnfLinks(_vnfPkgInfo.getId()));
	}

	@Override
	public String getSelfLink(final VnfPkgInfo _vnfPkgInfo) {
		return linkTo(methodOn(VnfPackage261Sol003Api.class).vnfPackagesVnfPkgIdGet(_vnfPkgInfo.getId())).withSelfRel().getHref();
	}
}
