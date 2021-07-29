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
package com.ubiqube.etsi.mano.service.plan.contributors.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.v2.ExternalCpTask;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.orchestrator.Bundle;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.VnfExtCp;
import com.ubiqube.etsi.mano.service.graph.VnfBundleAdapter;
import com.ubiqube.etsi.mano.service.plan.contributors.v2.vt.VnfExtCpVt;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfExtCpContributor extends AbstractContributorV2Base<ExternalCpTask, VnfExtCpVt, VnfBlueprint> {
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public VnfExtCpContributor(final VnfLiveInstanceJpa vnfLiveInstanceJpa) {
		super();
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
	}

	@Override
	public List<VnfExtCpVt> contribute(final Bundle bundle, final VnfBlueprint plan) {
		if (plan.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(plan.getVnfInstance());
		}
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(plan.getVnfInstance(), ExternalCpTask.class.getSimpleName());
		final VnfPackage vnfPackage = ((VnfBundleAdapter) bundle).getVnfPackage();
		final List<VnfExtCpVt> ret = new ArrayList<>();
		vnfPackage.getVnfExtCp().stream().forEach(x -> {
			final Optional<VnfVl> vl = vnfPackage.getVnfVl().stream()
					.filter(y -> y.getToscaName().equals(x.getInternalVirtualLink()))
					.findFirst();
			vl.ifPresent(y -> {
				final ExternalCpTask task = createTask(ExternalCpTask::new);
				task.setToscaName(x.getToscaName());
				task.setAlias(y.getToscaName() + "-" + x.getToscaName());
				task.setChangeType(ChangeType.ADDED);
				task.setType(ResourceTypeEnum.LINKPORT);
				task.setVnfExtCp(x);
				ret.add(new VnfExtCpVt(task));
			});
		});
		return ret;
	}

	private List<VnfExtCpVt> doTerminatePlan(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, ExternalCpTask.class.getSimpleName());
		return instances.stream().map(x -> {
			final ExternalCpTask networkTask = createDeleteTask(ExternalCpTask::new, x);
			networkTask.setType(ResourceTypeEnum.LINKPORT);
			networkTask.setVnfExtCp(((ExternalCpTask) x.getTask()).getVnfExtCp());
			return new VnfExtCpVt(networkTask);
		}).collect(Collectors.toList());
	}

	@Override
	public Class<? extends Node> getNode() {
		return VnfExtCp.class;
	}

}
