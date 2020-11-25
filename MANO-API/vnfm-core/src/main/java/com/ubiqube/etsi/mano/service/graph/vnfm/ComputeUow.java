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

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.service.graph.NodeNaming;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.Vim;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ComputeUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfCompute vnfCompute;

	private final List<VnfLinkPort> vnfLinkPort;

	private final ComputeTask task;

	public ComputeUow(final ComputeTask _computeTask, final VnfCompute _vnfCompute, final Set<VnfLinkPort> _linkPort) {
		super(_computeTask);
		vnfCompute = _vnfCompute;
		vnfLinkPort = _linkPort.stream().collect(Collectors.toList());
		task = _computeTask;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		final List<String> storages = vnfCompute.getStorages().stream().map(context::get).collect(Collectors.toList());
		final List<String> networks = vnfLinkPort.stream()
				.filter(x -> x.getVirtualBinding().equals(vnfCompute.getToscaName()))
				.map(VnfLinkPort::getVirtualLink)
				.map(context::get)
				.collect(Collectors.toList());
		return vim.createCompute(vimConnectionInformation, task.getAlias(), task.getFlavorId(), task.getImageId(), networks, storages, vnfCompute.getBootData());
	}

	@Override
	protected String getPrefix() {
		return "compute";
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final String resourceId, final Map<String, String> context) {
		vim.deleteCompute(vimConnectionInformation, resourceId);
		return null;
	}

	@Override
	public void connect(final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g, final Map<String, UnitOfWork> cache) {
		vnfLinkPort.stream()
				.map(VnfLinkPort::getVirtualLink)
				.map(x -> cache.get(NodeNaming.subnetwork(x)))
				.filter(Objects::nonNull)
				.forEach(x -> g.addEdge(x, this));
		task.getVnfCompute().getStorages().stream().forEach(x -> g.addEdge(cache.get(NodeNaming.storageName(task.getVnfCompute().getToscaName(), x)), this));
	}

}
