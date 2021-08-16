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
package com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.uow;

import java.util.List;
import java.util.stream.Collectors;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Compute;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Storage;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.vim.Vim;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VnfComputeUowV2 extends AbstractUowV2<ComputeTask> {

	private final Vim vim;
	private final VimConnectionInformation vimConnectionInformation;

	public VnfComputeUowV2(final VirtualTask<ComputeTask> task, final Vim vim, final VimConnectionInformation vimConnectionInformation) {
		super(task, Compute.class);
		this.vim = vim;
		this.vimConnectionInformation = vimConnectionInformation;
	}

	@Override
	public String execute(final Context context) {
		final ComputeTask t = getTask().getParameters();

		final List<String> storages = getTask().getParameters().getVnfCompute().getStorages().stream()
				.map(x -> context.getParent(Storage.class, x))
				.flatMap(List::stream)
				.collect(Collectors.toList());
		final List<String> net = t.getVnfCompute().getNetworks().stream()
				.map(x -> context.getParent(Network.class, x))
				.flatMap(List::stream)
				.collect(Collectors.toList());
		return vim.createCompute(vimConnectionInformation, t.getAlias(), t.getFlavorId(), t.getImageId(), net, storages, t.getBootData());
	}

	@Override
	public String rollback(final Context context) {
		final ComputeTask t = getTask().getParameters();
		vim.deleteCompute(vimConnectionInformation, t.getVimResourceId());
		return null;
	}

}
