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
package com.ubiqube.etsi.mano.nfvo.controller.nslcm;

import static com.ubiqube.etsi.mano.Constants.ensureInstantiated;
import static com.ubiqube.etsi.mano.Constants.ensureNotInstantiated;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.dao.mano.alarm.ResourceHandle;
import com.ubiqube.etsi.mano.dao.mano.dto.nsi.NsInstanceDto;
import com.ubiqube.etsi.mano.dao.mano.dto.nsi.NsVirtualLinkInfoDto;
import com.ubiqube.etsi.mano.dao.mano.dto.nsi.VnfInstanceDto;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLinkTask;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVnfTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.nfvo.factory.LcmFactory;
import com.ubiqube.etsi.mano.nfvo.jpa.NsLiveInstanceJpa;
import com.ubiqube.etsi.mano.nfvo.jpa.NsdPackageJpa;
import com.ubiqube.etsi.mano.nfvo.service.NsInstanceService;
import com.ubiqube.etsi.mano.service.NsBlueprintService;
import com.ubiqube.etsi.mano.service.VnfInstanceGatewayService;

import ma.glasnost.orika.MapperFacade;

@Service
public class NsInstanceControllerImpl implements NsInstanceController {
	private final NsBlueprintService blueprintService;
	private final NsInstanceService nsInstanceService;
	private final MapperFacade mapper;
	private final VnfInstanceGatewayService vnfInstancesService;
	private final NsLiveInstanceJpa nsLiveInstanceJpa;
	private final NsdPackageJpa nsdPackageJpa;

	public NsInstanceControllerImpl(final NsInstanceService _nsInstanceService, final NsBlueprintService _lcmOpOccsService, final NsLiveInstanceJpa nsLiveInstanceJpa,
			final MapperFacade mapper, final VnfInstanceGatewayService vnfInstancesService, final NsdPackageJpa nsdPackageJpa) {
		nsInstanceService = _nsInstanceService;
		blueprintService = _lcmOpOccsService;
		this.nsLiveInstanceJpa = nsLiveInstanceJpa;
		this.mapper = mapper;
		this.vnfInstancesService = vnfInstancesService;
		this.nsdPackageJpa = nsdPackageJpa;
	}

	@Override
	public List<NsdInstance> nsInstancesGet(final String filter) {
		return nsInstanceService.query(filter);
	}

	@Override
	public void nsInstancesNsInstanceIdDelete(final UUID id) {
		final NsdInstance nsInstanceDb = nsInstanceService.findById(id);
		ensureNotInstantiated(nsInstanceDb);
		nsInstanceService.delete(id);
		if (!nsInstanceService.isInstantiated(nsInstanceDb.getNsdInfo())) {
			final NsdPackage nsPkg = nsdPackageJpa.findById(nsInstanceDb.getNsdInfo().getId()).orElseThrow(() -> new GenericException("Could not find NSD " + nsInstanceDb.getNsdInfo().getId()));
			nsPkg.setNsdUsageState(PackageUsageState.NOT_IN_USE);
			nsdPackageJpa.save(nsPkg);
		}
	}

	@Override
	public NsInstanceDto nsInstancesNsInstanceIdGet(final UUID id) {
		final NsdInstance ret = nsInstanceService.findById(id);

		final NsInstanceDto dto = mapper.map(ret.getNsdInfo(), NsInstanceDto.class);
		mapper.map(ret, dto);
		final List<NsLiveInstance> vnfs = nsLiveInstanceJpa.findByNsdInstanceAndClass(ret, NsVnfTask.class.getSimpleName());
		final List<VnfInstanceDto> vnfInstance = vnfs.stream()
				.map(x -> vnfInstancesService.findById(UUID.fromString(x.getResourceId())))
				.map(x -> mapper.map(x, VnfInstanceDto.class))
				.toList();
		dto.setVnfInstance(vnfInstance);
		final List<NsLiveInstance> vls = nsLiveInstanceJpa.findByNsdInstanceAndClass(ret, NsVirtualLinkTask.class.getSimpleName());
		final List<NsVirtualLinkInfoDto> vlsDto = vls.stream().map(x -> {
			final NsVirtualLinkInfoDto vlDto = new NsVirtualLinkInfoDto();

			vlDto.setId(x.getId().toString());
			vlDto.setNsVirtualLinkDescId(x.getNsTask().getToscaName());
			// vlDto.setNsVirtualLinkProfileId(nsVirtualLinkProfileId);
			final List<ResourceHandle> resourceHandle = new ArrayList<>();
			final ResourceHandle r = mapper.map(x, ResourceHandle.class);
			resourceHandle.add(r);
			vlDto.setResourceHandle(resourceHandle);
			return vlDto;
		}).toList();
		dto.setVirtualLinkInfo(vlsDto);
		return dto;
	}

	@Override
	public NsdInstance nsInstancesNsInstanceIdHealPost(final UUID id) {
		final NsdInstance nsInstance = nsInstanceService.findById(id);
		ensureInstantiated(nsInstance);
		final NsBlueprint lcmOpOccs = LcmFactory.createNsLcmOpOcc(nsInstance, PlanOperationType.HEAL);
		blueprintService.save(lcmOpOccs);
		throw new GenericException("TODO");
	}

	@Override
	public NsdInstance nsInstancesNsInstanceIdScalePost(final UUID id) {
		final NsdInstance nsInstanceDb = nsInstanceService.findById(id);
		ensureInstantiated(nsInstanceDb);
		final NsBlueprint lcmOpOccs = LcmFactory.createNsLcmOpOcc(nsInstanceDb, PlanOperationType.SCALE);
		blueprintService.save(lcmOpOccs);
		throw new GenericException("TODO");
	}

	@Override
	public void nsInstancesNsInstanceIdUpdatePost(final UUID id) {
		final NsdInstance nsInstanceDb = nsInstanceService.findById(id);
		ensureInstantiated(nsInstanceDb);
		final NsBlueprint lcmOpOccs = LcmFactory.createNsLcmOpOcc(nsInstanceDb, PlanOperationType.UPDATE);
		blueprintService.save(lcmOpOccs);
		throw new GenericException("TODO");
	}
}
