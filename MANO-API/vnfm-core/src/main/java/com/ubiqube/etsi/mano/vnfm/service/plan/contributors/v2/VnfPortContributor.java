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
import java.util.Optional;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.ExternalCpTask;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.orchestrator.Bundle;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.vnfm.service.graph.VnfBundleAdapter;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.vt.PortVt;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class PortContributor extends AbstractContributorV2Base<ExternalCpTask, PortVt> {
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public PortContributor(final VnfLiveInstanceJpa vnfLiveInstanceJpa) {
		super();
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
	}

	@Override
	public List<PortVt> vnfContribute(final Bundle bundle, final VnfBlueprint plan) {
		if (plan.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(plan.getVnfInstance());
		}
		final VnfPackage vnfPackage = ((VnfBundleAdapter) bundle).vnfPackage();
		final List<PortVt> ret = new ArrayList<>();
		vnfPackage.getVnfExtCp().stream().forEach(x -> {
			final Optional<VnfCompute> compute = vnfPackage.getVnfCompute().stream()
					.filter(y -> y.getToscaName().equals(x.getInternalVirtualLink()))
					.findFirst();
			compute.ifPresent(y -> {
				final ExternalCpTask task = createTask(ExternalCpTask::new);
				task.setToscaName(x.getToscaName());
				task.setAlias(y.getToscaName() + "-" + x.getToscaName());
				task.setChangeType(ChangeType.ADDED);
				task.setType(ResourceTypeEnum.LINKPORT);
				task.setVnfExtCp(x);
				ret.add(new PortVt(task));
			});
		});
		return ret;
	}

	private List<PortVt> doTerminatePlan(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, ExternalCpTask.class.getSimpleName());
		return instances.stream().map(x -> {
			final ExternalCpTask task = createDeleteTask(ExternalCpTask::new, x);
			task.setType(ResourceTypeEnum.LINKPORT);
			task.setVnfExtCp(((ExternalCpTask) x.getTask()).getVnfExtCp());
			return new PortVt(task);
		}).toList();
	}

	@Override
	public Class<? extends Node> getNode() {
		return com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.VnfExtCp.class;
	}

}
