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
package com.ubiqube.etsi.mano.service.plan.contributors.v2.uow;

import com.ubiqube.etsi.mano.dao.mano.SubNetworkTask;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.SubNetwork;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWork;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.vim.Vim;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VnfSubnetworkUowV2 implements UnitOfWork<SubNetworkTask> {
	private final VirtualTask<SubNetworkTask> task;
	private final Vim vim;
	private final VimConnectionInformation vimConnectionInformation;

	public VnfSubnetworkUowV2(final VirtualTask<SubNetworkTask> task, final Vim vim, final VimConnectionInformation vimConnectionInformation) {
		super();
		this.task = task;
		this.vim = vim;
		this.vimConnectionInformation = vimConnectionInformation;
	}

	@Override
	public VirtualTask<SubNetworkTask> getTask() {
		return task;
	}

	@Override
	public String execute(final Context context) {
		final SubNetworkTask params = task.getParameters();
		final String networkId = context.get(Network.class, params.getParentName());
		return vim.network(vimConnectionInformation).createSubnet(params.getL3Data(), params.getIpPool(), networkId);
	}

	@Override
	public String rollback(final Context context) {
		// params.getVim().deleteSubnet(params.getVimConnectionInformation(),
		// params.getVimResourceId());
		return null;
	}

	@Override
	public Class<? extends Node> getNode() {
		return SubNetwork.class;
	}

}
