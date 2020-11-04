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
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.VduNamingStrategy;
import com.ubiqube.etsi.mano.service.graph.vnfm.ComputeUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.plan.ScalingStrategy;
import com.ubiqube.etsi.mano.service.plan.ScalingStrategy.NumberOfCompute;
import com.ubiqube.etsi.mano.service.vim.node.Compute;
import com.ubiqube.etsi.mano.service.vim.node.Node;

@Service
public class ComputeContributor extends AbstractPlanContributor {

	private final ScalingStrategy scalingStrategy;
	private final VduNamingStrategy vduNamingStrategy;
	private final VnfInstanceService vnfInstanceService;
	private final VnfPackageService vnfPackageService;

	public ComputeContributor(final ScalingStrategy _scalingStrategy, final VduNamingStrategy _vduNamingStrategy, final VnfInstanceService _vnfInstanceService, final VnfPackageService _vnfPackageService) {
		scalingStrategy = _scalingStrategy;
		vduNamingStrategy = _vduNamingStrategy;
		vnfInstanceService = _vnfInstanceService;
		vnfPackageService = _vnfPackageService;
	}

	@Override
	public Class<? extends Node> getContributionType() {
		return Compute.class;
	}

	@Override
	public List<Task> contribute(final VnfPackage vnfPackage, final Blueprint plan, final Set<ScaleInfo> scaling) {
		final List<Task> ret = new ArrayList<>();
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

	private Collection<? extends Task> removeInstance(final VnfCompute vnfCompute, final Blueprint plan, final ScaleInfo scaleInfo, final NumberOfCompute numInst) {
		final int toDelete = numInst.getCurrent() - numInst.getWanted();
		final Deque<VnfLiveInstance> instantiated = vnfInstanceService.getLiveComputeInstanceOf(plan, vnfCompute);
		final List<Task> ret = new ArrayList<>();
		for (int i = 0; i < toDelete; i++) {
			final ComputeTask computeTask = new ComputeTask();
			final VnfLiveInstance inst = instantiated.pop();
			computeTask.setVnfCompute(vnfCompute);
			computeTask.setChangeType(ChangeType.REMOVED);
			computeTask.setStatus(PlanStatusType.NOT_STARTED);
			computeTask.setType(ResourceTypeEnum.COMPUTE);
			computeTask.setScaleInfo(scaleInfo);
			computeTask.setToscaName(vnfCompute.getToscaName());
			computeTask.setVimResourceId(inst.getResourceId());
		}
		return ret;
	}

	private List<Task> addInstance(final VnfCompute vnfCompute, final Blueprint plan, final ScaleInfo scaleInfo, final NumberOfCompute numInst) {
		final int toCreate = numInst.getWanted() - numInst.getCurrent();
		final List<Task> ret = new ArrayList<>();
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
	public List<UnitOfWork> convertTasksToExecNode(final Set<Task> tasks, final Blueprint plan) {
		final ArrayList<UnitOfWork> ret = new ArrayList<>();
		final VnfInstance vnfInstance = vnfInstanceService.findById(plan.getVnfInstance().getId());
		final VnfPackage vnfPackage = vnfPackageService.findById(vnfInstance.getVnfPkg().getId());
		tasks.stream()
				.filter(x -> x instanceof ComputeTask)
				.map(x -> (ComputeTask) x)
				.forEach(computeTask -> {
					final Optional<VnfCompute> vnfCompute = vnfPackageService.findComputeById(computeTask.getVnfCompute().getId());
					final Set<VnfLinkPort> linkPort = vnfPackageService.findVnfVirtualLinks(vnfPackage);
					ret.add(new ComputeUow(computeTask, vnfCompute.get(), linkPort));
				});
		return ret;
	}

}
