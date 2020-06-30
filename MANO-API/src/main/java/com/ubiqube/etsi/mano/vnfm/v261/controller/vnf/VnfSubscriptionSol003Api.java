package com.ubiqube.etsi.mano.vnfm.v261.controller.vnf;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscription;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscriptionRequest;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgChangeNotification;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgOnboardingNotification;
import com.ubiqube.etsi.mano.controller.vnf.VnfSubscriptionManagement;
import com.ubiqube.etsi.mano.dao.mano.Subscription;

import ma.glasnost.orika.MapperFacade;

@Profile({ "!NFVO" })
@RestController
public class VnfSubscriptionSol003Api implements VnfSubscriptionSol003 {

	private static final Logger LOG = LoggerFactory.getLogger(VnfSubscriptionSol003Api.class);

	private final VnfSubscriptionManagement vnfSubscriptionManagement;

	private final Linkable links = new Sol003Linkable();

	private final MapperFacade mapper;

	public VnfSubscriptionSol003Api(final VnfSubscriptionManagement _vnfSubscriptionManagement, final MapperFacade _mapper) {
		vnfSubscriptionManagement = _vnfSubscriptionManagement;
		mapper = _mapper;
		LOG.info("Starting VNF Subscription Package SOL003 Controller.");
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
	public List<PkgmSubscription> subscriptionsGet(final String filter) {
		final List<Subscription> subs = vnfSubscriptionManagement.subscriptionsGet(filter);
		final List<PkgmSubscription> pkgms = mapper.mapAsList(subs, PkgmSubscription.class);
		pkgms.stream()
				.forEach(x -> x.setLinks(links.createSubscriptionsPkgmSubscriptionLinks(x.getId())));
		return pkgms;
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
	public PkgmSubscription subscriptionsPost(final PkgmSubscriptionRequest subscriptionsPostQuery) {
		final Subscription subs = mapper.map(subscriptionsPostQuery, Subscription.class);
		final Subscription res = vnfSubscriptionManagement.subscriptionsPost(subs);
		final PkgmSubscription pkgm = mapper.map(res, PkgmSubscription.class);
		pkgm.setLinks(links.createSubscriptionsPkgmSubscriptionLinks(pkgm.getId()));
		return pkgm;
	}

	/**
	 * Terminate a subscription.
	 *
	 * The DELETE method terminates an individual subscription.
	 *
	 */
	@Override
	public void subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		vnfSubscriptionManagement.subscriptionsSubscriptionIdDelete(subscriptionId);
	}

	/**
	 * Read an individual subscription resource.
	 *
	 * Query Subscription Information The GET method reads an individual
	 * subscription.
	 *
	 */
	@Override
	public PkgmSubscription subscriptionsSubscriptionIdGet(final String subscriptionId) {
		final Subscription res = vnfSubscriptionManagement.subscriptionsSubscriptionIdGet(UUID.fromString(subscriptionId));
		final PkgmSubscription pkgm = mapper.map(res, PkgmSubscription.class);
		pkgm.setLinks(links.createSubscriptionsPkgmSubscriptionLinks(pkgm.getId()));
		return pkgm;
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
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionVnfPackageChangeNotificationGet() {
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
	public void vnfPackageChangeNotificationPost(final VnfPackageChangeNotification notificationsMessage) {
		final VnfPkgChangeNotification msg = mapper.map(notificationsMessage, VnfPkgChangeNotification.class);
		vnfSubscriptionManagement.vnfPackageChangeNotificationPost(msg);
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
	public void vnfPackageOnboardingNotificationPost(final VnfPackageOnboardingNotification notificationsMessage) {
		final VnfPkgOnboardingNotification msg = mapper.map(notificationsMessage, VnfPkgOnboardingNotification.class);
		vnfSubscriptionManagement.vnfPackageOnboardingNotificationPost(msg);
	}

}
