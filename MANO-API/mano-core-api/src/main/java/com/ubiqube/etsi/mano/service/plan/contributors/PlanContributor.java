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
package com.ubiqube.etsi.mano.service.plan.contributors;

import java.util.List;
import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.wfe2.DependencyBuilder;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <P>
 * @param <B>
 * @param <U>
 * @param <R>
 * @deprecated Use orchestration module.
 */
@Deprecated(forRemoval = true)
public interface PlanContributor<P, B, U extends Task, R> {

	Class<? extends Node> getContributionType();

	// XXX: Why do we have a scaleInfo here ???
	List<U> contribute(P bundle, B blueprint, Set<ScaleInfo> scaling);

	List<UnitOfWork<U, R>> convertTasksToExecNode(Set<U> tasks, B blueprint);

	void getDependencies(DependencyBuilder dependencyBuilder);
}
