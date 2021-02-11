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
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.Instance;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppBlueprint;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppInstance;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppLiveInstance;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppTask;
import com.ubiqube.etsi.mano.dao.mec.tasks.AppComputeTask;
import com.ubiqube.etsi.mano.service.event.AbstractGenericAction;
import com.ubiqube.etsi.mano.service.event.OrchestrationAdapter;
import com.ubiqube.etsi.mano.service.event.Workflow;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;
import com.ubiqube.etsi.mec.mepm.repositories.AppLiveInstanceJpa;
import com.ubiqube.etsi.mec.mepm.service.AppBlueprintService;
import com.ubiqube.etsi.mec.mepm.service.AppInstanceService;
import com.ubiqube.etsi.mec.mepm.service.MeoGrantService;
import com.ubiqube.etsi.mec.mepm.service.graph.AppParameters;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class MepmActions extends AbstractGenericAction {

	private final AppBlueprintService blueprintService;

	private final AppInstanceService instanceService;

	private final VimManager vimManager;

	private final AppLiveInstanceJpa appLiveInstanceJpa;

	protected MepmActions(final VimManager _vimManager, final Workflow vnfWorkflow, final MeoGrantService vimResourceService, final OrchestrationAdapter<?, ?> orchestrationAdapter,
			final AppInstanceService _instanceService, final AppBlueprintService _blueprintService, final AppLiveInstanceJpa _appLiveInstanceJpa) {
		super(_vimManager, vnfWorkflow, vimResourceService, orchestrationAdapter);
		vimManager = _vimManager;
		instanceService = _instanceService;
		blueprintService = _blueprintService;
		appLiveInstanceJpa = _appLiveInstanceJpa;
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
				// vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STARTED);
			} else {
				vim.stopServer(vimConnection, x.getVimResourceId());
				// vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STOPPED);
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

	@Override
	protected GenericExecParams buildContext(final VimConnectionInformation vimConnection, final Vim vim, final Blueprint blueprint, final Instance vnfInstance) {
		final Map<String, String> context = blueprint.getParameters().getExtManagedVirtualLinks().stream()
				.collect(Collectors.toMap(ExtManagedVirtualLinkDataEntity::getVnfVirtualLinkDescId, ExtManagedVirtualLinkDataEntity::getResourceId));
		// Add all present VL if any.
		context.putAll(getLiveVl((AppInstance) vnfInstance));
		return new AppParameters(vimConnection, vim, appLiveInstanceJpa, context, null);
	}

	private Map<String, String> getLiveVl(final AppInstance vnfInstance) {
		final List<AppLiveInstance> res = instanceService.getLiveVirtualLinkInstanceOf(vnfInstance);
		return res.stream()
				.collect(Collectors.toMap(x -> x.getTask().getToscaName(), x -> x.getTask().getVimResourceId()));
	}
}
