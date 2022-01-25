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
package com.ubiqube.etsi.mano.vnfm.service.graph;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.ListenableGraph;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.task.ExecutionResults;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.ExecutionGraph;
import com.ubiqube.etsi.mano.orchestrator.OrchExecutionResults;
import com.ubiqube.etsi.mano.orchestrator.OrchestrationService;
import com.ubiqube.etsi.mano.orchestrator.Planner;
import com.ubiqube.etsi.mano.orchestrator.PreExecutionGraph;
import com.ubiqube.etsi.mano.orchestrator.nodes.ConnectivityEdge;
import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.AffinityRuleNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Compute;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.DnsZone;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Monitoring;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.SecurityGroupNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Storage;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.SubNetwork;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.VnfExtCp;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.event.Workflow;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.graph.GraphTools;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.vnfm.service.graph.vnfm.UowTaskCreateProvider;
import com.ubiqube.etsi.mano.vnfm.service.graph.vnfm.UowTaskDeleteProvider;
import com.ubiqube.etsi.mano.vnfm.service.graph.vnfm.VnfParameters;
import com.ubiqube.etsi.mano.vnfm.service.plan.VnfPlanner;
import com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.AbstractContributorV2Base;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfWorkflow implements Workflow<VnfPackage, VnfBlueprint, VnfReport, VnfTask> {
	private final VnfPlanner planner;
	private final Planner<VnfBlueprint, VnfTask, VnfTask> planv2;
	private final VnfPlanExecutor executor;
	private final List<AbstractContributorV2Base> planContributors;
	private final OrchestrationService<?> orchestrationService;
	private final VnfLiveInstanceJpa vnfInstanceJpa;

	public VnfWorkflow(final VnfPlanner planner, final VnfPlanExecutor executor, final List<AbstractContributorV2Base> planContributors,
			final Planner<VnfBlueprint, VnfTask, VnfTask> planv2,
			final OrchestrationService<?> orchestrationService, final VnfLiveInstanceJpa vnfInstanceJpa) {
		this.planner = planner;
		this.executor = executor;
		this.planContributors = planContributors;
		this.planv2 = planv2;
		this.orchestrationService = orchestrationService;
		this.vnfInstanceJpa = vnfInstanceJpa;
	}

	@Override
	public PreExecutionGraph<VnfTask> setWorkflowBlueprint(final VnfPackage bundle, final VnfBlueprint blueprint) {
		final List<Class<? extends Node>> planConstituent = new ArrayList<>();
		// Doesn't works with jdk17 but fine with eclipse.
		for (final AbstractContributorV2Base b : planContributors) {
			planConstituent.add(b.getNode());
		}
		final PreExecutionGraph<VnfTask> plan = planv2.makePlan(new VnfBundleAdapter(bundle), planConstituent, blueprint);
		plan.getPreTasks().stream().map(VirtualTask::getParameters).forEach(blueprint::addTask);
		return plan;
	}

	@Override
	public OrchExecutionResults execute(final PreExecutionGraph<VnfTask> plan, final VnfBlueprint parameters) {
		final ExecutionGraph impl = planv2.implement(plan);
		final Context context = orchestrationService.createEmptyContext();
		populateExtNetworks(context, parameters);
		return planv2.execute(impl, context, new OrchListenetImpl());
	}

	private void populateExtNetworks(final Context context, final VnfBlueprint parameters) {
		parameters.getExtVirtualLinks().forEach(x -> context.add(Network.class, x.getExtVirtualLinkId(), x.getResourceId()));
		parameters.getExtManagedVirtualLinks().forEach(x -> context.add(Network.class, x.getVnfVirtualLinkDescId(), x.getResourceId()));
		final List<VnfLiveInstance> l = vnfInstanceJpa.findByVnfInstance(parameters.getInstance());
		l.forEach(x -> {
			switch (x.getTask().getType()) {
			case COMPUTE:
				context.add(Compute.class, x.getTask().getToscaName(), x.getResourceId());
				break;
			case DNSZONE:
				context.add(DnsZone.class, x.getTask().getToscaName(), x.getResourceId());
				break;
			case LINKPORT:
				context.add(VnfExtCp.class, x.getTask().getToscaName(), x.getResourceId());
				break;
			case MONITORING:
				context.add(Monitoring.class, x.getTask().getToscaName(), x.getResourceId());
				break;
			case STORAGE:
				context.add(Storage.class, x.getTask().getToscaName(), x.getResourceId());
				break;
			case SUBNETWORK:
				context.add(SubNetwork.class, x.getTask().getToscaName(), x.getResourceId());
				break;
			case VL:
				context.add(Network.class, x.getTask().getToscaName(), x.getResourceId());
				break;
			case AFFINITY_RULE:
				context.add(AffinityRuleNode.class, x.getTask().getToscaName(), x.getResourceId());
				break;
			case SECURITY_GROUP:
				context.add(SecurityGroupNode.class, x.getTask().getToscaName(), x.getResourceId());
				break;
			default:
				throw new GenericException(x.getTask().getType() + " is not handled.");
			}
		});
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
