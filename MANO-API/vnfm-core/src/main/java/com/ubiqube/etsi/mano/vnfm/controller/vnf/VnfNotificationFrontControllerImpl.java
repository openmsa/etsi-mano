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
package com.ubiqube.etsi.mano.vnfm.controller.vnf;

import java.util.HashMap;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.dao.mano.config.RemoteSubscription;
import com.ubiqube.etsi.mano.jpa.RemoteSubscriptionJpa;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.vnfm.fc.vnf.VnfNotificationFrontController;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfPackageOnboardingNotificationJpa;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfNotificationFrontControllerImpl implements VnfNotificationFrontController {

	private static final Logger LOG = LoggerFactory.getLogger(VnfNotificationFrontControllerImpl.class);
	private final VnfPackageOnboardingNotificationJpa vnfPackageOnboardingNotificationJpa;
	private final EventManager eventManager;
	private final MapperFacade mapper;
	private final RemoteSubscriptionJpa remoteSubscriptionJpa;

	public VnfNotificationFrontControllerImpl(final EventManager eventManager, final MapperFacade mapper, final VnfPackageOnboardingNotificationJpa vnfPackageOnboardingNotificationJpa,
			final RemoteSubscriptionJpa remoteSubscriptionJpa) {
		super();
		this.eventManager = eventManager;
		this.mapper = mapper;
		this.vnfPackageOnboardingNotificationJpa = vnfPackageOnboardingNotificationJpa;
		this.remoteSubscriptionJpa = remoteSubscriptionJpa;
	}

	@Override
	public ResponseEntity<Void> check() {
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> onNotification(final Object body, final String version) {
		final VnfPackageOnboardingNotification event = mapper.map(body, VnfPackageOnboardingNotification.class);
		final Optional<RemoteSubscription> subscription = remoteSubscriptionJpa.findByRemoteSubscriptionId(event.getId().toString());
		if (subscription.isEmpty()) {
			LOG.warn("Unable to find event {} in database.", event.getId());
			return ResponseEntity.notFound().build();
		}
		final VnfPackageOnboardingNotification newEvent = vnfPackageOnboardingNotificationJpa.save(event);
		LOG.info("Event received: {} => Id: {}", newEvent.getNfvoId(), newEvent.getId());
		eventManager.sendActionVnfm(ActionType.VNF_PKG_ONBOARD_DOWNLOAD, newEvent.getId(), new HashMap<>());
		return ResponseEntity.noContent().build();
	}

}
