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
import static com.ubiqube.etsi.mano.Constants.ensureIsEnabled;
import static com.ubiqube.etsi.mano.Constants.ensureIsOnboarded;
import static com.ubiqube.etsi.mano.Constants.ensureNotInstantiated;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.model.NsInstantiate;
import com.ubiqube.etsi.mano.service.NsInstanceService;
import com.ubiqube.etsi.mano.service.NsLcmOpOccsService;
import com.ubiqube.etsi.mano.service.NsdPackageService;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;

import ma.glasnost.orika.MapperFacade;

@Service
public class NsInstanceControllerServiceImpl implements NsInstanceControllerService {

	private static final Logger LOG = LoggerFactory.getLogger(NsInstanceControllerService.class);

	private final NsdPackageService nsdPackageService;

	private final NsInstanceService nsInstanceService;

	private final NsLcmOpOccsService nsLcmOpOccsService;

	private final EventManager eventManager;

	private final MapperFacade mapper;

	public NsInstanceControllerServiceImpl(final NsdPackageService nsdPackageService, final NsInstanceService nsInstanceService, final NsLcmOpOccsService _nsLcmOpOccsService, final EventManager _eventManager, final MapperFacade _mapper) {
		super();
		this.nsdPackageService = nsdPackageService;
		this.nsInstanceService = nsInstanceService;
		nsLcmOpOccsService = _nsLcmOpOccsService;
		eventManager = _eventManager;
		mapper = _mapper;
	}

	@Override
	public NsdInstance createNsd(final String _nsdId, final String nsName, final String nsDescription) {
		final UUID nsdId = UUID.fromString(_nsdId);
		final NsdPackage nsd = nsdPackageService.findById(nsdId);
		ensureIsOnboarded(nsd);
		ensureIsEnabled(nsd);
		nsd.setNsdUsageState(PackageUsageState.IN_USE);
		nsdPackageService.save(nsd);

		final NsdInstance nsInstance = new NsdInstance();
		nsInstance.setNsInstanceName(nsName);
		nsInstance.setNsInstanceDescription(nsDescription);
		nsInstance.setNsdInfo(nsd);
		nsInstance.setNsState(InstantiationState.NOT_INSTANTIATED);
		final NsdInstance nsInstanceTmp = nsInstanceService.save(nsInstance);

		final List<VnfInstance> vnfInstances = new ArrayList<>();

		nsd.getNestedNsdInfoIds().forEach(x -> {
			// create nested instance.
			final NsdInstance nsIn = createNsd(_nsdId, nsName, nsDescription);
			nsInstanceTmp.addNestedNsInstance(nsIn);
		});
		nsInstanceTmp.setVnfInstance(vnfInstances);
		return nsInstanceService.save(nsInstanceTmp);
	}

	@Override
	public NsLcmOpOccs instantiate(final UUID nsUuid, final NsInstantiate req) {
		final NsdInstance nsInstanceDb = nsInstanceService.findById(nsUuid);
		ensureNotInstantiated(nsInstanceDb);
		final NsLcmOpOccs nsLcm = nsLcmOpOccsService.createLcmOpOccs(nsInstanceDb, NsdChangeType.INSTANTIATE);
		// XXX Should be mapped in lcm as an intermediate result.
		mapper.map(req, nsInstanceDb);
		nsInstanceService.save(nsInstanceDb);
		eventManager.sendAction(ActionType.NS_INSTANTIATE, nsLcm.getId(), new HashMap<>());
		return nsLcm;
	}

	@Override
	public NsLcmOpOccs terminate(final UUID nsInstanceUuid, final OffsetDateTime terminationTime) {
		final NsdInstance nsInstanceDb = nsInstanceService.findById(nsInstanceUuid);
		ensureInstantiated(nsInstanceDb);
		final NsLcmOpOccs nsLcm = nsLcmOpOccsService.createLcmOpOccs(nsInstanceDb, NsdChangeType.TERMINATE);
		// XXX we can use quartz cron job for terminationTime.
		eventManager.sendAction(ActionType.NS_TERMINATE, nsLcm.getId(), new HashMap<>());
		return nsLcm;
	}
}
