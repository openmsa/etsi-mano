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
package com.ubiqube.etsi.mano.service.graph;

import java.util.List;
import java.util.Set;

import org.jgrapht.ListenableGraph;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.task.ExecutionResults;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.UowTaskCreateProvider;
import com.ubiqube.etsi.mano.service.graph.vnfm.UowTaskDeleteProvider;
import com.ubiqube.etsi.mano.service.graph.vnfm.VnfParameters;
import com.ubiqube.etsi.mano.service.plan.VnfPlanner;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.node.Node;

@Service
public class VnfWorkflow {
	private final VnfPlanner planner;
	private final VnfPlanExecutor executor;

	public VnfWorkflow(final VnfPlanner planner, final VnfPlanExecutor executor) {
		super();
		this.planner = planner;
		this.executor = executor;
	}

	public void setWorkflowBlueprint(final VnfPackage bundle, final VnfBlueprint blueprint, final Set<ScaleInfo> scaling, final List<ConnectivityEdge<Node>> conns) {
		planner.doPlan(bundle, blueprint, scaling, conns);
	}

	public VnfReport execCreate(final VnfBlueprint plan, final VnfParameters params) {
		final ListenableGraph<UnitOfWork<VnfTask, VnfParameters>, ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> createPlan = planner.convertToExecution(plan, ChangeType.ADDED);
		final ExecutionResults<UnitOfWork<VnfTask, VnfParameters>, String> createResults = executor.execCreate(createPlan, () -> new UowTaskCreateProvider(params));
		return new VnfReport(createResults);
	}

	public VnfReport execDelete(final VnfBlueprint blueprint, final VnfParameters vparams) {
		final ListenableGraph<UnitOfWork<VnfTask, VnfParameters>, ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> graph = planner.convertToExecution(blueprint, ChangeType.REMOVED);
		final ExecutionResults<UnitOfWork<VnfTask, VnfParameters>, String> removeResults = executor.execDelete(graph, () -> new UowTaskDeleteProvider<>(vparams));
		return new VnfReport(removeResults);
	}

}
