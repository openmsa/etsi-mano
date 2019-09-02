package com.ubiqube.etsi.mano.controller.nsd.sol005;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.model.nsd.sol005.NsdChangeNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdDeletionNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdOnBoardingFailureNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdOnBoardingNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdDeletionNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdOnBoardingFailureNotification;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdOnBoardingNotification;

@Profile({ "default", "NFVO" })
@RestController
public class NsNotificationsSol005Api implements NsNotificationsSol005 {

	private static final Logger LOG = LoggerFactory.getLogger(NsNotificationsSol005Api.class);

	public NsNotificationsSol005Api() {
		LOG.info("Starting NSD Notification SOL005 Controller.");
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
	public void nsdChangeNotificationPost(final NsdChangeNotification nsdChangeNotification, final String accept, final String contentType) {
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
	public void nsdDeletionNotificationPost(final NsdDeletionNotification nsdDeletionNotification, final String accept, final String contentType) {
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
	public void nsdOnBoardingFailureNotificationPost(final NsdOnBoardingFailureNotification nsdOnBoardingFailureNotification, final String accept, final String contentType) {
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
	public void nsdOnBoardingNotificationPost(final NsdOnBoardingNotification nsdOnBoardingNotification, final String accept, final String contentType) {
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
	public void pnfdDeletionNotificationGet(final String accept) {
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
	public void pnfdDeletionNotificationPost(final PnfdDeletionNotification pnfdDeletionNotification, final String accept, final String contentType) {
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
	public void pnfdOnBoardingFailureNotificationPost(final PnfdOnBoardingFailureNotification pnfdOnBoardingFailureNotification, final String accept, final String contentType) {
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
	public void pnfdOnBoardingNotificationPost(final PnfdOnBoardingNotification pnfdOnBoardingNotification, final String accept, final String contentType) {
		// : Implement...

	}

}
