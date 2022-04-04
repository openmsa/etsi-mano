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
package com.ubiqube.etsi.mano.nfvo.service.event.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.service.event.VnfEvent;
import com.ubiqube.etsi.mano.service.event.jms.EventMessage;

@Service
public class NotificationsController {

	private static final Logger LOG = LoggerFactory.getLogger(NotificationsController.class);
	private final VnfEvent vnfEvent;

	public NotificationsController(final VnfEvent vnfEvent) {
		super();
		this.vnfEvent = vnfEvent;
	}

	@JmsListener(destination = "system.notifications", concurrency = "2-4")
	public void onEvent(final EventMessage ev) {
		LOG.info("Notification Controller Received event: {}", ev);
		switch (ev.getNotificationEvent()) {
		// NSD
		case NS_PKG_ONBOARDING -> vnfEvent.onEvent(ev.getObjectId(), "NsdOnBoardingNotification");
		case NS_PKG_ONDELETION -> vnfEvent.onEvent(ev.getObjectId(), "NsdDeletionNotification");
		case NS_PKG_ONCHANGE -> vnfEvent.onEvent(ev.getObjectId(), "NsdChangeNotification");
		// VNFD
		case VNF_PKG_ONBOARDING -> vnfEvent.onEvent(ev.getObjectId(), "VnfPackageOnboardingNotification");
		case VNF_PKG_ONDELETION -> vnfEvent.onEvent(ev.getObjectId(), "VnfPackageChangeNotification");
		case VNF_PKG_ONCHANGE -> vnfEvent.onEvent(ev.getObjectId(), "VnfPackageChangeNotification");
		case VRQAN -> vnfEvent.onEvent(ev.getObjectId(), "VrQuotaAvailNotification");
		// NS instance.
		case NS_INSTANCE_CREATE -> vnfEvent.onEvent(ev.getObjectId(), "NsIdentifierCreationNotification");
		case NS_INSTANCE_DELETE -> vnfEvent.onEvent(ev.getObjectId(), "NsIdentifierDeletionNotification");
		case NS_INSTANTIATE -> vnfEvent.onEvent(ev.getObjectId(), "NsLcmOperationOccurrenceNotification");
		// VNF instance.
		case VNF_INSTANCE_CREATE -> vnfEvent.onEvent(ev.getObjectId(), "VnfIdentifierCreationNotification");
		case VNF_INSTANCE_DELETE -> vnfEvent.onEvent(ev.getObjectId(), "VnfIdentifierDeletionNotification");
		case VNF_INSTANTIATE -> vnfEvent.onEvent(ev.getObjectId(), "VnfLcmOperationOccurrenceNotification");
		case VNF_INSTANTIATE_FAILED -> vnfEvent.onEvent(ev.getObjectId(), "VnfLcmOperationOccurrenceNotification");
		default -> LOG.error("Unable to handle event type {}", ev.getNotificationEvent());
		}
	}
}
