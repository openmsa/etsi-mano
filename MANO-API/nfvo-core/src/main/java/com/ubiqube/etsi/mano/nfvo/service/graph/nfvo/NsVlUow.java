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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.IpPool;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLinkTask;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.NsVlNode;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;

public class NsVlUow extends AbstractNsUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;
	private final NsVirtualLinkTask task;
	private final VlProtocolData vlProtocolData;

	public NsVlUow(final NsVirtualLinkTask task) {
		super(task);
		this.task = task;
		if (null != task.getNsVirtualLink()) {
			vlProtocolData = task.getNsVirtualLink().getNsVlProfile().getVlProtocolData().iterator().next();
		} else {
			vlProtocolData = null;
		}
	}

	@Override
	public String exec(final NsParameters params) {
		final String ret = params.getVim().network(params.getVimConnectionInformation()).createNetwork(vlProtocolData, task.getToscaName(), null, null);
		final IpPool ipAllocationPool = null;
		params.getVim().network(params.getVimConnectionInformation()).createSubnet(vlProtocolData.getL3ProtocolData(), ipAllocationPool, ret);
		return ret;
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
