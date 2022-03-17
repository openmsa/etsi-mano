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
package com.ubiqube.etsi.mano.tf;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.common.ListKeyPair;
import com.ubiqube.etsi.mano.dao.mano.nsd.CpPair;
import com.ubiqube.etsi.mano.dao.mano.nsd.NfpDescriptor;
import com.ubiqube.etsi.mano.dao.mano.nsd.VnffgDescriptor;
import com.ubiqube.etsi.mano.dao.mano.nsd.VnffgInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsSfcTask;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.orchestrator.OrchestrationService;
import com.ubiqube.etsi.mano.orchestrator.SystemBuilder;
import com.ubiqube.etsi.mano.orchestrator.entities.SystemConnections;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWork;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.sys.System;
import com.ubiqube.etsi.mano.tf.config.ContrailConfig;
import com.ubiqube.etsi.mano.tf.entities.NetworkPolicyTask;
import com.ubiqube.etsi.mano.tf.entities.PortTupleTask;
import com.ubiqube.etsi.mano.tf.entities.PtLinkTask;
import com.ubiqube.etsi.mano.tf.entities.ServiceInstanceTask;
import com.ubiqube.etsi.mano.tf.entities.ServiceTemplateTask;
import com.ubiqube.etsi.mano.tf.uow.NetworkPolicyUow;
import com.ubiqube.etsi.mano.tf.uow.PortTupleUow;
import com.ubiqube.etsi.mano.tf.uow.PtLinkUow;
import com.ubiqube.etsi.mano.tf.uow.ServiceInstanceUow;
import com.ubiqube.etsi.mano.tf.uow.ServiceTemplateUow;
import com.ubiqube.etsi.mano.tf.vt.NetworkPolicyVt;
import com.ubiqube.etsi.mano.tf.vt.PortTupleVt;
import com.ubiqube.etsi.mano.tf.vt.PtLinkVt;
import com.ubiqube.etsi.mano.tf.vt.ServiceInstanceVt;
import com.ubiqube.etsi.mano.tf.vt.ServiceTemplateVt;
import com.ubiqube.etsi.mano.vim.jpa.NsTaskJpa;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class ContrailSystem implements System<NsSfcTask> {
	private final NsTaskJpa nsTaskJpa;
	private final ContrailConfig contrailConfig;

	public ContrailSystem(final NsTaskJpa nsTaskJpa, final ContrailConfig cfg) {
		this.nsTaskJpa = nsTaskJpa;
		this.contrailConfig = cfg;
	}

	@Override
	public String getProviderId() {
		return "SFC";
	}

	@Transactional(TxType.NOT_SUPPORTED)
	@Override
	public SystemBuilder<UnitOfWork<NsSfcTask>> getImplementation(final OrchestrationService<NsSfcTask> orchestrationService, final VirtualTask<NsSfcTask> virtualTask, final SystemConnections vim2) {
		final SystemConnections vim = new SystemConnections();
		vim.setInterfaceInfo(new HashMap<>());
		vim.getInterfaceInfo().put("endpoint", contrailConfig.getEndpoint());
		vim.setAccessInfo(new HashMap<>());
		vim.getAccessInfo().put("username", contrailConfig.getUsername());
		vim.getAccessInfo().put("password", contrailConfig.getPassword());
		final SystemBuilder builder = orchestrationService.createEmptySystemBuilder();
		final NsSfcTask task = virtualTask.getParameters();
		final VnffgDescriptor vnffg = virtualTask.getParameters().getVnffg();
		// Service Template.
		final ServiceTemplateTask serviceTemplateTask = createServiceTemplateTask(task, vnffg);
		final ServiceTemplateUow stUow = new ServiceTemplateUow(new ServiceTemplateVt(serviceTemplateTask), vim);
		vnffg.getNfpd().forEach(nfp -> {
			nfp.getInstancces().stream()
					.flatMap(x -> x.getPairs().stream())
					.forEach(x -> {
						final String name = x.getIngressVl() + "-" + x.getEgressVl();
						final ServiceInstanceTask siTask = createServiceInstance(task, x, name, "st-" + vnffg.getName());
						final ServiceInstanceUow siUow = new ServiceInstanceUow(new ServiceInstanceVt(siTask), vim);
						// Policy Task.
						final NetworkPolicyTask networkPolicyTask = createNetworkPolicyTask(task, vnffg, siTask, name);
						final NetworkPolicyUow npUow = new NetworkPolicyUow(new NetworkPolicyVt(networkPolicyTask), vim);
						builder.add(siUow, npUow);
						builder.add(stUow, siUow);
						final PortTupleTask ptt = createPortTuple(task, x, siTask);
						final PortTupleUow ptUow = new PortTupleUow(new PortTupleVt(ptt), vim);
						builder.add(siUow, ptUow);
						final PtLinkTask ptLinkTask = createTask(PtLinkTask::new, task);
						ptLinkTask.setToscaName("pt-" + ptt.getToscaName());
						ptLinkTask.setLeftPortId(ptt.getLeftPortId());
						ptLinkTask.setRightPortId(ptt.getRightPortId());
						ptLinkTask.setPortTupleName(ptt.getToscaName());
						final PtLinkUow ptlUow = new PtLinkUow(new PtLinkVt(ptLinkTask), vim);
						builder.add(ptUow, ptlUow);
					});
		});
		return builder;
	}

	private PortTupleTask createPortTuple(final NsSfcTask task, final CpPair tuple, final ServiceInstanceTask serviceInstanceTask) {
		final PortTupleTask ptt = createTask(PortTupleTask::new, task);
		ptt.setToscaName(tuple.getToscaName());
		ptt.setServiceInstanceName(serviceInstanceTask.getToscaName());
		ptt.setLeftPortId(tuple.getIngress());
		ptt.setRightPortId(tuple.getEgress());
		return nsTaskJpa.save(ptt);
	}

	private ServiceInstanceTask createServiceInstance(final NsSfcTask task, final CpPair left, final String string, final String vnffgName) {
		final ServiceInstanceTask serviceInstanceTask = createTask(ServiceInstanceTask::new, task);
		serviceInstanceTask.setToscaName("si-" + string);
		serviceInstanceTask.setServiceTemplateId(vnffgName);
		// Not sure it's correct.
		serviceInstanceTask.setCpPorts(left);
		return nsTaskJpa.save(serviceInstanceTask);
	}

	private static List<ListKeyPair> gatherNetworks(final VnffgDescriptor vnffg) {
		final AtomicInteger i = new AtomicInteger();
		return vnffg.getNfpd().stream()
				.flatMap(x -> x.getInstancces().stream())
				.flatMap(x -> x.getPairs().stream())
				.map(x -> Arrays.asList(x.getIngressVl(), x.getEgressVl()))
				.flatMap(List::stream)
				.map(x -> new ListKeyPair(x, i.incrementAndGet()))
				.collect(Collectors.toSet())
				.stream()
				.toList();
	}

	private ServiceTemplateTask createServiceTemplateTask(final NsSfcTask task, final VnffgDescriptor vnffg) {
		final ServiceTemplateTask serviceTemplateTask = createTask(ServiceTemplateTask::new, task);
		serviceTemplateTask.setToscaName("st-" + vnffg.getName());
		return nsTaskJpa.save(serviceTemplateTask);
	}

	private NetworkPolicyTask createNetworkPolicyTask(final NsSfcTask task, final VnffgDescriptor vnffg, final ServiceInstanceTask serviceInstanceTask, final String string) {
		final NetworkPolicyTask networkPolicyTask = createTask(NetworkPolicyTask::new, task);
		networkPolicyTask.setClassifier(vnffg.getClassifier());
		networkPolicyTask.setToscaName("np-" + string);
		networkPolicyTask.setLeftId(getLogicalSourceVl(vnffg));
		networkPolicyTask.setRightId(getLogicalDestinationVl(vnffg));
		networkPolicyTask.setServiceInstance(serviceInstanceTask.getToscaName());
		return nsTaskJpa.save(networkPolicyTask);
	}

	protected static <U extends NsTask> U createTask(final Supplier<U> newInstance, final NsSfcTask toscaEntity) {
		final U task = newInstance.get();
		task.setId(UUID.randomUUID());
		task.setStartDate(LocalDateTime.now());
		task.setStatus(PlanStatusType.NOT_STARTED);
		task.setChangeType(ChangeType.ADDED);
		task.setToscaName(toscaEntity.getToscaName());
		task.setAlias(toscaEntity.getToscaName());
		task.setType(ResourceTypeEnum.VNFFG);
		return task;
	}

	private static String getLogicalDestinationVl(final VnffgDescriptor vnffg) {
		if (vnffg.getNfpd().isEmpty()) {
			return null;
		}
		final List<NfpDescriptor> nfpds = vnffg.getNfpd();
		final List<VnffgInstance> insts = nfpds.get(nfpds.size() - 1).getInstancces();
		if (insts.isEmpty()) {
			return null;
		}
		final List<CpPair> pairs = insts.get(insts.size() - 1).getPairs();
		if (pairs.isEmpty()) {
			return null;
		}
		final CpPair p = pairs.get(pairs.size() - 1);
		if (p.getEgressVl() != null) {
			return p.getEgressVl();
		}
		return p.getIngressVl();
	}

	private static String getLogicalSourceVl(final VnffgDescriptor vnffg) {
		if (vnffg.getNfpd().isEmpty()) {
			return null;
		}
		final List<VnffgInstance> insts = vnffg.getNfpd().get(0).getInstancces();
		if (insts.isEmpty()) {
			return null;
		}
		final List<CpPair> pairs = insts.get(0).getPairs();
		if (pairs.isEmpty()) {
			return null;
		}
		return pairs.get(0).getIngressVl();
	}

}
