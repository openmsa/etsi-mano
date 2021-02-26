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
package com.ubiqube.etsi.mano.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.ResponseEntity;

import com.ubiqube.etsi.mano.controller.vnf.VnfSubscriptionManagement;
import com.ubiqube.etsi.mano.dao.mano.ApiTypesEnum;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.model.WebEntity;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <U>
 */
public abstract class AbstractSubscriptionApi<U extends WebEntity<? extends U>> {
	private final VnfSubscriptionManagement vnfSubscriptionManagement;

	private final MapperFacade mapper;

	private final EntityLinks entityLinks;

	private final Class<U> clazz;

	private final SubscriptionType subscriptionType;

	protected AbstractSubscriptionApi(final VnfSubscriptionManagement _vnfSubscriptionManagement, final MapperFacade _mapper, final EntityLinks _entityLinks, final Class<U> _clazz, final SubscriptionType _subscriptionType) {
		vnfSubscriptionManagement = _vnfSubscriptionManagement;
		mapper = _mapper;
		entityLinks = _entityLinks;
		clazz = _clazz;
		subscriptionType = _subscriptionType;
	}

	/**
	 * Query multiple subscriptions.
	 *
	 * The GET method queries the list of active subscriptions of the functional block that invokes the method. It can be used e.g. for resynchronization after error situations. This method shall follow the provisions specified in the Tables 9.4.7.8.2-1 and 9.4.8.3.2-2 for URI query parameters, request and response data structures, and response codes. ²
	 */
	public final ResponseEntity<List<U>> subscriptionQuery(final String filter) {
		final List<Subscription> subs = vnfSubscriptionManagement.subscriptionsGet(filter, subscriptionType);
		final List<U> pkgms = mapper.mapAsList(subs, clazz);
		pkgms.stream()
				.forEach(this::setLink);
		return ResponseEntity.ok(pkgms);
	}

	/**
	 * Subscribe to notifications related to on-boarding and/or changes of VNF packages.
	 *
	 * The POST method creates a new subscription. This method shall follow the provisions specified in the Tables 9.4.8.3.1-1 and 9.4.8.3.1-2 for URI query parameters, request and response data structures, and response codes. Creation of two subscription resources with the same callbackURI and the same filter can result in performance degradation and will provide duplicates of notifications to the OSS, and might make sense only in very rare use cases. Consequently, the NFVO may either allow
	 * creating a subscription resource if another subscription resource with the same filter and callbackUri already exists (in which case it shall return the \&quot;201 Created\&quot; response code), or may decide to not create a duplicate subscription resource (in which case it shall return a \&quot;303 See Other\&quot; response code referencing the existing subscription resource with the same filter and callbackUri).
	 *
	 */
	public final ResponseEntity<U> subscriptionPost(final Object subscriptionsPostQuery) {
		final Subscription subs = mapper.map(subscriptionsPostQuery, Subscription.class);
		subs.setSubscriptionType(subscriptionType);
		final Subscription res = vnfSubscriptionManagement.subscriptionsPost(subs, ApiTypesEnum.SOL003);
		final U pkgm = mapper.map(res, clazz);
		setLink(pkgm);
		return ResponseEntity.ok(pkgm);
	}

	/**
	 * Terminate a subscription.
	 *
	 * The DELETE method terminates an individual subscription.
	 *
	 */
	public final ResponseEntity<Void> subscriptionDelete(@Nonnull final String subscriptionId) {
		vnfSubscriptionManagement.subscriptionsSubscriptionIdDelete(subscriptionId, subscriptionType);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Read an individual subscription resource.
	 *
	 * Query Subscription Information The GET method reads an individual subscription.
	 *
	 */
	public final ResponseEntity<U> subscriptionGetById(final String subscriptionId) {
		final Subscription res = vnfSubscriptionManagement.subscriptionsSubscriptionIdGet(UUID.fromString(subscriptionId), subscriptionType);
		final U pkgm = mapper.map(res, clazz);
		setLink(pkgm);
		return ResponseEntity.ok(pkgm);
	}

	private void setLink(final U obj) {
		final Link selfLink = entityLinks.linkToItemResource(clazz, obj.getId());
		obj.add(selfLink);
	}
}
