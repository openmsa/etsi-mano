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
package com.ubiqube.etsi.mano.service.graph.wfe2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.NodeConnectivity;
import com.ubiqube.etsi.mano.service.plan.contributors.PlanContributor;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class DependencyBuilder {
	private final List<NodeConnectivity> edges = new ArrayList<>();
	private final Map<Class<? extends Node>, ReplaceBuilder> replacements = new HashMap<>();
	private final Class<? extends Node> contributor;

	public DependencyBuilder(final PlanContributor _contributor) {
		contributor = _contributor.getContributionType();
	}

	public DependencyBuilder connectionFrom(final Class<? extends Node> class1) {
		edges.add(new NodeConnectivity(class1, contributor));
		return this;
	}

	public DependencyBuilder connectTo(final Class<? extends Node> class1) {
		edges.add(new NodeConnectivity(contributor, class1));
		return this;
	}

	public ReplaceBuilder replace(final Class<? extends Node> class1) {
		final ReplaceBuilder repl = new ReplaceBuilder(contributor);
		replacements.put(class1, repl);
		return repl;
	}

	public Map<Class<? extends Node>, ReplaceBuilder> getReplacement() {
		return replacements;
	}

	public List<NodeConnectivity> getEdges() {
		return edges;
	}

}
