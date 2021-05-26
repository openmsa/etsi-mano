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
package com.ubiqube.etsi.mano.nfvo.v331.controller.nslcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.nslcm.NsLcmSubscriptionsGenericFrontController;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.LccnSubscription;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.LccnSubscriptionLinks;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.LccnSubscriptionRequest;
import com.ubiqube.etsi.mano.nfvo.v331.model.nslcm.Link;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class NsLcmSubscriptions331Sol005Controller implements NsLcmSubscriptions331Sol005Api {
	private final NsLcmSubscriptionsGenericFrontController nsLcmSubscriptionsGenericFrontController;

	public NsLcmSubscriptions331Sol005Controller(final NsLcmSubscriptionsGenericFrontController nsLcmSubscriptionsGenericFrontController) {
		super();
		this.nsLcmSubscriptionsGenericFrontController = nsLcmSubscriptionsGenericFrontController;
	}

	@Override
	public ResponseEntity<LccnSubscription> subscriptionsPost(@Valid final LccnSubscriptionRequest body) {
		return nsLcmSubscriptionsGenericFrontController.create(body, LccnSubscription.class, NsLcmSubscriptions331Sol005Controller::makeLink, NsLcmSubscriptions331Sol005Controller::getSelfLink);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		return nsLcmSubscriptionsGenericFrontController.delete(subscriptionId);
	}

	@Override
	public ResponseEntity<LccnSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		return nsLcmSubscriptionsGenericFrontController.findById(subscriptionId, LccnSubscription.class, NsLcmSubscriptions331Sol005Controller::makeLink);
	}

	private static void makeLink(final LccnSubscription lccnSubscription) {
		final LccnSubscriptionLinks links = new LccnSubscriptionLinks();
		final Link self = new Link();
		self.setHref(linkTo(methodOn(NsLcmSubscriptions331Sol005Api.class).subscriptionsSubscriptionIdGet(lccnSubscription.getId())).withSelfRel().getHref());
		links.setSelf(self);
		lccnSubscription.setLinks(links);
	}

	private static String getSelfLink(final LccnSubscription lccnSubscription) {
		return linkTo(methodOn(NsLcmSubscriptions331Sol005Api.class).subscriptionsSubscriptionIdGet(lccnSubscription.getId())).withSelfRel().getHref();
	}

}
