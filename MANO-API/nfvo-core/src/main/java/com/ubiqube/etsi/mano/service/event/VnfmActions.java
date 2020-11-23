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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.jgrapht.ListenableGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.task.ExecutionResults;
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
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.service.BlueprintService;
import com.ubiqube.etsi.mano.service.VimResourceService;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.PlanExecutor;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.plan.Planner;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
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

	private final PlanExecutor executor;

	private final VnfInstanceService vnfInstancesService;

	private final VnfPackageService vnfPackageService;

	private final Planner planner;
	private final BlueprintService blueprintService;
	private final VimResourceService vimResourceService;

	public VnfmActions(final VimManager _vimManager, final VnfPackageService _vnfPackageService, final EventManager _eventManager, final PlanExecutor _executor, final VnfInstanceService _vnfInstancesService, final Planner _planner, final BlueprintService _blueprintService, final VimResourceService _vimResourceService) {
		super();
		vimManager = _vimManager;
		vnfPackageService = _vnfPackageService;
		eventManager = _eventManager;
		executor = _executor;
		vnfInstancesService = _vnfInstancesService;
		planner = _planner;
		blueprintService = _blueprintService;
		vimResourceService = _vimResourceService;
	}

	public void vnfInstantiate(@Nonnull final UUID blueprintId) {
		Thread.currentThread().setName(blueprintId + "-VI");
		final Blueprint blueprint = blueprintService.findById(blueprintId);
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

	public void instantiateInnerv2(final Blueprint blueprint, final VnfInstance vnfInstance) {
		if (null == blueprint.getParameters().getInstantiationLevelId()) {
			blueprint.getParameters().setInstantiationLevelId(vnfInstance.getInstantiatedVnfInfo().getInstantiationLevelId());
		}
		final VnfPackage vnfPkg = vnfPackageService.findById(vnfInstance.getVnfPkg());
		final Set<ScaleInfo> newScale = merge(blueprint, vnfInstance);
		planner.doPlan(vnfPkg, blueprint, newScale);
		Blueprint localPlan = blueprintService.save(blueprint);
		eventManager.sendNotification(NotificationEvent.VNF_INSTANTIATE, vnfInstance.getId());
		vimResourceService.allocate(localPlan);
		localPlan = blueprintService.updateState(localPlan, OperationStatusType.PROCESSING);
		final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> executionPlane = planner.convertToExecution(localPlan, ChangeType.REMOVED);
		//////////////////////////////////
		// XXX We can't refine a removal plan, because it has already been reverted.
		// XXX Multiple Vim ?
		final VimConnectionInformation vimConnection = localPlan.getVimConnections().iterator().next();
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		final ExecutionResults<UnitOfWork, String> removeResults = executor.execDelete(executionPlane, vimConnection, vim);
		/// XXX split this function for adding / removing live instances.
		setLiveSatus(localPlan, vnfInstance, removeResults);
		// Create plan
		final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> createPlan = planner.convertToExecution(localPlan, ChangeType.ADDED);
		final Map<String, String> context = buildContext(localPlan, vnfInstance);
		final ExecutionResults<UnitOfWork, String> createResults = executor.execCreate(createPlan, vimConnection, vim, context);
		setResultLcmInstance(localPlan, createResults);
		setLiveSatus(localPlan, vnfInstance, createResults);
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
		// XXX ??? error duplicate key in NSD.
		vnfInstance.setVimConnectionInfo(null);
		vnfInstancesService.save(vnfInstance);
		LOG.info("Saving VNF LCM OP OCCS.");
		localPlan = blueprintService.save(localPlan);
		// XXX Send COMPLETED event.
		LOG.info("VNF instance {} / LCM {} Finished.", vnfInstance.getId(), localPlan.getId());

	}

	private Map<String, String> buildContext(final Blueprint blueprint, final VnfInstance vnfInstance) {
		final Map<String, String> context = blueprint.getParameters().getExtManagedVirtualLinks().stream()
				.collect(Collectors.toMap(ExtManagedVirtualLinkDataEntity::getVnfVirtualLinkDescId, ExtManagedVirtualLinkDataEntity::getResourceId));
		// Add all present VL if any.
		context.putAll(getLiveVl(vnfInstance));
		return context;
	}

	private static Set<ScaleInfo> merge(final Blueprint plan, final VnfInstance vnfInstance) {
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

	private static void setResultLcmInstance(@NotNull final Blueprint blueprint, final ExecutionResults<UnitOfWork, String> results) {
		if (results.getErrored().isEmpty()) {
			blueprint.setOperationStatus(OperationStatusType.COMPLETED);
		} else {
			blueprint.setOperationStatus(OperationStatusType.FAILED);
		}
		blueprint.setStateEnteredTime(new Date());
	}

	public void vnfTerminate(@Nonnull final UUID blueprintId) {
		Thread.currentThread().setName(blueprintId + "-VT");
		final Blueprint blueprint = blueprintService.findById(blueprintId);
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
		final Blueprint blueprint = blueprintService.findById(blueprintId);
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
		final Blueprint blueprint = blueprintService.findById(blueprintId);
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
		final Blueprint blueprint = blueprintService.findById(blueprintId);
		final VnfInstance vnfInstance = vnfInstancesService.findById(blueprint.getVnfInstance().getId());
		// XXX Move this to controller.
		final List<VnfLiveInstance> instantiatedCompute = vnfInstancesService.getLiveComputeInstanceOf(vnfInstance);
		instantiatedCompute.forEach(x -> {
			final Task affectedCompute = copyInstantiedResource(x, new ComputeTask(), blueprint);
			blueprint.addTask(affectedCompute);
		});
		final Blueprint localBlueprint = blueprintService.save(blueprint);
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

	private static <T extends Task> T copyInstantiedResource(final VnfLiveInstance x, final T task, final Blueprint blueprint) {
		task.setChangeType(ChangeType.REMOVED);
		task.setStatus(PlanStatusType.STARTED);
		task.setBlueprint(blueprint);
		task.setStartDate(LocalDateTime.now());
		task.setToscaName(x.getTask().getToscaName());
		task.setVimResourceId(x.getTask().getVimResourceId());
		return task;
	}

	private void setLiveSatus(@NotNull final Blueprint blueprint, @NotNull final VnfInstance vnfInstance, final ExecutionResults<UnitOfWork, String> results) {
		LOG.info("Creating / deleting live instances.");
		results.getSuccess().forEach(x -> {
			final Task rhe = x.getId().getTaskEntity();
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
