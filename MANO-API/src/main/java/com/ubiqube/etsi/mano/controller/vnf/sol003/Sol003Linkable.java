package com.ubiqube.etsi.mano.controller.vnf.sol003;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.ubiqube.etsi.mano.controller.vnf.ApiTypesEnum;
import com.ubiqube.etsi.mano.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionLinks;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageOnboardingNotificationLinks;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoLinks;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoLinksSelf;

public class Sol003Linkable implements Linkable {

	@Override
	public VnfPackagesVnfPkgInfoLinks getVnfLinks(final String vnfPkgId) {
		final VnfPackagesVnfPkgInfoLinks links = new VnfPackagesVnfPkgInfoLinks();

		final VnfPackagesVnfPkgInfoLinksSelf self = new VnfPackagesVnfPkgInfoLinksSelf();
		self.setHref(linkTo(methodOn(VnfPackageSol003.class).vnfPackagesVnfPkgIdGet(vnfPkgId, "")).withSelfRel().getHref());
		links.self(self);

		final VnfPackagesVnfPkgInfoLinksSelf vnfd = new VnfPackagesVnfPkgInfoLinksSelf();
		vnfd.setHref(linkTo(methodOn(VnfPackageSol003.class).vnfPackagesVnfPkgIdVnfdGet(vnfPkgId, null)).withSelfRel().getHref());
		links.setVnfd(vnfd);

		final VnfPackagesVnfPkgInfoLinksSelf packageContent = new VnfPackagesVnfPkgInfoLinksSelf();
		packageContent.setHref(linkTo(methodOn(VnfPackageSol003.class).vnfPackagesVnfPkgIdPackageContentGet(vnfPkgId, "", null)).withSelfRel().getHref());
		links.setPackageContent(packageContent);
		return links;
	}

	@Override
	public VnfPackageOnboardingNotificationLinks createNotificationLink(final String _vnfPkgId, final String _subscriptionId) {
		final VnfPackageOnboardingNotificationLinks ret = new VnfPackageOnboardingNotificationLinks();
		final VnfPackagesVnfPkgInfoLinksSelf subscription = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfSubscriptionSol003.class).subscriptionsSubscriptionIdGet(_subscriptionId, "")).withSelfRel().getHref());
		ret.setSubscription(subscription);

		final VnfPackagesVnfPkgInfoLinksSelf vnfPackage = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfPackageSol003.class).vnfPackagesVnfPkgIdGet(_vnfPkgId, "")).withSelfRel().getHref());
		ret.setVnfPackage(vnfPackage);
		return ret;
	}

	@Override
	public VnfPackageOnboardingNotificationLinks createVnfPackageOnboardingNotificationLinks(final String _vnfPkgId, final String _subscriptionId) {
		final VnfPackageOnboardingNotificationLinks vnfPackageOnboardingNotificationLinks = new VnfPackageOnboardingNotificationLinks();
		final VnfPackagesVnfPkgInfoLinksSelf subscription = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfSubscriptionSol003.class).subscriptionsSubscriptionIdGet(_subscriptionId, "")).withSelfRel().getHref());
		vnfPackageOnboardingNotificationLinks.setSubscription(subscription);

		final VnfPackagesVnfPkgInfoLinksSelf vnfPackage = createVnfPackagesVnfPkgInfoLinksSelf(
				linkTo(methodOn(VnfPackageSol003.class).vnfPackagesVnfPkgIdGet(_vnfPkgId, "")).withSelfRel().getHref());
		vnfPackageOnboardingNotificationLinks.setVnfPackage(vnfPackage);

		return null;
	}

	public static VnfPackagesVnfPkgInfoLinksSelf createVnfPackagesVnfPkgInfoLinksSelf(final String _href) {
		final VnfPackagesVnfPkgInfoLinksSelf link = new VnfPackagesVnfPkgInfoLinksSelf();
		link.setHref(_href);
		return link;
	}

	@Override
	public SubscriptionsPkgmSubscriptionLinks createSubscriptionsPkgmSubscriptionLinks(final String _subscriptionId) {
		final SubscriptionsPkgmSubscriptionLinks subscriptionsPkgmSubscriptionLinks = new SubscriptionsPkgmSubscriptionLinks();
		final VnfPackagesVnfPkgInfoLinksSelf self = new VnfPackagesVnfPkgInfoLinksSelf();
		self.setHref(linkTo(methodOn(VnfSubscriptionSol003.class).subscriptionsSubscriptionIdGet(_subscriptionId, "")).withSelfRel().getHref());
		subscriptionsPkgmSubscriptionLinks.setSelf(self);
		return subscriptionsPkgmSubscriptionLinks;
	}

	@Override
	public ApiTypesEnum getApi() {
		return ApiTypesEnum.SOL003;
	}
}
