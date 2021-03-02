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
package com.ubiqube.etsi.mano.service.graph.contributors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.NetworkTask;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.service.graph.TestParameters;
import com.ubiqube.etsi.mano.service.graph.uow.VlUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.wfe2.DependencyBuilder;
import com.ubiqube.etsi.mano.service.plan.contributors.PlanContributor;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.Start;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Network;

public class NetworkCont implements PlanContributor<VnfPackage, VnfBlueprint, VnfTask, TestParameters> {

	@Override
	public Class<? extends Node> getContributionType() {
		return Network.class;
	}

	@Override
	public List<VnfTask> contribute(final VnfPackage bundle, final VnfBlueprint blueprint, final Set<ScaleInfo> scaling) {
		final List<VnfTask> ret = new ArrayList<>();
		final VnfTask task = new NetworkTask();
		ret.add(task);
		return ret;
	}

	@Override
	public List<UnitOfWork<VnfTask, TestParameters>> convertTasksToExecNode(final Set<VnfTask> tasks, final VnfBlueprint blueprint) {
		final ArrayList<UnitOfWork<VnfTask, TestParameters>> ret = new ArrayList<>();
		final UnitOfWork<VnfTask, TestParameters> uow = new VlUow();
		ret.add(uow);
		return ret;
	}

	@Override
	public void getDependencies(final DependencyBuilder dependencyBuilder) {
		dependencyBuilder.connectionFrom(Start.class);
	}

}
