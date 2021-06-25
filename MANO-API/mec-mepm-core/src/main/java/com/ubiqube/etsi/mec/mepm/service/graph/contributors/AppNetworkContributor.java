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
package com.ubiqube.etsi.mec.mepm.service.graph.contributors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppBlueprint;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppInstance;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppLiveInstance;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppTask;
import com.ubiqube.etsi.mano.dao.mec.pkg.AppPkg;
import com.ubiqube.etsi.mano.dao.mec.tasks.AppNetworkTask;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.wfe2.DependencyBuilder;
import com.ubiqube.etsi.mano.service.vim.node.Start;
import com.ubiqube.etsi.mec.mepm.repositories.AppLiveInstanceJpa;
import com.ubiqube.etsi.mec.mepm.service.AppBlueprintService;
import com.ubiqube.etsi.mec.mepm.service.graph.AbstractAppPlanContributor;
import com.ubiqube.etsi.mec.mepm.service.graph.AppParameters;
import com.ubiqube.etsi.mec.mepm.service.graph.uow.AppVirtualLinkUow;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class AppNetworkContributor extends AbstractAppPlanContributor {

	private final AppBlueprintService planService;
	private final AppLiveInstanceJpa vnfLiveInstanceJpa;

	public AppNetworkContributor(final AppBlueprintService planService, final AppLiveInstanceJpa vnfLiveInstanceJpa) {
		super();
		this.planService = planService;
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
	}

	@Override
	public Class<? extends Node> getContributionType() {
		return Network.class;
	}

	@Override
	public List<AppTask> contribute(final AppPkg bundle, final AppBlueprint blueprint, final Set<ScaleInfo> scaling) {
		if (blueprint.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(blueprint.getAppInstance());
		}
		final ArrayList<AppTask> ret = new ArrayList<>();
		final Set<VnfVl> vls = bundle.getVnfVl();
		for (final VnfVl vnfVl : vls) {
			final int num = planService.getNumberOfLiveVl(blueprint.getAppInstance(), vnfVl);
			if (num == 0) {
				final AppNetworkTask networkTask = createTask(AppNetworkTask::new);
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

	private List<AppTask> doTerminatePlan(final AppInstance vnfInstance) {
		final List<AppLiveInstance> instances = vnfLiveInstanceJpa.findByAppInstanceAndClass(vnfInstance, AppNetworkTask.class.getSimpleName());
		return instances.stream().map(x -> {
			final AppNetworkTask networkTask = createTask(AppNetworkTask::new);
			networkTask.setAlias(x.getTask().getAlias());
			networkTask.setChangeType(ChangeType.REMOVED);
			networkTask.setToscaName(x.getTask().getToscaName());
			networkTask.setType(ResourceTypeEnum.VL);
			networkTask.setRemovedVnfLiveInstance(x.getId());
			networkTask.setVimResourceId(x.getResourceId());
			networkTask.setVnfVl(((AppNetworkTask) (x.getTask())).getVnfVl());
			return networkTask;
		}).collect(Collectors.toList());
	}

	@Override
	public List<UnitOfWork<AppTask, AppParameters>> convertTasksToExecNode(final Set<AppTask> tasks, final AppBlueprint blueprint) {
		return tasks.stream()
				.filter(x -> x instanceof AppNetworkTask)
				.map(AppNetworkTask.class::cast)
				.map(x -> {
					final VnfVl vnfVl = x.getVnfVl();
					return new AppVirtualLinkUow(x, vnfVl);
				})
				.collect(Collectors.toList());
	}

	@Override
	public void getDependencies(final DependencyBuilder dependencyBuilder) {
		dependencyBuilder.connectionFrom(Start.class);
	}

}
