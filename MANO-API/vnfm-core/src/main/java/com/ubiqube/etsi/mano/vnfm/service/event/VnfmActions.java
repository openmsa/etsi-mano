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
package com.ubiqube.etsi.mano.vnfm.service.event;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.Instance;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.service.event.AbstractGenericAction;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.vnfm.service.ManoGrantService;
import com.ubiqube.etsi.mano.vnfm.service.VnfBlueprintService;
import com.ubiqube.etsi.mano.vnfm.service.VnfInstanceService;
import com.ubiqube.etsi.mano.vnfm.service.VnfInstanceServiceVnfm;
import com.ubiqube.etsi.mano.vnfm.service.graph.VnfWorkflow;
import com.ubiqube.etsi.mano.vnfm.service.graph.vnfm.VnfParameters;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfmActions extends AbstractGenericAction {

	private final VimManager vimManager;

	private final VnfInstanceService vnfInstancesService;

	private final VnfBlueprintService blueprintService;

	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	private final VnfInstanceServiceVnfm vnfInstanceServiceVnfm;

	public VnfmActions(final VimManager _vimManager, final VnfOrchestrationAdapter orchestrationAdapter, final VnfInstanceService _vnfInstancesService,
			final VnfWorkflow _planner, final VnfBlueprintService _blueprintService, final ManoGrantService _vimResourceService, final VnfLiveInstanceJpa _vnfLiveInstanceJpa,
			final VnfInstanceServiceVnfm vnfInstanceServiceVnfm) {
		super(_vimManager, _planner, _vimResourceService, orchestrationAdapter);
		vimManager = _vimManager;
		vnfInstancesService = _vnfInstancesService;
		blueprintService = _blueprintService;
		vnfLiveInstanceJpa = _vnfLiveInstanceJpa;
		this.vnfInstanceServiceVnfm = vnfInstanceServiceVnfm;
	}

	public void vnfOperate(@NotNull final UUID blueprintId) {
		final VnfBlueprint blueprint = blueprintService.findById(blueprintId);
		final VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(blueprint.getVnfInstance().getId());
		final VimConnectionInformation vimConnection = vnfInstance.getVimConnectionInfo().iterator().next();
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		blueprint.getTasks().forEach(x -> {
			if (blueprint.getOperateChanges().getTerminationType() == OperationalStateType.STARTED) {
				vim.startServer(vimConnection, x.getVimResourceId());
				// vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STARTED);
			} else {
				vim.stopServer(vimConnection, x.getVimResourceId());
				// vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STOPPED);
			}
		});
		vnfInstancesService.save(vnfInstance);
	}

	private Map<String, String> getLiveVl(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> res = vnfInstancesService.getLiveVirtualLinkInstanceOf(vnfInstance);
		return res.stream()
				.collect(Collectors.toMap(x -> x.getTask().getToscaName(), x -> x.getTask().getVimResourceId()));
	}

	@Override
	protected GenericExecParams buildContext(final VimConnectionInformation vimConnection, final Vim vim, final Blueprint blueprint, final Instance vnfInstance) {
		final Map<String, String> context = blueprint.getParameters().getExtManagedVirtualLinks().stream()
				.collect(Collectors.toMap(ExtManagedVirtualLinkDataEntity::getVnfVirtualLinkDescId, ExtManagedVirtualLinkDataEntity::getResourceId));
		// Add all present VL if any.
		context.putAll(getLiveVl((VnfInstance) vnfInstance));
		return new VnfParameters(vimConnection, vim, vnfLiveInstanceJpa, context, null);
	}

	public void vnfChangeVnfConn(@NotNull final UUID blueprintId) {
		final VnfBlueprint blueprint = blueprintService.findById(blueprintId);
		final VnfInstance vnfInstance = vnfInstanceServiceVnfm.findById(blueprint.getVnfInstance().getId());
		final Set<ExtVirtualLinkDataEntity> evl = blueprint.getChangeExtVnfConnRequest().getExtVirtualLinks();
		evl.forEach(x -> {
			x.getExtCps().forEach(y -> {
				final List<VnfLiveInstance> vli = vnfLiveInstanceJpa.findByTaskVnfInstanceAndToscaName(vnfInstance, y.getCpdId());
			});
		});

	}
}
