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
import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.SapTask;
import com.ubiqube.etsi.mano.service.NsBlueprintService;
import com.ubiqube.etsi.mano.service.NsdPackageService;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsParameters;
import com.ubiqube.etsi.mano.service.graph.nfvo.SapUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.wfe2.DependencyBuilder;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.SapNode;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class SapContributor extends AbstractNsContributor {
	NsBlueprintService blueprintService;
	NsdPackageService nsdPackageService;

	@Override
	public Class<? extends Node> getContributionType() {
		return SapNode.class;
	}

	@Override
	public List<NsTask> contribute(final NsdPackage bundle, final NsBlueprint plan, final Set<ScaleInfo> scaling) {
		final Set<NsSap> saps = bundle.getNsSaps();
		return saps.stream()
				.filter(x -> 0 == blueprintService.getNumberOfLiveSap(plan.getNsInstance(), x))
				.map(x -> {
					final SapTask sap = createTask(SapTask::new, x);
					sap.setSapd(x);
					sap.setChangeType(ChangeType.ADDED);
					return sap;
				}).collect(Collectors.toList());
	}

	@Override
	public List<UnitOfWork<NsTask, NsParameters>> convertTasksToExecNode(final Set<NsTask> tasks, final NsBlueprint plan) {
		final ArrayList<UnitOfWork<NsTask, NsParameters>> ret = new ArrayList<>();
		tasks.stream()
				.filter(x -> x instanceof SapTask)
				.map(x -> (SapTask) x)
				.forEach(x -> {
					ret.add(new SapUow(x));
				});
		return ret;
	}

	@Override
	public void getDependencies(final DependencyBuilder dependencyBuilder) {
		// TODO Auto-generated method stub

	}

}
