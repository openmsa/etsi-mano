package com.ubiqube.etsi.mano.controller.vnf.sol003;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.vnf.VnfManagement;
import com.ubiqube.etsi.mano.model.vnf.sol005.InlineResponse2001;
import com.ubiqube.etsi.mano.model.vnf.sol005.NotificationsMessage;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscription;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionRequest;

@RestController
public class VnfSubscriptionSol003Api implements VnfSubscriptionSol003 {
	private final VnfManagement vnfManagement;

	public VnfSubscriptionSol003Api(VnfManagement _vnfManagement) {
		vnfManagement = _vnfManagement;
	}

	/**
	 * Query multiple subscriptions.
	 *
	 * The GET method queries the list of active subscriptions of the functional
	 * block that invokes the method. It can be used e.g. for resynchronization
	 * after error situations. This method shall follow the provisions specified in
	 * the Tables 9.4.7.8.2-1 and 9.4.8.3.2-2 for URI query parameters, request and
	 * response data structures, and response codes. ²
	 */
	@Override
	public List<SubscriptionsPkgmSubscription> subscriptionsGet(String filter) {
		return vnfManagement.subscriptionsGet(filter);
	}

	/**
	 * Subscribe to notifications related to on-boarding and/or changes of VNF
	 * packages.
	 *
	 * The POST method creates a new subscription. This method shall follow the
	 * provisions specified in the Tables 9.4.8.3.1-1 and 9.4.8.3.1-2 for URI query
	 * parameters, request and response data structures, and response codes.
	 * Creation of two subscription resources with the same callbackURI and the same
	 * filter can result in performance degradation and will provide duplicates of
	 * notifications to the OSS, and might make sense only in very rare use cases.
	 * Consequently, the NFVO may either allow creating a subscription resource if
	 * another subscription resource with the same filter and callbackUri already
	 * exists (in which case it shall return the \&quot;201 Created\&quot; response
	 * code), or may decide to not create a duplicate subscription resource (in
	 * which case it shall return a \&quot;303 See Other\&quot; response code
	 * referencing the existing subscription resource with the same filter and
	 * callbackUri).
	 *
	 */
	@Override
	public List<InlineResponse2001> subscriptionsPost(SubscriptionsPkgmSubscriptionRequest subscriptionsPostQuery) {
		// Job
		final String id = UUID.randomUUID().toString();
		final String href = linkTo(methodOn(VnfSubscriptionSol003Api.class).subscriptionsSubscriptionIdGet(id, "")).withSelfRel().getHref();
		return vnfManagement.subscriptionsPost(subscriptionsPostQuery, href, id);
	}

	/**
	 * Terminate a subscription.
	 *
	 * The DELETE method terminates an individual subscription.
	 *
	 */
	@Override
	public void subscriptionsSubscriptionIdDelete(String subscriptionId) {
		vnfManagement.subscriptionsSubscriptionIdDelete(subscriptionId);
	}

	/**
	 * Read an individual subscription resource.
	 *
	 * Query Subscription Information The GET method reads an individual
	 * subscription.
	 *
	 */
	@Override
	public SubscriptionsPkgmSubscription subscriptionsSubscriptionIdGet(String subscriptionId, String accept) {
		return vnfManagement.subscriptionsSubscriptionIdGet(subscriptionId);
	}

	/**
	 * Test the notification endpoint
	 *
	 * The GET method allows the server to test the notification endpoint that is
	 * provided by the client, e.g. during subscription. This method shall follow
	 * the provisions specified in the Tables 9.4.10.3.2-1 and 9.4.10.3.2-2 for URI
	 * query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionVnfPackageChangeNotificationGet(String accept) {
		// Nothing.
	}

	/**
	 * Notify about VNF package onboarding or change
	 *
	 * The POST method delivers a notification from the server to the client. This
	 * method shall follow the provisions specified in the Tables 9.4.10.3.1-1 and
	 * 9.4.10.3.1-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	public void vnfPackageChangeNotificationPost(NotificationsMessage notificationsMessage) {

		final String id = UUID.randomUUID().toString();
		final String vnfPkgId = notificationsMessage.getVnfPkgId();
		final String subscriptionId = notificationsMessage.getSubscriptionId();

		final String hrefVnfPackage = linkTo(methodOn(VnfPackageSol003Api.class).vnfPackagesVnfPkgIdGet(vnfPkgId, "")).withSelfRel().getHref();
		final String hrefSubscription = linkTo(methodOn(VnfSubscriptionSol003Api.class).subscriptionsSubscriptionIdGet(subscriptionId, "")).withSelfRel().getHref();

		vnfManagement.vnfPackageChangeNotificationPost(notificationsMessage, id, hrefVnfPackage, hrefSubscription);
	}

	/**
	 * Notify about VNF package onboarding or change
	 *
	 * The POST method delivers a notification from the server to the client. This
	 * method shall follow the provisions specified in the Tables 9.4.10.3.1-1 and
	 * 9.4.10.3.1-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	public void vnfPackageOnboardingNotificationPost(NotificationsMessage notificationsMessage) {

		final String id = UUID.randomUUID().toString();
		final String subscriptionId = notificationsMessage.getSubscriptionId();
		final String vnfPkgId = notificationsMessage.getVnfPkgId();

		final String hrefSubscription = linkTo(methodOn(VnfSubscriptionSol003Api.class).subscriptionsSubscriptionIdGet(subscriptionId, "")).withSelfRel().getHref();
		final String hrefPackage = linkTo(methodOn(VnfPackageSol003Api.class).vnfPackagesVnfPkgIdGet(vnfPkgId, "")).withSelfRel().getHref();

		vnfManagement.vnfPackageOnboardingNotificationPost(notificationsMessage, id, hrefSubscription, hrefPackage);
	}

}
