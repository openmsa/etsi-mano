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

import javax.annotation.Nonnull;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification.NsdChangeNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification.NsdDeletionNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification.NsdOnBoardingFailureNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification.NsdOnBoardingNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification.PnfdDeletionNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification.PnfdOnBoardingFailureNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification.PnfdOnBoardingNotification;

@RequestMapping("/sol005/nsd/v1")
@RolesAllowed({ "ROLE_OSSBSS" })
public interface NsdNotifications261Sol005Api {

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this resource to send notifications to a subscribed client, which has provided the URI of this resource during the subscription process. The POST method delivers a notification from the server to the client. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@PostMapping(value = "/URI_is_provided_by_the_client_when_creating_the_subscription", consumes = { "application/json" }, produces = { "application/json" })
	void nsdChangeNotificationPost(@Nonnull @Valid NsdChangeNotification nsdChangeNotification, @RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType);

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this resource to send notifications to a subscribed client, which has provided the URI of this resource during the subscription process. The POST method delivers a notification from the server to the client. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@PostMapping(value = "/URI_is_provided_by_the_client_when_creating_the_subscription-NsdDeletionNotification", consumes = { "application/json" }, produces = { "application/json" })
	void nsdDeletionNotificationPost(@Nonnull @Valid NsdDeletionNotification nsdDeletionNotification, @RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType);

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this resource to send notifications to a subscribed client, which has provided the URI of this resource during the subscription process. The POST method delivers a notification from the server to the client. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@PostMapping(value = "/URI_is_provided_by_the_client_when_creating_the_subscription-NsdOnBoardingFailureNotification", consumes = { "application/json" }, produces = { "application/json" })
	void nsdOnBoardingFailureNotificationPost(@Nonnull @Valid NsdOnBoardingFailureNotification nsdOnBoardingFailureNotification, @RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType);

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this resource to send notifications to a subscribed client, which has provided the URI of this resource during the subscription process. The POST method delivers a notification from the server to the client. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@PostMapping(value = "/URI_is_provided_by_the_client_when_creating_the_subscription-NsdOnBoardingNotification", consumes = { "application/json" }, produces = { "application/json" })
	void nsdOnBoardingNotificationPost(@Nonnull @Valid NsdOnBoardingNotification nsdOnBoardingNotification, @RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType);

	/**
	 * Test the notification endpoint
	 *
	 * This resource represents a notification endpoint. The server can use this resource to send notifications to a subscribed client, which has provided the URI of this resource during the subscription process. The GET method allows the server to test the notification endpoint that is provided by the client, e.g. during subscription. This method shall follow the provisions specified in the Table 5.4.10.3.2-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@GetMapping(value = "/URI_is_provided_by_the_client_when_creating_the_subscription-PnfdDeletionNotification",  produces = { "application/json" })
	void pnfdDeletionNotificationGet(@RequestHeader("Accept") String accept);

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this resource to send notifications to a subscribed client, which has provided the URI of this resource during the subscription process. The POST method delivers a notification from the server to the client. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@PostMapping(value = "/URI_is_provided_by_the_client_when_creating_the_subscription-PnfdDeletionNotification", consumes = { "application/json" }, produces = { "application/json" })
	void pnfdDeletionNotificationPost(@Nonnull @Valid PnfdDeletionNotification pnfdDeletionNotification, @RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType);

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this resource to send notifications to a subscribed client, which has provided the URI of this resource during the subscription process. The POST method delivers a notification from the server to the client. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@PostMapping(value = "/URI_is_provided_by_the_client_when_creating_the_subscription-PnfdOnBoardingFailureNotification", consumes = { "application/json" }, produces = { "application/json" })
	void pnfdOnBoardingFailureNotificationPost(@Nonnull @Valid PnfdOnBoardingFailureNotification pnfdOnBoardingFailureNotification, @RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType);

	/**
	 * Notify about NSD and PNFD changes
	 *
	 * This resource represents a notification endpoint. The server can use this resource to send notifications to a subscribed client, which has provided the URI of this resource during the subscription process. The POST method delivers a notification from the server to the client. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Table 5.4.10.3.1-2.
	 *
	 */
	@PostMapping(value = "/URI_is_provided_by_the_client_when_creating_the_subscription-PnfdOnBoardingNotification", consumes = { "application/json" }, produces = { "application/json" })
	void pnfdOnBoardingNotificationPost(@Nonnull @Valid PnfdOnBoardingNotification pnfdOnBoardingNotification, @RequestHeader("Accept") String accept, @RequestHeader("Content-Type") String contentType);

}
