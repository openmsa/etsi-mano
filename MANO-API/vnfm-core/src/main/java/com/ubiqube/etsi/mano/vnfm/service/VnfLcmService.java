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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.OperateChanges;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.ScaleTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.dao.mano.vnfi.ChangeExtVnfConnRequest;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;
import com.ubiqube.etsi.mano.repository.jpa.SearchQueryer;
import com.ubiqube.etsi.mano.vnfm.controller.vnflcm.VnfLcmFactory;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfBlueprintJpa;

@Service
public class VnfLcmService {

	private final VnfBlueprintJpa planJpa;

	private final EntityManager em;

	private final VnfInstanceService vnfInstancesService;

	public VnfLcmService(final VnfBlueprintJpa _planJpa, final EntityManager _em, final VnfInstanceService vnfInstancesService) {
		planJpa = _planJpa;
		em = _em;
		this.vnfInstancesService = vnfInstancesService;
	}

	@Nonnull
	public VnfBlueprint createIntatiateOpOcc(final VnfInstance vnfInstance) {
		return createIntatiateTerminateVnfBlueprint(vnfInstance, PlanOperationType.INSTANTIATE);
	}

	@Nonnull
	public VnfBlueprint createTerminateOpOcc(final VnfInstance vnfInstance) {
		return createIntatiateTerminateVnfBlueprint(vnfInstance, PlanOperationType.TERMINATE);
	}

	@Nonnull
	private VnfBlueprint createIntatiateTerminateVnfBlueprint(final VnfInstance vnfInstance, final PlanOperationType state) {
		final VnfBlueprint VnfBlueprint = VnfLcmFactory.createVnfBlueprint(state, vnfInstance.getId());
		return planJpa.save(VnfBlueprint);
	}

	public VnfBlueprint createScaleToLevelOpOcc(final VnfInstance vnfInstance, final VnfScaleToLevelRequest scaleVnfToLevelRequest) {
		final VnfBlueprint lcmOpOccs = VnfLcmFactory.createVnfLcmOpOccs(PlanOperationType.SCALE_TO_LEVEL, vnfInstance.getId());
		lcmOpOccs.getParameters().setInstantiationLevelId(scaleVnfToLevelRequest.getInstantiationLevelId());
		if (scaleVnfToLevelRequest.getScaleInfo() != null) {
			final Set<ScaleInfo> scaleStatus = scaleVnfToLevelRequest.getScaleInfo().stream()
					.map(x -> new ScaleInfo(x.getAspectId(), x.getScaleLevel()))
					.collect(Collectors.toSet());
			lcmOpOccs.getParameters().setScaleStatus(scaleStatus);
		}
		return planJpa.save(lcmOpOccs);
	}

	public VnfBlueprint createScaleOpOcc(final VnfInstance vnfInstance, final VnfScaleRequest scaleVnfRequest) {
		final VnfBlueprint lcmOpOccs = VnfLcmFactory.createVnfLcmOpOccs(PlanOperationType.SCALE, vnfInstance.getId());
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

	public VnfBlueprint createOperateOpOcc(final VnfInstance vnfInstance, final VnfOperateRequest operateVnfRequest) {
		final VnfBlueprint lcmOpOccs = VnfLcmFactory.createVnfLcmOpOccs(PlanOperationType.OPERATE, vnfInstance.getId());
		final OperateChanges opChanges = lcmOpOccs.getOperateChanges();
		opChanges.setTerminationType(OperationalStateType.fromValue(operateVnfRequest.getChangeStateTo().toString()));
		opChanges.setGracefulTerminationTimeout(operateVnfRequest.getGracefulStopTimeout());
		final List<VnfLiveInstance> instantiatedCompute = vnfInstancesService.getLiveComputeInstanceOf(vnfInstance);
		instantiatedCompute.forEach(x -> {
			final VnfTask affectedCompute = copyInstantiedResource(x, new ComputeTask(), lcmOpOccs);
			lcmOpOccs.addTask(affectedCompute);
		});
		return planJpa.save(lcmOpOccs);
	}

	public List<VnfBlueprint> query(final String filter) {
		final SearchQueryer sq = new SearchQueryer(em);
		return sq.getCriteria(filter, VnfBlueprint.class);
	}

	public VnfBlueprint findById(final UUID id) {
		return planJpa.findById(id).orElseThrow(() -> new NotFoundException("Could not find VNF LCM operation: " + id));
	}

	public VnfBlueprint createOperateOpOcc(final VnfInstance vnfInstance, final ChangeExtVnfConnRequest cevcr) {
		final VnfBlueprint lcmOpOccs = VnfLcmFactory.createVnfLcmOpOccs(PlanOperationType.CHANGE_EXTERNAL_VNF_CONNECTIVITY, vnfInstance.getId());
		lcmOpOccs.setChangeExtVnfConnRequest(cevcr);
		return planJpa.save(lcmOpOccs);
	}

	private static <T extends VnfTask> T copyInstantiedResource(final VnfLiveInstance x, final T task, final VnfBlueprint blueprint) {
		task.setChangeType(ChangeType.REMOVED);
		task.setStatus(PlanStatusType.STARTED);
		task.setBlueprint(blueprint);
		task.setStartDate(LocalDateTime.now());
		task.setToscaName(x.getTask().getToscaName());
		task.setVimResourceId(x.getResourceId());
		return task;
	}
}
