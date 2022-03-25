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

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.PackageChangeType;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.dao.mano.VnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.dao.mano.config.RemoteSubscription;
import com.ubiqube.etsi.mano.jpa.RemoteSubscriptionJpa;
import com.ubiqube.etsi.mano.jpa.VnfPackageJpa;
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
	private final VnfPackageJpa vnfPackageJpa;

	public VnfNotificationFrontControllerImpl(final EventManager eventManager, final MapperFacade mapper, final VnfPackageOnboardingNotificationJpa vnfPackageOnboardingNotificationJpa,
			final RemoteSubscriptionJpa remoteSubscriptionJpa, final VnfPackageJpa vnfPackageJpa) {
		super();
		this.eventManager = eventManager;
		this.mapper = mapper;
		this.vnfPackageOnboardingNotificationJpa = vnfPackageOnboardingNotificationJpa;
		this.remoteSubscriptionJpa = remoteSubscriptionJpa;
		this.vnfPackageJpa = vnfPackageJpa;
	}

	@Override
	public ResponseEntity<Void> check() {
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> onNotification(final Object body, final String version) {
		final VnfPackageOnboardingNotification event = mapper.map(body, VnfPackageOnboardingNotification.class);
		final Optional<RemoteSubscription> subscription = remoteSubscriptionJpa.findByRemoteSubscriptionId(event.getSubscriptionId().toString());
		if (subscription.isEmpty()) {
			LOG.warn("Unable to find notification event {} in database.", event.getSubscriptionId());
			return ResponseEntity.notFound().build();
		}
		final VnfPackageOnboardingNotification newEvent = vnfPackageOnboardingNotificationJpa.save(event);
		LOG.info("Event received: {} => Id: {}", newEvent.getNfvoId(), newEvent.getId());
		eventManager.sendActionVnfm(ActionType.VNF_PKG_ONBOARD_DOWNLOAD, newEvent.getId(), new HashMap<>());
		return ResponseEntity.noContent().build();
	}

	@Transactional
	@Override
	public ResponseEntity<Void> onChange(final Object body, final String string) {
		final VnfPackageChangeNotification event = mapper.map(body, VnfPackageChangeNotification.class);
		final Optional<RemoteSubscription> subscription = remoteSubscriptionJpa.findByRemoteSubscriptionId(event.getSubscriptionId());
		if (subscription.isEmpty()) {
			LOG.warn("Unable to find change event {} in database.", event.getSubscriptionId());
			return ResponseEntity.notFound().build();
		}
		if (event.getChangeType() == PackageChangeType.PKG_DELETE) {
			vnfPackageJpa.deleteByVnfdId(event.getVnfdId());
		} else {
			final Optional<VnfPackage> pkg = vnfPackageJpa.findByDescriptorId(event.getVnfdId());
			if (pkg.isPresent()) {
				final VnfPackage p = pkg.get();
				p.setOperationalState(PackageOperationalState.fromValue(event.getOperationalState().toString()));
				vnfPackageJpa.save(p);
			} else {
				LOG.warn("Could not find vnfdId {}", event.getVnfdId());
			}
		}
		return ResponseEntity.noContent().build();
	}

}
