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
package com.ubiqube.etsi.mano.orchestrator;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWork;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWorkConnectivity;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ExecutionGraphImpl implements ExecutionGraph {

	private final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> g;
	private final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> r;

	public ExecutionGraphImpl(final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> g, final ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> nr) {
		this.g = g;
		this.r = nr;
	}

	public ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> getCreateImplementation() {
		return g;
	}

	public ListenableGraph<UnitOfWork<?>, UnitOfWorkConnectivity> getDeleteImplementation() {
		return r;
	}

}
