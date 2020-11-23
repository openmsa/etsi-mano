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
package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.Map;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.DnsHostTask;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class DnsHostUow extends AbstractUnitOfWork {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final DnsHostTask task;

	public DnsHostUow(final DnsHostTask _task) {
		super(_task);
		task = _task;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		return vim.createDnsRecordSet(vimConnectionInformation, task.getZoneId(), task.getHostname(), task.getNetworkName());
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final String resourceId, final Map<String, String> context) {
		vim.deleteDnsRecordSet(vimConnectionInformation, resourceId, task.getZoneId(), task.getIps());
		return null;
	}

	@Override
	public void connect(final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g, final Map<String, UnitOfWork> cache) {
		final UnitOfWork parent = cache.get(task.getParentAlias());
		g.addEdge(parent, this);
	}

	@Override
	protected String getPrefix() {
		return "dns-host";
	}

}
