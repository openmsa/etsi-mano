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

package com.ubiqube.etsi.mano.vnfm.v261.controller.vnffm.sol003;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.service.SubscriptionServiceV2;
import com.ubiqube.etsi.mano.vnfm.v261.controller.vnffm.sol002.FaultmngtSubscriptions261Sol002Api;
import com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt.FmSubscription;
import com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt.FmSubscriptionLinks;
import com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt.FmSubscriptionRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RolesAllowed({ "ROLE_NFVO" })
@RestController
public class FaultSubscriptionsSol003Api implements FaultSubscriptionsSol003 {
	private final SubscriptionServiceV2 subscriptionService;

	public FaultSubscriptionsSol003Api(final SubscriptionServiceV2 subscriptionService) {
		super();
		this.subscriptionService = subscriptionService;
	}

	@Override
	public ResponseEntity<List<FmSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, final String nextpageOpaqueMarker) {
		final List<FmSubscription> ret = subscriptionService.query(requestParams, FmSubscription.class, FaultSubscriptionsSol003Api::makeLinks, SubscriptionType.ALARM);
		return ResponseEntity.ok(ret);
	}

	@Override
	public ResponseEntity<FmSubscription> subscriptionsPost(@Valid final FmSubscriptionRequest fmSubscriptionRequest) throws URISyntaxException {
		final FmSubscription res = subscriptionService.create(fmSubscriptionRequest, FmSubscription.class, FaultSubscriptionsSol003Api::makeLinks, SubscriptionType.ALARM);
		final URI location = new URI(res.getLinks().getSelf().getHref());
		return ResponseEntity.created(location).body(res);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		subscriptionService.deleteById(UUID.fromString(subscriptionId), SubscriptionType.ALARM);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<FmSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		final FmSubscription res = subscriptionService.findById(UUID.fromString(subscriptionId), FmSubscription.class, FaultSubscriptionsSol003Api::makeLinks, SubscriptionType.ALARM);
		return ResponseEntity.ok(res);
	}

	private static void makeLinks(final FmSubscription subscription) {
		final FmSubscriptionLinks links = new FmSubscriptionLinks();
		final Link link = new Link();
		link.setHref(linkTo(methodOn(FaultmngtSubscriptions261Sol002Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref());
		links.setSelf(link);
		subscription.setLinks(links);
	}
}
