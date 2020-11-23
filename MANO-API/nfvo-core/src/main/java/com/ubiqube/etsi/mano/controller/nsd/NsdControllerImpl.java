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
package com.ubiqube.etsi.mano.controller.nsd;

import static com.ubiqube.etsi.mano.Constants.ensureDisabled;
import static com.ubiqube.etsi.mano.Constants.ensureIsOnboarded;
import static com.ubiqube.etsi.mano.Constants.ensureNotInUse;
import static com.ubiqube.etsi.mano.Constants.ensureNotOnboarded;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.Patcher;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;

@Service
public class NsdControllerImpl implements NsdController {

	private static final Logger LOG = LoggerFactory.getLogger(NsdController.class);
	private final NsdRepository nsdRepository;
	private final Patcher patcher;
	private final VnfPackageRepository vnfPackageRepository;
	private final EventManager eventManager;

	public NsdControllerImpl(final NsdRepository _nsdRepository, final VnfPackageRepository _vnfPackageRepository, final Patcher _patcher, final EventManager _eventManager) {
		nsdRepository = _nsdRepository;
		vnfPackageRepository = _vnfPackageRepository;
		patcher = _patcher;
		eventManager = _eventManager;
		LOG.info("Starting NSD Management SOL005 Controller.");
	}

	@Override
	public List<NsdPackage> nsDescriptorsGet(final String filter) {
		return nsdRepository.query(filter);
	}

	@Override
	public void nsDescriptorsNsdInfoIdDelete(final UUID id) {
		final NsdPackage nsdPackage = nsdRepository.get(id);
		ensureDisabled(nsdPackage);
		ensureNotInUse(nsdPackage);
		nsdRepository.delete(id);
		// NsdDeletionNotification OSS/BSS
	}

	@Override
	public NsdPackage nsDescriptorsNsdInfoIdGet(final UUID id) {
		return nsdRepository.get(id);
	}

	@Override
	public byte[] nsDescriptorsNsdInfoIdNsdContentGet(final UUID id) {
		final NsdPackage nsdInfo = nsdRepository.get(id);
		ensureIsOnboarded(nsdInfo);
		return nsdRepository.getBinary(id, "nsd");
	}

	@Override
	public void nsDescriptorsNsdInfoIdNsdContentPut(final UUID id, final InputStream is) {
		final NsdPackage nsdInfo = nsdRepository.get(id);
		ensureNotOnboarded(nsdInfo);
		nsdRepository.storeBinary(id, "nsd", is);
		eventManager.sendAction(ActionType.NSD_PKG_ONBOARD_FROM_BYTES, nsdInfo.getId(), new HashMap<>());
	}

	@Override
	public NsdPackage nsDescriptorsNsdInfoIdPatch(final UUID id, final String body) {
		final NsdPackage nsdPkgInfo = nsdRepository.get(id);
		patcher.patch(body, nsdPkgInfo);
		// NsdChangeNotification OSS/BSS
		return nsdRepository.save(nsdPkgInfo);
	}

	@Override
	public NsdPackage nsDescriptorsPost(final Map<String, String> userDefinedData) {
		final NsdPackage nsdPackage = new NsdPackage();
		nsdPackage.setUserDefinedData(userDefinedData);
		nsdPackage.setNsdOnboardingState(OnboardingStateType.CREATED);
		nsdPackage.setNsdOperationalState(PackageOperationalState.DISABLED);
		return nsdRepository.save(nsdPackage);
	}
}
