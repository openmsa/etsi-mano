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

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.DnsZoneTask;
import com.ubiqube.etsi.mano.dao.mano.v2.NetworkTask;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.DnsZone;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.service.graph.NodeNaming;
import com.ubiqube.etsi.mano.service.graph.vnfm.DnsZoneUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.VnfParameters;
import com.ubiqube.etsi.mano.service.graph.wfe2.DependencyBuilder;
import com.ubiqube.etsi.mano.service.vim.node.Start;

public class DnsZoneContributor extends AbstractVnfPlanContributor {
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public DnsZoneContributor(final VnfLiveInstanceJpa _vnfLiveInstanceJpa) {
		vnfLiveInstanceJpa = _vnfLiveInstanceJpa;
	}

	@Override
	public Class<? extends Node> getContributionType() {
		return DnsZone.class;
	}

	@Override
	public List<VnfTask> contribute(final VnfPackage vnfPackage, final VnfBlueprint plan, final Set<ScaleInfo> scaling) {
		if (plan.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(plan.getVnfInstance());
		}
		final DnsZoneTask dnsZoneTask = new DnsZoneTask();
		dnsZoneTask.setToscaName(NodeNaming.dnsZone());
		dnsZoneTask.setAlias(plan.getVnfInstance().getId() + ".mano.vm");
		dnsZoneTask.setDomainName(plan.getVnfInstance().getId() + ".mano.vm");
		dnsZoneTask.setChangeType(ChangeType.ADDED);
		dnsZoneTask.setType(ResourceTypeEnum.DNSZONE);
		return Collections.singletonList(dnsZoneTask);
	}

	private List<VnfTask> doTerminatePlan(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, NetworkTask.class.getSimpleName());
		return instances.stream().map(x -> {
			final DnsZoneTask dnsZoneTask = new DnsZoneTask();
			dnsZoneTask.setToscaName(NodeNaming.dnsZone());
			dnsZoneTask.setAlias(x.getTask().getAlias());
			dnsZoneTask.setChangeType(ChangeType.ADDED);
			dnsZoneTask.setType(ResourceTypeEnum.DNSZONE);
			dnsZoneTask.setVimResourceId(x.getResourceId());
			return dnsZoneTask;
		}).collect(Collectors.toList());
	}

	@Override
	public List<UnitOfWork<VnfTask, VnfParameters>> convertTasksToExecNode(final Set<VnfTask> tasks, final VnfBlueprint plan) {
		return tasks.stream()
				.filter(DnsZoneTask.class::isInstance)
				.map(DnsZoneTask.class::cast)
				.map(DnsZoneUow::new)
				.collect(Collectors.toList());
	}

	@Override
	public void getDependencies(final DependencyBuilder dependencyBuilder) {
		dependencyBuilder.connectionFrom(Start.class).connectTo(Network.class);
	}

}
