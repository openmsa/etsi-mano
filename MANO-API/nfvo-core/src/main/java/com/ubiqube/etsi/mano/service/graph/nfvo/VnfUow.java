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
package com.ubiqube.etsi.mano.service.graph.nfvo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVnfTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.NsVlNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.VnfNode;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VnfUow extends AbstractNsUnitOfWork {

	private static final Logger LOG = LoggerFactory.getLogger(VnfUow.class);

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final transient VnfInstantiate request;
	private final VnfmInterface vnfm;

	private final NsVnfTask task;

	public VnfUow(final NsVnfTask _task, final VnfInstantiate _request, final VnfmInterface _vnfm) {
		super(_task);
		task = _task;
		request = _request;
		vnfm = _vnfm;
	}

	@Override
	public String exec(final NsParameters params) {
		final Set<ExtVirtualLinkDataEntity> net = task.getExternalNetworks().stream().map(x -> {
			final String resource = params.getContext().get(x);
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
		final VnfBlueprint res = vnfm.vnfInstatiate(task.getVnfInstance(), request, null);
		final VnfBlueprint result = waitLcmCompletion(res, vnfm);
		if (OperationStatusType.COMPLETED != result.getOperationStatus()) {
			throw new GenericException("VNF LCM Failed: " + result.getError().getDetail());
		}
		return res.getId().toString();
	}

	@Override
	public String rollback(final NsParameters params) {
		final VnfBlueprint lcm = vnfm.vnfTerminate(task.getVnfInstance());
		final VnfBlueprint result = waitLcmCompletion(lcm, vnfm);
		if (OperationStatusType.COMPLETED != result.getOperationStatus()) {
			throw new GenericException("VNF LCM Failed: " + result.getError().getDetail());
		}
		return result.getId().toString();
	}

	@Override
	protected String getPrefix() {
		return "vnf";
	}

	/**
	 * XXX We should add a Max wait.
	 *
	 * @param vnfLcmOpOccs
	 * @param vnfm
	 * @return
	 */
	private static VnfBlueprint waitLcmCompletion(final VnfBlueprint vnfLcmOpOccs, final VnfmInterface vnfm) {
		VnfBlueprint tmp = vnfLcmOpOccs;
		OperationStatusType state = tmp.getOperationStatus();
		while ((state == OperationStatusType.PROCESSING) || (OperationStatusType.STARTING == state) || (OperationStatusType.NOT_STARTED == state)) {
			tmp = vnfm.vnfLcmOpOccsGet(vnfLcmOpOccs.getId());
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
	public List<WfDependency> getDependencies() {
		return task.getExternalNetworks().stream().map(x -> new WfDependency(NsVlNode.class, x)).collect(Collectors.toList());
	}

	@Override
	public List<WfProduce> getProduce() {
		return Arrays.asList(new WfProduce(VnfNode.class, task.getToscaName(), task.getId()));
	}

}
