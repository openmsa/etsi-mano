package com.ubiqube.etsi.mano.controller.nsd.sol005;

import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.model.nsd.sol005.NsdChangeNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdDeletionNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdOnBoardingFailureNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdOnBoardingNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdDeletionNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdOnBoardingFailureNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdOnBoardingNotification;

@RestController
public class NsNotificationsSol005Api implements NsNotificationsSol005 {

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The POST method
	 * delivers a notification from the server to the client. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsdChangeNotificationPost(final NsdChangeNotification nsdChangeNotification, final String accept, final String contentType) {
		// : Implement...

	}

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The POST method
	 * delivers a notification from the server to the client. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsdDeletionNotificationPost(final NsdDeletionNotification nsdDeletionNotification, final String accept, final String contentType) {
		// : Implement...

	}

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The POST method
	 * delivers a notification from the server to the client. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsdOnBoardingFailureNotificationPost(final NsdOnBoardingFailureNotification nsdOnBoardingFailureNotification, final String accept, final String contentType) {
		// : Implement...

	}

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The POST method
	 * delivers a notification from the server to the client. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsdOnBoardingNotificationPost(final NsdOnBoardingNotification nsdOnBoardingNotification, final String accept, final String contentType) {
		// : Implement...

	}

	/**
	 * Test the notification endpoint
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The GET method allows
	 * the server to test the notification endpoint that is provided by the client,
	 * e.g. during subscription. This method shall follow the provisions specified
	 * in the Table 5.4.10.3.2-2 for URI query parameters, request and response data
	 * structures, and response codes.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionPnfdDeletionNotificationGet(final String accept) {
		// : Implement...

	}

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The POST method
	 * delivers a notification from the server to the client. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionPnfdDeletionNotificationPost(final PnfdDeletionNotification pnfdDeletionNotification, final String accept, final String contentType) {
		// : Implement...

	}

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The POST method
	 * delivers a notification from the server to the client. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionPnfdOnBoardingFailureNotificationPost(final PnfdOnBoardingFailureNotification pnfdOnBoardingFailureNotification, final String accept, final String contentType) {
		// : Implement...

	}

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this
	 * resource to send notifications to a subscribed client, which has provided the
	 * URI of this resource during the subscription process. The POST method
	 * delivers a notification from the server to the client. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionPnfdOnBoardingNotificationPost(final PnfdOnBoardingNotification pnfdOnBoardingNotification, final String accept, final String contentType) {
		// : Implement...

	}

}
