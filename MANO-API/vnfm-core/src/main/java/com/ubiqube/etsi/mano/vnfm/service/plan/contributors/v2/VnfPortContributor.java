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

import java.util.List;
import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.ExternalCpTask;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfPortTask;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.orchestrator.Bundle;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.VnfPortNode;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.vt.VnfPortVt;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VnfPortContributor extends AbstractContributorV2Base<VnfPortTask, VnfPortVt> {
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public VnfPortContributor(final VnfLiveInstanceJpa vnfLiveInstanceJpa) {
		super();
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
	}

	@Override
	public List<VnfPortVt> vnfContribute(final Bundle bundle, final VnfBlueprint plan) {
		if (plan.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(plan.getVnfInstance());
		}
		final Set<VnfTask> tasks = plan.getTasks();
		return tasks.stream()
				.filter(VnfCompute.class::isInstance)
				.map(VnfCompute.class::cast)
				.map(x -> {
					final VnfPortTask task = createTask(VnfPortTask::new);
					task.setToscaName(x.getToscaName());
					task.setAlias("port-" + x.getToscaName());
					task.setChangeType(ChangeType.ADDED);
					task.setType(ResourceTypeEnum.LINKPORT);
					task.setVnfLinkPort(findVnfLink((VnfPackage) bundle, x.getToscaName()));
					return new VnfPortVt(task);
				}).toList();
	}

	private static VnfLinkPort findVnfLink(final VnfPackage vnfPackage, final String vdu) {
		return vnfPackage.getVnfLinkPort().stream().filter(x -> x.getVirtualBinding().equals(vdu)).findFirst().orElseThrow();
	}

	private List<VnfPortVt> doTerminatePlan(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, ExternalCpTask.class.getSimpleName());
		return instances.stream().map(x -> {
			final VnfPortTask task = createDeleteTask(VnfPortTask::new, x);
			task.setType(ResourceTypeEnum.LINKPORT);
			return new VnfPortVt(task);
		}).toList();
	}

	@Override
	public Class<? extends Node> getNode() {
		return VnfPortNode.class;
	}

}
