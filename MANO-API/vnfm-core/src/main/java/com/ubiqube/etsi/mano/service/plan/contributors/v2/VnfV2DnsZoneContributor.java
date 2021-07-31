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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Priority;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.DnsZoneTask;
import com.ubiqube.etsi.mano.dao.mano.v2.NetworkTask;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.orchestrator.Bundle;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.DnsZone;
import com.ubiqube.etsi.mano.service.graph.NodeNaming;
import com.ubiqube.etsi.mano.service.plan.contributors.v2.vt.DnsZoneVT;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Priority(100)
//@Service
public class VnfV2DnsZoneContributor extends AbstractContributorV2Base<DnsZoneTask, DnsZoneVT> {
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public VnfV2DnsZoneContributor(final VnfLiveInstanceJpa vnfLiveInstanceJpa) {
		super();
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
	}

	@Override
	public List<DnsZoneVT> vnfContribute(final Bundle bundle, final VnfBlueprint plan) {
		if (plan.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(plan.getVnfInstance());
		}
		final DnsZoneTask dnsZoneTask = new DnsZoneTask();
		dnsZoneTask.setToscaName(NodeNaming.dnsZone());
		dnsZoneTask.setAlias(plan.getVnfInstance().getId() + ".mano.vm");
		dnsZoneTask.setDomainName(plan.getVnfInstance().getId() + ".mano.vm");
		dnsZoneTask.setChangeType(ChangeType.ADDED);
		dnsZoneTask.setType(ResourceTypeEnum.DNSZONE);
		return Arrays.asList(new DnsZoneVT(dnsZoneTask));
	}

	private List<DnsZoneVT> doTerminatePlan(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, NetworkTask.class.getSimpleName());
		return instances.stream().map(x -> {
			final DnsZoneTask dnsZoneTask = createTask(DnsZoneTask::new, x.getTask());
			dnsZoneTask.setToscaName(NodeNaming.dnsZone());
			dnsZoneTask.setType(ResourceTypeEnum.DNSZONE);
			return new DnsZoneVT(dnsZoneTask);
		}).collect(Collectors.toList());
	}

	@Override
	public Class<? extends Node> getNode() {
		return DnsZone.class;
	}

}
