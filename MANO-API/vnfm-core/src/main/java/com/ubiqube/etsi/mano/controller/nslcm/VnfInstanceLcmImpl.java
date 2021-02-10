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
import static com.ubiqube.etsi.mano.Constants.getSingleField;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.controller.lcmgrant.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.VnfBlueprintService;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfLcmService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mano.service.vim.VimManager;

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

	public VnfInstanceLcmImpl(final VnfPackageRepository vnfPackageRepository, final EventManager _eventManager, final MapperFacade _mapper, final VnfLcmService _vnfLcmService, final VnfInstanceService _vnfInstanceService, final VimManager _vimManager, final VnfBlueprintService _planService, final VnfPackageService _vnfPackageService) {
		super();
		this.vnfPackageRepository = vnfPackageRepository;
		eventManager = _eventManager;
		mapper = _mapper;
		vnfLcmService = _vnfLcmService;
		vnfInstanceService = _vnfInstanceService;
		vimManager = _vimManager;
		planService = _planService;
		vnfPackageService = _vnfPackageService;
	}

	@Override
	public List<VnfInstance> get(final MultiValueMap<String, String> requestParams) {
		final String filter = getSingleField(requestParams, "filter");
		// XXX A little bit short !
		return vnfInstanceService.query(filter);
	}

	@Override
	public VnfInstance post(final String vnfdId, final String vnfInstanceName, final String vnfInstanceDescription) {
		final VnfPackage vnfPkgInfo = vnfPackageService.findByVnfdId(UUID.fromString(vnfdId));
		ensureIsOnboarded(vnfPkgInfo);
		ensureIsEnabled(vnfPkgInfo);
		VnfInstance vnfInstance = VnfLcmFactory.createVnfInstance(vnfInstanceName, vnfInstanceDescription, vnfPkgInfo);
		mapper.map(vnfPkgInfo, vnfInstance);
		// VnfIdentifierCreationNotification NFVO + EM
		vnfInstance = vnfInstanceService.save(vnfInstance);
		eventManager.sendNotification(NotificationEvent.VNF_INSTANCE_CREATE, vnfInstance.getId());
		return vnfInstance;
	}

	@Override
	public void delete(@Nonnull final UUID vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstanceService.findById(vnfInstanceId);
		ensureNotInstantiated(vnfInstance);

		if (vnfInstanceService.isInstantiate(vnfInstance.getVnfPkg().getId())) {
			final VnfPackage vnfPkg = vnfPackageRepository.get(vnfInstance.getVnfPkg().getId());
			vnfPkg.setUsageState(PackageUsageState.NOT_IN_USE);
			vnfPackageRepository.save(vnfPkg);
		}
		vnfInstanceService.delete(vnfInstanceId);
		// VnfIdentitifierDeletionNotification NFVO + EM
	}

	@Override
	public VnfBlueprint instantiate(@Nonnull final UUID vnfInstanceId, final VnfInstantiate instantiateVnfRequest) {
		final VnfInstance vnfInstance = vnfInstanceService.findById(vnfInstanceId);
		ensureNotInstantiated(vnfInstance);

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
		blueprint = planService.save(blueprint);
		eventManager.sendActionVnfm(ActionType.VNF_INSTANTIATE, blueprint.getId(), new HashMap<>());
		LOG.info("VNF Instantiation Event Sucessfully sent. {}", blueprint.getId());
		return blueprint;
	}

	@Override
	public VnfBlueprint terminate(@Nonnull final UUID vnfInstanceId, final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout) {
		final VnfInstance vnfInstance = vnfInstanceService.findById(vnfInstanceId);
		ensureInstantiated(vnfInstance);
		final VnfBlueprint blueprint = vnfLcmService.createTerminateOpOcc(vnfInstance);
		eventManager.sendActionVnfm(ActionType.VNF_TERMINATE, blueprint.getId(), new HashMap<>());
		LOG.info("Terminate sent for instancce: {}", vnfInstanceId);
		return blueprint;
	}

	@Override
	public VnfBlueprint scaleToLevel(final UUID uuid, final VnfScaleToLevelRequest scaleVnfToLevelRequest) {
		final VnfInstance vnfInstance = vnfInstanceService.findById(uuid);
		ensureInstantiated(vnfInstance);
		final VnfBlueprint lcmOpOccs = vnfLcmService.createScaleToLevelOpOcc(vnfInstance, scaleVnfToLevelRequest);
		eventManager.sendActionVnfm(ActionType.VNF_SCALE_TO_LEVEL, lcmOpOccs.getId(), new HashMap<>());
		return lcmOpOccs;
	}

	@Override
	public VnfBlueprint scale(final UUID uuid, final VnfScaleRequest scaleVnfRequest) {
		final VnfInstance vnfInstance = vnfInstanceService.findById(uuid);
		ensureInstantiated(vnfInstance);
		final VnfBlueprint lcmOpOccs = vnfLcmService.createScaleOpOcc(vnfInstance, scaleVnfRequest);
		eventManager.sendActionVnfm(ActionType.VNF_SCALE_TO_LEVEL, lcmOpOccs.getId(), new HashMap<>());
		return lcmOpOccs;
	}

	@Override
	public VnfBlueprint operate(final UUID uuid, final VnfOperateRequest operateVnfRequest) {
		final VnfInstance vnfInstance = vnfInstanceService.findById(uuid);
		ensureInstantiated(vnfInstance);
		final VnfBlueprint lcmOpOccs = vnfLcmService.createOperateOpOcc(vnfInstance, operateVnfRequest);
		eventManager.sendActionVnfm(ActionType.VNF_OPERATE, lcmOpOccs.getId(), new HashMap<>());
		return lcmOpOccs;
	}

	@Override
	public VnfBlueprint vnfLcmOpOccsGet(@NotNull final UUID id) {
		return vnfLcmService.findById(id);
	}

}
