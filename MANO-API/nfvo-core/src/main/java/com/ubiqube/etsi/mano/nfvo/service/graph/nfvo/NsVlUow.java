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
package com.ubiqube.etsi.mano.nfvo.service.graph.nfvo;

import com.ubiqube.etsi.mano.dao.mano.IpPool;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLinkTask;
import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.graph.AbstractUnitOfWork;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class NsVlUow extends AbstractUnitOfWork<NsVirtualLinkTask> {
	private final NsVirtualLinkTask task;
	private final VlProtocolData vlProtocolData;
	private final Vim vim;
	private final VimConnectionInformation vimConnectionInformation;

	public NsVlUow(final VirtualTask<NsVirtualLinkTask> task, final Vim vim, final VimConnectionInformation vimConnectionInformation) {
		super(task, Network.class);
		this.task = task.getParameters();
		this.vim = vim;
		this.vimConnectionInformation = vimConnectionInformation;
		if (null != this.task.getNsVirtualLink()) {
			vlProtocolData = this.task.getNsVirtualLink().getNsVlProfile().getVlProtocolData().iterator().next();
		} else {
			vlProtocolData = null;
		}
	}

	@Override
	public String execute(final Context context) {
		final String ret = vim.network(vimConnectionInformation).createNetwork(vlProtocolData, task.getAlias(), null, null);
		final IpPool ipAllocationPool = null;
		vim.network(vimConnectionInformation).createSubnet(vlProtocolData.getL3ProtocolData(), ipAllocationPool, ret);
		return ret;
	}

	@Override
	public String rollback(final Context context) {
		vim.network(vimConnectionInformation).deleteVirtualLink(task.getVimResourceId());
		return null;
	}

}
