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
package com.ubiqube.etsi.mano.controller.nslcm;

import static com.ubiqube.etsi.mano.Constants.ensureInstantiated;
import static com.ubiqube.etsi.mano.Constants.ensureNotInstantiated;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;
import com.ubiqube.etsi.mano.service.NsInstanceService;
import com.ubiqube.etsi.mano.service.NsLcmOpOccsService;

@Service
public class NsInstanceControllerImpl implements NsInstanceController {
	private final NsInstanceRepository nsInstanceRepository;
	private final NsLcmOpOccsService lcmOpOccsService;
	private final NsInstanceService nsInstanceService;

	public NsInstanceControllerImpl(final NsInstanceRepository _nsInstanceRepository, final NsInstanceService _nsInstanceService, final NsLcmOpOccsService _lcmOpOccsService) {
		nsInstanceRepository = _nsInstanceRepository;
		nsInstanceService = _nsInstanceService;
		lcmOpOccsService = _lcmOpOccsService;
	}

	@Override
	public List<NsdInstance> nsInstancesGet(final String filter) {
		return nsInstanceRepository.query(filter);
	}

	@Override
	public void nsInstancesNsInstanceIdDelete(final UUID id) {
		final NsdInstance nsInstanceDb = nsInstanceRepository.get(id);
		ensureNotInstantiated(nsInstanceDb);
		nsInstanceService.delete(id);
	}

	@Override
	public NsdInstance nsInstancesNsInstanceIdGet(final UUID id) {
		return nsInstanceRepository.get(id);
	}

	@Override
	public NsdInstance nsInstancesNsInstanceIdHealPost(final UUID id) {
		final NsdInstance nsInstance = nsInstanceRepository.get(id);
		ensureInstantiated(nsInstance);
		final NsLcmOpOccs lcmOpOccs = LcmFactory.createNsLcmOpOcc(nsInstance, NsdChangeType.HEAL);
		lcmOpOccsService.save(lcmOpOccs);
		throw new GenericException("TODO");
	}

	@Override
	public NsdInstance nsInstancesNsInstanceIdScalePost(final UUID id) {
		final NsdInstance nsInstanceDb = nsInstanceRepository.get(id);
		ensureInstantiated(nsInstanceDb);
		final NsLcmOpOccs lcmOpOccs = LcmFactory.createNsLcmOpOcc(nsInstanceDb, NsdChangeType.SCALE);
		lcmOpOccsService.save(lcmOpOccs);
		throw new GenericException("TODO");
	}

	@Override
	public void nsInstancesNsInstanceIdUpdatePost(final UUID id) {
		final NsdInstance nsInstanceDb = nsInstanceRepository.get(id);
		ensureInstantiated(nsInstanceDb);
		final NsLcmOpOccs lcmOpOccs = LcmFactory.createNsLcmOpOcc(nsInstanceDb, NsdChangeType.UPDATE);
		lcmOpOccsService.save(lcmOpOccs);
		throw new GenericException("TODO");
	}
}
