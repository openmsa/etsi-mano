package com.ubiqube.etsi.mano.vnfm.v261.controller.vnf;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.UUID;

import com.ubiqube.etsi.mano.common.v261.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmLinks;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscriptionLinks;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfoLinks;
import com.ubiqube.etsi.mano.dao.mano.ApiTypesEnum;

public class Sol003Linkable implements Linkable {

	@Override
	public VnfPkgInfoLinks getVnfLinks(final String vnfPkgId) {
		final VnfPkgInfoLinks links = new VnfPkgInfoLinks();

		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfPackageSol003.class).vnfPackagesVnfPkgIdGet(vnfPkgId)).withSelfRel().getHref());
		links.self(self);

		final Link vnfd = new Link();
		vnfd.setHref(linkTo(methodOn(VnfPackageSol003.class).vnfPackagesVnfPkgIdVnfdGet(vnfPkgId, null)).withSelfRel().getHref());
		links.setVnfd(vnfd);

		final Link packageContent = new Link();
		packageContent.setHref(linkTo(methodOn(VnfPackageSol003.class).vnfPackagesVnfPkgIdPackageContentGet(vnfPkgId, "")).withSelfRel().getHref());
		links.setPackageContent(packageContent);
		return links;
	}

	@Override
	public PkgmLinks createNotificationLink(final UUID _vnfPkgId, final UUID _subscriptionId) {
		final PkgmLinks ret = new PkgmLinks();
		final Link subscription = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfSubscriptionSol003.class).subscriptionsSubscriptionIdGet(_subscriptionId.toString())).withSelfRel().getHref());
		ret.setSubscription(subscription);

		final Link vnfPackage = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfPackageSol003.class).vnfPackagesVnfPkgIdGet(_vnfPkgId.toString())).withSelfRel().getHref());
		ret.setVnfPackage(vnfPackage);
		return ret;
	}

	@Override
	public PkgmLinks createVnfPackageOnboardingNotificationLinks(final UUID _vnfPkgId, final UUID _subscriptionId) {
		final PkgmLinks vnfPackageOnboardingNotificationLinks = new PkgmLinks();
		final Link subscription = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfSubscriptionSol003.class).subscriptionsSubscriptionIdGet(_subscriptionId.toString())).withSelfRel().getHref());
		vnfPackageOnboardingNotificationLinks.setSubscription(subscription);

		final Link vnfPackage = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfPackageSol003.class).vnfPackagesVnfPkgIdGet(_vnfPkgId.toString())).withSelfRel().getHref());
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
		self.setHref(linkTo(methodOn(VnfSubscriptionSol003.class).subscriptionsSubscriptionIdGet(_subscriptionId)).withSelfRel().getHref());
		subscriptionsPkgmSubscriptionLinks.setSelf(self);
		return subscriptionsPkgmSubscriptionLinks;
	}

	@Override
	public ApiTypesEnum getApi() {
		return ApiTypesEnum.SOL003;
	}
}
