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

import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;

public class StartUow<T extends Task, P> implements UnitOfWork<T, P> {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final T task;

	public StartUow(final T _task) {
		task = _task;
	}

	@Override
	public String exec(final P params) {
		return null;
	}

	@Override
	public String rollback(final P params) {
		return null;
	}

	@Override
	public void connect(final ListenableGraph<UnitOfWork<T, P>, ConnectivityEdge<UnitOfWork<T, P>>> g, final Map<String, UnitOfWork<T, P>> cache) {
		// Nothing to do.
	}

	@Override
	public String getName() {
		return "start";
	}

	@Override
	public String getToscaName() {
		return "start";
	}

	@Override
	public T getTaskEntity() {
		return task;
	}

}
