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
package com.ubiqube.etsi.mano.service.plan.contributors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.ExternalCpTask;
import com.ubiqube.etsi.mano.dao.mano.v2.NetworkTask;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.VnfExtCpUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.VnfParameters;
import com.ubiqube.etsi.mano.service.graph.wfe2.DependencyBuilder;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Network;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.VnfExtCp;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfExtCpContributor extends AbstractVnfPlanContributor {
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public VnfExtCpContributor(final VnfLiveInstanceJpa _vnfLiveInstanceJpa) {
		vnfLiveInstanceJpa = _vnfLiveInstanceJpa;
	}

	@Override
	public Class<? extends Node> getContributionType() {
		return VnfExtCp.class;
	}

	@Override
	public List<VnfTask> contribute(final VnfPackage vnfPackage, final VnfBlueprint plan, final Set<ScaleInfo> scaling) {
		if (plan.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(plan.getVnfInstance());
		}
		final List<VnfTask> ret = new ArrayList<>();
		vnfPackage.getVnfExtCp().stream().forEach(x -> {
			final Optional<NetworkTask> vl = plan.getTasks().stream()
					.filter(y -> y instanceof NetworkTask)
					.map(y -> (NetworkTask) y)
					.filter(y -> y.getToscaName().equals(x.getInternalVirtualLink()))
					.findFirst();
			vl.ifPresent(y -> {
				final ExternalCpTask task = createTask(ExternalCpTask::new);
				task.setToscaName(x.getToscaName());
				task.setAlias(y.getToscaName() + "-" + x.getToscaName());
				task.setChangeType(ChangeType.ADDED);
				task.setType(ResourceTypeEnum.LINKPORT);
				task.setVnfExtCp(x);
				ret.add(task);
			});
		});
		return ret;
	}

	private List<VnfTask> doTerminatePlan(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, ExternalCpTask.class.getSimpleName());
		return instances.stream().map(x -> {
			final ExternalCpTask networkTask = createTask(ExternalCpTask::new);
			networkTask.setAlias(x.getTask().getAlias());
			networkTask.setChangeType(ChangeType.REMOVED);
			networkTask.setToscaName(x.getTask().getToscaName());
			networkTask.setType(ResourceTypeEnum.VL);
			networkTask.setRemovedVnfLiveInstance(x.getId());
			networkTask.setVimResourceId(x.getResourceId());
			networkTask.setVnfExtCp(((ExternalCpTask) x.getTask()).getVnfExtCp());
			return networkTask;
		}).collect(Collectors.toList());
	}

	@Override
	public List<UnitOfWork<VnfTask, VnfParameters>> convertTasksToExecNode(final Set<VnfTask> tasks, final VnfBlueprint plan) {
		return tasks.stream()
				.filter(x -> x instanceof ExternalCpTask)
				.map(x -> (ExternalCpTask) x)
				.map(VnfExtCpUow::new)
				.collect(Collectors.toList());
	}

	@Override
	public void getDependencies(final DependencyBuilder dependencyBuilder) {
		dependencyBuilder.connectionFrom(Network.class);
	}

}
