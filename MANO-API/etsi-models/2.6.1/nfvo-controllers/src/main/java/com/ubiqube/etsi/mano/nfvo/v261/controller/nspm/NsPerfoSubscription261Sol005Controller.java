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

package com.ubiqube.etsi.mano.nfvo.v261.controller.nspm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.controller.SubscriptionFrontController;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.SubscriptionsPmSubscriptionRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmSubscription;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmSubscriptionLinks;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class NsPerfoSubscription261Sol005Controller implements NsPerfoSubscription261Sol005Api {
	private final SubscriptionFrontController subscriptionService;

	public NsPerfoSubscription261Sol005Controller(final SubscriptionFrontController subscriptionService) {
		super();
		this.subscriptionService = subscriptionService;
	}

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
	public ResponseEntity<List<PmSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return subscriptionService.search(requestParams, PmSubscription.class, NsPerfoSubscription261Sol005Controller::makeLinks, SubscriptionType.NSPM);
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
	 * @throws URISyntaxException
	 *
	 */
	@Override
	public ResponseEntity<PmSubscription> subscriptionsPost(final SubscriptionsPmSubscriptionRequest pmSubscriptionRequest) throws URISyntaxException {
		return subscriptionService.create(pmSubscriptionRequest, PmSubscription.class, NsPerfoSubscription261Sol005Controller::makeLinks, NsPerfoSubscription261Sol005Controller::makeSelf, SubscriptionType.NSPM);
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
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		return subscriptionService.deleteById(subscriptionId, SubscriptionType.NSPM);
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
	public ResponseEntity<PmSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		return subscriptionService.findById(subscriptionId, PmSubscription.class, NsPerfoSubscription261Sol005Controller::makeLinks, SubscriptionType.NSPM);
	}

	private static void makeLinks(final PmSubscription subscription) {
		final PmSubscriptionLinks links = new PmSubscriptionLinks();
		final Link link = new Link();
		link.setHref(linkTo(methodOn(NsPerfoSubscription261Sol005Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref());
		links.setSelf(link);
		subscription.setLinks(links);
	}

	private static String makeSelf(final PmSubscription subscription) {
		return linkTo(methodOn(NsPerfoSubscription261Sol005Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref();
	}
}
