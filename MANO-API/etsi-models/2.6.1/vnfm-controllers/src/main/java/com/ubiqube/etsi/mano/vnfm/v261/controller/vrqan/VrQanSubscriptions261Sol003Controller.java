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
package com.ubiqube.etsi.mano.vnfm.v261.controller.vrqan;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.service.SubscriptionServiceV2;
import com.ubiqube.etsi.mano.vnfm.v261.model.vrqan.VrQuotaAvailSubscription;
import com.ubiqube.etsi.mano.vnfm.v261.model.vrqan.VrQuotaAvailSubscriptionLinks;
import com.ubiqube.etsi.mano.vnfm.v261.model.vrqan.VrQuotaAvailSubscriptionRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class VrQanSubscriptions261Sol003Controller implements VrQanSubscriptions261Sol003Api {
	private final SubscriptionServiceV2 subscriptionService;

	public VrQanSubscriptions261Sol003Controller(final SubscriptionServiceV2 subscriptionService) {
		super();
		this.subscriptionService = subscriptionService;
	}

	@Override
	public ResponseEntity<List<VrQuotaAvailSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, final String nextpageOpaqueMarker) {
		final List<VrQuotaAvailSubscription> ret = subscriptionService.query(requestParams, VrQuotaAvailSubscription.class, VrQanSubscriptions261Sol003Controller::makeLinks, SubscriptionType.VRQAN);
		return ResponseEntity.ok(ret);
	}

	@Override
	public ResponseEntity<VrQuotaAvailSubscription> subscriptionsPost(@Valid final VrQuotaAvailSubscriptionRequest vrQuotaAvailSubscriptionRequest) throws URISyntaxException {
		final VrQuotaAvailSubscription res = subscriptionService.create(vrQuotaAvailSubscriptionRequest, VrQuotaAvailSubscription.class, VrQanSubscriptions261Sol003Controller::makeLinks, SubscriptionType.VRQAN);
		final URI location = new URI(res.getLinks().getSelf().getHref());
		return ResponseEntity.created(location).body(res);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		subscriptionService.deleteById(UUID.fromString(subscriptionId), SubscriptionType.VRQAN);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<VrQuotaAvailSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		final VrQuotaAvailSubscription res = subscriptionService.findById(UUID.fromString(subscriptionId), VrQuotaAvailSubscription.class, VrQanSubscriptions261Sol003Controller::makeLinks, SubscriptionType.VRQAN);
		return ResponseEntity.ok(res);
	}

	private static void makeLinks(final VrQuotaAvailSubscription subscription) {
		final VrQuotaAvailSubscriptionLinks links = new VrQuotaAvailSubscriptionLinks();
		final Link link = new Link();
		link.setHref(linkTo(methodOn(VrQanSubscriptions261Sol003Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref());
		links.setSelf(link);
		subscription.setLinks(links);
	}
}
