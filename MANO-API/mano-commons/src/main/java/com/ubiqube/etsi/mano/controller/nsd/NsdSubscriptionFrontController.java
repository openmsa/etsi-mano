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

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.service.SubscriptionService;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NsdSubscriptionFrontController {

	private final SubscriptionService subscriptionService;

	private final MapperFacade mapper;

	public NsdSubscriptionFrontController(final SubscriptionService _subscriptionService, final MapperFacade _mapper) {
		subscriptionService = _subscriptionService;
		mapper = _mapper;
	}

	/**
	 * Query multiple subscriptions.
	 *
	 * The GET method queries the list of active subscriptions of the functional block that invokes the method. It can be used e.g. for resynchronization after error situations. This method shall support the URI query parameters, request and response data structures, and response codes. This resource represents subscriptions. The client can use this resource to subscribe to notifications related to NSD management and to query its subscriptions.
	 *
	 */
	public <U> ResponseEntity<List<U>> search(final String filter, final Class<U> clazz, final Consumer<U> makeLink) {
		final List<Subscription> subs = subscriptionService.query(filter, SubscriptionType.NSD);
		final List<U> pkgms = mapper.mapAsList(subs, clazz);
		pkgms.stream()
				.forEach(x -> makeLink.accept(x));
		return ResponseEntity.ok(pkgms);
	}

	/**
	 * Subscribe to NSD and PNFD change notifications.
	 *
	 * The POST method creates a new subscription. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Tables 5.4.8.3.1-1 and 5.4.8.3.1-2 of GS-NFV SOL 005. Creation of two subscription resources with the same callbackURI and the same filter can result in performance degradation and will provide duplicates of notifications to the OSS, and might make sense only in very rare use cases. Consequently, the NFVO may either allow
	 * creating a subscription resource if another subscription resource with the same filter and callbackUri already exists (in which case it shall return the \&quot;201 Created\&quot; response code), or may decide to not create a duplicate subscription resource (in which case it shall return a \&quot;303 See Other\&quot; response code referencing the existing subscription resource with the same filter and callbackUri). This resource represents subscriptions. The client can use this resource to
	 * subscribe to notifications related to NSD management and to query its subscriptions.
	 *
	 */
	public <U> ResponseEntity<U> create(final Object body, final Class<U> clazz, final Consumer<U> makeLink, final Function<U, String> getSelfLink) {
		final Subscription subs = mapper.map(body, Subscription.class);
		final Subscription res = subscriptionService.save(subs, SubscriptionType.NSD);
		final U pkgm = mapper.map(res, clazz);
		makeLink.accept(pkgm);
		final String link = getSelfLink.apply(pkgm);
		return ResponseEntity.created(URI.create(link)).body(pkgm);
	}

	/**
	 * Terminate Subscription
	 *
	 * This resource represents an individual subscription. It can be used by the client to read and to terminate a subscription to notifications related to NSD management. The DELETE method terminates an individual subscription. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Table 5.4.9.3.3-2.
	 *
	 */
	public ResponseEntity<Void> delete(final String subscriptionId) {
		subscriptionService.delete(UUID.fromString(subscriptionId), SubscriptionType.NSD);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Read an individual subscription resource.
	 *
	 * This resource represents an individual subscription. It can be used by the client to read and to terminate a subscription to notifications related to NSD management. The GET method retrieves information about a subscription by reading an individual subscription resource. This resource represents an individual subscription. It can be used by the client to read and to terminate a subscription to notifications related to NSD management.
	 *
	 */
	public <U> ResponseEntity<U> findById(final String subscriptionId, final Class<U> clazz, final Consumer<U> makeLink) {
		final Subscription res = subscriptionService.findById(UUID.fromString(subscriptionId), SubscriptionType.NSD);
		final U pkgm = mapper.map(res, clazz);
		makeLink.accept(pkgm);
		return ResponseEntity.ok(pkgm);
	}

}
