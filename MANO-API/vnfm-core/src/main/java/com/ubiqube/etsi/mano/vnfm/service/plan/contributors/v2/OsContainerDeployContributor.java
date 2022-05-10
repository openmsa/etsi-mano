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
import java.util.Deque;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.pkg.OsContainerDeployableUnit;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.vnfm.OsContainerDeployableTask;
import com.ubiqube.etsi.mano.orchestrator.Bundle;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.OsContainerDeployableNode;
import com.ubiqube.etsi.mano.service.VnfInstanceGatewayService;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.vnfm.service.VnfInstanceService;
import com.ubiqube.etsi.mano.vnfm.service.graph.VduNamingStrategy;
import com.ubiqube.etsi.mano.vnfm.service.graph.VnfBundleAdapter;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.vt.OsContainerDeployableVt;

/**
 * This one is in fact an Host with K8S+helm installed
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class OsContainerDeployContributor extends AbstractContributorV2Base<OsContainerDeployableTask, OsContainerDeployableVt> {
	private final VnfInstanceGatewayService vnfInstanceGatewayService;
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;
	private final VduNamingStrategy vduNamingStrategy;
	private final VnfInstanceService vnfInstanceService;

	public OsContainerDeployContributor(final VnfInstanceGatewayService vnfInstanceGatewayService, final VnfLiveInstanceJpa vnfLiveInstanceJpa, final VduNamingStrategy vduNamingStrategy, final VnfInstanceService vnfInstanceService) {
		super();
		this.vnfInstanceGatewayService = vnfInstanceGatewayService;
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
		this.vduNamingStrategy = vduNamingStrategy;
		this.vnfInstanceService = vnfInstanceService;
	}

	@Override
	public Class<? extends Node> getNode() {
		return OsContainerDeployableNode.class;
	}

	@Override
	protected List<OsContainerDeployableVt> vnfContribute(final Bundle bundle, final VnfBlueprint blueprint) {
		if (blueprint.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminatePlan(blueprint.getVnfInstance());
		}
		final VnfPackage vnfPackage = ((VnfBundleAdapter) bundle).vnfPackage();
		final VnfInstance vnfInstance = vnfInstanceGatewayService.findById(blueprint.getInstance().getId());
		final List<OsContainerDeployableVt> ret = new ArrayList<>();
		vnfPackage.getOsContainerDeployableUnits().forEach(x -> {
			final int i = vnfLiveInstanceJpa.countByVnfInstanceAndTaskToscaName(vnfInstance, x.getName());
			final int wantedInstance = getInstanceNumber(vnfInstance, x);
			if (i == wantedInstance) {
				return;
			}
			final int delta = wantedInstance - i;
			if (delta < 0) {
				removeInstance(ret, delta, x.getName(), blueprint, x);
			} else {
				createInstances(ret, x, delta, blueprint, vnfInstance);
			}
		});
		return ret;
	}

	private void createInstances(final List<OsContainerDeployableVt> ret, final OsContainerDeployableUnit x, final int delta, final VnfBlueprint blueprint, final VnfInstance vnfInstance) {
		for (int i = 0; i < delta; i++) {
			final OsContainerDeployableTask t = createTask(blueprint, vnfInstance, x);
			ret.add(new OsContainerDeployableVt(t));
		}
	}

	private void removeInstance(final List<OsContainerDeployableVt> ret, final int delta, final String alias, final VnfBlueprint plan, final OsContainerDeployableUnit oscdu) {
		final Deque<VnfLiveInstance> instantiated = vnfInstanceService.getLiveOsContainerOf(plan, oscdu);
		for (int i = 0; i < delta; i++) {
			final VnfLiveInstance inst = instantiated.pop();
			final OsContainerDeployableTask task = createDeleteTask(OsContainerDeployableTask::new, inst);
			task.setOsContainerDeployableUnit(oscdu);
			task.setType(ResourceTypeEnum.CNF);
			task.setToscaName(alias);
			task.setAlias(alias);
			ret.add(new OsContainerDeployableVt(task));
		}
	}

	private static int getInstanceNumber(final VnfInstance vnfInstance, final OsContainerDeployableUnit x) {
		return 1;
	}

	private OsContainerDeployableTask createTask(final VnfBlueprint blueprint, final VnfInstance vnfInstance, final OsContainerDeployableUnit x) {
		final OsContainerDeployableTask t = createTask(OsContainerDeployableTask::new);
		t.setBlueprint(blueprint);
		t.setOsContainerDeployableUnit(x);
		t.setAlias(vduNamingStrategy.getOsContainerAlias(vnfInstance, x.getName()));
		t.setToscaName(x.getName());
		t.setType(ResourceTypeEnum.CNF);
		return t;
	}

	private List<OsContainerDeployableVt> doTerminatePlan(final VnfInstance vnfInstance) {
		final List<VnfLiveInstance> instances = vnfLiveInstanceJpa.findByVnfInstanceIdAndClass(vnfInstance, OsContainerDeployableTask.class.getSimpleName());
		return instances.stream().map(x -> {
			final OsContainerDeployableTask task = createDeleteTask(OsContainerDeployableTask::new, x);
			task.setType(ResourceTypeEnum.CNF);
			task.setOsContainerDeployableUnit(((OsContainerDeployableTask) x.getTask()).getOsContainerDeployableUnit());
			return new OsContainerDeployableVt(task);
		}).toList();
	}

}
