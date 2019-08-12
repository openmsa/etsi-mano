package com.ubiqube.etsi.mano.controller.vnf.sol005;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.vnf.VnfSubscriptionManagement;
import com.ubiqube.etsi.mano.model.vnf.sol005.InlineResponse2001;
import com.ubiqube.etsi.mano.model.vnf.sol005.NotificationsMessage;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscription;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionRequest;

@RestController
public class VnfSubscriptionSol005Api implements VnfSubscriptionSol005 {
	private final VnfSubscriptionManagement vnfSubscriptionManagement;

	public VnfSubscriptionSol005Api(VnfSubscriptionManagement _vnfSubscriptionManagement) {
		vnfSubscriptionManagement = _vnfSubscriptionManagement;
	}

	@Override
	public ResponseEntity<List<SubscriptionsPkgmSubscription>> subscriptionsGet(@RequestParam(value = "filter", required = false) String filters) {
		return new ResponseEntity<>(vnfSubscriptionManagement.subscriptionsGet(filters), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<InlineResponse2001>> subscriptionsPost(SubscriptionsPkgmSubscriptionRequest subscriptionsPostQuery) {
		// Job
		final String id = UUID.randomUUID().toString();
		final String href = linkTo(methodOn(VnfSubscriptionSol005Api.class).subscriptionsSubscriptionIdGet(id, "")).withSelfRel().getHref();
		return new ResponseEntity<>(vnfSubscriptionManagement.subscriptionsPost(subscriptionsPostQuery, href, id), HttpStatus.OK);
	}

	@Override
	public void subscriptionsSubscriptionIdDelete(String subscriptionId) {
		vnfSubscriptionManagement.subscriptionsSubscriptionIdDelete(subscriptionId);
	}

	@Override
	public ResponseEntity<SubscriptionsPkgmSubscription> subscriptionsSubscriptionIdGet(String subscriptionId, String accept) {
		return new ResponseEntity<>(vnfSubscriptionManagement.subscriptionsSubscriptionIdGet(subscriptionId), HttpStatus.OK);

	}

	@Override
	public void vnfPackageChangeNotificationPost(NotificationsMessage notificationsMessage) {

		final String id = UUID.randomUUID().toString();
		final String vnfPkgId = notificationsMessage.getVnfPkgId();
		final String subscriptionId = notificationsMessage.getSubscriptionId();

		final String hrefVnfPackage = linkTo(methodOn(VnfPackageSol005Api.class).vnfPackagesVnfPkgIdGet(vnfPkgId, "")).withSelfRel().getHref();
		final String hrefSubscription = linkTo(methodOn(VnfSubscriptionSol005Api.class).subscriptionsSubscriptionIdGet(subscriptionId, "")).withSelfRel().getHref();
		vnfSubscriptionManagement.vnfPackageChangeNotificationPost(notificationsMessage, id, hrefVnfPackage, hrefSubscription);
	}

	@Override
	public void vnfPackageOnboardingNotificationPost(NotificationsMessage notificationsMessage) {

		final String id = UUID.randomUUID().toString();
		final String subscriptionId = notificationsMessage.getSubscriptionId();
		final String vnfPkgId = notificationsMessage.getVnfPkgId();

		final String hrefSubscription = linkTo(methodOn(VnfSubscriptionSol005Api.class).subscriptionsSubscriptionIdGet(subscriptionId, "")).withSelfRel().getHref();
		final String hrefPackage = linkTo(methodOn(VnfPackageSol005Api.class).vnfPackagesVnfPkgIdGet(vnfPkgId, "")).withSelfRel().getHref();

		vnfSubscriptionManagement.vnfPackageOnboardingNotificationPost(notificationsMessage, id, hrefSubscription, hrefPackage);
	}

}
