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
import java.util.stream.Collectors;

import org.jgrapht.ListenableGraph;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.task.ExecutionResults;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.jpa.VnfTaskJpa;
import com.ubiqube.etsi.mano.orchestrator.ExecutionGraph;
import com.ubiqube.etsi.mano.orchestrator.OrchExecutionResults;
import com.ubiqube.etsi.mano.orchestrator.Planner;
import com.ubiqube.etsi.mano.orchestrator.PreExecutionGraph;
import com.ubiqube.etsi.mano.orchestrator.nodes.ConnectivityEdge;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.event.Workflow;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.UowTaskCreateProvider;
import com.ubiqube.etsi.mano.service.graph.vnfm.UowTaskDeleteProvider;
import com.ubiqube.etsi.mano.service.graph.vnfm.VnfParameters;
import com.ubiqube.etsi.mano.service.plan.VnfPlanner;
import com.ubiqube.etsi.mano.service.plan.contributors.AbstractVnfPlanContributor;

@Service
public class VnfWorkflow implements Workflow<VnfPackage, VnfBlueprint, VnfReport, VnfTask> {
	private final VnfPlanner planner;
	private final Planner<VnfBlueprint> planv2;
	private final VnfPlanExecutor executor;
	private final VnfTaskJpa vnfTaskJpa;
	private final List<AbstractVnfPlanContributor> planContributors;

	public VnfWorkflow(final VnfPlanner planner, final VnfPlanExecutor executor, final VnfTaskJpa vnfTaskJpa, final List<AbstractVnfPlanContributor> _planContributors, final Planner<VnfBlueprint> planv2) {
		this.planner = planner;
		this.executor = executor;
		planContributors = _planContributors;
		this.vnfTaskJpa = vnfTaskJpa;
		this.planv2 = planv2;
	}

	@Override
	public PreExecutionGraph<VnfTask> setWorkflowBlueprint(final VnfPackage bundle, final VnfBlueprint blueprint) {
		final List<Class<? extends Node>> planConstituent = planContributors.stream().map(AbstractVnfPlanContributor::getContributionType).collect(Collectors.toList());
		final PreExecutionGraph<VnfTask> plan = planv2.makePlan(new VnfBundleAdapter(bundle), planConstituent, blueprint);
		plan.getPreTasks().stream().map(VirtualTask::getParameters).forEach(blueprint::addTask);
		return plan;
	}

	@Override
	public OrchExecutionResults execute(final PreExecutionGraph<VnfTask> plan, final VnfBlueprint parameters) {
		final ExecutionGraph impl = planv2.implement(plan);
		return planv2.execute(impl, new OrchListenetImpl(vnfTaskJpa));
	}

	@Override
	public VnfReport execCreate(final VnfBlueprint plan, final GenericExecParams params) {
		final ListenableGraph<UnitOfWork<VnfTask, VnfParameters>, ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> createPlan = planner.convertToExecution(plan, ChangeType.ADDED);
		GraphTools.exportGraph(createPlan, "vnf-added.dot");
		final ExecutionResults<UnitOfWork<VnfTask, VnfParameters>, String> createResults = executor.execCreate(createPlan, () -> new UowTaskCreateProvider<>((VnfParameters) params));
		return new VnfReport(createResults);
	}

	@Override
	public VnfReport execDelete(final VnfBlueprint blueprint, final GenericExecParams vparams) {
		final ListenableGraph<UnitOfWork<VnfTask, VnfParameters>, ConnectivityEdge<UnitOfWork<VnfTask, VnfParameters>>> graph = planner.convertToExecution(blueprint, ChangeType.REMOVED);
		GraphTools.exportGraph(graph, "vnf-del.dot");
		final ExecutionResults<UnitOfWork<VnfTask, VnfParameters>, String> removeResults = executor.execDelete(graph, () -> new UowTaskDeleteProvider<>((VnfParameters) vparams));
		return new VnfReport(removeResults);
	}

	@Override
	public void refresh(final PreExecutionGraph<VnfTask> prePlan, final Blueprint<VnfTask, ?> localPlan) {
		prePlan.getPreTasks().forEach(x -> {
			final VnfTask task = find(x.getParameters().getToscaId(), localPlan);
			x.setParameters(task);
		});
	}

	private static VnfTask find(final String id, final Blueprint<VnfTask, ?> localPlan) {
		return localPlan.getTasks().stream().filter(x -> x.getToscaId().equals(id)).findFirst().orElseThrow();
	}

}
