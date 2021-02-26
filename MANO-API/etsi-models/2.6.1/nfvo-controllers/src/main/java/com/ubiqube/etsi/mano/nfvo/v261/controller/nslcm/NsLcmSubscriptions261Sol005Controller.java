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

package com.ubiqube.etsi.mano.nfvo.v261.controller.nslcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.LccnSubscription;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.LccnSubscriptionLinks;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.LccnSubscriptionRequest;
import com.ubiqube.etsi.mano.service.SubscriptionService;
import com.ubiqube.etsi.mano.service.event.Notifications;

import ma.glasnost.orika.MapperFacade;

@RolesAllowed({ "ROLE_OSSBSS" })
@RestController
public class NsLcmSubscriptions261Sol005Controller implements NsLcmSubscriptions261Sol005Api {
	private final SubscriptionService subscriptionService;

	private final MapperFacade mapper;

	private final Notifications notifications;

	public NsLcmSubscriptions261Sol005Controller(final SubscriptionService _subscriptionService, final MapperFacade _mapper, final Notifications _notifications) {
		subscriptionService = _subscriptionService;
		mapper = _mapper;
		notifications = _notifications;
	}

	/**
	 * Query multiple subscriptions.
	 *
	 * Query Subscription Information. The GET method queries the list of active subscriptions of the functional block that invokes the method. It can be used e.g. for resynchronization after error situations.
	 *
	 */
	@Override
	public ResponseEntity<List<LccnSubscription>> subscriptionsGet(final String filter) {
		final List<Subscription> list = subscriptionService.query(filter, SubscriptionType.NSLCM);
		final List<LccnSubscription> pkgms = mapper.mapAsList(list, LccnSubscription.class);
		pkgms.stream().forEach(x -> x.setLinks(createSubscriptionsLinks(x.getId())));
		return ResponseEntity.ok(pkgms);
	}

	/**
	 * Subscribe to NS lifecycle change notifications.
	 *
	 * The POST method creates a new subscription. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Tables 6.4.16.3.1-1 and 6.4.16.3.1-2. Creation of two subscription resources with the same callbackURI and the same filter can result in performance degradation and will provide duplicates of notifications to the OSS, and might make sense only in very rare use cases. Consequently, the NFVO may either allow creating a
	 * subscription resource if another subscription resource with the same filter and callbackUri already exists (in which case it shall return the \&quot;201 Created\&quot; response code), or may decide to not create a duplicate subscription resource (in which case it shall return a \&quot;303 See Other\&quot; response code referencing the existing subscription resource with the same filter and callbackUri).
	 *
	 * @throws URISyntaxException
	 *
	 */
	@Override
	public ResponseEntity<LccnSubscription> subscriptionsPost(final LccnSubscriptionRequest lccnSubscriptionRequest) throws URISyntaxException {
		Subscription subscription = mapper.map(lccnSubscriptionRequest, Subscription.class);
		notifications.check(subscription.getAuthentificationInformations(), subscription.getCallbackUri());
		subscription = subscriptionService.save(subscription, SubscriptionType.NSLCM);
		final LccnSubscription pkgmSubscription = mapper.map(subscription, LccnSubscription.class);
		pkgmSubscription.setLinks(createSubscriptionsLinks(pkgmSubscription.getId()));
		return ResponseEntity.created(new URI(pkgmSubscription.getLinks().getSelf().getHref())).body(pkgmSubscription);
	}

	/**
	 * Terminate a subscription.
	 *
	 * The DELETE method terminates an individual subscription. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Tables 6.4.17.3.5-1 and 6.4.17.3.5-2.
	 *
	 */
	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		subscriptionService.delete(UUID.fromString(subscriptionId), SubscriptionType.NSLCM);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Read an individual subscription resource.
	 *
	 * The GET method retrieves information about a subscription by reading an individual subscription resource. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Tables 6.4.17.3.2-1 and 6.4.17.3.2-2
	 *
	 */
	@Override
	public ResponseEntity<LccnSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		final Subscription subscription = subscriptionService.findById(UUID.fromString(subscriptionId), SubscriptionType.NSLCM);
		final LccnSubscription pkgmSubscription = mapper.map(subscription, LccnSubscription.class);
		pkgmSubscription.setLinks(createSubscriptionsLinks(subscriptionId));
		return new ResponseEntity<>(pkgmSubscription, HttpStatus.OK);
	}

	private static LccnSubscriptionLinks createSubscriptionsLinks(@NotNull final String id) {
		final LccnSubscriptionLinks links = new LccnSubscriptionLinks();
		final Link self = new Link();
		self.setHref(linkTo(methodOn(NsLcmSubscriptions261Sol005Api.class).subscriptionsSubscriptionIdGet(id)).withSelfRel().getHref());
		links.setSelf(self);
		return links;
	}
}
