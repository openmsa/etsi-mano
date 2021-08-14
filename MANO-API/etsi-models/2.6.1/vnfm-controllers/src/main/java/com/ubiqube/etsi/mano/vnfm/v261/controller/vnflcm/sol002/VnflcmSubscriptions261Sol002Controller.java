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
package com.ubiqube.etsi.mano.vnfm.v261.controller.vnflcm.sol002;

import static com.ubiqube.etsi.mano.Constants.getSafeUUID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.controller.vnflcm.VnfLcmSubscriptionFrontController;
import com.ubiqube.etsi.mano.vnfm.v261.controller.vnflcm.sol003.VnfLcmSubscriptions261Sol003Api;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.LccnSubscription;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.LccnSubscriptionLinks;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.LccnSubscriptionRequest;

@RestController
public class VnflcmSubscriptions261Sol002Controller implements VnflcmSubscriptions261Sol002Api {
	private final VnfLcmSubscriptionFrontController frontController;

	public VnflcmSubscriptions261Sol002Controller(final VnfLcmSubscriptionFrontController frontController) {
		super();
		this.frontController = frontController;
	}

	@Override
	public ResponseEntity<List<LccnSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return frontController.search(requestParams, nextpageOpaqueMarker, LccnSubscription.class, VnflcmSubscriptions261Sol002Controller::makeLinks);
	}

	@Override
	public ResponseEntity<LccnSubscription> subscriptionsPost(@Valid final LccnSubscriptionRequest body) {
		return frontController.create(body, LccnSubscription.class, VnflcmSubscriptions261Sol002Controller::makeLinks, VnflcmSubscriptions261Sol002Controller::getSelfLink);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		return frontController.deleteById(getSafeUUID(subscriptionId));
	}

	@Override
	public ResponseEntity<LccnSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		return frontController.findById(getSafeUUID(subscriptionId), LccnSubscription.class, VnflcmSubscriptions261Sol002Controller::makeLinks);
	}

	private static String getSelfLink(final LccnSubscription subscription) {
		return linkTo(methodOn(VnfLcmSubscriptions261Sol003Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref();
	}

	private static void makeLinks(@NotNull final LccnSubscription subscription) {
		final String id = subscription.getId();
		final LccnSubscriptionLinks links = new LccnSubscriptionLinks();
		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnflcmSubscriptions261Sol002Api.class).subscriptionsSubscriptionIdGet(id)).withSelfRel().getHref());
		links.setSelf(self);
		subscription.setLinks(links);
	}

}
