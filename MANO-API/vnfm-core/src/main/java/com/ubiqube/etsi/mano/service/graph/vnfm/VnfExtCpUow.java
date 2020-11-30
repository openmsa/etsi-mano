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

import java.util.Map;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.v2.ExternalCpTask;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;

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
		return params.getVim().createRouter(params.getVimConnectionInformation(), task.getAlias(), networkId, extNetwork);
	}

	@Override
	public String rollback(final VnfParameters params) {
		params.getVim().deleteRouter(params.getVimConnectionInformation(), params.getVimResourceId());
		return null;
	}

	@Override
	protected String getPrefix() {
		return "ext_cp";
	}

	@Override
	public void connect(final ListenableGraph<UnitOfWork<VnfTask, VnfParameters>, ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> g, final Map<String, UnitOfWork<VnfTask, VnfParameters>> cache) {
		final UnitOfWork internal = cache.get("sub_" + extCp.getInternalVirtualLink());
		if (null != internal) {
			g.addEdge(internal, this);
		}
	}
}
