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
package com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2;

import java.util.ArrayList;
import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.vnfm.OsContainerTask;
import com.ubiqube.etsi.mano.orchestrator.Bundle;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.OsContainerNode;
import com.ubiqube.etsi.mano.service.VnfInstanceGatewayService;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.vnfm.service.graph.VduNamingStrategy;
import com.ubiqube.etsi.mano.vnfm.service.graph.VnfBundleAdapter;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.vt.OsContainerVt;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class OsContainerContributor extends AbstractContributorV2Base<OsContainerTask, OsContainerVt> {
	private final VnfInstanceGatewayService vnfInstanceGatewayService;
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;
	private final VduNamingStrategy vduNamingStrategy;

	public OsContainerContributor(final VnfInstanceGatewayService vnfInstanceGatewayService, final VnfLiveInstanceJpa vnfLiveInstanceJpa, final VduNamingStrategy vduNamingStrategy) {
		super();
		this.vnfInstanceGatewayService = vnfInstanceGatewayService;
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
		this.vduNamingStrategy = vduNamingStrategy;
	}

	@Override
	public Class<? extends Node> getNode() {
		return OsContainerNode.class;
	}

	@Override
	protected List<OsContainerVt> vnfContribute(final Bundle bundle, final VnfBlueprint blueprint) {
		if (blueprint.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(blueprint.getVnfInstance());
		}
		final VnfPackage vnfPackage = ((VnfBundleAdapter) bundle).vnfPackage();
		final VnfInstance vnfInstance = vnfInstanceGatewayService.findById(blueprint.getInstance().getId());
		final List<OsContainerVt> ret = new ArrayList<>();
		vnfPackage.getOsContainer().forEach(x -> {
			final int i = vnfLiveInstanceJpa.countByVnfInstanceAndTaskToscaName(vnfInstance, x.getName());
			if (i > 0) {
				return;
			}
			final OsContainerTask t = createTask(OsContainerTask::new);
			t.setAlias(vduNamingStrategy.osContainerName(vnfInstance, x.getName()));
			t.setToscaName(x.getName());
			t.setBlueprint(blueprint);
			t.setType(ResourceTypeEnum.CNF);
			t.setChangeType(ChangeType.ADDED);
			t.setOsContainer(x);
			ret.add(new OsContainerVt(t));
		});
		return ret;
	}

	private List<OsContainerVt> doTerminatePlan(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, OsContainerTask.class.getSimpleName());
		return instances.stream().map(x -> {
			final OsContainerTask task = createDeleteTask(OsContainerTask::new, x);
			task.setType(ResourceTypeEnum.CNF);
			task.setRemovedLiveInstance(x.getId());
			task.setVimResourceId(x.getResourceId());
			return new OsContainerVt(task);
		}).toList();
	}

}
