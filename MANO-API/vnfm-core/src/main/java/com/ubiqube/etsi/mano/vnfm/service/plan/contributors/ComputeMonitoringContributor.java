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
package com.ubiqube.etsi.mano.vnfm.service.plan.contributors;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.dao.mano.v2.MonitoringTask;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Compute;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Monitoring;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.wfe2.DependencyBuilder;
import com.ubiqube.etsi.mano.vnfm.service.graph.vnfm.MonitoringUow;
import com.ubiqube.etsi.mano.vnfm.service.graph.vnfm.VnfParameters;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class ComputeMonitoringContributor extends AbstractVnfPlanContributor {

	@Override
	public Class<? extends Node> getContributionType() {
		return Monitoring.class;
	}

	@Override
	public List<VnfTask> contribute(final VnfPackage bundle, final VnfBlueprint blueprint, final Set<ScaleInfo> scaling) {
		return blueprint.getTasks().stream()
				.filter(ComputeTask.class::isInstance)
				.map(ComputeTask.class::cast)
				.map(ComputeTask::getVnfCompute)
				.flatMap(x -> x.getMonitoringParameters().stream().map(y -> {
					final MonitoringTask task = createTask(MonitoringTask::new);
					task.setVnfCompute(x);
					task.setAlias(x.getName());
					task.setBlueprint(blueprint);
					task.setChangeType(ChangeType.ADDED);
					task.setType(ResourceTypeEnum.MONITORING);
					task.setMonitoringParams(y);
					task.setToscaName(x.getName());
					return task;
				}).collect(Collectors.toList()).stream())
				.collect(Collectors.toList());
	}

	@Override
	public List<UnitOfWork<VnfTask, VnfParameters>> convertTasksToExecNode(final Set<VnfTask> tasks, final VnfBlueprint blueprint) {
		return tasks.stream()
				.filter(MonitoringTask.class::isInstance)
				.map(MonitoringTask.class::cast)
				.map(x -> new MonitoringUow(x, x.getVnfCompute()))
				.collect(Collectors.toList());
	}

	@Override
	public void getDependencies(final DependencyBuilder dependencyBuilder) {
		dependencyBuilder.connectTo(Compute.class);
	}

}
