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
package com.ubiqube.etsi.mano.vnfm.controller.vnflcm;

import static com.ubiqube.etsi.mano.Constants.ensureInstantiated;
import static com.ubiqube.etsi.mano.Constants.ensureIsEnabled;
import static com.ubiqube.etsi.mano.Constants.ensureIsOnboarded;
import static com.ubiqube.etsi.mano.Constants.ensureNotInstantiated;
import static com.ubiqube.etsi.mano.Constants.ensureNotLocked;
import static com.ubiqube.etsi.mano.Constants.getSafeUUID;
import static com.ubiqube.etsi.mano.Constants.getSingleField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.controller.vnflcm.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.vnfi.ChangeExtVnfConnRequest;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.model.NotificationEvent;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.rest.ManoClientFactory;
import com.ubiqube.etsi.mano.service.vim.VimManager;
import com.ubiqube.etsi.mano.vnfm.service.VnfBlueprintService;
import com.ubiqube.etsi.mano.vnfm.service.VnfInstanceService;
import com.ubiqube.etsi.mano.vnfm.service.VnfInstanceServiceVnfm;
import com.ubiqube.etsi.mano.vnfm.service.VnfLcmService;

import ma.glasnost.orika.MapperFacade;

@Service
public class VnfInstanceLcmImpl implements VnfInstanceLcm {

	private static final Logger LOG = LoggerFactory.getLogger(VnfInstanceLcmImpl.class);

	private final VnfPackageRepository vnfPackageRepository;

	private final VnfPackageService vnfPackageService;

	private final EventManager eventManager;

	private final MapperFacade mapper;

	private final VnfLcmService vnfLcmService;

	private final VnfInstanceService vnfInstanceService;

	private final VimManager vimManager;

	private final VnfBlueprintService planService;

	private final VnfInstanceServiceVnfm vnfInstanceServiceVnfm;

	private final ManoClientFactory manoClientFactory;

	public VnfInstanceLcmImpl(final VnfPackageRepository vnfPackageRepository, final EventManager eventManager, final MapperFacade mapper, final VnfLcmService vnfLcmService,
			final VnfInstanceService vnfInstanceService, final VimManager vimManager, final VnfBlueprintService planService, final VnfPackageService vnfPackageService,
			final VnfInstanceServiceVnfm vnfInstanceServiceVnfm, final ManoClientFactory manoClientFactory) {
		super();
		this.vnfPackageRepository = vnfPackageRepository;
		this.eventManager = eventManager;
		this.mapper = mapper;
		this.vnfLcmService = vnfLcmService;
		this.vnfInstanceService = vnfInstanceService;
		this.vimManager = vimManager;
		this.planService = planService;
		this.vnfPackageService = vnfPackageService;
		this.vnfInstanceServiceVnfm = vnfInstanceServiceVnfm;
		this.manoClientFactory = manoClientFactory;
	}

	@Override
	public List<VnfInstance> get(final Servers servers, final MultiValueMap<String, String> requestParams) {
		final String filter = getSingleField(requestParams, "filter");
		// XXX A little bit short !
		return vnfInstanceService.query(filter);
	}

	@Override
	public VnfInstance post(final Servers servers, final String vnfdId, final String vnfInstanceName, final String vnfInstanceDescription) {
		VnfPackage vnfPkgInfo;
		try {
			vnfPkgInfo = vnfPackageService.findByVnfdId(UUID.fromString(vnfdId));
		} catch (final NotFoundException e) {
			vnfPkgInfo = onboardPackage(vnfdId);
		}
		ensureIsOnboarded(vnfPkgInfo);
		ensureIsEnabled(vnfPkgInfo);
		VnfInstance vnfInstance = VnfLcmFactory.createVnfInstance(vnfInstanceName, vnfInstanceDescription, vnfPkgInfo);
		mapper.map(vnfPkgInfo, vnfInstance);
		// VnfIdentifierCreationNotification NFVO + EM
		vnfInstance = vnfInstanceService.save(vnfInstance);
		eventManager.sendNotification(NotificationEvent.VNF_INSTANCE_CREATE, vnfInstance.getId(), Map.of());
		return vnfInstance;
	}

	private VnfPackage onboardPackage(final String vnfdId) {
		final VnfPackage vnfPkg = manoClientFactory.getClient().onbardedVnfPackage(UUID.fromString(vnfdId)).find();
		vnfPkg.setNfvoId(vnfPkg.getId().toString());
		vnfPkg.setOnboardingState(OnboardingStateType.CREATED);
		vnfPkg.setUsageState(PackageUsageState.NOT_IN_USE);
		vnfPkg.setId(null);
		final VnfPackage nPkg = vnfPackageService.save(vnfPkg);
		eventManager.sendActionVnfm(ActionType.VNF_PKG_ONBOARD_DOWNLOAD_INSTANTIATE, nPkg.getId(), Map.of());
		return nPkg;
	}

	@Transactional
	@Override
	public void delete(final Servers servers, @Nonnull final UUID vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(vnfInstanceId);
		ensureNotInstantiated(vnfInstance);
		ensureNotLocked(vnfInstance);
		planService.deleteByVnfInstance(vnfInstance);
		vnfInstanceService.delete(vnfInstanceId);
		if (!vnfInstanceService.isInstantiate(vnfInstance.getVnfPkg().getId())) {
			final VnfPackage vnfPkg = vnfPackageRepository.get(vnfInstance.getVnfPkg().getId());
			vnfPkg.setUsageState(PackageUsageState.NOT_IN_USE);
			vnfPackageRepository.save(vnfPkg);
		}
		// VnfIdentitifierDeletionNotification NFVO + EM
	}

