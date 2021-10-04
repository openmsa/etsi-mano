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
package com.ubiqube.etsi.mano.service;

import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

import com.ubiqube.etsi.mano.dao.mano.BlueZoneGroupInformation;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.GrantVimAssetsEntity;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VimComputeResourceFlavourEntity;
import com.ubiqube.etsi.mano.dao.mano.VimSoftwareImageEntity;
import com.ubiqube.etsi.mano.dao.mano.VimTask;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.dto.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.dto.VnfGrantsRequest;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.exception.NotFoundException;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public abstract class AbstractGrantService implements VimResourceService {

	private final MapperFacade mapper;

	private final ResourceAllocate nfvo;

	protected AbstractGrantService(final MapperFacade _mapper, final ResourceAllocate _nfvo) {
		mapper = _mapper;
		nfvo = _nfvo;
	}

	@Override
	public final void allocate(final Blueprint plan) {
		final VnfGrantsRequest grantRequest = mapper.map(plan, VnfGrantsRequest.class);
		final Predicate<? super VimTask> isManoClass = x -> (x.getType() == ResourceTypeEnum.COMPUTE) ||
				(x.getType() == ResourceTypeEnum.LINKPORT) ||
				(x.getType() == ResourceTypeEnum.STORAGE) ||
				(x.getType() == ResourceTypeEnum.VL);
		plan.getTasks().stream()
				.filter(isManoClass)
				.forEach(xx -> {
					final VimTask x = (VimTask) xx;
					if (x.getChangeType() == ChangeType.ADDED) {
						grantRequest.getAddResources().add(mapper.map(x, GrantInformation.class));
					} else {
						grantRequest.getRemoveResources().add(mapper.map(x, GrantInformation.class));
					}
				});
		final GrantResponse grantsResp = nfvo.sendSyncGrantRequest(grantRequest);
		// Merge resources.
		grantsResp.getAddResources().forEach(x -> {
			// Get VNFM Grant Resource information ID.
			final UUID grantUuid = UUID.fromString(x.getResourceDefinitionId());
			final VimTask task = findTask(plan, grantUuid);
			task.setVimReservationId(x.getReservationId());
			task.setResourceGroupId(x.getResourceGroupId());
			task.setZoneId(x.getZoneId());
			task.setResourceProviderId(x.getResourceProviderId());
			task.setVimConnectionId(x.getVimConnectionId());
		});
		grantsResp.getVimConnections().forEach(x -> plan.addVimConnection(x));
		plan.setZoneGroups(mapper.mapAsSet(grantsResp.getZoneGroups(), BlueZoneGroupInformation.class));
		plan.setZones(grantsResp.getZones());
		plan.setExtManagedVirtualLinks(grantsResp.getExtManagedVirtualLinks());
		plan.setGrantsRequestId(grantsResp.getId().toString());
		mapVimAsset(plan.getTasks(), grantsResp.getVimAssets());
	}

	private static void mapVimAsset(final Set<VimTask> tasks, final GrantVimAssetsEntity vimAssets) {
		tasks.stream()
				.filter(x -> x instanceof ComputeTask)
				.filter(x -> x.getChangeType() != ChangeType.REMOVED)
				.map(ComputeTask.class::cast)
				.forEach(x -> {
					x.setFlavorId(findFlavor(vimAssets, x.getVnfCompute().getId()));
					x.setImageId(findImage(vimAssets, x.getVnfCompute().getId()));
				});
	}

	private static String findImage(final GrantVimAssetsEntity vimAssets, final UUID vduId) {
		return vimAssets.getSoftwareImages().stream()
				.filter(x -> x.getVnfdSoftwareImageId().equals(vduId.toString()))
				.map(VimSoftwareImageEntity::getVimSoftwareImageId)
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Could not find Image for vdu: " + vduId));

	}

	private static String findFlavor(final GrantVimAssetsEntity vimAssets, final UUID vduId) {
		return vimAssets.getComputeResourceFlavours().stream()
				.filter(x -> x.getVnfdVirtualComputeDescId().equals(vduId.toString()))
				.map(VimComputeResourceFlavourEntity::getVimFlavourId)
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Could not find flavor for vdu: " + vduId));
	}

	private static VimTask findTask(final Blueprint<VimTask, VnfInstance> plan, final UUID grantUuid) {
		return plan.getTasks().stream()
				.filter(x -> x.getId().compareTo(grantUuid) == 0)
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Could not find task: " + grantUuid));
	}
}
