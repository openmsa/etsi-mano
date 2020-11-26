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

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.nslcm.VnfLcmFactory;
import com.ubiqube.etsi.mano.dao.mano.OperateChanges;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.ScaleTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.BlueprintJpa;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;
import com.ubiqube.etsi.mano.repository.jpa.SearchQueryer;

@Service
public class VnfLcmService {

	private final BlueprintJpa planJpa;

	private final EntityManager em;

	public VnfLcmService(final BlueprintJpa _planJpa, final EntityManager _em) {
		planJpa = _planJpa;
		em = _em;
	}

	@Nonnull
	public Blueprint createIntatiateOpOcc(final VnfInstance vnfInstance) {
		return createIntatiateTerminateBlueprint(vnfInstance, PlanOperationType.INSTANTIATE);
	}

	@Nonnull
	public Blueprint createTerminateOpOcc(final VnfInstance vnfInstance) {
		return createIntatiateTerminateBlueprint(vnfInstance, PlanOperationType.TERMINATE);
	}

	@Nonnull
	private Blueprint createIntatiateTerminateBlueprint(final VnfInstance vnfInstance, final PlanOperationType state) {
		final Blueprint blueprint = VnfLcmFactory.createBlueprint(state, vnfInstance.getId());
		return planJpa.save(blueprint);
	}

	public Blueprint createScaleToLevelOpOcc(final VnfInstance vnfInstance, final VnfScaleToLevelRequest scaleVnfToLevelRequest) {
		final Blueprint lcmOpOccs = VnfLcmFactory.createVnfLcmOpOccs(PlanOperationType.SCALE_TO_LEVEL, vnfInstance.getId());
		lcmOpOccs.getParameters().setInstantiationLevelId(scaleVnfToLevelRequest.getInstantiationLevelId());
		if (scaleVnfToLevelRequest.getScaleInfo() != null) {
			final Set<ScaleInfo> scaleStatus = scaleVnfToLevelRequest.getScaleInfo().stream()
					.map(x -> new ScaleInfo(x.getAspectId(), x.getScaleLevel()))
					.collect(Collectors.toSet());
			lcmOpOccs.getParameters().setScaleStatus(scaleStatus);
		}
		return planJpa.save(lcmOpOccs);
	}

	public Blueprint createScaleOpOcc(final VnfInstance vnfInstance, final VnfScaleRequest scaleVnfRequest) {
		final Blueprint lcmOpOccs = VnfLcmFactory.createVnfLcmOpOccs(PlanOperationType.SCALE, vnfInstance.getId());
		lcmOpOccs.getParameters().setNumberOfSteps(scaleVnfRequest.getNumberOfSteps());
		lcmOpOccs.getParameters().setScaleType(scaleVnfRequest.getType());
		lcmOpOccs.getParameters().setAspectId(scaleVnfRequest.getAspectId());
		final Set<ScaleInfo> scaleStatus = vnfInstance.getInstantiatedVnfInfo().getScaleStatus().stream()
				.filter(x -> x.getAspectId().equals(scaleVnfRequest.getAspectId()))
				.map(x -> new ScaleInfo(x.getAspectId(), addDec(scaleVnfRequest.getType(), scaleVnfRequest.getNumberOfSteps(), x.getScaleLevel())))
				.collect(Collectors.toSet());
		lcmOpOccs.getParameters().setScaleStatus(scaleStatus);
		return planJpa.save(lcmOpOccs);
	}

	private static int addDec(@NotNull final ScaleTypeEnum type, final int numberOfSteps, final int scaleLevel) {
		switch (type) {
		case IN:
			return Math.max(0, scaleLevel - numberOfSteps);
		case OUT:
			return scaleLevel + numberOfSteps;
		default:
			throw new GenericException("Scaling value is incorrect: " + type);
		}
	}

	public Blueprint createOperateOpOcc(final VnfInstance vnfInstance, final VnfOperateRequest operateVnfRequest) {
		final Blueprint lcmOpOccs = VnfLcmFactory.createVnfLcmOpOccs(PlanOperationType.OPERATE, vnfInstance.getId());
		final OperateChanges opChanges = lcmOpOccs.getOperateChanges();
		opChanges.setTerminationType(OperationalStateType.fromValue(operateVnfRequest.getChangeStateTo().toString()));
		opChanges.setGracefulTerminationTimeout(operateVnfRequest.getGracefulStopTimeout());
		return planJpa.save(lcmOpOccs);
	}

	public List<Blueprint> query(final String filter) {
		final SearchQueryer sq = new SearchQueryer(em);
		return (List<Blueprint>) sq.getCriteria(filter, Blueprint.class)
				.getResultStream().collect(Collectors.toList());
	}

	public Blueprint findById(final UUID id) {
		return planJpa.findById(id).orElseThrow();
	}
}
