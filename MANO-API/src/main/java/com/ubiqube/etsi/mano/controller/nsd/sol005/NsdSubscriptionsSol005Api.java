package com.ubiqube.etsi.mano.controller.nsd.sol005;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.model.nsd.sol005.NsdmSubscription;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdmSubscriptionRequest;
import com.ubiqube.etsi.mano.model.nsperfo.sol005.SubscriptionsPostResponse;

@Profile({ "!VNFM" })
@RestController
public class NsdSubscriptionsSol005Api implements NsdSubscriptionsSol005 {

	private static final Logger LOG = LoggerFactory.getLogger(NsdSubscriptionsSol005Api.class);

	public NsdSubscriptionsSol005Api() {
		LOG.info("Starting NSD Subscription SOL005 Controller.");
	}

	/**
	 * Query multiple subscriptions.
	 *
	 * The GET method queries the list of active subscriptions of the functional
	 * block that invokes the method. It can be used e.g. for resynchronization
	 * after error situations. This method shall support the URI query parameters,
	 * request and response data structures, and response codes. This resource
	 * represents subscriptions. The client can use this resource to subscribe to
	 * notifications related to NSD management and to query its subscriptions.
	 *
	 */
	@Override
	public ResponseEntity<List<Object>> subscriptionsGet(final String accept, final String filter) {
		// : Implement...
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Subscribe to NSD and PNFD change notifications.
	 *
	 * The POST method creates a new subscription. This method shall support the URI
	 * query parameters, request and response data structures, and response codes,
	 * as specified in the Tables 5.4.8.3.1-1 and 5.4.8.3.1-2 of GS-NFV SOL 005.
	 * Creation of two subscription resources with the same callbackURI and the same
	 * filter can result in performance degradation and will provide duplicates of
	 * notifications to the OSS, and might make sense only in very rare use cases.
	 * Consequently, the NFVO may either allow creating a subscription resource if
	 * another subscription resource with the same filter and callbackUri already
	 * exists (in which case it shall return the \&quot;201 Created\&quot; response
	 * code), or may decide to not create a duplicate subscription resource (in
	 * which case it shall return a \&quot;303 See Other\&quot; response code
	 * referencing the existing subscription resource with the same filter and
	 * callbackUri). This resource represents subscriptions. The client can use this
	 * resource to subscribe to notifications related to NSD management and to query
	 * its subscriptions.
	 *
	 */
	@Override
	public ResponseEntity<NsdmSubscription> subscriptionsPost(final String accept, final String contentType, final NsdmSubscriptionRequest body) {
		// : Implement...
		return null;
	}

	/**
	 * Terminate Subscription
	 *
	 * This resource represents an individual subscription. It can be used by the
	 * client to read and to terminate a subscription to notifications related to
	 * NSD management. The DELETE method terminates an individual subscription. This
	 * method shall support the URI query parameters, request and response data
	 * structures, and response codes, as specified in the Table 5.4.9.3.3-2.
	 *
	 */
	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		// : Implement...
		return ResponseEntity.noContent().build();
	}

	/**
	 * Read an individual subscription resource.
	 *
	 * This resource represents an individual subscription. It can be used by the
	 * client to read and to terminate a subscription to notifications related to
	 * NSD management. The GET method retrieves information about a subscription by
	 * reading an individual subscription resource. This resource represents an
	 * individual subscription. It can be used by the client to read and to
	 * terminate a subscription to notifications related to NSD management.
	 *
	 */
	@Override
	public ResponseEntity<SubscriptionsPostResponse> subscriptionsSubscriptionIdGet(final String subscriptionId, final String accept) {
		// : Implement...
		return null;
	}

}
