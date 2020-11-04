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

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.SubNetworkTask;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.service.BlueprintService;
import com.ubiqube.etsi.mano.service.graph.vnfm.SubNetworkUow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.vim.node.Node;
import com.ubiqube.etsi.mano.service.vim.node.SubNetwork;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class SubNetworkContributor extends AbstractPlanContributor {
	private final BlueprintService blueprintService;

	public SubNetworkContributor(final BlueprintService _blueprintService) {
		blueprintService = _blueprintService;
	}

	@Override
	public Class<? extends Node> getContributionType() {
		return SubNetwork.class;
	}

	@Override
	public List<Task> contribute(final VnfPackage vnfPackage, final Blueprint plan, final Set<ScaleInfo> scaling) {
		final ArrayList<Task> ret = new ArrayList<>();
		final Set<VnfVl> vls = vnfPackage.getVnfVl();
		// XXX Or, we can enumerate plan/tasks and create subnetworks.
		for (final VnfVl vnfVl : vls) {
			final int num = blueprintService.getNumberOfLiveVl(plan.getVnfInstance(), vnfVl);
			if (num == 0) {
				vnfVl.getVlProfileEntity().getVirtualLinkProtocolData().stream()
						.forEach(x -> {
							x.getIpAllocationPools().stream()
									.forEach(y -> {
										final SubNetworkTask snt = new SubNetworkTask();
										snt.setAlias("sub_" + vnfVl.getToscaName());
										snt.setChangeType(ChangeType.ADDED);
										snt.setToscaName("sub_" + vnfVl.getToscaName());
										snt.setType(ResourceTypeEnum.SUBNETWORK);
										snt.setL3Data(x.getL3ProtocolData());
										snt.setIpPool(y);
										snt.setParentName(vnfVl.getToscaName());
										ret.add(snt);
									});
						});
			}
		}
		return ret;
	}

	@Override
	public List<UnitOfWork> convertTasksToExecNode(final Set<Task> tasks, final Blueprint plan) {
		final ArrayList<UnitOfWork> ret = new ArrayList<>();
		tasks.stream()
				.filter(x -> x instanceof SubNetworkTask)
				.map(x -> (SubNetworkTask) x)
				.forEach(x -> {
					ret.add(new SubNetworkUow(x));
				});
		return ret;
	}

}
