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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.controller.vnflcm.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsdTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.NsdNode;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class NsUow extends AbstractNsUnitOfWork<NsdTask> {

	private static final Logger LOG = LoggerFactory.getLogger(NsUow.class);

	private final NsdTask nsdTask;

	private final VnfInstanceLcm nsLcmOpOccsService;

	public NsUow(final VirtualTask<NsdTask> task, final VnfInstanceLcm nsLcmOpOccsService) {
		super(task, NsdNode.class);
		this.nsdTask = task.getParameters();
		this.nsLcmOpOccsService = nsLcmOpOccsService;
	}

	private VnfBlueprint waitLcmCompletion(final VnfBlueprint lcm) {
		VnfBlueprint tmp = lcm;
		OperationStatusType state = tmp.getOperationStatus();
		while (state == OperationStatusType.PROCESSING || OperationStatusType.STARTING == state) {
			tmp = nsLcmOpOccsService.vnfLcmOpOccsGet(nsdTask.getServer(), lcm.getId());
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
		final VnfInstantiate instantiateRequest = createInstantiateRequest();
		final VnfBlueprint lcm = nsLcmOpOccsService.instantiate(nsdTask.getServer(), nsdTask.getNsInstanceId(), instantiateRequest);
		final VnfBlueprint result = waitLcmCompletion(lcm);
		if (OperationStatusType.COMPLETED != result.getOperationStatus()) {
			throw new GenericException("NSD LCM Failed: " + result.getError().getDetail());
		}
		return lcm.getId().toString();
	}

	private VnfInstantiate createInstantiateRequest() {
		final VnfInstantiate inst = new VnfInstantiate();
		// inst.setExtManagedVirtualLinks(nsdTask.getExtCps());
		// inst.setExtVirtualLinks(nsdTask.getExtCps());
		inst.setFlavourId(nsdTask.getFlavourId());
		inst.setInstantiationLevelId(nsdTask.getInstantiationLevelId());
		inst.setLocalizationLanguage(nsdTask.getLocalizationLanguage());
		inst.setVimConnectionInfo(nsdTask.getVimConnectionInformations().stream().toList());
		return inst;
	}

	@Override
	public String rollback(final Context context) {
		final VnfBlueprint lcm = nsLcmOpOccsService.terminate(nsdTask.getServer(), nsdTask.getNsInstanceId(), null, 0);
		final VnfBlueprint result = waitLcmCompletion(lcm);
		if (OperationStatusType.COMPLETED != result.getOperationStatus()) {
			throw new GenericException("NSD LCM Failed: " + result.getError().getDetail());
		}
		return lcm.getId().toString();
	}

}
