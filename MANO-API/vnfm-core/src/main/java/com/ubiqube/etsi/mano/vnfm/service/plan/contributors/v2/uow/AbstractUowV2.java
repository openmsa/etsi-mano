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

import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWork;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <U> The Task.
 */
public abstract class AbstractUowV2<U> implements UnitOfWork<U> {
	private final VirtualTask<U> task;
	private final Class<? extends Node> node;

	protected AbstractUowV2(final VirtualTask<U> task, final Class<? extends Node> node) {
		super();
		this.task = task;
		this.node = node;
	}

	@Override
	public final VirtualTask<U> getTask() {
		return task;
	}

	@Override
	public final Class<? extends Node> getNode() {
		return node;
	}
}
