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
package com.ubiqube.etsi.mano.nfvo.controller.vnf;

import static com.ubiqube.etsi.mano.Constants.ensureDisabled;
import static com.ubiqube.etsi.mano.Constants.ensureNotInUse;
import static com.ubiqube.etsi.mano.Constants.ensureNotOnboarded;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.nfvo.factory.VnfPackageFactory;
import com.ubiqube.etsi.mano.service.Patcher;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;

@Service
public class VnfPackageControllerImpl implements VnfPackageController {
	private final VnfPackageService vnfPackageService;
	private final Patcher patcher;
	private final EventManager eventManager;

	public VnfPackageControllerImpl(final Patcher _patcher, final EventManager _eventManager, final VnfPackageService vnfPackageService) {
		patcher = _patcher;
		eventManager = _eventManager;
		this.vnfPackageService = vnfPackageService;
	}

	@Override
	public VnfPackage vnfPackagesPost(final Map<String, String> userData) {
		final VnfPackage vnfPackage = VnfPackageFactory.createVnfPkgInfo(userData);
		return vnfPackageService.save(vnfPackage);
	}

	@Override
	public void vnfPackagesVnfPkgIdDelete(final UUID id) {
		final VnfPackage vnfPackage = vnfPackageService.findById(id);
		ensureDisabled(vnfPackage);
		ensureNotInUse(vnfPackage);
		vnfPackageService.delete(id);
	}

	@Override
	public VnfPackage vnfPackagesVnfPkgIdPatch(final UUID id, final String body) {
		final VnfPackage vnfPackage = vnfPackageService.findById(id);
		patcher.patch(body, vnfPackage);
		eventManager.sendNotification(NotificationEvent.VNF_PKG_ONCHANGE, id);
		return vnfPackageService.save(vnfPackage);
	}

	@Override
	public void vnfPackagesVnfPkgIdPackageContentPut(final UUID id, final byte[] data, final String accept) {
		final VnfPackage vnfPackage = vnfPackageService.findById(id);
		ensureNotOnboarded(vnfPackage);
		final Map<String, Object> map = new HashMap<>();
		map.put("data", data);
		eventManager.sendActionNfvo(ActionType.VNF_PKG_ONBOARD_FROM_BYTES, id, map);
	}

	@Override
	public void vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(final UUID id, final String contentType, final String uri) {
		final VnfPackage vnfPackage = vnfPackageService.findById(id);
		ensureNotOnboarded(vnfPackage);
		final Map<String, Object> params = Map.of("contentType", contentType, "uri", uri);
		eventManager.sendActionNfvo(ActionType.VNF_PKG_ONBOARD_FROM_URI, id, params);

	}
}
