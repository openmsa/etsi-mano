package com.ubiqube.etsi.mano.controller.vnf;

import static com.ubiqube.etsi.mano.Constants.ensureDisabled;
import static com.ubiqube.etsi.mano.Constants.ensureNotInUse;
import static com.ubiqube.etsi.mano.Constants.ensureNotOnboarded;

import java.util.Map;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.nfvo.v261.VnfPackageFactory;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.Patcher;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;

public class VnfPackageController {
	private final VnfPackageRepository vnfPackageRepository;
	private final Patcher patcher;
	private final EventManager eventManager;

	public VnfPackageController(final Patcher _patcher, final VnfPackageRepository _vnfPackageRepository, final EventManager _eventManager) {
		patcher = _patcher;
		vnfPackageRepository = _vnfPackageRepository;
		eventManager = _eventManager;
	}

	public VnfPackage vnfPackagesPost(final Map<String, String> userData) {
		final VnfPackage vnfPackage = VnfPackageFactory.createVnfPkgInfo(userData);
		return vnfPackageRepository.save(vnfPackage);
	}

	public void vnfPackagesVnfPkgIdDelete(final UUID id) {
		final VnfPackage vnfPackage = vnfPackageRepository.get(id);
		ensureDisabled(vnfPackage);
		ensureNotInUse(vnfPackage);
		vnfPackageRepository.delete(id);
	}

	public VnfPackage vnfPackagesVnfPkgIdPatch(final UUID id, final String body) {
		final VnfPackage vnfPackage = vnfPackageRepository.get(id);
		patcher.patch(body, vnfPackage);
		eventManager.sendNotification(NotificationEvent.VNF_PKG_ONCHANGE, id);
		return vnfPackageRepository.save(vnfPackage);
	}

	public void vnfPackagesVnfPkgIdPackageContentPut(final UUID id) {
		final VnfPackage vnfPackage = vnfPackageRepository.get(id);
		ensureNotOnboarded(vnfPackage);
		// TODO
		eventManager.sendAction(ActionType.VNF_PKG_ONBOARD_FROM_BYTES, id, null);
	}

	public void vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(final UUID id) {
		final VnfPackage vnfPackage = vnfPackageRepository.get(id);
		ensureNotOnboarded(vnfPackage);
		// TODO
		eventManager.sendAction(ActionType.VNF_PKG_ONBOARD_FROM_URI, id, null);

	}
}
