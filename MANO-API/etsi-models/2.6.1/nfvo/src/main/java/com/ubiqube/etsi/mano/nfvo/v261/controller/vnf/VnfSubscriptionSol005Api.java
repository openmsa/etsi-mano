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
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscription;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscriptionRequest;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.controller.vnf.VnfSubscriptionManagement;
import com.ubiqube.etsi.mano.dao.mano.ApiTypesEnum;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.service.event.Notifications;

import ma.glasnost.orika.MapperFacade;

@Profile({ "!VNFM" })
@RestController
public class VnfSubscriptionSol005Api implements VnfSubscriptionSol005 {

	private static final Logger LOG = LoggerFactory.getLogger(VnfSubscriptionSol005Api.class);

	private final VnfSubscriptionManagement vnfSubscriptionManagement;

	private final Linkable links = new Sol005Linkable();

	private final MapperFacade mapper;

	private final Notifications notifications;

	public VnfSubscriptionSol005Api(final VnfSubscriptionManagement _vnfSubscriptionManagement, final MapperFacade _mapper, final Notifications _notifications) {
		vnfSubscriptionManagement = _vnfSubscriptionManagement;
		mapper = _mapper;
		notifications = _notifications;
		LOG.info("Starting VNF Subscription Package SOL005 Controller.");
	}

	@Override
	public ResponseEntity<List<PkgmSubscription>> subscriptionsGet(@RequestParam(value = "filter", required = false) final String filters) {
		final List<Subscription> list = vnfSubscriptionManagement.subscriptionsGet(filters);
		final List<PkgmSubscription> pkgms = mapper.mapAsList(list, PkgmSubscription.class);
		pkgms.stream().forEach(x -> x.setLinks(links.createSubscriptionsPkgmSubscriptionLinks(x.getId())));
		return ResponseEntity.ok(pkgms);
	}

	@Override
	public ResponseEntity<PkgmSubscription> subscriptionsPost(final PkgmSubscriptionRequest subscriptionsPostQuery) {
		Subscription subscription = mapper.map(subscriptionsPostQuery, Subscription.class);
		// Check subscription.
		notifications.check(subscription.getAuthentificationInformations(), subscription.getCallbackUri());
		subscription = vnfSubscriptionManagement.subscriptionsPost(subscription, ApiTypesEnum.SOL005);
		final PkgmSubscription pkgmSubscription = mapper.map(subscription, PkgmSubscription.class);
		pkgmSubscription.setLinks(links.createSubscriptionsPkgmSubscriptionLinks(pkgmSubscription.getId()));
		return new ResponseEntity<>(pkgmSubscription, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		vnfSubscriptionManagement.subscriptionsSubscriptionIdDelete(subscriptionId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<PkgmSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		final Subscription subscription = vnfSubscriptionManagement.subscriptionsSubscriptionIdGet(UUID.fromString(subscriptionId));
		final PkgmSubscription pkgmSubscription = mapper.map(subscription, PkgmSubscription.class);
		pkgmSubscription.setLinks(links.createSubscriptionsPkgmSubscriptionLinks(subscriptionId));
		return new ResponseEntity<>(pkgmSubscription, HttpStatus.OK);

	}

	@Override
	public void vnfPackageChangeNotificationPost(final VnfPackageChangeNotification notificationsMessage) {
		final com.ubiqube.etsi.mano.dao.mano.VnfPackageChangeNotification msg = mapper.map(notificationsMessage, com.ubiqube.etsi.mano.dao.mano.VnfPackageChangeNotification.class);
		vnfSubscriptionManagement.vnfPackageChangeNotificationPost(msg);
	}

	@Override
	public void vnfPackageOnboardingNotificationPost(final VnfPackageOnboardingNotification notificationsMessage) {
		final com.ubiqube.etsi.mano.dao.mano.VnfPackageOnboardingNotification msg = mapper.map(notificationsMessage, com.ubiqube.etsi.mano.dao.mano.VnfPackageOnboardingNotification.class);
		vnfSubscriptionManagement.vnfPackageOnboardingNotificationPost(msg);
	}

}
