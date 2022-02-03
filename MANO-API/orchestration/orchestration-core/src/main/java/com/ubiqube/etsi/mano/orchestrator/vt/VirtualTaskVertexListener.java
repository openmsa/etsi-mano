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
package com.ubiqube.etsi.mano.orchestrator.vt;

import org.jgrapht.event.GraphEdgeChangeEvent;
import org.jgrapht.event.GraphListener;
import org.jgrapht.event.GraphVertexChangeEvent;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VirtualTaskVertexListener<V> implements GraphListener<VirtualTask<V>, VirtualTaskConnectivity<V>> {

	@Override
	public void vertexAdded(final GraphVertexChangeEvent<VirtualTask<V>> e) {
		// Nothing.
	}

	@Override
	public void vertexRemoved(final GraphVertexChangeEvent<VirtualTask<V>> e) {
		// Nothing.
	}

	@Override
	public void edgeAdded(final GraphEdgeChangeEvent<VirtualTask<V>, VirtualTaskConnectivity<V>> e) {
		final VirtualTaskConnectivity<V> edge = e.getEdge();
		edge.setSource(e.getEdgeSource());
		edge.setTarget(e.getEdgeTarget());
	}

	@Override
	public void edgeRemoved(final GraphEdgeChangeEvent<VirtualTask<V>, VirtualTaskConnectivity<V>> e) {
		// Nothing.
	}

}
