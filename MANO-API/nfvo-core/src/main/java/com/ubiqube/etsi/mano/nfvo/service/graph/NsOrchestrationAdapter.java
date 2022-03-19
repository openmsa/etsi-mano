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
package com.ubiqube.etsi.mano.nfvo.service.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.Instance;
import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.PackageBase;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.NotificationEvent;
import com.ubiqube.etsi.mano.nfvo.jpa.NsLiveInstanceJpa;
import com.ubiqube.etsi.mano.nfvo.service.NsInstanceService;
import com.ubiqube.etsi.mano.nfvo.service.NsdPackageService;
import com.ubiqube.etsi.mano.nfvo.service.graph.nfvo.NsParameters;
import com.ubiqube.etsi.mano.service.NsBlueprintService;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.OrchestrationAdapter;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.graph.WorkflowEvent;
import com.ubiqube.etsi.mano.service.vim.Vim;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NsOrchestrationAdapter implements OrchestrationAdapter<NsTask, NsdInstance> {
	private final NsLiveInstanceJpa liveInstanceJpa;
	private final NsBlueprintService blueprintService;
	private final NsInstanceService instanceService;
	private final NsdPackageService packageService;
	private final EventManager eventManager;

	public NsOrchestrationAdapter(final NsLiveInstanceJpa nsLiveInstanceJpa, final NsBlueprintService nsBlueprintService, final NsInstanceService nsInstanceService, final NsdPackageService nsdPackageService, final EventManager eventManager) {
		super();
		this.liveInstanceJpa = nsLiveInstanceJpa;
		this.blueprintService = nsBlueprintService;
		this.instanceService = nsInstanceService;
		this.packageService = nsdPackageService;
		this.eventManager = eventManager;
	}

	@Override
	public void createLiveInstance(@NotNull final Instance instance, final String il, final Task task, @NotNull final Blueprint<? extends Task, ? extends Instance> blueprint) {
		final NsLiveInstance nli = new NsLiveInstance(null, (NsTask) task, (NsBlueprint) blueprint, (NsdInstance) instance);
		liveInstanceJpa.save(nli);
	}

	@Override
	public void deleteLiveInstance(final UUID removedLiveInstanceId) {
		liveInstanceJpa.deleteById(removedLiveInstanceId);
	}

	@Override
	public Blueprint<NsTask, NsdInstance> getBluePrint(final UUID blueprintId) {
		return blueprintService.findById(blueprintId);
	}

	@Override
	public Instance getInstance(final UUID blueprintId) {
		return instanceService.findById(blueprintId);
	}

	@Override
	public PackageBase getPackage(final Instance instance) {
		final NsdInstance inst = (NsdInstance) instance;
		return packageService.findById(inst.getNsdInfo().getId());
	}

	@Override
	public Instance getInstance(final Blueprint<NsTask, NsdInstance> blueprint) {
		final NsBlueprint blue = (NsBlueprint) blueprint;
		return blue.getInstance();
	}

	@Override
	public @NotNull Blueprint<NsTask, NsdInstance> save(final Blueprint blueprint) {
		return blueprintService.save((NsBlueprint) blueprint);
	}

	@Override
	public Instance save(final Instance instance) {
		final NsdInstance nsi = (NsdInstance) instance;
		final long c = liveInstanceJpa.countByNsInstance(nsi);
		nsi.setInstantiationState(c > 0 ? InstantiationState.INSTANTIATED : InstantiationState.NOT_INSTANTIATED);
		return instanceService.save(nsi);
	}

	@Override
	public GenericExecParams createParameter(final VimConnectionInformation vimConnection, final Vim vim, final HashMap<String, String> hashMap, final Object object) {
		return new NsParameters(vim, vimConnection, hashMap, null);
	}

	@Override
	public Blueprint<NsTask, NsdInstance> updateState(final Blueprint localPlan, final OperationStatusType processing) {
		return blueprintService.updateState((NsBlueprint) localPlan, OperationStatusType.PROCESSING);
	}

	@Override
	public void fireEvent(final WorkflowEvent instantiateProcessing, final UUID id) {
		final NotificationEvent notificationEvent = convert(instantiateProcessing);
		eventManager.sendNotification(notificationEvent, id, Map.of());
	}

	@Nonnull
	private static NotificationEvent convert(final WorkflowEvent instantiateProcessing) {
		switch (instantiateProcessing) {
		case INSTANTIATE_PROCESSING:
			return NotificationEvent.NS_INSTANCE_CREATE;
		case INSTANTIATE_SUCCESS:
			return NotificationEvent.NS_INSTANTIATE;
		case INSTANTIATE_FAILED:
			return NotificationEvent.NS_INSTANTIATE;
		case SCALE_FAILED:
			return NotificationEvent.NS_SCALE;
		case SCALE_SUCCESS:
			return NotificationEvent.NS_SCALE;
		case SCALETOLEVEL_FAILED:
			return NotificationEvent.NS_SCALE;
		case SCALETOLEVEL_SUCCESS:
			return NotificationEvent.NS_SCALE;
		case TERMINATE_FAILED:
			return NotificationEvent.NS_TERMINATE;
		case TERMINATE_SUCCESS:
			return NotificationEvent.NS_TERMINATE;
		default:
			throw new GenericException("Unknow event: " + instantiateProcessing);
		}
	}

}
