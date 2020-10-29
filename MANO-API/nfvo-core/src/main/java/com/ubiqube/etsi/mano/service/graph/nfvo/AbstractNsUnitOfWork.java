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
package com.ubiqube.etsi.mano.service.graph.nfvo;

import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedBase;

public abstract class AbstractNsUnitOfWork implements NsUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final NsInstantiatedBase resourceHandleEntity;

	private final String name;

	// Nothing.
	public AbstractNsUnitOfWork(final NsInstantiatedBase _resourceHandleEntity, final String _name) {
		resourceHandleEntity = _resourceHandleEntity;
		name = _name;
	}

	@Override
	public final NsInstantiatedBase getResourceHandleEntity() {
		return resourceHandleEntity;
	}

	@Override
	public final String getName() {
		return getPrefix() + "_" + name;
	}

	protected abstract String getPrefix();

	@Override
	public final String getToscaName() {
		return name;
	}

}
