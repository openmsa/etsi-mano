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
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.NetworkTask;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.service.BlueprintService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.VirtualLinkUow;
import com.ubiqube.etsi.mano.service.graph.wfe2.DependencyBuilder;
import com.ubiqube.etsi.mano.service.vim.node.Network;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.Start;

@Service
public class NetworkContributor extends AbstractPlanContributor {
	private final BlueprintService planService;
	private final VnfPackageService vnfPackageService;
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public NetworkContributor(final BlueprintService _planService, final VnfPackageService _vnfPackageService, final VnfLiveInstanceJpa _vnfLiveInstanceJpa) {
		planService = _planService;
		vnfPackageService = _vnfPackageService;
		vnfLiveInstanceJpa = _vnfLiveInstanceJpa;
	}

	@Override
	public Class<? extends Node> getContributionType() {
		return Network.class;
	}

	@Override
	public List<Task> contribute(final VnfPackage vnfPackage, final Blueprint plan, final Set<ScaleInfo> scaling) {
		if (plan.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(plan.getVnfInstance());
		}
		final ArrayList<Task> ret = new ArrayList<>();
		final Set<VnfVl> vls = vnfPackage.getVnfVl();
		for (final VnfVl vnfVl : vls) {
			final int num = planService.getNumberOfLiveVl(plan.getVnfInstance(), vnfVl);
			if (num == 0) {
				final NetworkTask networkTask = createTask(NetworkTask::new);
				networkTask.setAlias(vnfVl.getToscaName());
				networkTask.setChangeType(ChangeType.ADDED);
				networkTask.setToscaName(vnfVl.getToscaName());
				networkTask.setType(ResourceTypeEnum.VL);
				networkTask.setVnfVl(vnfVl);
				ret.add(networkTask);
			}
		}
		return ret;
	}

	private List<Task> doTerminatePlan(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, NetworkTask.class.getSimpleName());
		return instances.stream().map(x -> {
			final NetworkTask networkTask = createTask(NetworkTask::new);
			networkTask.setAlias(x.getTask().getAlias());
			networkTask.setChangeType(ChangeType.REMOVED);
			networkTask.setToscaName(x.getTask().getToscaName());
			networkTask.setType(ResourceTypeEnum.VL);
			networkTask.setRemovedVnfLiveInstance(x.getId());
			networkTask.setVimResourceId(x.getResourceId());
			networkTask.setVnfVl(((NetworkTask) (x.getTask())).getVnfVl());
			return networkTask;
		}).collect(Collectors.toList());
	}

	@Override
	public List<UnitOfWork> convertTasksToExecNode(final Set<Task> tasks, final Blueprint plan) {
		final ArrayList<UnitOfWork> ret = new ArrayList<>();
		tasks.stream()
				.filter(x -> x instanceof NetworkTask)
				.map(x -> (NetworkTask) x)
				.forEach(x -> {
					final VnfVl vnfVl = vnfPackageService.findVirtualLnkById(x.getVnfVl().getId()).orElseThrow();
					ret.add(new VirtualLinkUow(x, vnfVl));
				});
		return ret;
	}

	@Override
	public <U extends Node> void getDependencies(final DependencyBuilder dependencyBuilder) {
		dependencyBuilder.connectionFrom(Start.class);
	}

}
