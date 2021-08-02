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

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.Instance;
import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.PackageBase;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.orchestrator.OrchExecutionResults;
import com.ubiqube.etsi.mano.orchestrator.Planner;
import com.ubiqube.etsi.mano.orchestrator.PreExecutionGraph;
import com.ubiqube.etsi.mano.service.VimResourceService;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.graph.WorkflowEvent;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;

public abstract class AbstractGenericAction {

	private final static Logger LOG = LoggerFactory.getLogger(AbstractGenericAction.class);

	private final VimManager vimManager;

	private final Workflow vnfWorkflow;

	private final VimResourceService vimResourceService;

	private Planner planner;

	OrchestrationAdapter<?, ?> orchestrationAdapter;

	protected AbstractGenericAction(final VimManager vimManager, final Workflow vnfWorkflow, final VimResourceService vimResourceService, final OrchestrationAdapter<?, ?> orchestrationAdapter) {
		super();
		this.vimManager = vimManager;
		this.vnfWorkflow = vnfWorkflow;
		this.vimResourceService = vimResourceService;
		this.orchestrationAdapter = orchestrationAdapter;
	}

	public final void instantiate(@Nonnull final UUID blueprintId) {
		final Blueprint blueprint = orchestrationAdapter.getBluePrint(blueprintId);
		final Instance vnfInstance = orchestrationAdapter.getInstance(blueprint.getInstance().getId());
		try {
			instantiateInnerv2(blueprint, vnfInstance);
			LOG.info("Instantiate {} Success...", blueprintId);
			orchestrationAdapter.fireEvent(WorkflowEvent.INSTANTIATE_SUCCESS, blueprintId);
		} catch (final RuntimeException e) {
			LOG.error("VNF Instantiate Failed", e);
			vnfInstance.setInstantiationState(InstantiationState.NOT_INSTANTIATED);
			blueprint.setOperationStatus(OperationStatusType.FAILED);
			blueprint.setError(new FailureDetails(500L, e.getMessage()));
			orchestrationAdapter.save(blueprint);
			orchestrationAdapter.save(vnfInstance);
		}
	}

	private final void instantiateInnerv2(final Blueprint blueprint, final Instance vnfInstance) {
		if (null == blueprint.getParameters().getInstantiationLevelId()) {
			blueprint.getParameters().setInstantiationLevelId(vnfInstance.getInstantiatedVnfInfo().getInstantiationLevelId());
		}
		final PackageBase vnfPkg = orchestrationAdapter.getPackage(vnfInstance);
		final Set<ScaleInfo> newScale = merge(blueprint, vnfInstance);
		final PreExecutionGraph<?> prePlan = vnfWorkflow.setWorkflowBlueprint(vnfPkg, blueprint);
		Blueprint<?, ?> localPlan = orchestrationAdapter.save(blueprint);
		orchestrationAdapter.fireEvent(WorkflowEvent.INSTANTIATE_PROCESSING, vnfInstance.getId());
		vimResourceService.allocate(localPlan);
		localPlan = orchestrationAdapter.updateState(localPlan, OperationStatusType.PROCESSING);
		//
		vnfWorkflow.refresh(prePlan, localPlan);
		final OrchExecutionResults res = vnfWorkflow.execute(prePlan, localPlan);
		localPlan = orchestrationAdapter.getBluePrint(localPlan.getId());
		setLiveSatus(localPlan, vnfInstance, res);
		//
		setResultLcmInstance(localPlan, res);
		if (OperationStatusType.COMPLETED == localPlan.getOperationStatus()) {
			setInstanceStatus(vnfInstance, localPlan, newScale);
		}
		// XXX ??? error duplicate key in NSD.
		vnfInstance.setVimConnectionInfo(null);
		orchestrationAdapter.save(vnfInstance);
		LOG.info("Saving VNF LCM OP OCCS.");
		localPlan = orchestrationAdapter.save(localPlan);
		// XXX Send COMPLETED event.
		LOG.info("VNF instance {} / LCM {} Finished.", vnfInstance.getId(), localPlan.getId());
	}

	protected abstract GenericExecParams buildContext(VimConnectionInformation vimConnection, Vim vim, Blueprint localPlan, Instance vnfInstance);

	private static void setInstanceStatus(final Instance vnfInstance, final Blueprint localPlan, final Set<ScaleInfo> newScale) {
		Optional.ofNullable(localPlan.getParameters().getScaleStatus()).map(x -> x.stream().map(y -> new ScaleInfo(y.getAspectId(), y.getScaleLevel())).collect(Collectors.toSet())).ifPresent(x -> vnfInstance.getInstantiatedVnfInfo().setScaleStatus(x));
		LOG.info("Saving VNF Instance.");
		vnfInstance.getInstantiatedVnfInfo().setInstantiationLevelId(localPlan.getParameters().getInstantiationLevelId());
		if (null != localPlan.getParameters().getFlavourId()) {
			vnfInstance.getInstantiatedVnfInfo().setFlavourId(localPlan.getParameters().getFlavourId());
		}
		// XXX Copy new ScaleInfo.
		removeScaleStatus(vnfInstance, newScale);
	}

	private static Set<ScaleInfo> merge(final Blueprint plan, final Instance vnfInstance) {
		final Set<ScaleInfo> tmp = vnfInstance.getInstantiatedVnfInfo().getScaleStatus().stream().filter(x -> notIn(x.getAspectId(), plan.getParameters().getScaleStatus())).map(x -> new ScaleInfo(x.getAspectId(), x.getScaleLevel())).collect(Collectors.toSet());
		tmp.addAll(plan.getParameters().getScaleStatus());
		return tmp;
	}

