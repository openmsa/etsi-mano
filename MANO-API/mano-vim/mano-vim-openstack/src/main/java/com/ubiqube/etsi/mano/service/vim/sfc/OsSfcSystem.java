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
package com.ubiqube.etsi.mano.service.vim.sfc;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.nsd.Classifier;
import com.ubiqube.etsi.mano.dao.mano.nsd.CpPair;
import com.ubiqube.etsi.mano.dao.mano.nsd.VnffgDescriptor;
import com.ubiqube.etsi.mano.dao.mano.nsd.VnffgInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsSfcTask;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.orchestrator.OrchestrationService;
import com.ubiqube.etsi.mano.orchestrator.SystemBuilder;
import com.ubiqube.etsi.mano.orchestrator.entities.SystemConnections;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.sys.System;
import com.ubiqube.etsi.mano.service.vim.sfc.enity.SfcFlowClassifierTask;
import com.ubiqube.etsi.mano.service.vim.sfc.enity.SfcPortChainTask;
import com.ubiqube.etsi.mano.service.vim.sfc.enity.SfcPortPairGroupTask;
import com.ubiqube.etsi.mano.service.vim.sfc.enity.SfcPortPairTask;
import com.ubiqube.etsi.mano.service.vim.sfc.vt.SfcFlowClassifierVt;
import com.ubiqube.etsi.mano.service.vim.sfc.vt.SfcPortChainVt;
import com.ubiqube.etsi.mano.service.vim.sfc.vt.SfcPortPairGroupVt;
import com.ubiqube.etsi.mano.service.vim.sfc.vt.SfcPortPairVt;
import com.ubiqube.etsi.mano.vim.jpa.NsTaskJpa;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
//@Service
public class OsSfcSystem implements System<NsSfcTask> {

	private static final Logger LOG = LoggerFactory.getLogger(OsSfcSystem.class);

	private final NsTaskJpa nsTaskJpa;

	public OsSfcSystem(final NsTaskJpa nsTaskJpa) {
		this.nsTaskJpa = nsTaskJpa;
	}

	@Override
	public String getProviderId() {
		return "SFC";
	}

	@Transactional(TxType.NOT_SUPPORTED)
	@Override
	public SystemBuilder getImplementation(final OrchestrationService<NsSfcTask> orchestrationService, final VirtualTask<NsSfcTask> virtualTask, final SystemConnections vim) {
		final SystemBuilder builder = orchestrationService.createEmptySystemBuilder();
		final NsSfcTask task = virtualTask.getParameters();
		final VnffgDescriptor vnffg = task.getVnffg();
		// Classifier.
		final SfcFlowClassifierTask flowTask = createFlowTask(task, vnffg);
		final SfcFlowClassifierUow flowUow = new SfcFlowClassifierUow(new SfcFlowClassifierVt(flowTask), vim);
		// Port chain.
		final SfcPortChainTask chainTask = createChainPortTask(task, vnffg);
		final Set<String> portPairGroup = chainTask.getPortPairGroups();
		final SfcPortChainUow chain = new SfcPortChainUow(new SfcPortChainVt(chainTask), vim);
		builder.add(flowUow, chain);
		vnffg.getNfpd().forEach(nfp -> {
			nfp.getInstancces().forEach(inst -> {
				final SfcPortPairGroupTask ppg = createPortPairGroup(task, inst);
				final Set<String> portPair = ppg.getPortPair();
				final SfcPortPairGroupUow portPairGroupUow = new SfcPortPairGroupUow(new SfcPortPairGroupVt(ppg), vim);
				portPairGroup.add(ppg.getToscaName());
				builder.add(portPairGroupUow, chain);
				LOG.debug("Adding chain: {} -> {}", chain, portPairGroupUow);
				inst.getPairs().forEach(y -> {
					final SfcPortPairTask pp = createPortPair(y, task);
					portPair.add(y.getToscaName());
					final SfcPortPairUow portPairVt = new SfcPortPairUow(new SfcPortPairVt(pp), vim);
					builder.add(portPairVt, portPairGroupUow);
					LOG.debug("Adding port: {} -> {}", portPairVt, portPairGroupUow);
				});
			});
		});
		return builder;
	}

	private SfcPortPairGroupTask createPortPairGroup(final NsSfcTask task, final VnffgInstance inst) {
		final SfcPortPairGroupTask ppg = createTask(SfcPortPairGroupTask::new, task);
		final Set<String> portPair = new LinkedHashSet<>();
		ppg.setPortPair(portPair);
		ppg.setToscaName(inst.getToscaName());
		return nsTaskJpa.save(ppg);
	}

	private SfcPortChainTask createChainPortTask(final NsSfcTask task, final VnffgDescriptor vnffg) {
		final SfcPortChainTask chainTask = createTask(SfcPortChainTask::new, task);
		final Set<String> portPairGroup = new LinkedHashSet<>();
		chainTask.setPortPairGroups(portPairGroup);
		chainTask.setFlowClassifier(Set.of(vnffg.getClassifier().getClassifierName()));
		chainTask.setToscaName(vnffg.getName());
		chainTask.setAlias(vnffg.getName());
		chainTask.setType(ResourceTypeEnum.VNFFG);
		return nsTaskJpa.save(chainTask);
	}

	private SfcFlowClassifierTask createFlowTask(final NsSfcTask virtualTask, final VnffgDescriptor vnffg) {
		final SfcFlowClassifierTask task = createTask(SfcFlowClassifierTask::new, virtualTask);
		task.setId(UUID.randomUUID());
		final Classifier classifier = vnffg.getClassifier();
		task.setClassifier(classifier);
		task.setToscaName(classifier.getClassifierName());
		task.setAlias(classifier.getClassifierName());
		task.setType(ResourceTypeEnum.VNFFG);
		return nsTaskJpa.save(task);
	}

	private SfcPortPairTask createPortPair(final CpPair y, final NsSfcTask task) {
		final SfcPortPairTask pp = createTask(SfcPortPairTask::new, task);
		pp.setEgressId(y.getEgress());
		pp.setIngressId(y.getIngress());
		pp.setToscaName(y.getToscaName());
		pp.setType(ResourceTypeEnum.VNFFG);
		return nsTaskJpa.save(pp);
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
}
