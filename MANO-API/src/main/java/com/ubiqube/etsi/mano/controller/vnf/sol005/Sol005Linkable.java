package com.ubiqube.etsi.mano.controller.vnf.sol005;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.ubiqube.etsi.mano.controller.vnf.ApiTypesEnum;
import com.ubiqube.etsi.mano.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.model.vnf.sol005.Link;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoLinksSelf;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfoLinks;

public class Sol005Linkable implements Linkable {

	@Override
	public VnfPkgInfoLinks getVnfLinks(final String vnfPkgId) {
		final VnfPkgInfoLinks links = new VnfPkgInfoLinks();

		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfPackageSol005.class).vnfPackagesVnfPkgIdGet(vnfPkgId, "")).withSelfRel().getHref());
		links.self(self);

		final Link vnfd = new Link();
		vnfd.setHref(linkTo(methodOn(VnfPackageSol005.class).vnfPackagesVnfPkgIdVnfdGet(vnfPkgId, null)).withSelfRel().getHref());
		links.setVnfd(vnfd);

		final Link packageContent = new Link();
		packageContent.setHref(linkTo(methodOn(VnfPackageSol005.class).vnfPackagesVnfPkgIdPackageContentGet(vnfPkgId, "", null)).withSelfRel().getHref());
		links.setPackageContent(packageContent);
		return links;
	}

	@Override
	public VnfPkgInfoLinks createNotificationLink(final String _vnfPkgId, final String _subscriptionId) {
		final VnfPkgInfoLinks ret = new VnfPkgInfoLinks();
		final VnfPackagesVnfPkgInfoLinksSelf subscription = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfSubscriptionSol005.class).subscriptionsSubscriptionIdGet(_subscriptionId, "")).withSelfRel().getHref());
		ret.setSubscription(subscription);

		final VnfPackagesVnfPkgInfoLinksSelf vnfPackage = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfPackageSol005.class).vnfPackagesVnfPkgIdGet(_vnfPkgId, "")).withSelfRel().getHref());
		ret.setVnfPackage(vnfPackage);
		return ret;
	}

	@Override
	public VnfPkgInfoLinks createVnfPackageOnboardingNotificationLinks(final String _vnfPkgId, final String _subscriptionId) {
		final VnfPkgInfoLinks vnfPackageOnboardingNotificationLinks = new VnfPkgInfoLinks();
		final VnfPackagesVnfPkgInfoLinksSelf subscription = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfSubscriptionSol005.class).subscriptionsSubscriptionIdGet(_subscriptionId, "")).withSelfRel().getHref());
		vnfPackageOnboardingNotificationLinks.setSubscription(subscription);

		final VnfPackagesVnfPkgInfoLinksSelf vnfPackage = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfPackageSol005.class).vnfPackagesVnfPkgIdGet(_vnfPkgId, "")).withSelfRel().getHref());
		vnfPackageOnboardingNotificationLinks.setVnfPackage(vnfPackage);

		return null;
	}

	private static Link createVnfPackagesVnfPkgInfoLinksSelf(final String _href) {
		final Link link = new Link();
		link.setHref(_href);
		return link;
	}

	@Override
	public VnfPkgInfoLinks createSubscriptionsPkgmSubscriptionLinks(final String _subscriptionId) {
		final VnfPkgInfoLinks subscriptionsPkgmSubscriptionLinks = new VnfPkgInfoLinks();
		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfSubscriptionSol005.class).subscriptionsSubscriptionIdGet(_subscriptionId, "")).withSelfRel().getHref());
		subscriptionsPkgmSubscriptionLinks.setSelf(self);
		return subscriptionsPkgmSubscriptionLinks;
	}

	@Override
	public ApiTypesEnum getApi() {
		return ApiTypesEnum.SOL005;
	}
}
