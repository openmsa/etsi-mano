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
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVnfTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.nfvo.service.plan.contributors.vt.NsVnfVt;
import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.VnfNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.service.VnfmInterface;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VnfUow extends AbstractNsUnitOfWork<NsVnfTask> {

	private static final Logger LOG = LoggerFactory.getLogger(VnfUow.class);

	private final VnfInstantiate request;
	private final VnfmInterface vnfm;

	private final NsVnfTask task;

	public VnfUow(final NsVnfVt task, final VnfInstantiate request, final VnfmInterface vnfm) {
		super(task, VnfNode.class);
		this.task = task.getParameters();
		this.request = request;
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
		OperationStatusType state = tmp.getOperationStatus();
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
		request.setExtVirtualLinks(net);
		final VnfBlueprint res = vnfm.vnfInstatiate(task.getServer(), task.getVnfInstance(), request, null);
		final VnfBlueprint result = waitLcmCompletion(res, vnfm);
		if (OperationStatusType.COMPLETED != result.getOperationStatus()) {
			throw new GenericException("VNF LCM Failed: " + result.getError().getDetail());
		}
		return res.getInstance().getId().toString();
	}

	@Override
	public String rollback(final Context context) {
		final VnfInstance inst = vnfm.getVnfInstance(task.getServer(), task.getVnfInstance());
		if (inst.getInstantiationState() == InstantiationState.NOT_INSTANTIATED) {
			return null;
		}
		final VnfBlueprint lcm = vnfm.vnfTerminate(task.getServer(), task.getVnfInstance());
		final VnfBlueprint result = waitLcmCompletion(lcm, vnfm);
		if (OperationStatusType.COMPLETED != result.getOperationStatus()) {
			throw new GenericException("VNF LCM Failed: " + result.getError().getDetail());
		}
		// XXX OVI: We need some other mechanism, we should not delete the instance at
		// this point. But as long a vnf instance exist you can't delete the package.
		vnfm.delete(task.getServer(), task.getVnfInstance());
		return result.getId().toString();
	}

}
