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
package com.ubiqube.etsi.mec.mepm.service.graph.uow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mec.tasks.AppNetworkTask;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Network;
import com.ubiqube.etsi.mec.mepm.service.graph.AppParameters;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class AppVirtualLinkUow extends AppAbstractUnitOfWork {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final AppNetworkTask networkTask;

	private final VlProtocolData vlProtocolData;

	public AppVirtualLinkUow(final AppNetworkTask x, final VnfVl vnfVl) {
		super(x);
		networkTask = x;
		vlProtocolData = vnfVl.getVlProfileEntity().getVirtualLinkProtocolData().iterator().next();
	}

	@Override
	public String exec(final AppParameters params) {
		final String domainName = null; // dnsZone
		return params.getVim().createNetwork(params.getVimConnectionInformation(), vlProtocolData, networkTask.getAlias(), domainName, null);
	}

	@Override
	public String rollback(final AppParameters params) {
		params.getVim().deleteVirtualLink(params.getVimConnectionInformation(), params.getVimResourceId());
		return null;
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

	@Override
	protected String getPrefix() {
		return "vl";
	}

}
