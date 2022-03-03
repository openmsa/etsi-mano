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
package com.ubiqube.etsi.mano.service.vim.sfc;

import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.entities.SystemConnections;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.VnfPortNode;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.graph.AbstractUnitOfWork;
import com.ubiqube.etsi.mano.service.vim.OsSfc;
import com.ubiqube.etsi.mano.service.vim.sfc.enity.SfcPortPairTask;
import com.ubiqube.etsi.mano.service.vim.sfc.node.PortPairNode;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SfcPortPairUow extends AbstractUnitOfWork<SfcPortPairTask> {
	private final SystemConnections vimConnectionInformation;
	private final OsSfc sfc;
	private final SfcPortPairTask task;

	protected SfcPortPairUow(final VirtualTask<SfcPortPairTask> task, final SystemConnections vimConnectionInformation) {
		super(task, PortPairNode.class);
		this.vimConnectionInformation = vimConnectionInformation;
		sfc = new OsSfc();
		this.task = task.getParameters();
	}

	@Override
	public String execute(final Context context) {
		final String egress = context.get(VnfPortNode.class, task.getEgressId());
		final String igress = context.get(VnfPortNode.class, task.getIngressId());
		return sfc.createPortPair(vimConnectionInformation, task.getToscaName(), egress, igress);
	}

	@Override
	public String rollback(final Context context) {
		sfc.deletePortPair(vimConnectionInformation, task.getVimResourceId());
		return null;
	}

}
