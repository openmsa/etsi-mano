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

import com.ubiqube.etsi.mano.dao.mano.v2.DnsHostTask;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;

public class DnsHostUow extends VnfAbstractUnitOfWork {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final DnsHostTask task;

	public DnsHostUow(final DnsHostTask _task) {
		super(_task);
		task = _task;
	}

	@Override
	public String exec(final VnfParameters params) {
		return params.getVim().createDnsRecordSet(params.getVimConnectionInformation(), task.getZoneId(), task.getHostname(), task.getNetworkName());
	}

	@Override
	public String rollback(final VnfParameters params) {
		params.getVim().deleteDnsRecordSet(params.getVimConnectionInformation(), params.getVimResourceId(), task.getZoneId(), task.getIps());
		return null;
	}

	@Override
	public void connect(final ListenableGraph<UnitOfWork<VnfTask, VnfParameters>, ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> g, final Map<String, UnitOfWork<VnfTask, VnfParameters>> cache) {
		final UnitOfWork<VnfTask, VnfParameters> parent = cache.get(task.getParentAlias());
		g.addEdge(parent, this);
	}

	@Override
	protected String getPrefix() {
		return "dns-host";
	}

}
