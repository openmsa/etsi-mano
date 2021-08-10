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

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLinkTask;
import com.ubiqube.etsi.mano.jpa.NsLiveInstanceJpa;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.NsVlNode;
import com.ubiqube.etsi.mano.service.NsBlueprintService;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsParameters;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsVlUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.wfe2.DependencyBuilder;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NsVirtualLinkContributor extends AbstractNsContributor {
	private final NsBlueprintService blueprintService;
	private final NsLiveInstanceJpa nsLiveInstanceJpa;

	public NsVirtualLinkContributor(final NsBlueprintService blueprintService, NsLiveInstanceJpa nsLiveInstanceJpa) {
		super();
		this.blueprintService = blueprintService;
		this.nsLiveInstanceJpa = nsLiveInstanceJpa;
	}

	@Override
	public Class<? extends Node> getContributionType() {
		return NsVlNode.class;
	}

	@Override
	public List<NsTask> contribute(final NsdPackage bundle, final NsBlueprint plan, final Set<ScaleInfo> scaling) {
		if (plan.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminate(plan.getInstance());
		}
		final Set<NsVirtualLink> vlss = bundle.getNsVirtualLinks();
		return vlss.stream()
				.filter(x -> 0 == blueprintService.getNumberOfLiveVl(plan.getNsInstance(), x))
				.map(x -> {
					final NsVirtualLinkTask nsVl = createTask(NsVirtualLinkTask::new, x);
					nsVl.setChangeType(ChangeType.ADDED);
					nsVl.setNsVirtualLink(x);
					return nsVl;
				}).collect(Collectors.toList());
	}

	private List<NsTask> doTerminate(NsdInstance instance) {
		List<NsTask> ret = new ArrayList<>();
		List<NsLiveInstance> insts = nsLiveInstanceJpa.findByNsdInstanceAndClass(instance, NsVirtualLinkTask.class.getSimpleName());
		insts.stream().forEach(x -> {
			NsVirtualLinkTask nt = createDeleteTask(NsVirtualLinkTask::new, x);
			nt.setVimResourceId(x.getResourceId());
			ret.add(nt);
		});
		return ret;
	}

	@Override
	public List<UnitOfWork<NsTask, NsParameters>> convertTasksToExecNode(final Set<NsTask> tasks, final NsBlueprint plan) {
		final ArrayList<UnitOfWork<NsTask, NsParameters>> ret = new ArrayList<>();
		tasks.stream()
				.filter(NsVirtualLinkTask.class::isInstance)
				.map(NsVirtualLinkTask.class::cast)
				.forEach(x -> {
					ret.add(new NsVlUow(x));
				});
		return ret;
	}

	@Override
	public void getDependencies(final DependencyBuilder dependencyBuilder) {
		// Nothing.
	}

}
