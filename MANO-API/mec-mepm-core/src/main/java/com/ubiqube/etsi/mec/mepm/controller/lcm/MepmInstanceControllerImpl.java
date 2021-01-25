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
package com.ubiqube.etsi.mec.mepm.controller.lcm;

import static com.ubiqube.etsi.mano.Constants.ensureInstantiated;
import static com.ubiqube.etsi.mano.Constants.ensureIsEnabled;
import static com.ubiqube.etsi.mano.Constants.ensureIsOnboarded;
import static com.ubiqube.etsi.mano.Constants.ensureNotInstantiated;

import java.util.HashMap;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppBlueprint;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppInstance;
import com.ubiqube.etsi.mano.dao.mec.pkg.AppPkg;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mec.mepm.event.MepmEventManager;
import com.ubiqube.etsi.mec.mepm.service.AppBlueprintService;
import com.ubiqube.etsi.mec.mepm.service.AppInstanceService;
import com.ubiqube.etsi.mec.mepm.service.AppLcmService;
import com.ubiqube.etsi.mec.mepm.service.AppPackageService;
import com.ubiqube.etsi.mec.repositories.AppPackageRepository;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class MepmInstanceControllerImpl implements MepmInstanceController {

	private static final Logger LOG = LoggerFactory.getLogger(MepmInstanceControllerImpl.class);

	private final AppInstanceService appInstanceService;

	private final AppPackageRepository appPackageRepository;

	private final MapperFacade mapper;

	private final MepmEventManager eventManager;

	private final AppLcmService appLcmService;

	private final AppBlueprintService planService;

	private final AppPackageService appPackageService;

	public MepmInstanceControllerImpl(final AppInstanceService appInstanceService, final AppPackageRepository appPackageRepository, final MapperFacade mapper, final MepmEventManager eventManager, final AppLcmService appLcmService, final AppBlueprintService planService, final AppPackageService _appPackageService) {
		super();
		this.appInstanceService = appInstanceService;
		this.appPackageRepository = appPackageRepository;
		this.mapper = mapper;
		this.eventManager = eventManager;
		this.appLcmService = appLcmService;
		this.planService = planService;
		appPackageService = _appPackageService;
	}

	@Override
	public void delete(final UUID id) {
		final AppInstance appInstance = appInstanceService.findById(id);
		ensureNotInstantiated(appInstance);
		if (appInstanceService.isInstantiate(appInstance.getAppPkg().getId())) {
			final AppPkg vnfPkg = appPackageRepository.get(appInstance.getAppPkg().getId());
			vnfPkg.setUsageState(PackageUsageState.NOT_IN_USE);
			appPackageRepository.save(vnfPkg);
		}
		appInstanceService.delete(id);
	}

	@Override
	public AppInstance findById(final UUID fromString) {
		return appInstanceService.findById(fromString);
	}

	@Override
	public AppInstance createInstance(@NotNull final String appDId, final String appInstanceDescription, final String appInstanceName) {
		final AppPkg appPkgInfo = appPackageService.findByAppdId(appDId);
		ensureIsOnboarded(appPkgInfo);
		ensureIsEnabled(appPkgInfo);
		AppInstance appInstance = createAppInstance(appDId, appInstanceDescription, appInstanceName, appPkgInfo);
		mapper.map(appPkgInfo, appInstance);
		// VnfIdentifierCreationNotification NFVO + EM
		appInstance = appInstanceService.save(appInstance);
		eventManager.sendNotification(NotificationEvent.VNF_INSTANCE_CREATE, appInstance.getId());
		return appInstance;
	}

	private static AppInstance createAppInstance(@NotNull final String appDId, final String appInstanceDescription, final String appInstanceName, final AppPkg appPkgInfo) {
		final AppInstance appInstance = new AppInstance();
		appInstance.setAppPkg(appPkgInfo);
		appInstance.setVnfInstanceName(appInstanceName);
		appInstance.setVnfInstanceDescription(appInstanceDescription);
		return appInstance;
	}

	@Override
	public AppBlueprint terminate(final UUID appInstanceId, @NotNull @Valid final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout) {
		final AppInstance appInstance = appInstanceService.findById(appInstanceId);
		ensureInstantiated(appInstance);
		final AppBlueprint blueprint = appLcmService.createTerminateOpOcc(appInstance);
		eventManager.sendActionMepm(ActionType.VNF_TERMINATE, blueprint.getId(), new HashMap<>());
		LOG.info("Terminate sent for instancce: {}", appInstanceId);
		return blueprint;
	}

	@Override
	public AppBlueprint operate(final UUID uuid, final VnfOperateRequest req) {
		final AppInstance appInstance = appInstanceService.findById(uuid);
		ensureInstantiated(appInstance);
		final AppBlueprint lcmOpOccs = appLcmService.createOperateOpOcc(appInstance, req);
		eventManager.sendActionMepm(ActionType.VNF_OPERATE, lcmOpOccs.getId(), new HashMap<>());
		return lcmOpOccs;
	}

	@Override
	public AppBlueprint instantiate(final UUID appInstanceId) {
		final AppInstance appInstance = appInstanceService.findById(appInstanceId);
		ensureNotInstantiated(appInstance);

		final UUID vnfPkgId = appInstance.getAppPkg().getId();
		final AppPkg vnfPkg = appPackageRepository.get(vnfPkgId);
		ensureIsEnabled(vnfPkg);

		AppBlueprint blueprint = appLcmService.createIntatiateOpOcc(appInstance);
		// mapper.map(instantiateVnfRequest, blueprint);
		blueprint = planService.save(blueprint);
		eventManager.sendActionMepm(ActionType.VNF_INSTANTIATE, blueprint.getId(), new HashMap<>());
		LOG.info("VNF Instantiation Event Sucessfully sent. {}", blueprint.getId());
		return blueprint;
	}

}
