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
package com.ubiqube.etsi.mano.nfvo.service.graph;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.ListenableGraph;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.task.ExecutionResults;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.nfvo.service.graph.nfvo.NsParameters;
import com.ubiqube.etsi.mano.nfvo.service.graph.nfvo.UowNsTaskCreateProvider;
import com.ubiqube.etsi.mano.nfvo.service.graph.nfvo.UowNsTaskDeleteProvider;
import com.ubiqube.etsi.mano.nfvo.service.plan.NsPlanner;
import com.ubiqube.etsi.mano.nfvo.service.plan.contributors.AbstractNsContributor;
import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.ExecutionGraph;
import com.ubiqube.etsi.mano.orchestrator.OrchExecutionResults;
import com.ubiqube.etsi.mano.orchestrator.OrchestrationService;
import com.ubiqube.etsi.mano.orchestrator.Planner;
import com.ubiqube.etsi.mano.orchestrator.PreExecutionGraph;
import com.ubiqube.etsi.mano.orchestrator.nodes.ConnectivityEdge;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.event.Workflow;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.graph.GraphTools;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NsWorkflow implements Workflow<NsdPackage, NsBlueprint, NsReport, NsTask> {
	private final NsPlanner planner;
	private final Planner<NsBlueprint, NsTask, NsTask> planv2;
	private final NsPlanExecutor executor;
	private final List<AbstractNsContributor> planContributors;
	private final OrchestrationService<?> orchestrationService;

	public NsWorkflow(final Planner<NsBlueprint, NsTask, NsTask> planv2, final NsPlanExecutor executor, final List<AbstractNsContributor> planContributors,
			final OrchestrationService<?> orchestrationService, final NsPlanner planner) {
		super();
		this.planv2 = planv2;
		this.executor = executor;
		this.planContributors = planContributors;
		this.orchestrationService = orchestrationService;
		this.planner = planner;
	}

	@Override
	public PreExecutionGraph<NsTask> setWorkflowBlueprint(final NsdPackage bundle, final NsBlueprint blueprint) {
		final List<Class<? extends Node>> planConstituent = new ArrayList<>();
		// Doesn't works with jdk17 but fine with eclipse.
		for (final AbstractNsContributor b : planContributors) {
			planConstituent.add(b.getNode());
		}
		final PreExecutionGraph<NsTask> plan = planv2.makePlan(new NsBundleAdapter(bundle), planConstituent, blueprint);
		plan.getPreTasks().stream().map(VirtualTask::getParameters).forEach(blueprint::addTask);
		return plan;
	}

	@Override
	public NsReport execDelete(final NsBlueprint blueprint, final GenericExecParams vparams) {
		final ListenableGraph<UnitOfWork<NsTask, NsParameters>, ConnectivityEdge<UnitOfWork<NsTask, NsParameters>>> graph = planner.convertToExecution(blueprint, ChangeType.REMOVED);
		GraphTools.exportGraph(graph, "vnf-del.dot");
		final ExecutionResults<UnitOfWork<NsTask, NsParameters>, String> removeResults = executor.execDelete(graph, () -> new UowNsTaskDeleteProvider<>((NsParameters) vparams));
		return new NsReport(removeResults);
	}

	@Override
	public NsReport execCreate(final NsBlueprint plan, final GenericExecParams params) {
		final ListenableGraph<UnitOfWork<NsTask, NsParameters>, ConnectivityEdge<UnitOfWork<NsTask, NsParameters>>> createPlan = planner.convertToExecution(plan, ChangeType.ADDED);
		GraphTools.exportGraph(createPlan, "vnf-added.dot");
		final ExecutionResults<UnitOfWork<NsTask, NsParameters>, String> createResults = executor.execCreate(createPlan, () -> new UowNsTaskCreateProvider<>((NsParameters) params));
		return new NsReport(createResults);
	}

	@Override
	public OrchExecutionResults<NsTask> execute(final PreExecutionGraph<NsTask> plan, final NsBlueprint parameters) {
		final ExecutionGraph impl = planv2.implement(plan);
		final Context context = orchestrationService.createEmptyContext();
		populateExtNetworks(context, parameters);
		return planv2.execute(impl, context, new NsOrchListenetImpl());
	}

	private void populateExtNetworks(final Context context, final NsBlueprint parameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(final PreExecutionGraph<NsTask> prePlan, final Blueprint<NsTask, ?> localPlan) {
		prePlan.getPreTasks().forEach(x -> {
			final NsTask task = find(x.getParameters().getToscaId(), localPlan);
			x.setParameters(task);
		});
	}

	private static NsTask find(final String id, final Blueprint<NsTask, ?> localPlan) {
		return localPlan.getTasks().stream().filter(x -> x.getToscaId().equals(id)).findFirst().orElseThrow();
	}

}
