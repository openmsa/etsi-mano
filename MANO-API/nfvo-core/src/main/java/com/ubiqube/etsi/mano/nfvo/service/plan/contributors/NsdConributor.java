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
package com.ubiqube.etsi.mano.nfvo.service.plan.contributors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.vnflcm.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsdTask;
import com.ubiqube.etsi.mano.nfvo.controller.nslcm.NsInstanceControllerService;
import com.ubiqube.etsi.mano.nfvo.jpa.NsLiveInstanceJpa;
import com.ubiqube.etsi.mano.nfvo.service.NsInstanceService;
import com.ubiqube.etsi.mano.nfvo.service.graph.NsBundleAdapter;
import com.ubiqube.etsi.mano.nfvo.service.plan.contributors.vt.NsVt;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.NsdNode;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NsdConributor extends AbstractNsContributor<NsdTask, NsVt> {
	private final NsInstanceService nsInstanceService;
	private final NsInstanceControllerService nsInstanceControllerService;
	private final VnfInstanceLcm nsLcmOpOccsService;
	private final NsLiveInstanceJpa nsLiveInstanceJpa;

	public NsdConributor(final NsInstanceService nsInstanceService, final NsInstanceControllerService nsInstanceControllerService, final VnfInstanceLcm nsLcmOpOccsService,
			final NsLiveInstanceJpa nsLiveInstanceJpa) {
		this.nsInstanceService = nsInstanceService;
		this.nsInstanceControllerService = nsInstanceControllerService;
		this.nsLcmOpOccsService = nsLcmOpOccsService;
		this.nsLiveInstanceJpa = nsLiveInstanceJpa;
	}

	private List<NsVt> doTerminate(final NsdInstance instance) {
		final List<NsVt> ret = new ArrayList<>();
		final List<NsLiveInstance> insts = nsLiveInstanceJpa.findByNsdInstanceAndClass(instance, NsdTask.class.getSimpleName());
		insts.stream().forEach(x -> {
			final NsdTask nt = createDeleteTask(NsdTask::new, x);
			ret.add(new NsVt(nt));
		});
		return ret;
	}

	@Override
	public Class<? extends Node> getNode() {
		return NsdNode.class;
	}

	@Override
	protected List<NsVt> nsContribute(final NsBundleAdapter bundle, final NsBlueprint blueprint) {
		if (blueprint.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminate(blueprint.getInstance());
		}

		final Set<NsdPackage> saps = nsInstanceService.findNestedNsdByNsInstance(bundle.nsPackage());
		return saps.stream()
				.filter(x -> 0 == nsInstanceService.countLiveInstanceOfNsd(blueprint.getNsInstance(), x.getId()))
				.map(x -> {
					final NsdInstance inst = nsInstanceControllerService.createNsd(x.getNsdId().toString(), "nested_of_" + blueprint.getNsInstance().getId(), "");
					final NsdTask nsd = createTask(NsdTask::new);
					nsd.setNsdId(inst.getId());
					nsd.setChangeType(ChangeType.ADDED);
					nsd.setVirtualLinks(x.getNsVirtualLinks());
					return new NsVt(nsd);
				}).toList();
	}

}
