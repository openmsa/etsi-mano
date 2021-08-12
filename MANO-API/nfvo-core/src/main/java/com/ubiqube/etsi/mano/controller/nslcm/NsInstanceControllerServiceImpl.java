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

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.dao.mano.nfvo.NsVnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.model.NsInstantiate;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.service.NsBlueprintService;
import com.ubiqube.etsi.mano.service.NsInstanceService;
import com.ubiqube.etsi.mano.service.NsdPackageService;
import com.ubiqube.etsi.mano.service.SearchableService;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;

import ma.glasnost.orika.MapperFacade;

@Service
public class NsInstanceControllerServiceImpl extends SearchableService implements NsInstanceControllerService {

	private final NsdPackageService nsdPackageService;

	private final NsInstanceService nsInstanceService;

	private final EventManager eventManager;

	private final MapperFacade mapper;
	private final NsBlueprintService nsBlueprintService;

	public NsInstanceControllerServiceImpl(final NsdPackageService nsdPackageService, final NsInstanceService nsInstanceService, final EventManager _eventManager, final MapperFacade _mapper, final NsBlueprintService _nsBlueprintService, final EntityManager _em, final ManoSearchResponseService searchService) {
		super(searchService, _em, NsdInstance.class);
		this.nsdPackageService = nsdPackageService;
		this.nsInstanceService = nsInstanceService;
		eventManager = _eventManager;
		mapper = _mapper;
		nsBlueprintService = _nsBlueprintService;
	}

	@Override
	public NsdInstance createNsd(final String _nsdId, final String nsName, final String nsDescription) {
		final NsdPackage nsd = nsdPackageService.findByNsdId(_nsdId);
		ensureIsOnboarded(nsd);
		ensureIsEnabled(nsd);
		nsd.setNsdUsageState(PackageUsageState.IN_USE);
		nsdPackageService.save(nsd);

		final NsdInstance nsInstance = new NsdInstance();
		nsInstance.setNsInstanceName(nsName);
		nsInstance.setNsInstanceDescription(nsDescription);
		nsInstance.setNsdInfo(nsd);
		nsInstance.setInstantiationState(InstantiationState.NOT_INSTANTIATED);
		final NsdInstance nsInstanceTmp = nsInstanceService.save(nsInstance);

		final List<NsVnfInstance> vnfInstances = new ArrayList<>();

		nsd.getNestedNsdInfoIds().forEach(x -> {
			// create nested instance.
			final NsdInstance nsIn = createNsd(_nsdId, nsName, nsDescription);
			nsInstanceTmp.addNestedNsInstance(nsIn);
		});
		nsInstanceTmp.setVnfInstance(vnfInstances);
		return nsInstanceService.save(nsInstanceTmp);
	}

	@Override
	public NsBlueprint instantiate(final UUID nsUuid, final NsInstantiate req) {
		final NsdInstance nsInstanceDb = nsInstanceService.findById(nsUuid);
		ensureNotInstantiated(nsInstanceDb);
		final NsBlueprint nsLcm = LcmFactory.createNsLcmOpOcc(nsInstanceDb, PlanOperationType.INSTANTIATE);
		nsBlueprintService.save(nsLcm);
		// XXX Should be mapped in lcm as an intermediate result.
		mapper.map(req, nsInstanceDb);
		nsInstanceService.save(nsInstanceDb);
		eventManager.sendActionNfvo(ActionType.NS_INSTANTIATE, nsLcm.getId(), new HashMap<>());
		return nsLcm;
	}

	@Override
	public NsBlueprint terminate(final UUID nsInstanceUuid, final OffsetDateTime terminationTime) {
		final NsdInstance nsInstanceDb = nsInstanceService.findById(nsInstanceUuid);
		ensureInstantiated(nsInstanceDb);
		final NsBlueprint nsLcm = LcmFactory.createNsLcmOpOcc(nsInstanceDb, PlanOperationType.TERMINATE);
		nsBlueprintService.save(nsLcm);
		// XXX we can use quartz cron job for terminationTime.
		eventManager.sendActionNfvo(ActionType.NS_TERMINATE, nsLcm.getId(), new HashMap<>());
		return nsLcm;
	}
}
