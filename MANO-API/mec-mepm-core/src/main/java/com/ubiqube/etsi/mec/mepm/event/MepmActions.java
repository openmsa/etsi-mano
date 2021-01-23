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
package com.ubiqube.etsi.mec.mepm.event;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageBase;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.dao.mano.v2.AbstractBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppBlueprint;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppInstance;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppLiveInstance;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppTask;
import com.ubiqube.etsi.mano.dao.mec.tasks.AppComputeTask;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.service.VimResourceService;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;
import com.ubiqube.etsi.mec.mepm.repositories.AppLiveInstanceJpa;
import com.ubiqube.etsi.mec.mepm.service.AppBlueprintService;
import com.ubiqube.etsi.mec.mepm.service.AppInstanceService;
import com.ubiqube.etsi.mec.mepm.service.AppPackageService;
import com.ubiqube.etsi.mec.mepm.service.graph.AppParameters;
import com.ubiqube.etsi.mec.mepm.service.graph.AppReport;
import com.ubiqube.etsi.mec.mepm.service.graph.AppWorkflow;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class MepmActions {

	private static final Logger LOG = LoggerFactory.getLogger(MepmActions.class);

	private final AppBlueprintService blueprintService;

	private final AppInstanceService instanceService;

	private final AppPackageService appPackageService;

	private final AppWorkflow vnfWorkflow;

	private final MepmEventManager eventManager;

	private final VimManager vimManager;

	private final VimResourceService vimResourceService;

	private final AppLiveInstanceJpa appLiveInstanceJpa;

	public MepmActions(final AppBlueprintService blueprintService, final AppInstanceService instanceService, final AppPackageService appPackageService, final AppWorkflow vnfWorkflow, final MepmEventManager eventManager, final VimManager vimManager, final VimResourceService vimResourceService, final AppLiveInstanceJpa appLiveInstanceJpa) {
		super();
		this.blueprintService = blueprintService;
		this.instanceService = instanceService;
		this.appPackageService = appPackageService;
		this.vnfWorkflow = vnfWorkflow;
		this.eventManager = eventManager;
		this.vimManager = vimManager;
		this.vimResourceService = vimResourceService;
		this.appLiveInstanceJpa = appLiveInstanceJpa;
	}

	public void instantiate(@NotNull final UUID blueprintId) {
		final AppBlueprint blueprint = blueprintService.findById(blueprintId);
		final AppInstance instance = instanceService.findById(blueprint.getAppInstance().getId());
		try {
			instantiateInnerv2(blueprint, instance);
			LOG.info("Instantiate {} Success...", blueprintId);
		} catch (final RuntimeException e) {
			LOG.error("VNF Instantiate Failed", e);
			instance.setInstantiationState(InstantiationState.NOT_INSTANTIATED);
			blueprint.setOperationStatus(OperationStatusType.FAILED);
			blueprint.setError(new FailureDetails(500L, e.getMessage()));
			blueprintService.save(blueprint);
			instanceService.save(instance);
		}
	}

	private void instantiateInnerv2(final AppBlueprint blueprint, final AppInstance instance) {
		if (null == blueprint.getParameters().getInstantiationLevelId()) {
			blueprint.getParameters().setInstantiationLevelId(instance.getInstantiatedVnfInfo().getInstantiationLevelId());
		}
		final PackageBase vnfPkg = appPackageService.findById(instance.getAppPkg());
		vnfWorkflow.setWorkflowBlueprint(vnfPkg, blueprint, null);
		AppBlueprint localPlan = blueprintService.save(blueprint);
		eventManager.sendNotification(NotificationEvent.VNF_INSTANTIATE, instance.getId());
		vimResourceService.allocate(localPlan);
		localPlan = blueprintService.updateState(localPlan, OperationStatusType.PROCESSING);
		//////////////////////////////////
		// XXX Multiple Vim ?
		final VimConnectionInformation vimConnection = localPlan.getVimConnections().iterator().next();
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		//
		final AppParameters vparams = new AppParameters(vimConnection, vim, appLiveInstanceJpa, new HashMap<>(), null);
		final AppReport removeResults = vnfWorkflow.execDelete(localPlan, vparams);
		setLiveSatus(localPlan, instance, removeResults);
		// Create plan
		final AppParameters params = buildContext(vimConnection, vim, localPlan, instance);
		final AppReport createResults = vnfWorkflow.execCreate(localPlan, params);
		setLiveSatus(localPlan, instance, createResults);
		//
		setResultLcmInstance(localPlan, createResults);
		// XXX ??? error duplicate key in NSD.
		instance.setVimConnectionInfo(null);
		instanceService.save(instance);
		LOG.info("Saving VNF LCM OP OCCS.");
		localPlan = blueprintService.save(localPlan);
		// XXX Send COMPLETED event.
		LOG.info("VNF instance {} / LCM {} Finished.", instance.getId(), localPlan.getId());

	}

	private AppParameters buildContext(final VimConnectionInformation vimConnection, final Vim vim, final AppBlueprint blueprint, final AppInstance vnfInstance) {
		final Map<String, String> context = blueprint.getParameters().getExtManagedVirtualLinks().stream()
				.collect(Collectors.toMap(ExtManagedVirtualLinkDataEntity::getVnfVirtualLinkDescId, ExtManagedVirtualLinkDataEntity::getResourceId));
		// Add all present VL if any.
		context.putAll(getLiveVl(vnfInstance));
		return new AppParameters(vimConnection, vim, appLiveInstanceJpa, context, null);
	}

	private Map<String, String> getLiveVl(final AppInstance vnfInstance) {
		final List<VnfLiveInstance> res = instanceService.getLiveVirtualLinkInstanceOf(vnfInstance);
		return res.stream()
				.collect(Collectors.toMap(x -> x.getTask().getToscaName(), x -> x.getTask().getVimResourceId()));
	}

	private static void setResultLcmInstance(@NotNull final AbstractBlueprint<?> blueprint, final AppReport createResults) {
		if (createResults.getErrored().isEmpty()) {
			blueprint.setOperationStatus(OperationStatusType.COMPLETED);
		} else {
			blueprint.setOperationStatus(OperationStatusType.FAILED);
		}
		blueprint.setStateEnteredTime(new Date());
	}

	public void terminate(@NotNull final UUID blueprintId) {
		final AppBlueprint blueprint = blueprintService.findById(blueprintId);
		final AppInstance vnfInstance = instanceService.findById(blueprint.getAppInstance().getId());
		try {
			instantiateInnerv2(blueprint, vnfInstance);
			LOG.info("Terminate {} Success...", blueprintId);
		} catch (final RuntimeException e) {
			LOG.error("VNF Instantiate Failed", e);
			blueprint.setOperationStatus(OperationStatusType.FAILED);
			blueprint.setError(new FailureDetails(500L, e.getMessage()));
			blueprint.setStateEnteredTime(new Date());
			blueprintService.save(blueprint);
			instanceService.save(vnfInstance);
		}
	}

	public void operate(@NotNull final UUID blueprintId) {
		final AppBlueprint blueprint = blueprintService.findById(blueprintId);
		final AppInstance vnfInstance = instanceService.findById(blueprint.getAppInstance().getId());
		// XXX Move this to controller.
		final List<AppLiveInstance> instantiatedCompute = instanceService.getLiveComputeInstanceOf(vnfInstance);
		instantiatedCompute.forEach(x -> {
			final AppTask affectedCompute = copyInstantiedResource(x, new AppComputeTask(), blueprint);
			blueprint.addTask(affectedCompute);
		});
		final AppBlueprint localBlueprint = blueprintService.save(blueprint);
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
		instanceService.save(vnfInstance);
	}

	private static <T extends AppTask> T copyInstantiedResource(final AppLiveInstance x, final T task, final AppBlueprint blueprint) {
		task.setChangeType(ChangeType.REMOVED);
		task.setStatus(PlanStatusType.STARTED);
		task.setBlueprint(blueprint);
		task.setStartDate(LocalDateTime.now());
		task.setToscaName(x.getTask().getToscaName());
		task.setVimResourceId(x.getTask().getVimResourceId());
		return task;
	}

	private void setLiveSatus(@NotNull final AppBlueprint blueprint, @NotNull final AppInstance vnfInstance, final AppReport createResults) {
		LOG.info("Creating / deleting live instances.");
		createResults.getSuccess().forEach(x -> {
			final AppTask rhe = x.getId().getTaskEntity();
			final ChangeType ct = rhe.getChangeType();
			if (ct == ChangeType.ADDED) {
				String il = null;
				if (rhe.getScaleInfo() != null) {
					il = rhe.getScaleInfo().getAspectId();
				}
				if (null != rhe.getId()) {
					final AppLiveInstance vli = new AppLiveInstance(vnfInstance, il, rhe, blueprint, rhe.getVimResourceId());
					instanceService.save(vli);
				} else {
					LOG.warn("Could not store: {}", x.getId().getName());
				}
			} else if (ct == ChangeType.REMOVED) {
				LOG.info("Removing {}", rhe.getId());
				final AppLiveInstance vli = instanceService.findLiveInstanceById(rhe.getRemovedVnfLiveInstance()).orElseThrow(() -> new NotFoundException("" + rhe.getId()));
				instanceService.deleteLiveInstanceById(vli.getId());
			}
		});
	}

}
