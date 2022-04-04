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

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.nsd.Classifier;
import com.ubiqube.etsi.mano.dao.mano.nsd.VnffgDescriptor;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsSfcTask;
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

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class OsSfcSystem implements System<NsSfcTask> {

	private static final Logger LOG = LoggerFactory.getLogger(OsSfcSystem.class);

	@Override
	public String getProviderId() {
		return "SFC";
	}

	@Override
	public SystemBuilder getImplementation(final OrchestrationService<NsSfcTask> orchestrationService, final VirtualTask<NsSfcTask> virtualTask, final SystemConnections vim) {
		final SystemBuilder builder = orchestrationService.createEmptySystemBuilder();
		final VnffgDescriptor vnffg = virtualTask.getParameters().getVnffg();
		final SfcFlowClassifierTask flowTask = new SfcFlowClassifierTask();
		final Classifier classifier = vnffg.getClassifier();
		flowTask.setClassifier(classifier);
		flowTask.setToscaName(classifier.getToscaName());
		final SfcFlowClassifierUow flowUow = new SfcFlowClassifierUow(new SfcFlowClassifierVt(flowTask), vim);
		final SfcPortChainTask chainTask = new SfcPortChainTask();
		final Set<String> portPairGroup = new LinkedHashSet<>();
		chainTask.setPortPairGroups(portPairGroup);
		chainTask.setFlowClassifier(Set.of(vnffg.getClassifier().getToscaName()));
		chainTask.setToscaName(vnffg.getName());
		final SfcPortChainUow chain = new SfcPortChainUow(new SfcPortChainVt(chainTask), vim);
		builder.add(flowUow, chain);
		vnffg.getNfpd().forEach(nfp -> {
			nfp.getInstancces().forEach(x -> {
				final SfcPortPairGroupTask ppg = new SfcPortPairGroupTask();
				final List<String> portPair = new ArrayList<>();
				ppg.setPortPair(portPair);
				ppg.setToscaName(x.getToscaName());
				final SfcPortPairGroupUow portPairGroupUow = new SfcPortPairGroupUow(new SfcPortPairGroupVt(ppg), vim);
				portPairGroup.add(ppg.getToscaName());
				builder.add(chain, portPairGroupUow);
				LOG.debug("Adding chain: {} -> {}", chain, portPairGroupUow);
				x.getPairs().forEach(y -> {
					final SfcPortPairTask pp = new SfcPortPairTask();
					pp.setEgressId(y.getEgress());
					pp.setIngressId(y.getIngress());
					pp.setToscaName(y.getToscaName());
					portPair.add(y.getToscaName());
					final SfcPortPairUow portPairVt = new SfcPortPairUow(new SfcPortPairVt(pp), vim);
					builder.add(portPairGroupUow, portPairVt);
					LOG.debug("Adding port: {} -> {}", portPairGroupUow, portPairVt);
				});
			});
		});
		return builder;
	}

}
