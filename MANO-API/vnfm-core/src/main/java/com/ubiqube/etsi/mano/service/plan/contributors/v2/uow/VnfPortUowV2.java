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
package com.ubiqube.etsi.mano.service.plan.contributors.v2.uow;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.ExternalCpTask;
import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Compute;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.VnfExtCp;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.vim.Vim;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VnfPortUowV2 extends AbstractUowV2<ExternalCpTask> {
	private final Vim vim;
	private final VimConnectionInformation vimConnectionInformation;

	public VnfPortUowV2(final VirtualTask<ExternalCpTask> task, final Vim vim, final VimConnectionInformation vimConnectionInformation) {
		super(task, VnfExtCp.class);
		this.vim = vim;
		this.vimConnectionInformation = vimConnectionInformation;
	}

	@Override
	public String execute(final Context context) {
		final com.ubiqube.etsi.mano.dao.mano.VnfExtCp extCp = getTask().getParameters().getVnfExtCp();
		final String computeId = context.get(Compute.class, extCp.getInternalVirtualLink());
		final String extNetwork = context.get(Network.class, extCp.getExternalVirtualLink());
		return vim.network(vimConnectionInformation).createPort(extCp.getToscaName(), extNetwork, computeId);
	}

	@Override
	public String rollback(final Context context) {
		final ExternalCpTask param = getTask().getParameters();
		vim.network(vimConnectionInformation).deletePort(param.getVimResourceId());
		return null;
	}

}
