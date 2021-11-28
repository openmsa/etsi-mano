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
import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.v2.ObjectStorageTask;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;

public class ObjectStorageUow extends VnfAbstractUnitOfWork {

	private final VnfStorage vnfStorage;

	public ObjectStorageUow(final ObjectStorageTask objectStorageTask, final VnfStorage vnfStorage) {
		super(objectStorageTask);
		this.vnfStorage = vnfStorage;
	}

	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Override
	public String exec(final VnfParameters params) {
		return params.getVim().storage(params.getVimConnectionInformation()).createObjectStorage(vnfStorage);
	}

	@Override
	protected String getPrefix() {
		return "object_storage";
	}

	@Override
	public String rollback(final VnfParameters params) {
		params.getVim().storage(params.getVimConnectionInformation()).deleteObjectStorage(params.getVimResourceId());
		return null;
	}

	@Override
	public List<WfDependency> getDependencies() {
		return new ArrayList<>();
	}

	@Override
	public List<WfProduce> getProduce() {
		return List.of();
	}

}
