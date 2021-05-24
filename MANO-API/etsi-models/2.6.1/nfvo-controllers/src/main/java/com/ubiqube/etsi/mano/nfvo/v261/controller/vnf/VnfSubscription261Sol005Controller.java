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

package com.ubiqube.etsi.mano.nfvo.v261.controller.vnf;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscription;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscriptionRequest;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.controller.vnf.VnfSubscriptionSol005FrontController;

@RolesAllowed({ "ROLE_OSSBSS" })
@RestController
public class VnfSubscription261Sol005Controller implements VnfSubscription261Sol005Api {
	private final VnfSubscriptionSol005FrontController vnfSubscriptionSol005FrontController;

	private final Linkable links = new Sol005Linkable();

	public VnfSubscription261Sol005Controller(final VnfSubscriptionSol005FrontController vnfSubscriptionSol005FrontController) {
		super();
		this.vnfSubscriptionSol005FrontController = vnfSubscriptionSol005FrontController;
	}

	@Override
	public ResponseEntity<List<PkgmSubscription>> subscriptionsGet(@RequestParam(value = "filter", required = false) final String filters) {
		return vnfSubscriptionSol005FrontController.search(filters, PkgmSubscription.class, links::makeSubscriptionLink);
	}

	@Override
	public ResponseEntity<PkgmSubscription> subscriptionsPost(final PkgmSubscriptionRequest subscriptionsPostQuery) {
		return vnfSubscriptionSol005FrontController.create(subscriptionsPostQuery, PkgmSubscription.class, links::makeSubscriptionLink);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		return vnfSubscriptionSol005FrontController.delete(subscriptionId);
	}

	@Override
	public ResponseEntity<PkgmSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		return vnfSubscriptionSol005FrontController.findById(subscriptionId, PkgmSubscription.class, links::makeSubscriptionLink);

	}

	@Override
	public void vnfPackageChangeNotificationPost(final VnfPackageChangeNotification notificationsMessage) {
		vnfSubscriptionSol005FrontController.vnfPackageChangeNotificationPost(notificationsMessage);
	}

	@Override
	public void vnfPackageOnboardingNotificationPost(final VnfPackageOnboardingNotification notificationsMessage) {
		vnfSubscriptionSol005FrontController.vnfPackageOnboardingNotificationPost(notificationsMessage);
	}

}
