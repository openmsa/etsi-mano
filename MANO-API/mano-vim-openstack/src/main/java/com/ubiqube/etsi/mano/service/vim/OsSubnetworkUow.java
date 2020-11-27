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
package com.ubiqube.etsi.mano.service.vim;

import java.util.Map;

import org.jgrapht.ListenableGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.IpPool;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.service.graph.vnfm.AbstractUnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;

public class OsSubnetworkUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(OsSubnetworkUow.class);

	private final VlProtocolData vlProtocolData;
	private final IpPool ipAllocationPool;

	private final String parentName;

	public OsSubnetworkUow(final VnfTask _task, final VlProtocolData y, final IpPool _ipAllocationPool, final String _parentName) {
		super(_task);
		vlProtocolData = y;
		ipAllocationPool = _ipAllocationPool;
		parentName = _parentName;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		final OpenStackVim osVim = (OpenStackVim) vim;
		final String netId = context.get(parentName);
		LOG.debug("Parent network: {} = {}", parentName, netId);
		return osVim.createSubnet(vimConnectionInformation, vlProtocolData.getL3ProtocolData(), ipAllocationPool, netId);
	}

	@Override
	protected String getPrefix() {
		return "os_subnet";
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final String resourceId, final Map<String, String> context) {
		final OpenStackVim osVim = (OpenStackVim) vim;
		LOG.debug("Deleting subnetwork: {}", resourceId);
		// XXX Don't delete for the momment osVim.deleteSubnet(vimConnectionInformation,
		// resourceId);
		return null;
	}

	@Override
	public void connect(final ListenableGraph<UnitOfWork<VnfTask>, ConnectivityEdge<UnitOfWork<VnfTask>>> g, final Map<String, UnitOfWork<VnfTask>> cache) {
		// TODO Auto-generated method stub

	}

}
