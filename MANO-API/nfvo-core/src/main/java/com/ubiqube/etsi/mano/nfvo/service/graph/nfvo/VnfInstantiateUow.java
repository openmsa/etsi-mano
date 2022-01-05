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

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVnfTask;
import com.ubiqube.etsi.mano.exception.GenericException;
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
public class VnfInstantiateUow extends AbstractNsUnitOfWork<NsVnfTask> {

	private static final Logger LOG = LoggerFactory.getLogger(VnfInstantiateUow.class);

	private final VnfmInterface vnfm;

	private final NsVnfTask task;

	public VnfInstantiateUow(final VirtualTask<NsVnfTask> task, final VnfmInterface vnfm) {
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
		final String inst = context.get(VnfCreateNode.class, task.getToscaName());
		final Set<ExtVirtualLinkDataEntity> net = task.getExternalNetworks().stream().map(x -> {
			final String resource = context.get(Network.class, x);
			if (null == resource) {
				return null;
			}
			final ExtVirtualLinkDataEntity ext = new ExtVirtualLinkDataEntity();
			ext.setResourceId(resource);
			ext.setVimLevelResourceType(x);
			ext.setResourceProviderId("PROVIDER");
			ext.setVimConnectionId("VIM");
			return ext;
		})
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());
		final VnfInstantiate request = createRequest();
		request.setExtVirtualLinks(net);
		final VnfBlueprint res = vnfm.vnfInstatiate(task.getServer(), inst, request);
		final VnfBlueprint result = waitLcmCompletion(res, vnfm);
		if (OperationStatusType.COMPLETED != result.getOperationStatus()) {
			final String details = Optional.ofNullable(result.getError()).map(FailureDetails::getDetail).orElse("[No content]");
			throw new GenericException("VNF LCM Failed: " + details + " With state: " + result.getOperationStatus());
		}
		return res.getInstance().getId().toString();
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
		final VnfInstance inst = vnfm.getVnfInstance(task.getServer(), task.getVimResourceId());
		if (inst.getInstantiationState() == InstantiationState.NOT_INSTANTIATED) {
			return null;
		}
		final VnfBlueprint lcm = vnfm.vnfTerminate(task.getServer(), task.getVimResourceId());
		final VnfBlueprint result = waitLcmCompletion(lcm, vnfm);
		if (OperationStatusType.COMPLETED != result.getOperationStatus()) {
			throw new GenericException("VNF LCM Failed: " + result.getError().getDetail());
		}
		return result.getId().toString();
	}

}
