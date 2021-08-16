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
package com.ubiqube.etsi.mano.vnfm.v261.controller.vnfind.sol002;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.vnfm.fc.vnfind.VnfIndSubscriptionsFrontController;
import com.ubiqube.etsi.mano.vnfm.v261.model.indicator.VnfIndicatorSubscription;
import com.ubiqube.etsi.mano.vnfm.v261.model.indicator.VnfIndicatorSubscriptionLinks;
import com.ubiqube.etsi.mano.vnfm.v261.model.indicator.VnfIndicatorSubscriptionRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-11T19:19:34.580+01:00")
/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RolesAllowed({ "ROLE_EM" })
@RestController
public class VnfIndSubscriptions261Sol002Controller implements VnfIndSubscriptions261Sol002Api {
	private final VnfIndSubscriptionsFrontController vnfIndSubscriptionsFrontController;

	public VnfIndSubscriptions261Sol002Controller(final VnfIndSubscriptionsFrontController vnfIndSubscriptionsFrontController) {
		super();
		this.vnfIndSubscriptionsFrontController = vnfIndSubscriptionsFrontController;
	}

	@Override
	public ResponseEntity<List<VnfIndicatorSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return vnfIndSubscriptionsFrontController.search(requestParams, VnfIndicatorSubscription.class, VnfIndSubscriptions261Sol002Controller::makeLinks);
	}

	@Override
	public ResponseEntity<VnfIndicatorSubscription> subscriptionsPost(@Valid final VnfIndicatorSubscriptionRequest vnfIndicatorSubscriptionRequest) {
		return vnfIndSubscriptionsFrontController.create(vnfIndicatorSubscriptionRequest, VnfIndicatorSubscription.class, VnfIndSubscriptions261Sol002Controller::makeLinks, VnfIndSubscriptions261Sol002Controller::getSelfLink);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		return vnfIndSubscriptionsFrontController.delete(subscriptionId);
	}

	@Override
	public ResponseEntity<VnfIndicatorSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		return vnfIndSubscriptionsFrontController.findById(subscriptionId, VnfIndicatorSubscription.class, VnfIndSubscriptions261Sol002Controller::makeLinks);
	}

	private static void makeLinks(final VnfIndicatorSubscription subscription) {
		final VnfIndicatorSubscriptionLinks links = new VnfIndicatorSubscriptionLinks();
		final Link link = new Link();
		link.setHref(linkTo(methodOn(VnfIndSubscriptions261Sol002Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref());
		links.setSelf(link);
		subscription.setLinks(links);
	}

	private static String getSelfLink(final VnfIndicatorSubscription subscription) {
		return linkTo(methodOn(VnfIndSubscriptions261Sol002Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref();
	}
}
