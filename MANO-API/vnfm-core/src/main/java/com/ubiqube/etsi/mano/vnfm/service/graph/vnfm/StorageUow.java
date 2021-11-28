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
package com.ubiqube.etsi.mano.vnfm.service.graph.vnfm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.v2.StorageTask;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Storage;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;

public class StorageUow extends VnfAbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final StorageTask storageTask;

	private final VnfStorage vnfStorage;

	public StorageUow(final StorageTask storageTask, final VnfStorage vnfStorage) {
		super(storageTask);
		this.vnfStorage = vnfStorage;
		this.storageTask = storageTask;
	}

	@Override
	public String exec(final VnfParameters params) {
		return params.getVim().storage(params.getVimConnectionInformation()).createStorage(vnfStorage, storageTask.getAlias());
	}

	@Override
	protected String getPrefix() {
		return "block_storage";
	}

	@Override
	public String rollback(final VnfParameters params) {
		params.getVim().storage(params.getVimConnectionInformation()).deleteStorage(params.getVimResourceId());
		return null;
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
