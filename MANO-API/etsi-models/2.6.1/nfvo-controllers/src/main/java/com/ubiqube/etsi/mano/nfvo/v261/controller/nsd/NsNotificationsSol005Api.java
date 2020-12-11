/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.ubiqube.etsi.mano.nfvo.v261.controller.nsd;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification.NsdChangeNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification.NsdDeletionNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification.NsdOnBoardingFailureNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification.NsdOnBoardingNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification.PnfdDeletionNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification.PnfdOnBoardingFailureNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification.PnfdOnBoardingNotification;

@RolesAllowed({ "ROLE_OSSBSS" })
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
