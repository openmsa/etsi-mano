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
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Priority;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.StorageTask;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.orchestrator.Bundle;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Storage;
import com.ubiqube.etsi.mano.service.graph.VnfBundleAdapter;
import com.ubiqube.etsi.mano.service.plan.contributors.v2.vt.StorageVt;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Priority(200)
@Service
public class VnfV2StorageContributor extends AbstractContributorV2Base<StorageTask, StorageVt> {
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public VnfV2StorageContributor(final VnfLiveInstanceJpa vnfLiveInstanceJpa) {
		super();
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
	}

	@Override
	public List<StorageVt> vnfContribute(final Bundle bundle, final VnfBlueprint plan) {
		if (plan.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(plan.getVnfInstance());
		}
		final VnfPackage vnfPackage = ((VnfBundleAdapter) bundle).getVnfPackage();
		final List<StorageVt> ret = new ArrayList<>();
		plan.getTasks().stream()
				.filter(ComputeTask.class::isInstance)
				.map(ComputeTask.class::cast)
				.forEach(x -> {
					x.getVnfCompute().getStorages().forEach(y -> {
						final StorageTask task = createTask(StorageTask::new);
						task.setType(ResourceTypeEnum.STORAGE);
						task.setToscaName(y);
						task.setAlias(y + "-" + x.getAlias() + "-" + RandomStringUtils.random(5, true, true));
						task.setParentAlias(x.getAlias());
						task.setVnfStorage(findVnfStorage(vnfPackage.getVnfStorage(), y));
						ret.add(new StorageVt(task));
					});
				});
		return ret;
	}

	private List<StorageVt> doTerminatePlan(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, StorageTask.class.getSimpleName());
		return instances.stream().map(x -> {
			final StorageTask task = createDeleteTask(StorageTask::new, x);
			task.setType(ResourceTypeEnum.STORAGE);
			task.setRemovedLiveInstance(x.getId());
			task.setVnfStorage(((StorageTask) x.getTask()).getVnfStorage());
			return new StorageVt(task);
		}).collect(Collectors.toList());
	}

	private static VnfStorage findVnfStorage(final Set<VnfStorage> vnfStorage, final String toscaName) {
		return vnfStorage.stream()
				.filter(x -> x.getToscaName().equals(toscaName))
				.findAny()
				.orElseThrow();
	}

	@Override
	public Class<? extends Node> getNode() {
		return Storage.class;
	}

}
