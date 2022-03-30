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

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.dao.mano.VnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.vnfm.fc.vnf.VnfNotificationFrontController;
import com.ubiqube.etsi.mano.vnfm.service.VnfNotificationService;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfNotificationFrontControllerImpl implements VnfNotificationFrontController {
	private final MapperFacade mapper;
	private final VnfNotificationService vnfNotificationService;

	public VnfNotificationFrontControllerImpl(final MapperFacade mapper, final VnfNotificationService vnfNotificationService) {
		super();
		this.mapper = mapper;
		this.vnfNotificationService = vnfNotificationService;

	}

	@Override
	public ResponseEntity<Void> check() {
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> onNotification(final Object body, final String version) {
		final VnfPackageOnboardingNotification event = mapper.map(body, VnfPackageOnboardingNotification.class);
		vnfNotificationService.onNotification(event, version);
		return ResponseEntity.noContent().build();
	}

	@Transactional
	@Override
	public ResponseEntity<Void> onChange(final Object body, final String version) {
		final VnfPackageChangeNotification event = mapper.map(body, VnfPackageChangeNotification.class);
		vnfNotificationService.onChange(event, version);
		return ResponseEntity.noContent().build();
	}

}
