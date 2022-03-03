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
package com.ubiqube.etsi.mano.service.vim.sfc.vt;

import java.util.ArrayList;
import java.util.List;

import com.ubiqube.etsi.mano.orchestrator.NamedDependency;
import com.ubiqube.etsi.mano.service.graph.vt.NsVtBase;
import com.ubiqube.etsi.mano.service.vim.sfc.enity.SfcPortChainTask;
import com.ubiqube.etsi.mano.service.vim.sfc.node.FlowClassifierNode;
import com.ubiqube.etsi.mano.service.vim.sfc.node.PortChainNode;
import com.ubiqube.etsi.mano.service.vim.sfc.node.PortPairGroupNode;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SfcPortChainVt extends NsVtBase<SfcPortChainTask> {

	private final SfcPortChainTask task;

	public SfcPortChainVt(final SfcPortChainTask nt) {
		super(nt);
		this.task = nt;
	}

	@Override
	public List<NamedDependency> getNameDependencies() {
		final ArrayList<NamedDependency> ret = new ArrayList<>();
		task.getFlowClassifier().forEach(x -> ret.add(new NamedDependency(FlowClassifierNode.class, x)));
		task.getPortPairGroups().forEach(x -> ret.add(new NamedDependency(PortPairGroupNode.class, x)));
		return ret;
	}

	@Override
	public List<NamedDependency> getNamedProduced() {
		return List.of(new NamedDependency(PortChainNode.class, task.getToscaName()));
	}

	@Override
	public String getFactoryProviderId() {
		return "SFC-PORT-CHAIN";
	}

	@Override
	public String getVimProviderId() {
		return "OPENSTACK_V3";
	}

}
