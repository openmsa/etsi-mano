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

import java.util.Arrays;
import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.v2.DnsHostTask;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Compute;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.DnsHost;

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
		return params.getVim().dns(params.getVimConnectionInformation()).createDnsRecordSet(task.getZoneId(), task.getHostname(), task.getNetworkName());
	}

	@Override
	public String rollback(final VnfParameters params) {
		params.getVim().dns(params.getVimConnectionInformation()).deleteDnsRecordSet(params.getVimResourceId(), task.getZoneId(), task.getIps());
		return null;
	}

	@Override
	protected String getPrefix() {
		return "dns-host";
	}

	@Override
	public List<WfDependency> getDependencies() {
		return Arrays.asList(new WfDependency(Compute.class, task.getParentAlias()));
	}

	@Override
	public List<WfProduce> getProduce() {
		return Arrays.asList(new WfProduce(DnsHost.class, task.getToscaName(), task.getId()));
	}

}
