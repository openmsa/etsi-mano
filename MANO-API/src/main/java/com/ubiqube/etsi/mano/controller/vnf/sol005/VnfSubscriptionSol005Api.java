package com.ubiqube.etsi.mano.controller.vnf.sol005;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.controller.vnf.VnfSubscriptionManagement;
import com.ubiqube.etsi.mano.model.vnf.sol005.InlineResponse2001;
import com.ubiqube.etsi.mano.model.vnf.sol005.NotificationsMessage;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscription;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionRequest;

@Profile({ "!VNFM" })
@RestController
public class VnfSubscriptionSol005Api implements VnfSubscriptionSol005 {

	private static final Logger LOG = LoggerFactory.getLogger(VnfSubscriptionSol005Api.class);

	private final VnfSubscriptionManagement vnfSubscriptionManagement;
	private final Linkable links = new Sol005Linkable();

	public VnfSubscriptionSol005Api(final VnfSubscriptionManagement _vnfSubscriptionManagement) {
		vnfSubscriptionManagement = _vnfSubscriptionManagement;
		LOG.info("Starting VNF Subscription Package SOL005 Controller.");
	}

	@Override
	public ResponseEntity<List<SubscriptionsPkgmSubscription>> subscriptionsGet(@RequestParam(value = "filter", required = false) final String filters) {
		return new ResponseEntity<>(vnfSubscriptionManagement.subscriptionsGet(filters, links), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<InlineResponse2001>> subscriptionsPost(final SubscriptionsPkgmSubscriptionRequest subscriptionsPostQuery) {
		// Job
		return new ResponseEntity<>(vnfSubscriptionManagement.subscriptionsPost(subscriptionsPostQuery, links), HttpStatus.OK);
	}

	@Override
	public void subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		vnfSubscriptionManagement.subscriptionsSubscriptionIdDelete(subscriptionId);
	}

	@Override
	public ResponseEntity<SubscriptionsPkgmSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId, final String accept) {
		return new ResponseEntity<>(vnfSubscriptionManagement.subscriptionsSubscriptionIdGet(subscriptionId, links), HttpStatus.OK);

	}

	@Override
	public void vnfPackageChangeNotificationPost(final NotificationsMessage notificationsMessage) {
		vnfSubscriptionManagement.vnfPackageChangeNotificationPost(notificationsMessage, links);
	}

	@Override
	public void vnfPackageOnboardingNotificationPost(final NotificationsMessage notificationsMessage) {
		vnfSubscriptionManagement.vnfPackageOnboardingNotificationPost(notificationsMessage, links);
	}

}
