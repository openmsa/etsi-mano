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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jgrapht.ListenableGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.SubNetworkTask;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Network;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.SubNetwork;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SubNetworkUow extends VnfAbstractUnitOfWork {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(SubNetworkUow.class);

	private final SubNetworkTask task;

	public SubNetworkUow(final SubNetworkTask _task) {
		super(_task);
		task = _task;
	}

	@Override
	public String exec(final VnfParameters params) {
		final String networkId = params.getContext().get(task.getParentName());
		return params.getVim().createSubnet(params.getVimConnectionInformation(), task.getL3Data(), task.getIpPool(), networkId);
	}

	@Override
	public String rollback(final VnfParameters params) {
		// params.getVim().deleteSubnet(params.getVimConnectionInformation(),
		// params.getVimResourceId());
		return null;
	}

	@Override
	protected String getPrefix() {
		return "subnet";
	}

	@Override
	public void connect(final ListenableGraph<UnitOfWork<VnfTask, VnfParameters>, ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> g, final Map<String, UnitOfWork<VnfTask, VnfParameters>> cache) {
		final Optional<UnitOfWork> parent = Optional.ofNullable(cache.get(task.getParentName()));
		LOG.warn("Subnetwork: " + task.getAlias() + " => " + task.getParentName() + " => " + cache);
		parent.ifPresent(x -> {
			g.addEdge(x, this);
		});
	}

	@Override
	public List<WfDependency> getDependencies() {
		return Arrays.asList(new WfDependency(Network.class, task.getParentName()));
	}

	@Override
	public List<WfProduce> getProduce() {
		return Arrays.asList(new WfProduce(SubNetwork.class, task.getToscaName(), task.getId()));
	}

}
