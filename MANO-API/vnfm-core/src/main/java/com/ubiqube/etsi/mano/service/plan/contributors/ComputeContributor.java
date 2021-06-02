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
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.VduNamingStrategy;
import com.ubiqube.etsi.mano.service.graph.vnfm.ComputeUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.VnfParameters;
import com.ubiqube.etsi.mano.service.graph.wfe2.DependencyBuilder;
import com.ubiqube.etsi.mano.service.plan.ScalingStrategy;
import com.ubiqube.etsi.mano.service.plan.ScalingStrategy.NumberOfCompute;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Compute;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Network;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class ComputeContributor extends AbstractVnfPlanContributor {

	private final ScalingStrategy scalingStrategy;
	private final VduNamingStrategy vduNamingStrategy;
	private final VnfInstanceService vnfInstanceService;
	private final VnfPackageService vnfPackageService;
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public ComputeContributor(final ScalingStrategy _scalingStrategy, final VduNamingStrategy _vduNamingStrategy, final VnfInstanceService _vnfInstanceService, final VnfPackageService _vnfPackageService, final VnfLiveInstanceJpa _vnfLiveInstanceJpa) {
		scalingStrategy = _scalingStrategy;
		vduNamingStrategy = _vduNamingStrategy;
		vnfInstanceService = _vnfInstanceService;
		vnfPackageService = _vnfPackageService;
		vnfLiveInstanceJpa = _vnfLiveInstanceJpa;
	}

	@Override
	public Class<? extends Node> getContributionType() {
		return Compute.class;
	}

	@Override
	public List<VnfTask> contribute(final VnfPackage vnfPackage, final VnfBlueprint plan, final Set<ScaleInfo> scaling) {
		if (plan.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(plan.getVnfInstance());
		}
		final List<VnfTask> ret = new ArrayList<>();
		vnfPackage.getVnfCompute().forEach(x -> {
			final NumberOfCompute numInst = scalingStrategy.getNumberOfCompute(plan, vnfPackage, scaling, x);
			if (numInst.getCurrent() < numInst.getWanted()) {
				ret.addAll(addInstance(x, plan, numInst.getScaleInfo(), numInst));
			} else if (numInst.getCurrent() > numInst.getWanted()) {
				ret.addAll(removeInstance(x, plan, numInst.getScaleInfo(), numInst));
			}
		});
		return ret;
	}

	private List<VnfTask> doTerminatePlan(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, ComputeTask.class.getSimpleName());
		return instances.stream().map(x -> {
			final ComputeTask computeTask = new ComputeTask();
			computeTask.setChangeType(ChangeType.REMOVED);
			computeTask.setStatus(PlanStatusType.NOT_STARTED);
			computeTask.setType(ResourceTypeEnum.COMPUTE);
			computeTask.setToscaName(x.getTask().getToscaName());
			computeTask.setAlias(x.getTask().getAlias());
			computeTask.setVimResourceId(x.getResourceId());
			computeTask.setRemovedVnfLiveInstance(x.getId());
			computeTask.setVnfCompute(((ComputeTask) x.getTask()).getVnfCompute());
			return computeTask;
		}).collect(Collectors.toList());
	}

	private List<VnfTask> removeInstance(final VnfCompute vnfCompute, final VnfBlueprint plan, final ScaleInfo scaleInfo, final NumberOfCompute numInst) {
		final int toDelete = numInst.getCurrent() - numInst.getWanted();
		final Deque<VnfLiveInstance> instantiated = vnfInstanceService.getLiveComputeInstanceOf(plan, vnfCompute);
		final List<VnfTask> ret = new ArrayList<>();
		for (int i = 0; i < toDelete; i++) {
			final ComputeTask computeTask = new ComputeTask();
			final VnfLiveInstance inst = instantiated.pop();
			computeTask.setVnfCompute(vnfCompute);
			computeTask.setChangeType(ChangeType.REMOVED);
			computeTask.setStatus(PlanStatusType.NOT_STARTED);
			computeTask.setType(ResourceTypeEnum.COMPUTE);
			computeTask.setScaleInfo(scaleInfo);
			computeTask.setToscaName(vnfCompute.getToscaName());
			computeTask.setAlias(inst.getTask().getAlias());
			computeTask.setVimResourceId(inst.getResourceId());
			computeTask.setRemovedVnfLiveInstance(inst.getId());
			ret.add(computeTask);
		}
		return ret;
	}

	private List<VnfTask> addInstance(final VnfCompute vnfCompute, final VnfBlueprint plan, final ScaleInfo scaleInfo, final NumberOfCompute numInst) {
		final int toCreate = numInst.getWanted() - numInst.getCurrent();
		final List<VnfTask> ret = new ArrayList<>();
		for (int i = 0; i < toCreate; i++) {
			final ComputeTask computeTask = new ComputeTask();
			computeTask.setVnfCompute(vnfCompute);
			computeTask.setChangeType(ChangeType.ADDED);
			computeTask.setStatus(PlanStatusType.NOT_STARTED);
			computeTask.setType(ResourceTypeEnum.COMPUTE);
			computeTask.setScaleInfo(scaleInfo);
			computeTask.setAlias(vduNamingStrategy.nameVdu(plan, vnfCompute.getToscaName(), numInst.getCurrent() + i));
			computeTask.setToscaName(vnfCompute.getToscaName());
			ret.add(computeTask);
		}
		return ret;
	}

	@Override
	public List<UnitOfWork<VnfTask, VnfParameters>> convertTasksToExecNode(final Set<VnfTask> tasks, final VnfBlueprint plan) {
		final VnfInstance vnfInstance = vnfInstanceService.findById(plan.getVnfInstance().getId());
		final VnfPackage vnfPackage = vnfPackageService.findById(vnfInstance.getVnfPkg().getId());
		return tasks.stream()
				.filter(ComputeTask.class::isInstance)
				.map(ComputeTask.class::cast)
				.map(x -> {
					final Optional<VnfCompute> vnfCompute = vnfPackageService.findComputeById(x.getVnfCompute().getId());
					final Set<VnfLinkPort> linkPort = vnfPackageService.findVnfVirtualLinks(vnfPackage);
					return new ComputeUow(x, vnfCompute.get(), linkPort);
				})
				.collect(Collectors.toList());
	}

	@Override
	public void getDependencies(final DependencyBuilder dependencyBuilder) {
		dependencyBuilder.connectionFrom(Network.class);
	}
}
