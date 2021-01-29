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
package com.ubiqube.etsi.mec.mepm.service.graph;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.ListenableGraph;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.task.ExecutionResults;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.PackageBase;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppBlueprint;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppTask;
import com.ubiqube.etsi.mano.service.graph.GraphTools;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.wfe2.WfConfiguration;
import com.ubiqube.etsi.mano.service.plan.contributors.PlanContributor;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.node.Node;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class AppWorkflow {
	private final AppPlanner planner;

	private final AppPlanExecutor executor;

	private final List<AbstractAppPlanContributor> planContributors;

	public AppWorkflow(final AppPlanner planner, final AppPlanExecutor executor, final List<AbstractAppPlanContributor> _planContributors) {
		this.planner = planner;
		this.executor = executor;
		planContributors = _planContributors;
	}

	public void setWorkflowBlueprint(final PackageBase bundle, final AppBlueprint blueprint, final Set<ScaleInfo> scaling) {
		final WfConfiguration wfc = new WfConfiguration((List<PlanContributor>) (Object) planContributors);
		final List<ConnectivityEdge<Class<? extends Node>>> conns = wfc.getConfigurationGraph().edgeSet().stream().collect(Collectors.toList());
		planner.doPlan(bundle, blueprint, scaling, conns);
	}

	public AppReport execCreate(final AppBlueprint plan, final AppParameters params) {
		final ListenableGraph<UnitOfWork<AppTask, AppParameters>, ConnectivityEdge<UnitOfWork<AppTask, AppParameters>>> createPlan = planner.convertToExecution(plan, ChangeType.ADDED);
		GraphTools.exportGraph(createPlan, "added.dot");
		final ExecutionResults<UnitOfWork<AppTask, AppParameters>, String> createResults = executor.execCreate(createPlan, () -> new UowTaskCreateProvider<>(params));
		return new AppReport(createResults);
	}

	public AppReport execDelete(final AppBlueprint blueprint, final AppParameters vparams) {
		final ListenableGraph<UnitOfWork<AppTask, AppParameters>, ConnectivityEdge<UnitOfWork<AppTask, AppParameters>>> graph = planner.convertToExecution(blueprint, ChangeType.REMOVED);
		GraphTools.exportGraph(graph, "del.dot");
		final ExecutionResults<UnitOfWork<AppTask, AppParameters>, String> removeResults = executor.execDelete(graph, () -> new UowTaskDeleteProvider<>(vparams));
		return new AppReport(removeResults);
	}

}
