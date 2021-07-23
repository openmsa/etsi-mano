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

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.v2.NetworkTask;
import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.DnsZone;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWork;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.vim.Vim;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VirtualLinkUowV2 implements UnitOfWork<NetworkTask> {
	private final VirtualTask<NetworkTask> task;

	private final Vim vim;

	private final VimConnectionInformation vimConnectionInformation;

	private final VlProtocolData vlProtocolData;

	public VirtualLinkUowV2(final VirtualTask<NetworkTask> task, final Vim vim, final VimConnectionInformation vimConnectionInformation) {
		this.task = task;
		this.vim = vim;
		this.vimConnectionInformation = vimConnectionInformation;
		vlProtocolData = task.getParameters().getVnfVl().getVlProfileEntity().getVirtualLinkProtocolData().iterator().next();
	}

	@Override
	public VirtualTask<NetworkTask> getTask() {
		return task;
	}

	@Override
	public String execute(final Context context) {
		final NetworkTask params = task.getParameters();
		final String domainName = context.get(DnsZone.class, task.getParameters().getToscaName());
		return vim.network(vimConnectionInformation).createNetwork(vlProtocolData, params.getToscaName(), domainName, params.getQosPolicy());
	}

	@Override
	public String rollback(final Context context) {
		final NetworkTask params = task.getParameters();
		vim.network(vimConnectionInformation).deleteVirtualLink(params.getVimResourceId());
		return null;
	}

	@Override
	public Class<? extends Node> getNode() {
		return Network.class;
	}

}
