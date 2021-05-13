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

import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.v2.ExternalCpTask;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Network;

public class VnfExtCpUow extends VnfAbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfExtCp extCp;

	private final ExternalCpTask task;

	public VnfExtCpUow(final ExternalCpTask _ExternalCpTask) {
		super(_ExternalCpTask);
		task = _ExternalCpTask;
		extCp = task.getVnfExtCp();
	}

	@Override
	public String exec(final VnfParameters params) {
		final String networkId = params.getContext().get(extCp.getInternalVirtualLink());
		final String extNetwork = params.getContext().get(extCp.getExternalVirtualLink());
		return params.getVim().network(params.getVimConnectionInformation()).createRouter(task.getAlias(), networkId, extNetwork);
	}

	@Override
	public String rollback(final VnfParameters params) {
		params.getVim().network(params.getVimConnectionInformation()).deleteRouter(params.getVimResourceId());
		return null;
	}

	@Override
	protected String getPrefix() {
		return "ext_cp";
	}

	@Override
	public List<WfDependency> getDependencies() {
		return Arrays.asList(new WfDependency(Network.class, extCp.getInternalVirtualLink()));
	}

	@Override
	public List<WfProduce> getProduce() {
		return Arrays.asList(new WfProduce(com.ubiqube.etsi.mano.service.vim.node.vnfm.VnfExtCp.class, task.getToscaName(), task.getId()));
	}
}
