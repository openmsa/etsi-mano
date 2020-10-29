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
package com.ubiqube.etsi.mano.controller.vnf;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ApiTypesEnum;
import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.dao.mano.VnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;
import com.ubiqube.etsi.mano.service.event.Notifications;

@Service
public class VnfSubscriptionManagementImpl implements VnfSubscriptionManagement {
	private final Notifications notifications;
	private final SubscriptionRepository subscriptionRepository;

	public VnfSubscriptionManagementImpl(final Notifications _notifications, final SubscriptionRepository _subscriptionRepository) {
		super();
		this.notifications = _notifications;
		this.subscriptionRepository = _subscriptionRepository;
	}

	@Override
	public List<Subscription> subscriptionsGet(final String filter) {
		return subscriptionRepository.query(filter);

	}

	@Override
	public Subscription subscriptionsPost(@Nonnull final Subscription subscription, final ApiTypesEnum api) {
		subscription.setApi(api);
		return subscriptionRepository.save(subscription);
	}

	@Override
	public void vnfPackageChangeNotificationPost(@Nonnull final VnfPackageChangeNotification notificationsMessage) {
		final UUID subscriptionId = UUID.fromString(notificationsMessage.getSubscriptionId());

		final Subscription subscriptionsRepository = subscriptionRepository.get(subscriptionId);
		final AuthentificationInformations auth = subscriptionsRepository.getAuthentificationInformations();
		final String callbackUri = subscriptionsRepository.getCallbackUri();
		// There is a version, problem.
		notifications.doNotification(notificationsMessage, callbackUri, auth);
	}

	@Override
	public void vnfPackageOnboardingNotificationPost(@Nonnull final VnfPackageOnboardingNotification notificationsMessage) {
		final UUID subscriptionId = UUID.fromString(notificationsMessage.getSubscriptionId());
		final Subscription subscription = subscriptionRepository.get(subscriptionId);
		final AuthentificationInformations auth = subscription.getAuthentificationInformations();
		final String cbUrl = subscription.getCallbackUri();
		// Version problem.
		notifications.doNotification(notificationsMessage, cbUrl, auth);
	}

	@Override
	public void subscriptionsSubscriptionIdDelete(@Nonnull final String _subscriptionId) {
		final UUID subscriptionId = UUID.fromString(_subscriptionId);
		subscriptionRepository.get(subscriptionId);
		subscriptionRepository.delete(subscriptionId);
	}

	@Override
	public Subscription subscriptionsSubscriptionIdGet(@Nonnull final UUID _subscriptionId) {
		return subscriptionRepository.get(_subscriptionId);
	}

}
