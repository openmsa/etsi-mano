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

import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public abstract class VnfAbstractUnitOfWork extends VnfmUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final String name;

	private final VnfTask task;

	public VnfAbstractUnitOfWork(final VnfTask _task) {
		task = _task;
		name = task.getToscaName();
	}

	@Override
	public final VnfTask getTaskEntity() {
		return task;
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

	@Override
	public String toString() {
		return task.getClass().getSimpleName() + "(" + name + ")";
	}

}
