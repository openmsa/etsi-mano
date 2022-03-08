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
package com.ubiqube.etsi.mano.nfvo.v351.controller.vnf;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.vnf.VnfSubscriptionSol005FrontController;
import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.Link;
import com.ubiqube.etsi.mano.nfvo.v351.model.vnf.PkgmSubscription;
import com.ubiqube.etsi.mano.nfvo.v351.model.vnf.PkgmSubscriptionLinks;
import com.ubiqube.etsi.mano.nfvo.v351.model.vnf.PkgmSubscriptionRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class VnfSubscriptions351Sol005Controller implements VnfSubscriptions351Sol005Api {
	private final VnfSubscriptionSol005FrontController vnfSubscriptionSol03FrontController;

	public VnfSubscriptions351Sol005Controller(final VnfSubscriptionSol005FrontController vnfSubscriptionSol03FrontController) {
		super();
		this.vnfSubscriptionSol03FrontController = vnfSubscriptionSol03FrontController;
	}

	/**
	 * Query multiple subscriptions.
	 *
	 * The GET method queries the list of active subscriptions of the functional
	 * block that invokes the method. It can be used e.g. for resynchronization
	 * after error situations. This method shall follow the provisions specified in
	 * the Tables 9.4.7.8.2-1 and 9.4.8.3.2-2 for URI query parameters, request and
	 * response data structures, and response codes. ²
	 */
	@Override
	public ResponseEntity<List<PkgmSubscription>> subscriptionsGet(final String filter) {
		return vnfSubscriptionSol03FrontController.search(filter, PkgmSubscription.class, VnfSubscriptions351Sol005Controller::makeLinks);
	}

	/**
	 * Subscribe to notifications related to on-boarding and/or changes of VNF
	 * packages.
	 *
	 * The POST method creates a new subscription. This method shall follow the
	 * provisions specified in the Tables 9.4.8.3.1-1 and 9.4.8.3.1-2 for URI query
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
	 * callbackUri).
	 *
	 */
	@Override
	public ResponseEntity<PkgmSubscription> subscriptionsPost(final PkgmSubscriptionRequest subscriptionsPostQuery) {
		return vnfSubscriptionSol03FrontController.create(subscriptionsPostQuery, VnfSubscriptions351Sol005Api.class, PkgmSubscription.class, VnfSubscriptions351Sol005Controller::makeLinks);
	}

	/**
	 * Terminate a subscription.
	 *
	 * The DELETE method terminates an individual subscription.
	 *
	 */
	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		return vnfSubscriptionSol03FrontController.delete(subscriptionId);
	}

	/**
	 * Read an individual subscription resource.
	 *
	 * Query Subscription Information The GET method reads an individual
	 * subscription.
	 *
	 */
	@Override
	public ResponseEntity<PkgmSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		return vnfSubscriptionSol03FrontController.findById(subscriptionId, PkgmSubscription.class, VnfSubscriptions351Sol005Controller::makeLinks);
	}

	public static void makeLinks(final PkgmSubscription pkgmSubscription) {
		final PkgmSubscriptionLinks subscriptionsPkgmSubscriptionLinks = new PkgmSubscriptionLinks();
		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfSubscriptions351Sol005Api.class).subscriptionsSubscriptionIdGet(pkgmSubscription.getId())).withSelfRel().getHref());
		subscriptionsPkgmSubscriptionLinks.setSelf(self);
		pkgmSubscription.setLinks(subscriptionsPkgmSubscriptionLinks);
	}

}
