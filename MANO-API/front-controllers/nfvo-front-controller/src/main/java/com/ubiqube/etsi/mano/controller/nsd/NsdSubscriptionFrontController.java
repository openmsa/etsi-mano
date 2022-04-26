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
package com.ubiqube.etsi.mano.controller.nsd;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface NsdSubscriptionFrontController {

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
	<U> ResponseEntity<List<U>> search(MultiValueMap<String, String> requestParams, Class<U> clazz, Consumer<U> makeLink);

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
	<U> ResponseEntity<U> create(Object body, Class<U> clazz, Consumer<U> makeLink, Function<U, String> getSelfLink);

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
	ResponseEntity<Void> delete(@NotNull String subscriptionId);

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
	<U> ResponseEntity<U> findById(@NotNull String subscriptionId, Class<U> clazz, Consumer<U> makeLink);

}