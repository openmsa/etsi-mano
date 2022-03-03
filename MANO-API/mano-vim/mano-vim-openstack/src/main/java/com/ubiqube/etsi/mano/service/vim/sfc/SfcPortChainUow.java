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

import java.util.Set;
import java.util.stream.Collectors;

import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.entities.SystemConnections;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.graph.AbstractUnitOfWork;
import com.ubiqube.etsi.mano.service.vim.OsSfc;
import com.ubiqube.etsi.mano.service.vim.sfc.enity.SfcPortChainTask;
import com.ubiqube.etsi.mano.service.vim.sfc.node.FlowClassifierNode;
import com.ubiqube.etsi.mano.service.vim.sfc.node.PortChainNode;
import com.ubiqube.etsi.mano.service.vim.sfc.node.PortPairGroupNode;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SfcPortChainUow extends AbstractUnitOfWork<SfcPortChainTask> {

	private final SystemConnections vci;
	private final OsSfc sfc;
	private final SfcPortChainTask task;

	protected SfcPortChainUow(final VirtualTask<SfcPortChainTask> task, final SystemConnections vci) {
		super(task, PortChainNode.class);
		this.vci = vci;
		sfc = new OsSfc();
		this.task = task.getParameters();
	}

	@Override
	public String execute(final Context context) {
		final Set<String> flows = task.getFlowClassifier().stream().map(x -> context.get(FlowClassifierNode.class, x)).collect(Collectors.toSet());
		final Set<String> ppg = task.getPortPairGroups().stream().map(x -> context.get(PortPairGroupNode.class, x)).collect(Collectors.toSet());
		return sfc.createPortChain(vci, task.getToscaName(), flows, ppg);
	}

	@Override
	public String rollback(final Context context) {
		sfc.deletePortChain(vci, task.getVimResourceId());
		return null;
	}

}
