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
package com.ubiqube.etsi.mano.service.event;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.service.HttpGateway;
import com.ubiqube.etsi.mano.service.ServerService;
import com.ubiqube.etsi.mano.service.SubscriptionService;
import com.ubiqube.etsi.mano.service.rest.ServerAdapter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
@Transactional
public class VnfEvent {
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(VnfEvent.class);
	private final ServerService serverService;
	private final SubscriptionService subscriptionService;
	private final Notifications notifications;

	public VnfEvent(final SubscriptionService subscriptionRepository, final Notifications notifications, final ServerService serverService) {
		super();
		this.subscriptionService = subscriptionRepository;
		this.notifications = notifications;
		this.serverService = serverService;
	}

	public void onEvent(final UUID vnfPkgId, final String event) {
		final List<Subscription> res = subscriptionService.selectNotifications(vnfPkgId, event);
		LOG.info("VNF Package event received: {}/{} with {} elements.", event, vnfPkgId, res.size());
		res.stream().forEach(x -> sendNotification(vnfPkgId, x, event));
	}

	private void sendNotification(final UUID vnfPkgId, final Subscription subscription, final String event) {
		final ServerAdapter server = serverService.buildServerAdapter(subscription);
		final HttpGateway httpGateway = server.httpGateway();
		Object object;
		if ("VnfPackageChangeNotification".equals(event)) {
			object = httpGateway.createVnfPackageChangeNotification(subscription.getId(), vnfPkgId);
		} else if ("VnfPackageOnboardingNotification".equals(event)) {
			object = httpGateway.createNotificationVnfPackageOnboardingNotification(subscription.getId(), vnfPkgId);
		} else if ("VnfLcmOperationOccurrenceNotification".equals(event)) {
			object = httpGateway.createNotificationVnfLcmOperationOccurrenceNotification(subscription.getId(), vnfPkgId);
		} else if ("VnfIdentifierCreationNotification".equals(event)) {
			object = httpGateway.createNotificationVnfIdentifierCreationNotification(subscription.getId(), vnfPkgId);
		} else if ("VnfIdentifierDeletionNotification".equals(event)) {
			object = httpGateway.createNotificationVnfIdentifierDeletionNotification(subscription.getId(), vnfPkgId);
		} else {
			LOG.warn("Unknown event received: {}", event);
			return;
		}
		final var callbackUri = subscription.getCallbackUri();
		try {
			notifications.doNotification(object, callbackUri, server);
		} catch (final RuntimeException e) {
			LOG.error("Could not send notification.", e);
		}
	}

}
