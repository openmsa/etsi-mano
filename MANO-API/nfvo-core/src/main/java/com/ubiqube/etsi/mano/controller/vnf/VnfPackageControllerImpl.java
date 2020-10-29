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

import static com.ubiqube.etsi.mano.Constants.ensureDisabled;
import static com.ubiqube.etsi.mano.Constants.ensureNotInUse;
import static com.ubiqube.etsi.mano.Constants.ensureNotOnboarded;

import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.factory.VnfPackageFactory;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.Patcher;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;

@Service
public class VnfPackageControllerImpl implements VnfPackageController {
	private final VnfPackageRepository vnfPackageRepository;
	private final Patcher patcher;
	private final EventManager eventManager;

	public VnfPackageControllerImpl(final Patcher _patcher, final VnfPackageRepository _vnfPackageRepository, final EventManager _eventManager) {
		patcher = _patcher;
		vnfPackageRepository = _vnfPackageRepository;
		eventManager = _eventManager;
	}

	@Override
	public VnfPackage vnfPackagesPost(final Map<String, String> userData) {
		final VnfPackage vnfPackage = VnfPackageFactory.createVnfPkgInfo(userData);
		return vnfPackageRepository.save(vnfPackage);
	}

	@Override
	public void vnfPackagesVnfPkgIdDelete(final UUID id) {
		final VnfPackage vnfPackage = vnfPackageRepository.get(id);
		ensureDisabled(vnfPackage);
		ensureNotInUse(vnfPackage);
		vnfPackageRepository.delete(id);
	}

	@Override
	public VnfPackage vnfPackagesVnfPkgIdPatch(final UUID id, final String body) {
		final VnfPackage vnfPackage = vnfPackageRepository.get(id);
		patcher.patch(body, vnfPackage);
		eventManager.sendNotification(NotificationEvent.VNF_PKG_ONCHANGE, id);
		return vnfPackageRepository.save(vnfPackage);
	}

	@Override
	public void vnfPackagesVnfPkgIdPackageContentPut(final UUID id) {
		final VnfPackage vnfPackage = vnfPackageRepository.get(id);
		ensureNotOnboarded(vnfPackage);
		// TODO
		eventManager.sendAction(ActionType.VNF_PKG_ONBOARD_FROM_BYTES, id, null);
	}

	@Override
	public void vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(final UUID id) {
		final VnfPackage vnfPackage = vnfPackageRepository.get(id);
		ensureNotOnboarded(vnfPackage);
		// TODO
		eventManager.sendAction(ActionType.VNF_PKG_ONBOARD_FROM_URI, id, null);

	}
}
