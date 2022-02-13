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

import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
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
import com.ubiqube.etsi.mano.dao.mano.PackageBase;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.ScaleTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.orchestrator.OrchExecutionResults;
import com.ubiqube.etsi.mano.orchestrator.PreExecutionGraph;
import com.ubiqube.etsi.mano.service.NsScaleStrategy;
import com.ubiqube.etsi.mano.service.VimResourceService;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.graph.WorkflowEvent;
import com.ubiqube.etsi.mano.service.vim.Vim;

public abstract class AbstractGenericAction {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractGenericAction.class);

	private final Workflow vnfWorkflow;

	private final VimResourceService vimResourceService;

	private final OrchestrationAdapter<?, ?> orchestrationAdapter;

	private final NsScaleStrategy nsScaleStrategy;

	protected AbstractGenericAction(final Workflow vnfWorkflow, final VimResourceService vimResourceService, final OrchestrationAdapter<?, ?> orchestrationAdapter, final NsScaleStrategy nsScaleStrategy) {
		super();
		this.vnfWorkflow = vnfWorkflow;
		this.vimResourceService = vimResourceService;
		this.orchestrationAdapter = orchestrationAdapter;
		this.nsScaleStrategy = nsScaleStrategy;
	}

	public final void instantiate(@Nonnull final UUID blueprintId) {
		instantiateShield(blueprintId, WorkflowEvent.INSTANTIATE_SUCCESS, WorkflowEvent.INSTANTIATE_FAILED);
	}

	private final void instantiateInnerv2(final Blueprint blueprint, final Instance vnfInstance) {
		if (null == blueprint.getParameters().getInstantiationLevelId()) {
			blueprint.getParameters().setInstantiationLevelId(vnfInstance.getInstantiatedVnfInfo().getInstantiationLevelId());
		}
		final PackageBase vnfPkg = orchestrationAdapter.getPackage(vnfInstance);
		final Set<ScaleInfo> newScale = merge(blueprint, vnfInstance);
		final PreExecutionGraph<?> prePlan = vnfWorkflow.setWorkflowBlueprint(vnfPkg, blueprint);
		if (!System.getenv().isEmpty()) {
			// throw new GenericException("");
		}
		Blueprint<?, ?> localPlan = orchestrationAdapter.save(blueprint);
		orchestrationAdapter.fireEvent(WorkflowEvent.INSTANTIATE_PROCESSING, vnfInstance.getId());
		vimResourceService.allocate(localPlan);
		localPlan = orchestrationAdapter.updateState(localPlan, OperationStatusType.PROCESSING);
		//
		vnfWorkflow.refresh(prePlan, localPlan);
		final OrchExecutionResults<Task> res = vnfWorkflow.execute(prePlan, localPlan);
		localPlan = orchestrationAdapter.getBluePrint(localPlan.getId());
		setLiveSatus(localPlan, vnfInstance, res);
		//
		setResultLcmInstance(localPlan, res);
		if (OperationStatusType.COMPLETED == localPlan.getOperationStatus()) {
			setInstanceStatus(vnfInstance, localPlan, newScale);
		}
		// XXX ??? error duplicate key in NSD.
		copyVimConnections(vnfInstance, localPlan);
		vnfInstance.setLockedBy(null);
		orchestrationAdapter.save(vnfInstance);
		LOG.info("Saving LCM OP OCCS.");
		localPlan = orchestrationAdapter.save(localPlan);
		// XXX Send COMPLETED event.
		LOG.info("Instance {} / LCM {} Finished.", vnfInstance.getId(), localPlan.getId());
	}

	private static void copyVimConnections(final Instance vnfInstance, final Blueprint<?, ?> localPlan) {
		vnfInstance.setVimConnectionInfo(new LinkedHashSet<>());
		localPlan.getVimConnections().forEach(vnfInstance::addVimConnectionInfo);
	}

	protected abstract GenericExecParams buildContext(VimConnectionInformation vimConnection, Vim vim, Blueprint localPlan, Instance instance);

	/**
	 * Move this function to scale strategy.
	 *
	 * @param instance
	 * @param localPlan
	 * @param newScale
	 */
	private void setInstanceStatus(final Instance instance, final Blueprint localPlan, final Set<ScaleInfo> newScale) {
		Optional.ofNullable(localPlan.getParameters().getScaleStatus())
				.map(x -> x.stream()
						.map(y -> new ScaleInfo(y.getAspectId(), y.getScaleLevel()))
						.collect(Collectors.toSet()))
				.ifPresent(x -> setScaleStatus(instance, x, localPlan.getParameters().getScaleType()));
		if (localPlan.getOperation() == PlanOperationType.INSTANTIATE) {
			instance.getInstantiatedVnfInfo().setNsStepStatus(copy(localPlan.getParameters().getNsStepStatus()));
			instance.getInstantiatedVnfInfo().setScaleStatus(copy(localPlan.getParameters().getScaleStatus()));
		}
		Optional.ofNullable(localPlan.getParameters().getNsScale()).ifPresent(x -> nsScaleStrategy.remapNsScale(x, instance));
		LOG.info("Saving Instance.");
		instance.getInstantiatedVnfInfo().setInstantiationLevelId(localPlan.getParameters().getInstantiationLevelId());
		if (null != localPlan.getParameters().getFlavourId()) {
			instance.getInstantiatedVnfInfo().setFlavourId(localPlan.getParameters().getFlavourId());
		}
	}

	private static Object setScaleStatus(final Instance instance, final Set<ScaleInfo> si, final ScaleTypeEnum scaleTypeEnum) {
		si.stream().forEach(x -> instance.getInstantiatedVnfInfo().getScaleStatus().stream()
				.filter(y -> y.getAspectId().equals(x.getAspectId()))
				.findFirst()
				.ifPresent(y -> y.setScaleLevel(getNewStep(scaleTypeEnum, y.getScaleLevel(), x.getScaleLevel()))));
		return null;
	}

	private static int getNewStep(final ScaleTypeEnum scaleTypeEnum, final int orig, final int adder) {
		if (scaleTypeEnum == ScaleTypeEnum.IN) {
			final int i = orig - adder;
			return i < 0 ? 0 : i;
		}
		return orig + adder;
	}

	private static Set<ScaleInfo> copy(final Set<ScaleInfo> nsStepStatus) {
		return nsStepStatus.stream().map(x -> new ScaleInfo(x.getAspectId(), x.getScaleLevel())).collect(Collectors.toSet());
	}

	private static Set<ScaleInfo> merge(final Blueprint plan, final Instance instance) {
		final Set<ScaleInfo> tmp = instance.getInstantiatedVnfInfo().getScaleStatus().stream()
				.filter(x -> notIn(x.getAspectId(), plan.getParameters().getScaleStatus()))
				.map(x -> new ScaleInfo(x.getAspectId(), x.getScaleLevel()))
				.collect(Collectors.toSet());
		tmp.addAll(plan.getParameters().getScaleStatus());
		return tmp;
	}

	private static void removeScaleStatus(final Instance localVnfInstance, final Set<ScaleInfo> newScale) {
		final Set<ScaleInfo> scales = localVnfInstance.getInstantiatedVnfInfo().getScaleStatus();
		newScale.stream().forEach(x -> find(scales, x.getAspectId()).ifPresent(scales::remove));
		final List<ScaleInfo> si = newScale.stream().map(x -> new ScaleInfo(x.getAspectId(), x.getScaleLevel())).toList();
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
		blueprint.setStateEnteredTime(OffsetDateTime.now());
	}

	public final void terminate(@Nonnull final UUID blueprintId) {
		instantiateShield(blueprintId, WorkflowEvent.TERMINATE_SUCCESS, WorkflowEvent.TERMINATE_FAILED);
	}

	public final void scaleToLevel(@NotNull final UUID blueprintId) {
		instantiateShield(blueprintId, WorkflowEvent.SCALETOLEVEL_SUCCESS, WorkflowEvent.SCALETOLEVEL_FAILED);
	}

	public final void scale(@NotNull final UUID blueprintId) {
		instantiateShield(blueprintId, WorkflowEvent.SCALE_SUCCESS, WorkflowEvent.SCALE_FAILED);
	}

	private void instantiateShield(final UUID blueprintId, final WorkflowEvent success, final WorkflowEvent failure) {
		final Blueprint<?, ?> blueprint = orchestrationAdapter.getBluePrint(blueprintId);
		final Instance vnfInstance = orchestrationAdapter.getInstance(blueprint.getInstance().getId());
		try {
			instantiateInnerv2(blueprint, vnfInstance);
			LOG.info("{} {} Success...", success, blueprintId);
			orchestrationAdapter.fireEvent(success, blueprintId);
		} catch (final RuntimeException e) {
			LOG.error("{} Failed", failure, e);
			onFailure(failure, blueprint, vnfInstance, e);
		}
	}

	private final void onFailure(final WorkflowEvent workflowEvent, final Blueprint<?, ?> blueprintOrig, final Instance instance, final Exception e) {
		final Blueprint<?, ?> blueprint = orchestrationAdapter.getBluePrint(blueprintOrig.getId());
		blueprint.setOperationStatus(OperationStatusType.FAILED);
		blueprint.setError(new FailureDetails(500L, e.getMessage()));
		blueprint.setStateEnteredTime(OffsetDateTime.now());
		orchestrationAdapter.save(blueprint);
		instance.setLockedBy(null);
		orchestrationAdapter.save(instance);
		orchestrationAdapter.fireEvent(workflowEvent, blueprint.getId());
	}

	private void setLiveSatus(@NotNull final Blueprint<? extends Task, ? extends Instance> blueprint, @NotNull final Instance vnfInstance, final OrchExecutionResults<Task> res) {
		LOG.info("Creating / deleting live instances.");
		res.getSuccess().forEach(x -> {
			final Task rhe = x.getTask().getTask().getParameters();
			final ChangeType ct = rhe.getChangeType();
			if (ct == ChangeType.ADDED) {
				final String il = Optional.ofNullable(rhe.getScaleInfo()).map(ScaleInfo::getAspectId).orElse(null);
				if (null != rhe.getId() && null != rhe.getVimResourceId()) {
					// orchestrationAdapter.createLiveInstance(vnfInstance, il, rhe, blueprint);
				} else {
					LOG.warn("No vim resource or database id for: {}", x.getTask().getTask().getParameters().getToscaName());
				}
			} else if (ct == ChangeType.REMOVED && null != rhe.getId()) {
				LOG.info("Removing {}", rhe.getId());
				orchestrationAdapter.deleteLiveInstance(rhe.getRemovedLiveInstance());
			}
		});
	}
}
