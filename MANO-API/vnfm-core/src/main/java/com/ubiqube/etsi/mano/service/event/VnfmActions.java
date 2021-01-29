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
package com.ubiqube.etsi.mano.service.event;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstanceScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.service.ManoGrantService;
import com.ubiqube.etsi.mano.service.VimResourceService;
import com.ubiqube.etsi.mano.service.VnfBlueprintService;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.VnfReport;
import com.ubiqube.etsi.mano.service.graph.VnfWorkflow;
import com.ubiqube.etsi.mano.service.graph.vnfm.VnfParameters;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfmActions {
	private static final Logger LOG = LoggerFactory.getLogger(VnfmActions.class);

	private final VimManager vimManager;

	private final EventManager eventManager;

	private final VnfInstanceService vnfInstancesService;

	private final VnfPackageService vnfPackageService;

	private final VnfWorkflow vnfWorkflow;
	private final VnfBlueprintService blueprintService;
	private final VimResourceService vimResourceService;

	private VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public VnfmActions(final VimManager _vimManager, final VnfPackageService _vnfPackageService, final EventManager _eventManager, final VnfInstanceService _vnfInstancesService, final VnfWorkflow _planner, final VnfBlueprintService _blueprintService, final ManoGrantService _vimResourceService) {
		super();
		vimManager = _vimManager;
		vnfPackageService = _vnfPackageService;
		eventManager = _eventManager;
		vnfInstancesService = _vnfInstancesService;
		vnfWorkflow = _planner;
		blueprintService = _blueprintService;
		vimResourceService = _vimResourceService;
	}

	public void vnfInstantiate(@Nonnull final UUID blueprintId) {
		Thread.currentThread().setName(blueprintId + "-VI");
		final VnfBlueprint blueprint = blueprintService.findById(blueprintId);
		final VnfInstance vnfInstance = vnfInstancesService.findById(blueprint.getVnfInstance().getId());
		try {
			instantiateInnerv2(blueprint, vnfInstance);
			LOG.info("Instantiate {} Success...", blueprintId);
		} catch (final RuntimeException e) {
			LOG.error("VNF Instantiate Failed", e);
			vnfInstance.setInstantiationState(InstantiationState.NOT_INSTANTIATED);
			blueprint.setOperationStatus(OperationStatusType.FAILED);
			blueprint.setError(new FailureDetails(500L, e.getMessage()));
			blueprintService.save(blueprint);
			vnfInstancesService.save(vnfInstance);
		}
	}

	public void instantiateInnerv2(final VnfBlueprint blueprint, final VnfInstance vnfInstance) {
		if (null == blueprint.getParameters().getInstantiationLevelId()) {
			blueprint.getParameters().setInstantiationLevelId(vnfInstance.getInstantiatedVnfInfo().getInstantiationLevelId());
		}
		final VnfPackage vnfPkg = vnfPackageService.findById(vnfInstance.getVnfPkg());
		final Set<ScaleInfo> newScale = merge(blueprint, vnfInstance);
		vnfWorkflow.setWorkflowBlueprint(vnfPkg, blueprint, newScale);
		VnfBlueprint localPlan = blueprintService.save(blueprint);
		eventManager.sendNotification(NotificationEvent.VNF_INSTANTIATE, vnfInstance.getId());
		vimResourceService.allocate(localPlan);
		localPlan = blueprintService.updateState(localPlan, OperationStatusType.PROCESSING);
		//////////////////////////////////
		// XXX Multiple Vim ?
		final VimConnectionInformation vimConnection = localPlan.getVimConnections().iterator().next();
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		//
		final VnfParameters vparams = new VnfParameters(vimConnection, vim, vnfLiveInstanceJpa, new HashMap<>(), null);
		final VnfReport removeResults = vnfWorkflow.execDelete(localPlan, vparams);
		setLiveSatus(localPlan, vnfInstance, removeResults);
		// Create plan
		final VnfParameters params = buildContext(vimConnection, vim, localPlan, vnfInstance);
		final VnfReport createResults = vnfWorkflow.execCreate(localPlan, params);
		setLiveSatus(localPlan, vnfInstance, createResults);
		//
		setResultLcmInstance(localPlan, createResults);
		if (OperationStatusType.COMPLETED == localPlan.getOperationStatus()) {
			setInstanceStatus(vnfInstance, localPlan, newScale);
		}
		// XXX ??? error duplicate key in NSD.
		vnfInstance.setVimConnectionInfo(null);
		vnfInstancesService.save(vnfInstance);
		LOG.info("Saving VNF LCM OP OCCS.");
		localPlan = blueprintService.save(localPlan);
		// XXX Send COMPLETED event.
		LOG.info("VNF instance {} / LCM {} Finished.", vnfInstance.getId(), localPlan.getId());

	}

	private static void setInstanceStatus(final VnfInstance vnfInstance, final VnfBlueprint localPlan, final Set<ScaleInfo> newScale) {
		Optional.ofNullable(localPlan.getParameters().getScaleStatus())
				.map(x -> x.stream()
						.map(y -> new VnfInstanceScaleInfo(y.getAspectId(), y.getScaleLevel()))
						.collect(Collectors.toSet()))
				.ifPresent(x -> vnfInstance.getInstantiatedVnfInfo().setScaleStatus(x));
		LOG.info("Saving VNF Instance.");
		vnfInstance.getInstantiatedVnfInfo().setInstantiationLevelId(localPlan.getParameters().getInstantiationLevelId());
		if (null != localPlan.getParameters().getFlavourId()) {
			vnfInstance.getInstantiatedVnfInfo().setFlavourId(localPlan.getParameters().getFlavourId());
		}
		// XXX Copy new ScaleInfo.
		removeScaleScatus(vnfInstance, newScale);
	}

	private VnfParameters buildContext(final VimConnectionInformation vimConnection, final Vim vim, final VnfBlueprint blueprint, final VnfInstance vnfInstance) {
		final Map<String, String> context = blueprint.getParameters().getExtManagedVirtualLinks().stream()
				.collect(Collectors.toMap(ExtManagedVirtualLinkDataEntity::getVnfVirtualLinkDescId, ExtManagedVirtualLinkDataEntity::getResourceId));
		// Add all present VL if any.
		context.putAll(getLiveVl(vnfInstance));
		return new VnfParameters(vimConnection, vim, vnfLiveInstanceJpa, context, null);
	}

	private static Set<ScaleInfo> merge(final VnfBlueprint plan, final VnfInstance vnfInstance) {
		final Set<ScaleInfo> tmp = vnfInstance.getInstantiatedVnfInfo().getScaleStatus().stream()
				.filter(x -> notIn(x.getAspectId(), plan.getParameters().getScaleStatus()))
				.map(x -> new ScaleInfo(x.getAspectId(), x.getScaleLevel()))
				.collect(Collectors.toSet());
		tmp.addAll(plan.getParameters().getScaleStatus());
		return tmp;
	}

	private static void removeScaleScatus(final VnfInstance localVnfInstance, final Set<ScaleInfo> newScale) {
		final Set<VnfInstanceScaleInfo> scales = localVnfInstance.getInstantiatedVnfInfo().getScaleStatus();
		newScale.stream()
				.forEach(x -> find(scales, x.getAspectId()).ifPresent(scales::remove));
		final Set<VnfInstanceScaleInfo> newScalings = newScale.stream()
				.map(x -> new VnfInstanceScaleInfo(x.getAspectId(), x.getScaleLevel()))
				.collect(Collectors.toSet());
		scales.addAll(newScalings);
	}

	private static Optional<VnfInstanceScaleInfo> find(final Set<VnfInstanceScaleInfo> scales, final String aspectId) {
		return scales.stream()
				.filter(x -> x.getAspectId().equals(aspectId))
				.findFirst();
	}

	private static boolean notIn(final String aspectId, final Set<ScaleInfo> scaleInfos) {
		return scaleInfos.stream()
				.noneMatch(x -> x.getAspectId().equals(aspectId));
	}

	private Map<String, String> getLiveVl(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> res = vnfInstancesService.getLiveVirtualLinkInstanceOf(vnfInstance);
		return res.stream()
				.collect(Collectors.toMap(x -> x.getTask().getToscaName(), x -> x.getTask().getVimResourceId()));
	}

	private static void setResultLcmInstance(@NotNull final VnfBlueprint blueprint, final VnfReport createResults) {
		if (createResults.getErrored().isEmpty()) {
			blueprint.setOperationStatus(OperationStatusType.COMPLETED);
		} else {
			blueprint.setOperationStatus(OperationStatusType.FAILED);
		}
		blueprint.setStateEnteredTime(new Date());
	}

	public void vnfTerminate(@Nonnull final UUID blueprintId) {
		Thread.currentThread().setName(blueprintId + "-VT");
		final VnfBlueprint blueprint = blueprintService.findById(blueprintId);
		final VnfInstance vnfInstance = vnfInstancesService.findById(blueprint.getVnfInstance().getId());
		try {
			instantiateInnerv2(blueprint, vnfInstance);
			LOG.info("Terminate {} Success...", blueprintId);
		} catch (final RuntimeException e) {
			LOG.error("VNF Instantiate Failed", e);
			blueprint.setOperationStatus(OperationStatusType.FAILED);
			blueprint.setError(new FailureDetails(500L, e.getMessage()));
			blueprint.setStateEnteredTime(new Date());
			blueprintService.save(blueprint);
			vnfInstancesService.save(vnfInstance);
		}
	}

	public void scaleToLevel(@NotNull final UUID blueprintId) {
		Thread.currentThread().setName(blueprintId + "-SL");
		final VnfBlueprint blueprint = blueprintService.findById(blueprintId);
		final VnfInstance vnfInstance = vnfInstancesService.findById(blueprint.getVnfInstance().getId());
		try {
			instantiateInnerv2(blueprint, vnfInstance);
			LOG.info("Scale to level {} Success...", blueprintId);
		} catch (final RuntimeException e) {
			LOG.error("VNF Scale to level Failed", e);
			blueprint.setOperationStatus(OperationStatusType.FAILED);
			blueprint.setError(new FailureDetails(500L, e.getMessage()));
			blueprint.setStateEnteredTime(new Date());
			blueprintService.save(blueprint);
		}
		eventManager.sendNotification(NotificationEvent.VNF_SCALE, blueprintId);
	}

	public void scale(@NotNull final UUID blueprintId) {
		Thread.currentThread().setName(blueprintId + "-SS");
		final VnfBlueprint blueprint = blueprintService.findById(blueprintId);
		final VnfInstance vnfInstance = vnfInstancesService.findById(blueprint.getVnfInstance().getId());
		try {
			instantiateInnerv2(blueprint, vnfInstance);
			LOG.info("Scale to level {} Success...", blueprintId);
		} catch (final RuntimeException e) {
			LOG.error("VNF Scale to level Failed", e);
			blueprint.setOperationStatus(OperationStatusType.FAILED);
			blueprint.setError(new FailureDetails(500L, e.getMessage()));
			blueprint.setStateEnteredTime(new Date());
			blueprintService.save(blueprint);
		}
		eventManager.sendNotification(NotificationEvent.VNF_SCALE, blueprintId);
	}

	public void vnfOperate(@NotNull final UUID blueprintId) {
		final VnfBlueprint blueprint = blueprintService.findById(blueprintId);
		final VnfInstance vnfInstance = vnfInstancesService.findById(blueprint.getVnfInstance().getId());
		// XXX Move this to controller.
		final List<VnfLiveInstance> instantiatedCompute = vnfInstancesService.getLiveComputeInstanceOf(vnfInstance);
		instantiatedCompute.forEach(x -> {
			final VnfTask affectedCompute = copyInstantiedResource(x, new ComputeTask(), blueprint);
			blueprint.addTask(affectedCompute);
		});
		final VnfBlueprint localBlueprint = blueprintService.save(blueprint);
		final VimConnectionInformation vimConnection = vnfInstance.getVimConnectionInfo().iterator().next();
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		localBlueprint.getTasks().forEach(x -> {
			if (blueprint.getOperateChanges().getTerminationType() == OperationalStateType.STARTED) {
				vim.startServer(vimConnection, x.getVimResourceId());
				vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STARTED);
			} else {
				vim.stopServer(vimConnection, x.getVimResourceId());
				vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STOPPED);
			}
		});
		vnfInstancesService.save(vnfInstance);
	}

	private static <T extends VnfTask> T copyInstantiedResource(final VnfLiveInstance x, final T task, final VnfBlueprint blueprint) {
		task.setChangeType(ChangeType.REMOVED);
		task.setStatus(PlanStatusType.STARTED);
		task.setBlueprint(blueprint);
		task.setStartDate(LocalDateTime.now());
		task.setToscaName(x.getTask().getToscaName());
		task.setVimResourceId(x.getTask().getVimResourceId());
		return task;
	}

	private void setLiveSatus(@NotNull final VnfBlueprint blueprint, @NotNull final VnfInstance vnfInstance, final VnfReport createResults) {
		LOG.info("Creating / deleting live instances.");
		createResults.getSuccess().forEach(x -> {
			final VnfTask rhe = x.getId().getTaskEntity();
			final ChangeType ct = rhe.getChangeType();
			if (ct == ChangeType.ADDED) {
				String il = null;
				if (rhe.getScaleInfo() != null) {
					il = rhe.getScaleInfo().getAspectId();
				}
				if (null != rhe.getId()) {
					final VnfLiveInstance vli = new VnfLiveInstance(vnfInstance, il, rhe, blueprint, rhe.getVimResourceId());
					vnfInstancesService.save(vli);
				} else {
					LOG.warn("Could not store: {}", x.getId().getName());
				}
			} else if (ct == ChangeType.REMOVED) {
				LOG.info("Removing {}", rhe.getId());
				final VnfLiveInstance vli = vnfInstancesService.findLiveInstanceById(rhe.getRemovedVnfLiveInstance()).orElseThrow(() -> new NotFoundException("" + rhe.getId()));
				vnfInstancesService.deleteLiveInstanceById(vli.getId());
			}
		});
	}
}
