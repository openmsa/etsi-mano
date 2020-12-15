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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.v2.StorageTask;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.node.vnfm.Storage;

public class StorageUow extends VnfAbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final StorageTask storageTask;

	private final VnfStorage vnfStorage;

	public StorageUow(final StorageTask _storageTask, final VnfStorage _vnfStorage) {
		super(_storageTask);
		vnfStorage = _vnfStorage;
		storageTask = _storageTask;
	}

	@Override
	public String exec(final VnfParameters params) {
		return params.getVim().createStorage(params.getVimConnectionInformation(), vnfStorage, storageTask.getAlias());
	}

	@Override
	protected String getPrefix() {
		return "block_storage";
	}

	@Override
	public String rollback(final VnfParameters params) {
		params.getVim().deleteStorage(params.getVimConnectionInformation(), params.getVimResourceId());
		return null;
	}

	@Override
	public void connect(final ListenableGraph<UnitOfWork<VnfTask, VnfParameters>, ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> g, final Map<String, UnitOfWork<VnfTask, VnfParameters>> cache) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<WfDependency> getDependencies() {
		return new ArrayList<>();
	}

	@Override
	public List<WfProduce> getProduce() {
		return Arrays.asList(new WfProduce(Storage.class, storageTask.getToscaName(), storageTask.getId()));
	}

}
