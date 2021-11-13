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

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.SecurityGroup;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.vnfm.AffinityRuleTask;
import com.ubiqube.etsi.mano.orchestrator.Bundle;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.AffinityRuleNode;
import com.ubiqube.etsi.mano.service.VnfInstanceGatewayService;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.vnfm.service.graph.VduNamingStrategy;
import com.ubiqube.etsi.mano.vnfm.service.graph.VnfBundleAdapter;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.vt.AffinityRuleVt;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class AffinityRuleContributor extends AbstractContributorV2Base<AffinityRuleTask, AffinityRuleVt> {
	private final VnfInstanceGatewayService vnfInstanceGatewayService;
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;
	private final VduNamingStrategy vduNamingStrategy;

	public AffinityRuleContributor(final VnfInstanceGatewayService vnfInstanceGatewayService, final VnfLiveInstanceJpa vnfLiveInstanceJpa, final VduNamingStrategy vduNamingStrategy) {
		this.vnfInstanceGatewayService = vnfInstanceGatewayService;
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
		this.vduNamingStrategy = vduNamingStrategy;
	}

	@Override
	public Class<? extends Node> getNode() {
		return AffinityRuleNode.class;
	}

	@Override
	protected List<AffinityRuleVt> vnfContribute(final Bundle bundle, final VnfBlueprint blueprint) {
		if (blueprint.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(blueprint.getVnfInstance());
		}
		final VnfPackage vnfPackage = ((VnfBundleAdapter) bundle).getVnfPackage();
		final VnfInstance vnfInstance = vnfInstanceGatewayService.findById(blueprint.getInstance().getId());
		final List<AffinityRuleVt> ret = new ArrayList<>();
		vnfPackage.getAffinityRules().forEach(x -> {
			final int i = vnfLiveInstanceJpa.countByVnfInstanceAndTaskToscaName(vnfInstance, x.getToscaName());
			if (i > 0) {
				return;
			}
			final AffinityRuleTask sgt = createTask(AffinityRuleTask::new);
			sgt.setAlias(vduNamingStrategy.nameSingleResource(blueprint, x.getToscaName()));
			sgt.setType(ResourceTypeEnum.AFFINITY_RULE);
			sgt.setToscaName(x.getToscaName());
			sgt.setAffinityRule(x);
			ret.add(new AffinityRuleVt(sgt));
		});
		return ret;
	}

	private List<AffinityRuleVt> doTerminatePlan(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, SecurityGroup.class.getSimpleName());
		return instances.stream().map(x -> {
			final AffinityRuleTask computeTask = createDeleteTask(AffinityRuleTask::new, x);
			computeTask.setType(ResourceTypeEnum.AFFINITY_RULE);
			computeTask.setRemovedLiveInstance(x.getId());
			computeTask.setVimResourceId(x.getResourceId());
			return new AffinityRuleVt(computeTask);
		}).toList();
	}

}
