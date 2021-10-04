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
package com.ubiqube.etsi.mano.vnfm.service.system;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.SubNetworkTask;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.v2.NetworkTask;
import com.ubiqube.etsi.mano.orchestrator.OrchestrationService;
import com.ubiqube.etsi.mano.orchestrator.SystemBuilder;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.uow.VirtualLinkUowV2;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.uow.VnfSubnetworkUowV2;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.vt.SubNetworkVt;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VimNetworkSystem extends AbstractVimSystem<NetworkTask> {
	private final Vim vim;

	public VimNetworkSystem(final Vim vim) {
		super();
		this.vim = vim;
	}

	@Override
	public String getProviderId() {
		return "NETWORK";
	}

	@Override
	SystemBuilder getImplementation(final OrchestrationService<NetworkTask> orchestrationService, final VirtualTask<NetworkTask> virtualTask, final VimConnectionInformation vimConnectionInformation) {
		final VirtualLinkUowV2 net = new VirtualLinkUowV2(virtualTask, vim, vimConnectionInformation);
		final NetworkTask p = virtualTask.getParameters();
		final Set<VlProtocolData> vlp = p.getVnfVl().getVlProfileEntity().getVirtualLinkProtocolData();
		final SystemBuilder s = orchestrationService.createEmptySystemBuilder();
		vlp.forEach(x -> x.getIpAllocationPools().forEach(y -> {
			final SubNetworkTask sn = new SubNetworkTask(x.getL3ProtocolData(), y, virtualTask.getParameters().getToscaName());
			s.add(net, new VnfSubnetworkUowV2(new SubNetworkVt(sn), vim, vimConnectionInformation));
		}));
		return s;
	}

}