	private static void removeScaleStatus(final Instance localVnfInstance, final Set<ScaleInfo> newScale) {
		final Set<ScaleInfo> scales = localVnfInstance.getInstantiatedVnfInfo().getScaleStatus();
		newScale.stream().forEach(x -> find(scales, x.getAspectId()).ifPresent(scales::remove));
		final List<ScaleInfo> si = newScale.stream().map(x -> new ScaleInfo(x.getAspectId(), x.getScaleLevel())).collect(Collectors.toList());
		newScale.addAll(si);
	}

	private static Optional<? extends ScaleInfo> find(final Set<? extends ScaleInfo> scales, final String aspectId) {
		return scales.stream().filter(x -> x.getAspectId().equals(aspectId)).findFirst();
	}

	private static boolean notIn(final String aspectId, final Set<? extends ScaleInfo> scaleInfos) {
		return scaleInfos.stream().noneMatch(x -> x.getAspectId().equals(aspectId));
	}

	private static void setResultLcmInstance(@NotNull final Blueprint<?, ?> blueprint, final OrchExecutionResults<?> res) {
		if (res.getErrored().isEmpty()) {
			blueprint.setOperationStatus(OperationStatusType.COMPLETED);
		} else {
			blueprint.setOperationStatus(OperationStatusType.FAILED);
		}
		blueprint.setStateEnteredTime(new Date());
	}

	public final void terminate(@Nonnull final UUID blueprintId) {
		final Blueprint<?, ?> blueprint = orchestrationAdapter.getBluePrint(blueprintId);
		final Instance vnfInstance = orchestrationAdapter.getInstance(blueprint.getInstance().getId());
		try {
			instantiateInnerv2(blueprint, vnfInstance);
			LOG.info("Terminate {} Success...", blueprintId);
			orchestrationAdapter.fireEvent(WorkflowEvent.TERMINATE_SUCCESS, blueprintId);
		} catch (final RuntimeException e) {
			LOG.error("VNF Instantiate Failed", e);
			blueprint.setOperationStatus(OperationStatusType.FAILED);
			blueprint.setError(new FailureDetails(500L, e.getMessage()));
			blueprint.setStateEnteredTime(new Date());
			orchestrationAdapter.save(blueprint);
			orchestrationAdapter.save(vnfInstance);
			orchestrationAdapter.fireEvent(WorkflowEvent.TERMINATE_FAILED, blueprintId);
		}
	}

	public final void scaleToLevel(@NotNull final UUID blueprintId) {
		final Blueprint<?, ?> blueprint = orchestrationAdapter.getBluePrint(blueprintId);
		final Instance vnfInstance = orchestrationAdapter.getInstance(blueprint.getInstance().getId());
		try {
			instantiateInnerv2(blueprint, vnfInstance);
			LOG.info("Scale to level {} Success...", blueprintId);
			orchestrationAdapter.fireEvent(WorkflowEvent.SCALETOLEVEL_SUCCESS, blueprintId);
		} catch (final RuntimeException e) {
			LOG.error("VNF Scale to level Failed", e);
			blueprint.setOperationStatus(OperationStatusType.FAILED);
			blueprint.setError(new FailureDetails(500L, e.getMessage()));
			blueprint.setStateEnteredTime(new Date());
			orchestrationAdapter.save(blueprint);
			orchestrationAdapter.fireEvent(WorkflowEvent.SCALETOLEVEL_FAILED, blueprintId);
		}
	}

	public final void scale(@NotNull final UUID blueprintId) {
		final Blueprint<?, ?> blueprint = orchestrationAdapter.getBluePrint(blueprintId);
		final Instance vnfInstance = orchestrationAdapter.getInstance(blueprint.getId());
		try {
			instantiateInnerv2(blueprint, vnfInstance);
			LOG.info("Scale to level {} Success...", blueprintId);
			orchestrationAdapter.fireEvent(WorkflowEvent.SCALE_SUCCESS, blueprintId);
		} catch (final RuntimeException e) {
			LOG.error("VNF Scale to level Failed", e);
			blueprint.setOperationStatus(OperationStatusType.FAILED);
			blueprint.setError(new FailureDetails(500L, e.getMessage()));
			blueprint.setStateEnteredTime(new Date());
			orchestrationAdapter.save(blueprint);
			orchestrationAdapter.fireEvent(WorkflowEvent.SCALE_FAILED, blueprintId);
		}
	}

	private void setLiveSatus(@NotNull final Blueprint<? extends Task, ? extends Instance> blueprint, @NotNull final Instance vnfInstance, final OrchExecutionResults<Task> res) {
		LOG.info("Creating / deleting live instances.");
		res.getSuccess().forEach(x -> {
			final Task rhe = x.getTask().getTask().getParameters();
			final ChangeType ct = rhe.getChangeType();
			if (ct == ChangeType.ADDED) {
				String il = null;
				if (rhe.getScaleInfo() != null) {
					il = rhe.getScaleInfo().getAspectId();
				}
				if (null != rhe.getId()) {
					orchestrationAdapter.createLiveInstance(vnfInstance, il, rhe, blueprint);
				} else {
					LOG.warn("Could not store: {}", x.getTask().getTask().getParameters().getToscaName());
				}
			} else if (ct == ChangeType.REMOVED) {
				LOG.info("Removing {}", rhe.getId());
				orchestrationAdapter.deleteLiveInstance(rhe.getRemovedLiveInstance());
			}
		});
	}
}
