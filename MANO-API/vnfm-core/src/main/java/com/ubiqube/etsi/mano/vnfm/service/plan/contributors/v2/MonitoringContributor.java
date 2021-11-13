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
import java.util.stream.Collectors;

import javax.annotation.Priority;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.dao.mano.v2.MonitoringTask;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.orchestrator.Bundle;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Monitoring;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.vt.MonitoringVt;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Priority(200)
@Service
public class MonitoringContributor extends AbstractContributorV2Base<MonitoringTask, MonitoringVt> {
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public MonitoringContributor(final VnfLiveInstanceJpa vnfLiveInstanceJpa) {
		super();
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
	}

	@Override
	public Class<? extends Node> getNode() {
		return Monitoring.class;
	}

	@Override
	protected List<MonitoringVt> vnfContribute(final Bundle bundle, final VnfBlueprint plan) {
		if (plan.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(plan.getVnfInstance());
		}
		final List<MonitoringVt> ret = new ArrayList<>();
		plan.getTasks().stream()
				.filter(ComputeTask.class::isInstance)
				.map(ComputeTask.class::cast)
				.forEach(x -> {
					if (x.getChangeType() == ChangeType.REMOVED) {
						removeMonitoring(ret, x, plan.getInstance());
					} else {
						createMonitoring(ret, x, plan.getInstance());
					}
				});
		return ret;
	}

	private void createMonitoring(final List<MonitoringVt> ret, final ComputeTask x, final VnfInstance vnfInstance) {
		x.getVnfCompute().getMonitoringParameters().forEach(y -> {
			final int cnt = countVli(vnfInstance, x.getAlias());
			if (cnt != 0) {
				return;
			}
			final MonitoringTask task = createTask(MonitoringTask::new);
			task.setType(ResourceTypeEnum.MONITORING);
			task.setToscaName(y + "-" + x.getAlias());
			task.setAlias(y + "-" + x.getAlias());
			task.setParentAlias(x.getAlias());
			task.setMonitoringParams(y);
			ret.add(new MonitoringVt(task));
		});
	}

	private int countVli(final VnfInstance vnfInstance, final String alias) {
		final List<VnfLiveInstance> vs = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, MonitoringTask.class.getSimpleName());
		int i = 0;
		for (final VnfLiveInstance vnfLiveInstance : vs) {
			if (vnfLiveInstance.getTask()instanceof final MonitoringTask t && t.getParentAlias().equals(alias)) {
				i++;
			}
		}
		return i;
	}

	private void removeMonitoring(final List<MonitoringVt> ret, final ComputeTask computeTask, final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, MonitoringTask.class.getSimpleName());
		findMonitoringByName(computeTask.getAlias(), instances).ifPresent(x -> {
			final MonitoringTask task = createDeleteTask(MonitoringTask::new, x);
			task.setType(ResourceTypeEnum.MONITORING);
			task.setRemovedLiveInstance(x.getId());
			ret.add(new MonitoringVt(task));
		});

	}

	private static Optional<VnfLiveInstance> findMonitoringByName(final String alias, final List<VnfLiveInstance> instances) {
		for (final VnfLiveInstance vnfLiveInstance : instances) {
			final MonitoringTask t = (MonitoringTask) vnfLiveInstance.getTask();
			if (t.getToscaName().equals(alias)) {
				return Optional.of(vnfLiveInstance);
			}
		}
		return Optional.empty();
	}

	private List<MonitoringVt> doTerminatePlan(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, MonitoringTask.class.getSimpleName());
		return instances.stream().map(x -> {
			final MonitoringTask task = createDeleteTask(MonitoringTask::new, x);
			task.setType(ResourceTypeEnum.MONITORING);
			task.setRemovedLiveInstance(x.getId());
			return new MonitoringVt(task);
		}).collect(Collectors.toList());
	}

}
