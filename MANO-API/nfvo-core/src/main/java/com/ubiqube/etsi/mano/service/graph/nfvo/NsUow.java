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

import java.util.Map;

import org.jgrapht.ListenableGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.controller.nslcm.NsInstanceControllerService;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsdTask;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.NsInstantiate;
import com.ubiqube.etsi.mano.service.NsLcmOpOccsService;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class NsUow extends AbstractNsUnitOfWork {

	private static final Logger LOG = LoggerFactory.getLogger(NsUow.class);

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final NsdTask nsdTask;

	private final NsInstantiate instantiateRequest;

	private final transient NsInstanceControllerService nsInstanceControllerService;

	private final transient NsLcmOpOccsService nsLcmOpOccsService;

	public NsUow(final NsdTask _task, final NsInstantiate req, final NsInstanceControllerService _nsInstanceControllerService, final NsLcmOpOccsService _nsLcmOpOccsService) {
		super(_task);
		nsdTask = _task;
		instantiateRequest = req;
		nsInstanceControllerService = _nsInstanceControllerService;
		nsLcmOpOccsService = _nsLcmOpOccsService;
	}

	@Override
	public String exec(final NsParameters params) {
		final NsLcmOpOccs lcm = nsInstanceControllerService.instantiate(nsdTask.getNsInstanceId(), instantiateRequest);
		final NsLcmOpOccs result = waitLcmCompletion(lcm);
		if (InstantiationStatusType.COMPLETED != result.getOperationState()) {
			throw new GenericException("NSD LCM Failed: " + result.getError().getDetail());
		}
		return lcm.getId().toString();
	}

	@Override
	public String rollback(final NsParameters params) {
		final NsLcmOpOccs lcm = nsInstanceControllerService.terminate(nsdTask.getNsInstanceId(), null);
		final NsLcmOpOccs result = waitLcmCompletion(lcm);
		if (InstantiationStatusType.COMPLETED != result.getOperationState()) {
			throw new GenericException("NSD LCM Failed: " + result.getError().getDetail());
		}
		return lcm.getId().toString();
	}

	@Override
	protected String getPrefix() {
		return "nsd";
	}

	private NsLcmOpOccs waitLcmCompletion(final NsLcmOpOccs lcm) {
		NsLcmOpOccs tmp = lcm;
		InstantiationStatusType state = tmp.getOperationState();
		while ((state == InstantiationStatusType.PROCESSING) || (InstantiationStatusType.STARTING == state)) {
			tmp = nsLcmOpOccsService.findById(lcm.getId());
			state = tmp.getOperationState();
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
	public void connect(final ListenableGraph<UnitOfWork<NsTask, NsParameters>, ConnectivityEdge<UnitOfWork<NsTask, NsParameters>>> g, final Map<String, UnitOfWork<NsTask, NsParameters>> cache) {
		// TODO Auto-generated method stub

	}

}
