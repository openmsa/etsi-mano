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

import java.util.List;

import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.entities.SystemConnections;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.graph.AbstractUnitOfWork;
import com.ubiqube.etsi.mano.service.vim.OsSfc;
import com.ubiqube.etsi.mano.service.vim.sfc.enity.SfcPortPairGroupTask;
import com.ubiqube.etsi.mano.service.vim.sfc.node.PortPairGroupNode;
import com.ubiqube.etsi.mano.service.vim.sfc.node.PortPairNode;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SfcPortPairGroupUow extends AbstractUnitOfWork<SfcPortPairGroupTask> {
	private final SystemConnections vimConnectionInformation;
	private final OsSfc sfc;
	private final SfcPortPairGroupTask task;

	protected SfcPortPairGroupUow(final VirtualTask<SfcPortPairGroupTask> task, final SystemConnections vimConnectionInformation) {
		super(task, PortPairGroupNode.class);
		this.vimConnectionInformation = vimConnectionInformation;
		sfc = new OsSfc();
		this.task = task.getParameters();
	}

	@Override
	public String execute(final Context context) {
		final List<String> portPairs = task.getPortPair().stream().map(x -> context.get(PortPairNode.class, x)).toList();
		return sfc.createPortPairGroup(vimConnectionInformation, task.getToscaName(), portPairs);
	}

	@Override
	public String rollback(final Context context) {
		sfc.deletePortPairGroup(vimConnectionInformation, task.getVimResourceId());
		return null;
	}

}
