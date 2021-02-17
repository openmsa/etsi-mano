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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdmSubscription;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdmSubscriptionLinks;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdmSubscriptionRequest;
import com.ubiqube.etsi.mano.service.SubscriptionService;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RolesAllowed({ "ROLE_OSSBSS" })
@RestController
public class NsdSubscriptionsSol005Api implements NsdSubscriptionsSol005 {

	private static final Logger LOG = LoggerFactory.getLogger(NsdSubscriptionsSol005Api.class);

	private final SubscriptionService subscriptionService;

	private final MapperFacade mapper;

	public NsdSubscriptionsSol005Api(final SubscriptionService _subscriptionService, final MapperFacade _mapper) {
		subscriptionService = _subscriptionService;
		mapper = _mapper;
		LOG.info("Starting NSD Subscription SOL005 Controller.");
	}

	/**
	 * Query multiple subscriptions.
	 *
	 * The GET method queries the list of active subscriptions of the functional block that invokes the method. It can be used e.g. for resynchronization after error situations. This method shall support the URI query parameters, request and response data structures, and response codes. This resource represents subscriptions. The client can use this resource to subscribe to notifications related to NSD management and to query its subscriptions.
	 *
	 */
	@Override
	public ResponseEntity<List<NsdmSubscription>> subscriptionsGet(final String accept, final String filter) {
		final List<Subscription> subs = subscriptionService.query(filter, SubscriptionType.NSD);
		final List<NsdmSubscription> pkgms = mapper.mapAsList(subs, NsdmSubscription.class);
		pkgms.stream()
				.forEach(x -> x.setLinks(createSubscriptionsLinks(x.getId())));
		return ResponseEntity.ok(pkgms);
	}

	/**
	 * Subscribe to NSD and PNFD change notifications.
	 *
	 * The POST method creates a new subscription. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Tables 5.4.8.3.1-1 and 5.4.8.3.1-2 of GS-NFV SOL 005. Creation of two subscription resources with the same callbackURI and the same filter can result in performance degradation and will provide duplicates of notifications to the OSS, and might make sense only in very rare use cases. Consequently, the NFVO may either allow
	 * creating a subscription resource if another subscription resource with the same filter and callbackUri already exists (in which case it shall return the \&quot;201 Created\&quot; response code), or may decide to not create a duplicate subscription resource (in which case it shall return a \&quot;303 See Other\&quot; response code referencing the existing subscription resource with the same filter and callbackUri). This resource represents subscriptions. The client can use this resource to
	 * subscribe to notifications related to NSD management and to query its subscriptions.
	 *
	 * @throws URISyntaxException
	 *
	 */
	@Override
	public ResponseEntity<NsdmSubscription> subscriptionsPost(final String accept, final String contentType, final NsdmSubscriptionRequest body) throws URISyntaxException {
		final Subscription subs = mapper.map(body, Subscription.class);
		final Subscription res = subscriptionService.save(subs, SubscriptionType.NSD);
		final NsdmSubscription pkgm = mapper.map(res, NsdmSubscription.class);
		pkgm.setLinks(createSubscriptionsLinks(pkgm.getId()));
		return ResponseEntity.created(new URI(pkgm.getLinks().getSelf().getHref())).body(pkgm);
	}

	/**
	 * Terminate Subscription
	 *
	 * This resource represents an individual subscription. It can be used by the client to read and to terminate a subscription to notifications related to NSD management. The DELETE method terminates an individual subscription. This method shall support the URI query parameters, request and response data structures, and response codes, as specified in the Table 5.4.9.3.3-2.
	 *
	 */
	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		subscriptionService.delete(UUID.fromString(subscriptionId), SubscriptionType.NSD);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Read an individual subscription resource.
	 *
	 * This resource represents an individual subscription. It can be used by the client to read and to terminate a subscription to notifications related to NSD management. The GET method retrieves information about a subscription by reading an individual subscription resource. This resource represents an individual subscription. It can be used by the client to read and to terminate a subscription to notifications related to NSD management.
	 *
	 */
	@Override
	public ResponseEntity<NsdmSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId, final String accept) {
		final Subscription res = subscriptionService.findById(UUID.fromString(subscriptionId), SubscriptionType.NSD);
		final NsdmSubscription pkgm = mapper.map(res, NsdmSubscription.class);
		pkgm.setLinks(createSubscriptionsLinks(pkgm.getId()));
		return ResponseEntity.ok(pkgm);
	}

	private static NsdmSubscriptionLinks createSubscriptionsLinks(@NotNull final String id) {
		final NsdmSubscriptionLinks nsdmSubscriptionLinks = new NsdmSubscriptionLinks();
		final Link self = new Link();
		self.setHref(linkTo(methodOn(NsdSubscriptionsSol005.class).subscriptionsSubscriptionIdGet(id, null)).withSelfRel().getHref());
		nsdmSubscriptionLinks.setSelf(self);
		return nsdmSubscriptionLinks;
	}

}
