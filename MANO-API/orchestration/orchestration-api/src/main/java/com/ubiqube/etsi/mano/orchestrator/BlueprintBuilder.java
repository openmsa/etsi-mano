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

import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.NodeConnectivity;

public class BlueprintBuilder {
	private ListenableGraph<Class<? extends Node>, NodeConnectivity> g;
	private Class<? extends Node> last;

	public static BlueprintBuilder builder() {
		return new BlueprintBuilder();
	}

	public BlueprintBuilder from(final Class<? extends Node> class1) {
		g.addVertex(class1);
		last = class1;
		return this;
	}

	public BlueprintBuilder connect(final Class<? extends Node> class1) {
		g.addVertex(class1);
		g.addEdge(last, class1);
		last = class1;
		return this;
	}

	public BlueprintBuilder then(final Class<? extends Node> class1) {
		connect(class1);
		return this;
	}

	public BlueprintBuilder fork() {
		//
		return this;
	}

	public BlueprintBuilder join() {
		//
		return this;
	}

	public ExecutionGraph build() {
		// TODO Auto-generated method stub
		return null;
	}

}
