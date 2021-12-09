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
package com.ubiqube.etsi.mano.service.event;

import com.ubiqube.etsi.mano.dao.mano.PackageBase;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.orchestrator.OrchExecutionResults;
import com.ubiqube.etsi.mano.orchestrator.PreExecutionGraph;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <P>
 * @param <B>
 */
public interface Workflow<P extends PackageBase, B extends Blueprint, R extends Report, T extends Task> {

	PreExecutionGraph<T> setWorkflowBlueprint(P bundle, B blueprint);

	R execDelete(B localPlan, GenericExecParams params);

	R execCreate(B localPlan, GenericExecParams params);

	OrchExecutionResults<T> execute(final PreExecutionGraph<T> plan, final B parameters);

	void refresh(PreExecutionGraph<T> prePlan, Blueprint<T, ?> localPlan);
}
