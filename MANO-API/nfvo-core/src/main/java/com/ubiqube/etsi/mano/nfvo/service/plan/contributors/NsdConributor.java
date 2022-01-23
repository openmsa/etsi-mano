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

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageNsdPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanOperationType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVnfTask;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsdTask;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.nfvo.jpa.NsLiveInstanceJpa;
import com.ubiqube.etsi.mano.nfvo.service.NsInstanceService;
import com.ubiqube.etsi.mano.nfvo.service.graph.NsBundleAdapter;
import com.ubiqube.etsi.mano.nfvo.service.plan.contributors.vt.NsCreateVt;
import com.ubiqube.etsi.mano.nfvo.service.plan.contributors.vt.NsInstantiateVt;
import com.ubiqube.etsi.mano.nfvo.service.plan.contributors.vt.NsVtBase;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.NsdInstantiateNode;
import com.ubiqube.etsi.mano.service.NsScaleStrategy;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NsdConributor extends AbstractNsContributor<NsdTask, NsVtBase<NsdTask>> {
	private final NsInstanceService nsInstanceService;
	private final NsLiveInstanceJpa nsLiveInstanceJpa;
	private final NsScaleStrategy nsScaleStrategy;

	public NsdConributor(final NsInstanceService nsInstanceService, final NsLiveInstanceJpa nsLiveInstanceJpa, final NsScaleStrategy nsScaleStrategy) {
		this.nsInstanceService = nsInstanceService;
		this.nsLiveInstanceJpa = nsLiveInstanceJpa;
		this.nsScaleStrategy = nsScaleStrategy;
	}

	private List<NsVtBase<NsdTask>> doTerminate(final NsdInstance instance) {
		final List<NsVtBase<NsdTask>> ret = new ArrayList<>();
		final List<NsLiveInstance> insts = nsLiveInstanceJpa.findByNsdInstanceAndClass(instance, NsdTask.class.getSimpleName());
		insts.stream().forEach(x -> {
			final NsdTask nt = createDeleteTask(NsdTask::new, x);
			ret.add(new NsInstantiateVt(nt));
		});
		return ret;
	}

	@Override
	public Class<? extends Node> getNode() {
		return NsdInstantiateNode.class;
	}

	@Override
	protected List<NsVtBase<NsdTask>> nsContribute(final NsBundleAdapter bundle, final NsBlueprint blueprint) {
		if (blueprint.getOperation() == PlanOperationType.TERMINATE) {
			return doTerminate(blueprint.getInstance());
		}

		final Set<NsdPackage> nsds = nsInstanceService.findNestedNsdByNsInstance(bundle.nsPackage());
		final List<NsVtBase<NsdTask>> ret = new ArrayList<>();
		nsds.stream()
				.forEach(x -> {
					final NsdPackageNsdPackage nsPackageNsPackage = find(x, bundle.nsPackage().getNestedNsdInfoIds());
					final int curr = nsInstanceService.countLiveInstanceOfNsd(blueprint.getNsInstance(), x.getNsdName());
					final int inst = nsScaleStrategy.getNumberOfInstances(nsPackageNsPackage, blueprint);
					if (curr > inst) {
						remove(curr - inst, blueprint.getInstance(), ret);
					} else if (curr < inst) {
						add(inst - curr, x, nsPackageNsPackage, ret);
					}
				});
		return ret;
	}

	private static void add(final int cnt, final NsdPackage pkg, final NsdPackageNsdPackage nsPackageNsPackage, final List<NsVtBase<NsdTask>> ret) {
		for (int i = 0; i < cnt; i++) {
			final NsdTask nsd = createTask(NsdTask::new);
			nsd.setNsdId(pkg.getId());
			nsd.setChangeType(ChangeType.ADDED);
			nsd.setVirtualLinks(nsPackageNsPackage.getVirtualLinks());
			ret.add(new NsCreateVt(nsd));
			ret.add(new NsInstantiateVt(nsd));
		}
	}

	private void remove(final int cnt, final NsdInstance instance, final List<NsVtBase<NsdTask>> ret) {
		final List<NsLiveInstance> insts = nsLiveInstanceJpa.findByNsdInstanceAndClass(instance, NsVnfTask.class.getSimpleName());
		for (int i = 0; i < cnt; i++) {
			final NsdTask task = (NsdTask) insts.get(i).getNsTask();
			final NsdTask nt = createDeleteTask(NsdTask::new, insts.get(i));
			nt.setVimResourceId(task.getVimResourceId());
			nt.setServer(task.getServer());
			ret.add(new NsCreateVt(nt));
			ret.add(new NsInstantiateVt(nt));
		}
	}

	private static NsdPackageNsdPackage find(final NsdPackage nsPackage, final Set<NsdPackageNsdPackage> nestedNsdInfoIds) {
		return nestedNsdInfoIds.stream()
				.filter(x -> x.getChild().getId().compareTo(nsPackage.getId()) == 0)
				.findFirst()
				.orElseThrow(() -> new NotFoundException("NSD Package not found: " + nsPackage.getId()));
	}

}
