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
package com.ubiqube.etsi.mec.mepm.controller.lcm;

import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.Instance;
import com.ubiqube.etsi.mano.dao.mano.PackageBase;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppBlueprint;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppInstance;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppLiveInstance;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mano.service.event.OrchestrationAdapter;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.graph.WorkflowEvent;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mec.mepm.repositories.AppLiveInstanceJpa;
import com.ubiqube.etsi.mec.mepm.service.AppBlueprintService;
import com.ubiqube.etsi.mec.mepm.service.AppInstanceService;
import com.ubiqube.etsi.mec.mepm.service.graph.AppParameters;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class AppOrchestrationAdapter implements OrchestrationAdapter<AppTask, AppInstance> {
	private final AppInstanceService instancesService;
	private final AppBlueprintService blueprintService;
	private final AppLiveInstanceJpa liveInstanceJpa;
	private final EventManager eventManager;

	public AppOrchestrationAdapter(final AppInstanceService instancesService, final AppBlueprintService blueprintService, final AppLiveInstanceJpa liveInstanceJpa, final EventManager eventManager) {
		super();
		this.instancesService = instancesService;
		this.blueprintService = blueprintService;
		this.liveInstanceJpa = liveInstanceJpa;
		this.eventManager = eventManager;
	}

	@Override
	public void createLiveInstance(@NotNull final Instance vnfInstance, final String il, final Task task, @NotNull final Blueprint<? extends Task, ? extends Instance> blueprint) {
		final AppLiveInstance vli = new AppLiveInstance((AppInstance) vnfInstance, il, (AppTask) task, (AppBlueprint) blueprint, task.getVimResourceId());
		instancesService.save(vli);
	}

	@Override
	public void deleteLiveInstance(final UUID removedLiveInstance) {
		final AppLiveInstance vli = instancesService.findLiveInstanceById(removedLiveInstance).orElseThrow();
		instancesService.deleteLiveInstanceById(vli.getId());
	}

	@Override
	public Blueprint<AppTask, AppInstance> getBluePrint(final UUID blueprintId) {
		return blueprintService.findById(blueprintId);
	}

	@Override
	public Instance getInstance(final UUID blueprintId) {
		return instancesService.findById(blueprintId);
	}

	@Override
	public PackageBase getPackage(final Instance instance) {
		final AppInstance vnfInstance = (AppInstance) instance;
		return vnfInstance.getAppPkg();
	}

	@Override
	public Instance getInstance(final Blueprint<AppTask, AppInstance> blueprint) {
		final AppBlueprint vb = (AppBlueprint) blueprint;
		return vb.getAppInstance();
	}

	@Override
	public Blueprint<AppTask, AppInstance> save(final Blueprint blueprint) {
		return blueprintService.save((AppBlueprint) blueprint);
	}

	@Override
	public Instance save(final Instance vnfInstance) {
		return instancesService.save((AppInstance) vnfInstance);
	}

	@Override
	public GenericExecParams createParameter(final VimConnectionInformation vimConnection, final Vim vim, final HashMap<String, String> hashMap, final Object object) {
		return new AppParameters(vimConnection, vim, liveInstanceJpa, new HashMap<>(), null);
	}

	@Override
	public Blueprint<AppTask, AppInstance> updateState(final Blueprint localPlan, final OperationStatusType processing) {
		return blueprintService.updateState((AppBlueprint) localPlan, OperationStatusType.PROCESSING);
	}

	@Override
	public void fireEvent(final WorkflowEvent instantiateProcessing, final UUID id) {
		final NotificationEvent notificationEvent = convert(instantiateProcessing);
		eventManager.sendNotification(notificationEvent, id);

	}

	@Nonnull
	private static NotificationEvent convert(final WorkflowEvent instantiateProcessing) {
		switch (instantiateProcessing) {
		case INSTANTIATE_PROCESSING:
			return NotificationEvent.APP_INSTANCE_CREATE;
		case INSTANTIATE_SUCCESS:
			return NotificationEvent.APPINSTANTIATE;
		case SCALE_FAILED:
			return NotificationEvent.APP_SCALE_FAILED;
		case SCALE_SUCCESS:
			return NotificationEvent.APP_SCALE_SUCCESS;
		case SCALETOLEVEL_FAILED:
			return NotificationEvent.APP_SCALETOLEVEL_FAILED;
		case SCALETOLEVEL_SUCCESS:
			return NotificationEvent.APP_SCALETOLEVEL_SUCCESS;
		case TERMINATE_FAILED:
			return NotificationEvent.APP_TERMINATE_FAILED;
		case TERMINATE_SUCCESS:
			return NotificationEvent.APP_TERMINATE_SUCCESS;
		default:
			throw new GenericException("Unknow event: " + instantiateProcessing);
		}
	}
}
