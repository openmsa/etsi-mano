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
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppBlueprint;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppInstance;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppLiveInstance;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppTask;
import com.ubiqube.etsi.mano.dao.mec.pkg.AppPkg;
import com.ubiqube.etsi.mano.dao.mec.tasks.AppComputeTask;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Compute;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.wfe2.DependencyBuilder;
import com.ubiqube.etsi.mec.mepm.repositories.AppLiveInstanceJpa;
import com.ubiqube.etsi.mec.mepm.service.AppInstanceService;
import com.ubiqube.etsi.mec.mepm.service.AppPackageService;
import com.ubiqube.etsi.mec.mepm.service.graph.AbstractAppPlanContributor;
import com.ubiqube.etsi.mec.mepm.service.graph.AppParameters;
import com.ubiqube.etsi.mec.mepm.service.graph.uow.AppComputeUow;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class AppVdu extends AbstractAppPlanContributor {
	private final AppInstanceService instanceService;
	private final AppPackageService packageService;
	private final AppLiveInstanceJpa liveInstanceJpa;

	public AppVdu(final AppInstanceService appInstanceService, final AppPackageService appPackageService, final AppLiveInstanceJpa appLiveInstanceJpa) {
		super();
		this.instanceService = appInstanceService;
		this.packageService = appPackageService;
		this.liveInstanceJpa = appLiveInstanceJpa;
	}

	@Override
	public Class<? extends Node> getContributionType() {
		return Compute.class;
	}

	@Override
	public List<AppTask> contribute(final AppPkg bundle, final AppBlueprint plan, final Set<ScaleInfo> scaling) {
		if (plan.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(plan.getAppInstance());
		}
		final List<AppTask> ret = new ArrayList<>();
		final int currentInst = liveInstanceJpa.findByAppInstanceAndClass(plan.getAppInstance(), AppComputeTask.class.getSimpleName()).size();
		final VnfCompute compute = bundle.getVirtualComputeDescriptor();
		if (currentInst < 1) {
			ret.addAll(addInstance(compute));
		} else if (currentInst > 1) {
			ret.addAll(removeInstance(compute, plan));
		}
		return ret;
	}

	private List<AppTask> doTerminatePlan(final AppInstance appInstance) {
		final List<AppLiveInstance> instances = liveInstanceJpa.findByAppInstanceAndClass(appInstance, AppComputeTask.class.getSimpleName());
		return instances.stream().map(x -> {
			final AppComputeTask computeTask = new AppComputeTask();
			computeTask.setChangeType(ChangeType.REMOVED);
			computeTask.setStatus(PlanStatusType.NOT_STARTED);
			computeTask.setType(ResourceTypeEnum.COMPUTE);
			computeTask.setToscaName(x.getTask().getToscaName());
			computeTask.setAlias(x.getTask().getAlias());
			computeTask.setVimResourceId(x.getResourceId());
			computeTask.setRemovedVnfLiveInstance(x.getId());
			computeTask.setVnfCompute(((AppComputeTask) x.getTask()).getVnfCompute());
			return computeTask;
		}).collect(Collectors.toList());
	}

	private List<AppTask> removeInstance(final VnfCompute vnfCompute, final AppBlueprint plan) {
		final List<AppLiveInstance> instantiated = instanceService.getLiveComputeInstanceOf(plan.getAppInstance());
		final List<AppTask> ret = new ArrayList<>();
		instantiated.forEach(x -> {
			final AppComputeTask computeTask = new AppComputeTask();
			computeTask.setVnfCompute(vnfCompute);
			computeTask.setChangeType(ChangeType.REMOVED);
			computeTask.setStatus(PlanStatusType.NOT_STARTED);
			computeTask.setType(ResourceTypeEnum.COMPUTE);
			computeTask.setToscaName(vnfCompute.getToscaName());
			computeTask.setAlias(x.getTask().getAlias());
			computeTask.setVimResourceId(x.getResourceId());
			computeTask.setRemovedVnfLiveInstance(x.getId());
			ret.add(computeTask);
		});
		return ret;
	}

	private static List<AppTask> addInstance(final VnfCompute vnfCompute) {
		final AppComputeTask computeTask = new AppComputeTask();
		computeTask.setVnfCompute(vnfCompute);
		computeTask.setChangeType(ChangeType.ADDED);
		computeTask.setStatus(PlanStatusType.NOT_STARTED);
		computeTask.setType(ResourceTypeEnum.COMPUTE);
		computeTask.setAlias(vnfCompute.getToscaName());
		computeTask.setToscaName(vnfCompute.getToscaName());
		return Arrays.asList(computeTask);
	}

	@Override
	public List<UnitOfWork<AppTask, AppParameters>> convertTasksToExecNode(final Set<AppTask> tasks, final AppBlueprint plan) {
		final AppInstance vnfInstance = instanceService.findById(plan.getAppInstance().getId());
		final AppPkg appPackage = packageService.findById(vnfInstance.getAppPkg());
		return tasks.stream()
				.filter(x -> x instanceof AppComputeTask)
				.map(AppComputeTask.class::cast)
				.map(x -> {
					final VnfCompute vnfCompute = appPackage.getVirtualComputeDescriptor();
					final Set<VnfLinkPort> linkPort = appPackage.getVnfLinkPort();
					return new AppComputeUow(x, vnfCompute, linkPort);
				})
				.collect(Collectors.toList());
	}

	@Override
	public void getDependencies(final DependencyBuilder dependencyBuilder) {
		dependencyBuilder.connectionFrom(Network.class);
	}
}
