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

package com.ubiqube.etsi.mano.nfvo.v261.controller.nsperfo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.SubscriptionsPostQuery;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.SubscriptionsPostResponse;

@Profile({ "!VNFM" })
public class NsPerfoSubscriptionSol005Api implements NsPerfoSubscriptionSol005 {
	/**
	 * Query PM related subscriptions.
	 *
	 * The client can use this method to query the list of active subscriptions to
	 * Performance management notifications subscribed by the client. This method
	 * shall follow the provisions specified in the Tables 7.4.7.3.2-1 and
	 * 7.4.7.3.2-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	public ResponseEntity<List<Object>> subscriptionsGet(final String accept, final String filter) {
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}

	/**
	 * Subscribe to PM notifications.
	 *
	 * The POST method creates a new subscription. This method shall follow the
	 * provisions specified in the Tables 7.4.7.3.1-1 and 7.4.7.3.1-2 for URI query
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
	 * callbackUri)
	 *
	 */
	@Override
	public ResponseEntity<SubscriptionsPostResponse> subscriptionsPost(final String accept, final String contentType, final SubscriptionsPostQuery body) {
		// : Implement...
		return null;
	}

	/**
	 * Terminate a subscription.
	 *
	 * This method terminates an individual subscription. This method shall follow
	 * the provisions specified in the Tables 7.4.8.3.5-1 and 7.4.8.3.5-2 for URI
	 * query parameters, request and response data structures, and response codes
	 *
	 */
	@Override
	public void subscriptionsSubscriptionIdDelete(final String subscriptionId, final String accept) {
		// : Implement...
	}

	/**
	 * Query a single PM related subscription.
	 *
	 * The client can use this method for reading an individual subscription about
	 * Performance management notifications subscribed by the client. This method
	 * shall follow the provisions specified in the Tables 7.4.8.3.2-1 and
	 * 7.4.8.3.2-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	public ResponseEntity<SubscriptionsPostResponse> subscriptionsSubscriptionIdGet(final String subscriptionId, final String accept) {
		// : Implement...
		return null;
	}

}
