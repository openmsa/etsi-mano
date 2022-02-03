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
package com.ubiqube.etsi.mano.vnfm.service;

import java.util.UUID;

import javax.annotation.Nonnull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfBlueprintJpa;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfLiveInstanceJpa;

@Service
public class VnfBlueprintService {
	private final VnfBlueprintJpa blueprintJpa;
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public VnfBlueprintService(final VnfBlueprintJpa blueprintJpa, final VnfLiveInstanceJpa vnfLiveInstanceJpa) {
		this.blueprintJpa = blueprintJpa;
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
	}

	public int getNumberOfLiveVl(final VnfInstance vnfInstance, final VnfVl x) {
		return vnfLiveInstanceJpa.countByVnfInstanceAndTaskToscaName(vnfInstance, x.getToscaName());
	}

	public int getNumberOfLiveInstance(final VnfInstance vnfInstance, final VnfCompute vnfCompute) {
		return vnfLiveInstanceJpa.countByVnfInstanceAndTaskToscaName(vnfInstance, vnfCompute.getToscaName());
	}

	@Nonnull
	public VnfBlueprint save(final VnfBlueprint plan) {
		return blueprintJpa.save(plan);
	}

	public VnfBlueprint updateState(final VnfBlueprint plan, final OperationStatusType processing) {
		plan.setOperationStatus(processing);
		return save(plan);
	}

	@Nonnull
	public VnfBlueprint findById(final UUID blueprintId) {
		return blueprintJpa.findById(blueprintId).orElseThrow(() -> new GenericException("Blueprint not found " + blueprintId));
	}

	public void deleteByVnfInstance(final VnfInstance vnfInstance) {
		blueprintJpa.deleteByVnfInstance(vnfInstance);
	}

}
