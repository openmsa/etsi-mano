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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.lcmgrant.VnfInstanceLcm;
import com.ubiqube.etsi.mano.controller.nslcm.NsInstanceControllerService;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsdTask;
import com.ubiqube.etsi.mano.jpa.NsLiveInstanceJpa;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.NsVlNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.NsdNode;
import com.ubiqube.etsi.mano.service.NsInstanceService;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsParameters;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.wfe2.DependencyBuilder;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NsdConributor extends AbstractNsContributor {
	private final NsInstanceService nsInstanceService;
	private final NsInstanceControllerService nsInstanceControllerService;
	private final VnfInstanceLcm nsLcmOpOccsService;
	private final NsLiveInstanceJpa nsLiveInstanceJpa;

	public NsdConributor(final NsInstanceService _nsInstanceService, final NsInstanceControllerService _nsInstanceControllerService, final VnfInstanceLcm _nsLcmOpOccsService, NsLiveInstanceJpa nsLiveInstanceJpa) {
		nsInstanceService = _nsInstanceService;
		nsInstanceControllerService = _nsInstanceControllerService;
		nsLcmOpOccsService = _nsLcmOpOccsService;
		this.nsLiveInstanceJpa = nsLiveInstanceJpa;
	}

	@Override
	public Class<? extends Node> getContributionType() {
		return NsdNode.class;
	}

	@Override
	public List<NsTask> contribute(final NsdPackage bundle, final NsBlueprint blueprint, final Set<ScaleInfo> scaling) {
		if (blueprint.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminate(blueprint.getInstance());
		}
		final Set<NsdPackage> saps = nsInstanceService.findNestedNsdByNsInstance(bundle);
		return saps.stream()
				.filter(x -> 0 == nsInstanceService.countLiveInstanceOfNsd(blueprint.getNsInstance(), x.getId()))
				.map(x -> {
					final NsdInstance inst = nsInstanceControllerService.createNsd(x.getId().toString(), "nested_of_" + blueprint.getNsInstance().getId(), "");
					final NsdTask sap = createTask(NsdTask::new);
					sap.setNsdId(inst.getId());
					sap.setChangeType(ChangeType.ADDED);
					return sap;
				}).collect(Collectors.toList());
	}

	private List<NsTask> doTerminate(NsdInstance instance) {
		List<NsTask> ret = new ArrayList<>();
		List<NsLiveInstance> insts = nsLiveInstanceJpa.findByNsdInstanceAndClass(instance, NsdTask.class.getSimpleName());
		insts.stream().forEach(x -> {
			NsdTask nt = createDeleteTask(NsdTask::new, x);
			ret.add(nt);
		});
		return ret;
	}

	@Override
	public List<UnitOfWork<NsTask, NsParameters>> convertTasksToExecNode(final Set<NsTask> tasks, final NsBlueprint blueprint) {
		final ArrayList<UnitOfWork<NsTask, NsParameters>> ret = new ArrayList<>();
		tasks.stream()
				.filter(NsdTask.class::isInstance)
				.map(NsdTask.class::cast)
				.forEach(x -> {
					// XXX Null is a future problem.
					ret.add(new NsUow(x, null, nsInstanceControllerService, nsLcmOpOccsService));
				});
		return ret;
	}

	@Override
	public void getDependencies(final DependencyBuilder dependencyBuilder) {
		dependencyBuilder.connectionFrom(NsVlNode.class);
	}

}
