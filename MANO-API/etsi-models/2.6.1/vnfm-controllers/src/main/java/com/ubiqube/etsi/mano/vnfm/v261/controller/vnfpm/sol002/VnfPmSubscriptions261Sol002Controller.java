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
package com.ubiqube.etsi.mano.vnfm.v261.controller.vnfpm.sol002;

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
import com.ubiqube.etsi.mano.vnfm.v261.controller.vnffm.sol002.FaultmngtSubscriptions261Sol002Api;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmSubscription;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmSubscriptionLinks;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmSubscriptionRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-11T19:14:16.145+01:00")

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class VnfPmSubscriptions261Sol002Controller implements VnfPmSubscriptions261Sol002Api {
	private final SubscriptionServiceV2 subscriptionService;

	public VnfPmSubscriptions261Sol002Controller(final SubscriptionServiceV2 subscriptionService) {
		super();
		this.subscriptionService = subscriptionService;
	}

	@Override
	public ResponseEntity<List<PmSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		final List<PmSubscription> ret = subscriptionService.query(requestParams, PmSubscription.class, VnfPmSubscriptions261Sol002Controller::makeLinks, SubscriptionType.VNFPM);
		return ResponseEntity.ok(ret);
	}

	@Override
	public ResponseEntity<PmSubscription> subscriptionsPost(@Valid final PmSubscriptionRequest pmSubscriptionRequest) throws URISyntaxException {
		final PmSubscription res = subscriptionService.create(pmSubscriptionRequest, PmSubscription.class, VnfPmSubscriptions261Sol002Controller::makeLinks, SubscriptionType.VNFPM);
		final URI location = new URI(res.getLinks().getSelf().getHref());
		return ResponseEntity.created(location).body(res);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		subscriptionService.deleteById(UUID.fromString(subscriptionId), SubscriptionType.VNFPM);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<PmSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		final PmSubscription res = subscriptionService.findById(UUID.fromString(subscriptionId), PmSubscription.class, VnfPmSubscriptions261Sol002Controller::makeLinks, SubscriptionType.ALARM);
		return ResponseEntity.ok(res);
	}

	private static void makeLinks(final PmSubscription subscription) {
		final PmSubscriptionLinks links = new PmSubscriptionLinks();
		final Link link = new Link();
		link.setHref(linkTo(methodOn(FaultmngtSubscriptions261Sol002Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref());
		links.setSelf(link);
		subscription.setLinks(links);
	}

}