	@Override
	public VnfBlueprint instantiate(final Servers servers, @Nonnull final UUID vnfInstanceId, final VnfInstantiate instantiateVnfRequest) {
		final VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(vnfInstanceId);
		ensureNotInstantiated(vnfInstance);
		ensureNotLocked(vnfInstance);
		final UUID vnfPkgId = vnfInstance.getVnfPkg().getId();
		final VnfPackage vnfPkg = vnfPackageRepository.get(vnfPkgId);
		ensureIsEnabled(vnfPkg);

		if (null != instantiateVnfRequest.getVimConnectionInfo()) {
			final List<VimConnectionInformation> vimconnections = mapper.mapAsList(instantiateVnfRequest.getVimConnectionInfo(), VimConnectionInformation.class);
			final Set<VimConnectionInformation> vimSet = vimconnections.stream()
					.map(x -> {
						if (null != x.getId()) {
							return vimManager.findVimById(x.getId());
						}
						final Optional<VimConnectionInformation> optVim = vimManager.findOptionalVimByVimId(x.getVimId());
						if (optVim.isPresent()) {
							return optVim.get();
						}
						return vimManager.save(x);
					}).collect(Collectors.toSet());
			vnfInstance.setVimConnectionInfo(vimSet);
			vnfInstanceService.save(vnfInstance);
		}

		VnfBlueprint blueprint = vnfLcmService.createIntatiateOpOcc(vnfInstance);
		mapper.map(instantiateVnfRequest, blueprint);
		blueprint.getParameters().setScaleStatus(extractScaleStaus(vnfPkg));
		blueprint = planService.save(blueprint);
		eventManager.sendActionVnfm(ActionType.VNF_INSTANTIATE, blueprint.getId(), new HashMap<>());
		LOG.info("VNF Instantiation Event Sucessfully sent. {}", blueprint.getId());
		return blueprint;
	}

	private Set<ScaleInfo> extractScaleStaus(final VnfPackage vnfPkg) {
		return vnfPkg.getVnfCompute().stream()
				.flatMap(x -> x.getScalingAspectDeltas().stream())
				.map(VnfComputeAspectDelta::getAspectName)
				.collect(Collectors.toSet())
				.stream()
				.map(x -> new ScaleInfo(x, 0))
				.collect(Collectors.toSet());
	}

	@Override
	public VnfBlueprint terminate(final Servers servers, @Nonnull final UUID vnfInstanceId, final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout) {
		final VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(vnfInstanceId);
		ensureInstantiated(vnfInstance);
		ensureNotLocked(vnfInstance);
		final VnfBlueprint blueprint = vnfLcmService.createTerminateOpOcc(vnfInstance);
		eventManager.sendActionVnfm(ActionType.VNF_TERMINATE, blueprint.getId(), new HashMap<>());
		LOG.info("Terminate sent for instancce: {}", vnfInstanceId);
		return blueprint;
	}

	@Override
	public VnfBlueprint scaleToLevel(final Servers servers, final UUID uuid, final VnfScaleToLevelRequest scaleVnfToLevelRequest) {
		final VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(uuid);
		ensureInstantiated(vnfInstance);
		ensureNotLocked(vnfInstance);
		final VnfBlueprint lcmOpOccs = vnfLcmService.createScaleToLevelOpOcc(vnfInstance, scaleVnfToLevelRequest);
		eventManager.sendActionVnfm(ActionType.VNF_SCALE_TO_LEVEL, lcmOpOccs.getId(), new HashMap<>());
		return lcmOpOccs;
	}

	@Override
	public VnfBlueprint scale(final Servers servers, final UUID uuid, final VnfScaleRequest scaleVnfRequest) {
		final VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(uuid);
		ensureInstantiated(vnfInstance);
		ensureNotLocked(vnfInstance);
		final VnfBlueprint lcmOpOccs = vnfLcmService.createScaleOpOcc(vnfInstance, scaleVnfRequest);
		eventManager.sendActionVnfm(ActionType.VNF_SCALE_TO_LEVEL, lcmOpOccs.getId(), new HashMap<>());
		return lcmOpOccs;
	}

	@Override
	public VnfBlueprint operate(final Servers servers, final UUID uuid, final VnfOperateRequest operateVnfRequest) {
		final VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(uuid);
		ensureInstantiated(vnfInstance);
		ensureNotLocked(vnfInstance);
		final VnfBlueprint lcmOpOccs = vnfLcmService.createOperateOpOcc(vnfInstance, operateVnfRequest);
		eventManager.sendActionVnfm(ActionType.VNF_OPERATE, lcmOpOccs.getId(), new HashMap<>());
		return lcmOpOccs;
	}

	@Override
	public VnfBlueprint vnfLcmOpOccsGet(final Servers servers, @NotNull final UUID id) {
		return vnfLcmService.findById(id);
	}

	@Override
	public VnfBlueprint changeExtConn(final Servers servers, @NotNull final UUID uuid, final ChangeExtVnfConnRequest cevcr) {
		final VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(uuid);
		ensureInstantiated(vnfInstance);
		ensureNotLocked(vnfInstance);
		final VnfBlueprint lcmOpOccs = vnfLcmService.createOperateOpOcc(vnfInstance, cevcr);
		eventManager.sendActionVnfm(ActionType.VNF_CHANGE_CONN, lcmOpOccs.getId(), new HashMap<>());
		return lcmOpOccs;
	}

	@Override
	public VnfInstance findById(final Servers servers, final String vnfInstance) {
		return vnfInstanceServiceVnfm.findById(getSafeUUID(vnfInstance));
	}

}
