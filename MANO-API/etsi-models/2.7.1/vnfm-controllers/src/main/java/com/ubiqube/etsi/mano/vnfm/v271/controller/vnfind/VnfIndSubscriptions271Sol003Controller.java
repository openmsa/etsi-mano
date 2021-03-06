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
package com.ubiqube.etsi.mano.vnfm.v271.controller.vnfind;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.service.SubscriptionServiceV2;
import com.ubiqube.etsi.mano.vnfm.v271.model.vnfind.Link;
import com.ubiqube.etsi.mano.vnfm.v271.model.vnfind.VnfIndicatorSubscription;
import com.ubiqube.etsi.mano.vnfm.v271.model.vnfind.VnfIndicatorSubscriptionLinks;
import com.ubiqube.etsi.mano.vnfm.v271.model.vnfind.VnfIndicatorSubscriptionRequest;

@Controller
public class VnfIndSubscriptions271Sol003Controller implements VnfIndSubscriptions271Sol003Api {
	private final SubscriptionServiceV2 subscriptionService;

	public VnfIndSubscriptions271Sol003Controller(final SubscriptionServiceV2 subscriptionService) {
		super();
		this.subscriptionService = subscriptionService;
	}

	@Override
	public ResponseEntity<List<VnfIndicatorSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		final List<VnfIndicatorSubscription> ret = subscriptionService.query(requestParams, VnfIndicatorSubscription.class, VnfIndSubscriptions271Sol003Controller::makeLinks, SubscriptionType.VNFIND);
		return ResponseEntity.ok(ret);
	}

	@Override
	public ResponseEntity<VnfIndicatorSubscription> subscriptionsPost(@Valid final VnfIndicatorSubscriptionRequest vnfIndicatorSubscriptionRequest) {
		final VnfIndicatorSubscription res = subscriptionService.create(vnfIndicatorSubscriptionRequest, VnfIndicatorSubscription.class, VnfIndSubscriptions271Sol003Controller::makeLinks, SubscriptionType.VNFPM);
		final URI location = URI.create(res.getLinks().getSelf().getHref());
		return ResponseEntity.created(location).body(res);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		subscriptionService.deleteById(UUID.fromString(subscriptionId), SubscriptionType.VNFPM);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<VnfIndicatorSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		final VnfIndicatorSubscription res = subscriptionService.findById(UUID.fromString(subscriptionId), VnfIndicatorSubscription.class, VnfIndSubscriptions271Sol003Controller::makeLinks, SubscriptionType.ALARM);
		return ResponseEntity.ok(res);
	}

	private static void makeLinks(final VnfIndicatorSubscription subscription) {
		final VnfIndicatorSubscriptionLinks links = new VnfIndicatorSubscriptionLinks();
		final Link link = new Link();
		link.setHref(linkTo(methodOn(VnfIndSubscriptions271Sol003Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref());
		links.setSelf(link);
		subscription.setLinks(links);
	}

}
