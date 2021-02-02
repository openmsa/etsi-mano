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

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfComputeAspectDelta;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.BlueprintParameters;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;

public class VnfLcmFactory {

	// XXX Is it to be in LCM ?
	@Nonnull
	public static VnfInstance createVnfInstance(final String vnfInstanceName, final String vnfInstanceDescription, final VnfPackage vnfPkgInfo) {
		final VnfInstance vnfInstance = new VnfInstance();
		vnfInstance.setVnfPkg(vnfPkgInfo);
		vnfInstance.setVnfInstanceName(vnfInstanceName);
		vnfInstance.setVnfInstanceDescription(vnfInstanceDescription);
		final BlueprintParameters instantiatedVnfInfo = new BlueprintParameters();
		instantiatedVnfInfo.setState(OperationalStateType.STOPPED);
		vnfInstance.setInstantiationState(InstantiationState.NOT_INSTANTIATED);
		vnfInstance.setInstantiatedVnfInfo(instantiatedVnfInfo);
		final Set<ScaleInfo> scaleInfo = vnfPkgInfo.getVnfCompute().stream()
				.map(x -> x.getScalingAspectDeltas().stream()
						.map(VnfComputeAspectDelta::getAspectName)
						.distinct()
						.collect(Collectors.toList()))
				.flatMap(List::stream)
				.distinct()
				.map(x -> new ScaleInfo(x, Integer.valueOf(0)))
				.collect(Collectors.toSet());
		instantiatedVnfInfo.setScaleStatus(scaleInfo);
		return vnfInstance;
	}

	@Nonnull
	public static VnfBlueprint createVnfLcmOpOccs(final PlanOperationType operation, final UUID vnfInstanceId) {
		final VnfBlueprint vnfLcmOpOcc = new VnfBlueprint();
		vnfLcmOpOcc.setOperation(operation);
		final VnfInstance vnfInstance = new VnfInstance();
		vnfInstance.setId(vnfInstanceId);
		vnfLcmOpOcc.setVnfInstance(vnfInstance);
		vnfLcmOpOcc.setStateEnteredTime(new Date());
		vnfLcmOpOcc.setStartTime(new Date());
		vnfLcmOpOcc.setOperationStatus(OperationStatusType.STARTING);
		return vnfLcmOpOcc;
	}

	public static VnfBlueprint createVnfBlueprint(final PlanOperationType operation, final UUID vnfInstanceId) {
		final VnfBlueprint blueprint = new VnfBlueprint();
		blueprint.setOperation(operation);
		final VnfInstance vnfInstance = new VnfInstance();
		vnfInstance.setId(vnfInstanceId);
		blueprint.setVnfInstance(vnfInstance);
		blueprint.setStartTime(new Date());
		blueprint.setOperationStatus(OperationStatusType.NOT_STARTED);
		return blueprint;
	}

}
