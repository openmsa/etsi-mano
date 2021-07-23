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

import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.Instance;
import com.ubiqube.etsi.mano.dao.mano.PackageBase;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.service.VnfBlueprintService;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.graph.WorkflowEvent;
import com.ubiqube.etsi.mano.service.graph.vnfm.VnfParameters;
import com.ubiqube.etsi.mano.service.vim.Vim;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfOrchestrationAdapter implements OrchestrationAdapter<VnfTask, VnfInstance> {
	private final VnfInstanceService vnfInstancesService;
	private final VnfBlueprintService blueprintService;
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;
	private final VnfPackageService vnfPackageService;
	private final EventManager eventManager;

	public VnfOrchestrationAdapter(final VnfInstanceService vnfInstancesService, final VnfBlueprintService blueprintService, final VnfLiveInstanceJpa vnfLiveInstanceJpa, final EventManager eventManager, final VnfPackageService _vnfPackageService) {
		super();
		this.vnfInstancesService = vnfInstancesService;
		this.blueprintService = blueprintService;
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
		this.eventManager = eventManager;
		vnfPackageService = _vnfPackageService;
	}

	@Override
	public void createLiveInstance(@NotNull final Instance vnfInstance, final String il, final Task task, @NotNull final Blueprint<? extends Task, ? extends Instance> blueprint) {
		final VnfLiveInstance vli = new VnfLiveInstance((VnfInstance) vnfInstance, il, (VnfTask) task, (VnfBlueprint) blueprint, task.getVimResourceId(), task.getVimConnectionId());
		vnfInstancesService.save(vli);
	}

	@Override
	public void deleteLiveInstance(final UUID removedLiveInstance) {
		final VnfLiveInstance vli = vnfInstancesService.findLiveInstanceById(removedLiveInstance).orElseThrow();
		vnfInstancesService.deleteLiveInstanceById(vli.getId());
	}

	@Override
	public Blueprint<VnfTask, VnfInstance> getBluePrint(final UUID blueprintId) {
		return blueprintService.findById(blueprintId);
	}

	@Override
	public Instance getInstance(final UUID blueprintId) {
		return vnfInstancesService.findById(blueprintId);
	}

	@Override
	public PackageBase getPackage(final Instance instance) {
		final VnfInstance vnfInstance = (VnfInstance) instance;
		return vnfPackageService.findById(vnfInstance.getVnfPkg().getId());
	}

	@Override
	public Instance getInstance(final Blueprint<VnfTask, VnfInstance> blueprint) {
		final VnfBlueprint vb = (VnfBlueprint) blueprint;
		return vb.getVnfInstance();
	}

	@Override
	public Blueprint<VnfTask, VnfInstance> save(final Blueprint blueprint) {
		return blueprintService.save((VnfBlueprint) blueprint);
	}

	@Override
	public Instance save(final Instance vnfInstance) {
		return vnfInstancesService.save((VnfInstance) vnfInstance);
	}

	@Override
	public GenericExecParams createParameter(final VimConnectionInformation vimConnection, final Vim vim, final HashMap<String, String> hashMap, final Object object) {
		return new VnfParameters(vimConnection, vim, vnfLiveInstanceJpa, new HashMap<>(), null);
	}

	@Override
	public Blueprint<VnfTask, VnfInstance> updateState(final Blueprint localPlan, final OperationStatusType processing) {
		return blueprintService.updateState((VnfBlueprint) localPlan, OperationStatusType.PROCESSING);
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
			return NotificationEvent.VNF_INSTANCE_CREATE;
		case INSTANTIATE_SUCCESS:
			return NotificationEvent.VNF_INSTANTIATE;
		case SCALE_FAILED:
			return NotificationEvent.VNF_SCALE;
		case SCALE_SUCCESS:
			return NotificationEvent.VNF_SCALE;
		case SCALETOLEVEL_FAILED:
			return NotificationEvent.VNF_SCALE;
		case SCALETOLEVEL_SUCCESS:
			return NotificationEvent.VNF_SCALE;
		case TERMINATE_FAILED:
			return NotificationEvent.VNF_TERMINATE;
		case TERMINATE_SUCCESS:
			return NotificationEvent.VNF_TERMINATE;
		default:
			throw new GenericException("Unknow event: " + instantiateProcessing);
		}
	}

}
