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
package com.ubiqube.etsi.mano.service.graph.nfvo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.L2Data;
import com.ubiqube.etsi.mano.dao.mano.L3Data;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLinkTask;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.NsVlNode;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;

public class NsVlUow extends AbstractNsUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;
	private final NsVirtualLinkTask task;

	public NsVlUow(final NsVirtualLinkTask _task) {
		super(_task);
		task = _task;
	}

	@Override
	public String exec(final NsParameters params) {
		final VlProtocolData vlProtocolData = new VlProtocolData();
		final L2Data l2 = new L2Data();
		l2.setMtu(1500);
		// l2.setName(name);
		l2.setNetworkType("flat");
		l2.setVlanTransparent(false);
		vlProtocolData.setL2ProtocolData(l2);
		final L3Data l3 = new L3Data();
		// l3.setCidr(cidr);
		l3.setDhcpEnabled(true);
		// l3.setGatewayIp(gatewayIp);
		l3.setIpVersion("ipv4");
		// l3.setL3Name(l3Name);
		return params.getVim().network(params.getVimConnectionInformation()).createNetwork(vlProtocolData, null, null, null);
	}

	@Override
	public String rollback(final NsParameters params) {
		params.getVim().network(params.getVimConnectionInformation()).deleteVirtualLink(task.getVimResourceId());
		return null;
	}

	@Override
	protected String getPrefix() {
		return "nsvl";
	}

	@Override
	public List<WfDependency> getDependencies() {
		return new ArrayList<>();
	}

	@Override
	public List<WfProduce> getProduce() {
		return Arrays.asList(new WfProduce(NsVlNode.class, task.getToscaName(), task.getId()));
	}

}
