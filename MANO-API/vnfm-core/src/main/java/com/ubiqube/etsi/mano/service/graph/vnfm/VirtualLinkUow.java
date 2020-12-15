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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.v2.NetworkTask;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.service.graph.NodeNaming;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Network;

public class VirtualLinkUow extends VnfAbstractUnitOfWork {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VlProtocolData vlProtocolData;

	private final String name;

	private final NetworkTask networkTask;

	public VirtualLinkUow(final NetworkTask _networkTask, final VnfVl vnfVl) {
		super(_networkTask);
		name = _networkTask.getToscaName();
		vlProtocolData = vnfVl.getVlProfileEntity().getVirtualLinkProtocolData().iterator().next();
		networkTask = _networkTask;
	}

	public VlProtocolData getVlProtocolData() {
		return vlProtocolData;
	}

	@Override
	public String exec(final VnfParameters params) {
		final String domainName = params.getContext().get(NodeNaming.dnsZone());
		return params.getVim().createNetwork(params.getVimConnectionInformation(), vlProtocolData, networkTask.getAlias(), domainName, null);
	}

	@Override
	public String toString() {
		return "VirtualLinkUow [name=" + name + "]";
	}

	@Override
	protected String getPrefix() {
		return "vl";
	}

	@Override
	public String rollback(final VnfParameters params) {
		params.getVim().deleteVirtualLink(params.getVimConnectionInformation(), params.getVimResourceId());
		return null;
	}

	@Override
	public void connect(final ListenableGraph<UnitOfWork<VnfTask, VnfParameters>, ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> g, final Map<String, UnitOfWork<VnfTask, VnfParameters>> cache) {
		Optional.ofNullable(cache.get(NodeNaming.dnsZone())).ifPresent(x -> g.addEdge(x, this));
	}

	@Override
	public List<WfDependency> getDependencies() {
		// May require a DNS zone.
		return new ArrayList<>();
	}

	@Override
	public List<WfProduce> getProduce() {
		return Arrays.asList(new WfProduce(Network.class, networkTask.getToscaName(), networkTask.getId()));
	}

}
