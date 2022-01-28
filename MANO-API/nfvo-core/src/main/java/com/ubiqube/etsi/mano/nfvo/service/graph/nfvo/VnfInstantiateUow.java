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
package com.ubiqube.etsi.mano.nfvo.service.graph.nfvo;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVnfInstantiateTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.ExternalManagedVirtualLink;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.VnfCreateNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.VnfInstantiateNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;
import com.ubiqube.etsi.mano.service.VnfmInterface;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VnfInstantiateUow extends AbstractNsUnitOfWork<NsVnfInstantiateTask> {

	private static final Logger LOG = LoggerFactory.getLogger(VnfInstantiateUow.class);

	private final VnfmInterface vnfm;

	private final NsVnfInstantiateTask task;

	public VnfInstantiateUow(final VirtualTask<NsVnfInstantiateTask> task, final VnfmInterface vnfm) {
		super(task, VnfInstantiateNode.class);
		this.task = task.getParameters();
		this.vnfm = vnfm;
	}

	/**
	 * XXX We should add a Max wait.
	 *
	 * @param vnfLcmOpOccs
	 * @param vnfm
	 * @return
	 */
	private VnfBlueprint waitLcmCompletion(final VnfBlueprint vnfLcmOpOccs, final VnfmInterface vnfm) {
		VnfBlueprint tmp = vnfLcmOpOccs;
		OperationStatusType state = OperationStatusType.NOT_STARTED;
		while (state == OperationStatusType.PROCESSING || OperationStatusType.STARTING == state || OperationStatusType.NOT_STARTED == state) {
			tmp = vnfm.vnfLcmOpOccsGet(task.getServer(), vnfLcmOpOccs.getId());
			state = tmp.getOperationStatus();
			sleepSeconds(1);
		}
		LOG.info("VNF Lcm complete with state: {}", state);
		return tmp;
	}

	private static void sleepSeconds(final long seconds) {
		try {
			Thread.sleep(seconds * 1000L);
		} catch (final InterruptedException e) {
			LOG.warn("Interrupted exception.", e);
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public String execute(final Context context) {
		final String inst = context.get(VnfCreateNode.class, task.getAlias());
		final List<ExternalManagedVirtualLink> net = task.getExternalNetworks().stream().map(x -> {
			final String resource = context.get(Network.class, x);
			if (null == resource) {
				LOG.warn("Could not find network resource {} => {}, not released.", x, context);
				return null;
			}
			final ExternalManagedVirtualLink ext = new ExternalManagedVirtualLink();
			ext.setResourceId(resource);
			ext.setExtManagedVirtualLinkId(x);
			ext.setResourceProviderId("ETSI-MANO-VNFM");
			ext.setVimId(task.getServer().getId().toString());
			return ext;
		})
				.filter(Objects::nonNull)
				.toList();
		final VnfInstantiate request = createRequest();
		request.setExtManagedVirtualLinks(net);
		final VnfBlueprint res = vnfm.vnfInstatiate(task.getServer(), inst, request);
		final VnfBlueprint result = waitLcmCompletion(res, vnfm);
		if (OperationStatusType.COMPLETED != result.getOperationStatus()) {
			final String details = Optional.ofNullable(result.getError()).map(FailureDetails::getDetail).orElse("[No content]");
			throw new GenericException("VNF LCM Failed: " + details + " With state:  " + result.getOperationStatus());
		}
		return null;
	}

	private VnfInstantiate createRequest() {
		final VnfInstantiate inst = new VnfInstantiate();
		// inst.setExtManagedVirtualLinks(task.getExternalNetworks());
		// inst.setExtVirtualLinks(extVirtualLinks);
		inst.setFlavourId(task.getFlavourId());
		inst.setInstantiationLevelId(task.getInstantiationLevelId());
		inst.setLocalizationLanguage(task.getLocalizationLanguage());
		// inst.setVimConnectionInfo(task.getVimConnectionInfo());
		return inst;
	}

	@Override
	public String rollback(final Context context) {
		try {
			final VnfInstance inst = vnfm.getVnfInstance(task.getServer(), task.getVimResourceId());
			if (inst.getInstantiationState() == InstantiationState.NOT_INSTANTIATED) {
				return null;
			}
		} catch (final WebClientResponseException.NotFound e) {
			LOG.trace("Not found exception, ignoring.", e);
			return null;
		}
		final VnfBlueprint lcm = vnfm.vnfTerminate(task.getServer(), task.getVimResourceId());
		if (lcm == null) {
			return null;
		}
		final VnfBlueprint result = waitLcmCompletion(lcm, vnfm);
		if (OperationStatusType.COMPLETED != result.getOperationStatus()) {
			throw new GenericException("VNF LCM Failed: " + result.getError().getDetail());
		}
		return result.getId().toString();
	}

}
