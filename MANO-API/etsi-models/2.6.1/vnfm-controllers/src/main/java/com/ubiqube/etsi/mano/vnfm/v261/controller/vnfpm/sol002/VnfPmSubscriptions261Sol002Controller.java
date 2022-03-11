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

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.controller.SubscriptionFrontController;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.vnfm.v261.controller.vnffm.sol002.FaultmngtSubscriptions261Sol002Api;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmSubscription;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmSubscriptionLinks;
import com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo.PmSubscriptionRequest;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
@RestController
public class VnfPmSubscriptions261Sol002Controller implements VnfPmSubscriptions261Sol002Api {
	private final SubscriptionFrontController subscriptionService;

	public VnfPmSubscriptions261Sol002Controller(final SubscriptionFrontController subscriptionService) {
		super();
		this.subscriptionService = subscriptionService;
	}

	@Override
	public ResponseEntity<List<PmSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return subscriptionService.search(requestParams, PmSubscription.class, VnfPmSubscriptions261Sol002Controller::makeLinks, SubscriptionType.VNFPM);
	}

	@Override
	public ResponseEntity<PmSubscription> subscriptionsPost(@Valid final PmSubscriptionRequest pmSubscriptionRequest) {
		return subscriptionService.create(pmSubscriptionRequest, PmSubscription.class, VnfPmSubscriptions261Sol002Controller::makeLinks, VnfPmSubscriptions261Sol002Controller::makeSelf, SubscriptionType.VNFPM);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		return subscriptionService.deleteById(subscriptionId, SubscriptionType.VNFPM);
	}

	@Override
	public ResponseEntity<PmSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		return subscriptionService.findById(subscriptionId, PmSubscription.class, VnfPmSubscriptions261Sol002Controller::makeLinks, SubscriptionType.ALARM);
	}

	private static String makeSelf(final PmSubscription subscription) {
		return linkTo(methodOn(FaultmngtSubscriptions261Sol002Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref();
	}

	private static void makeLinks(final PmSubscription subscription) {
		final PmSubscriptionLinks links = new PmSubscriptionLinks();
		final Link link = new Link();
		link.setHref(linkTo(methodOn(FaultmngtSubscriptions261Sol002Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref());
		links.setSelf(link);
		subscription.setLinks(links);
	}

}
