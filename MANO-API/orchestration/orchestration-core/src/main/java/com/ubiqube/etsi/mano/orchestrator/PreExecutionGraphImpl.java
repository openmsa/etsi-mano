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

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTaskConnectivity;

public class PreExecutionGraphImpl<U> implements PreExecutionGraph<U> {
	/**
	 * The create VT graps.
	 */
	private final ListenableGraph<VirtualTask<U>, VirtualTaskConnectivity> g;

	/**
	 * Graph for removal.
	 */
	private final ListenableGraph<VirtualTask<U>, VirtualTaskConnectivity> r;

	public PreExecutionGraphImpl(final ListenableGraph<VirtualTask<U>, VirtualTaskConnectivity> g, final ListenableGraph<VirtualTask<U>, VirtualTaskConnectivity> r) {
		this.g = g;
		this.r = r;
	}

	public ListenableGraph<VirtualTask<U>, VirtualTaskConnectivity> getCreateGraph() {
		return g;
	}

	public ListenableGraph<VirtualTask<U>, VirtualTaskConnectivity> getDeleteGraph() {
		return r;
	}

	@Override
	public List<VirtualTask<U>> getPreTasks() {
		final List<VirtualTask<U>> ret = new ArrayList<>(g.vertexSet().stream().toList());
		ret.addAll(r.vertexSet().stream().toList());
		return ret;
	}

}
