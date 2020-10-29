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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedVnf;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.OperationStatusType;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class VnfUow extends AbstractNsUnitOfWork {

	private static final Logger LOG = LoggerFactory.getLogger(VnfUow.class);

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final NsInstantiatedVnf resourceHandleEntity;

	private final transient VnfInstantiate request;

	public VnfUow(final NsInstantiatedVnf _resourceHandleEntity, final VnfInstantiate _request, final String _name) {
		super(_resourceHandleEntity, _name);
		resourceHandleEntity = _resourceHandleEntity;
		request = _request;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final VnfmInterface vnfm, final Vim vim, final Map<String, String> context) {
		final Blueprint res = vnfm.vnfInstatiate(resourceHandleEntity.getVnfInstance(), request, null);
		final Blueprint result = waitLcmCompletion(res, vnfm);
		if (OperationStatusType.COMPLETED != result.getOperationStatus()) {
			throw new GenericException("VNF LCM Failed: " + result.getError().getDetail());
		}
		return res.getId().toString();
	}

	@Override
	public NsUowType getType() {
		return NsUowType.VNF;
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final VnfmInterface vnfm, final Vim vim, final String resourceId, final Map<String, String> context) {
		final Blueprint lcm = vnfm.vnfTerminate(resourceHandleEntity.getVnfInstance());
		final Blueprint result = waitLcmCompletion(lcm, vnfm);
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
	private static Blueprint waitLcmCompletion(final Blueprint vnfLcmOpOccs, final VnfmInterface vnfm) {
		Blueprint tmp = vnfLcmOpOccs;
		OperationStatusType state = tmp.getOperationStatus();
		while ((state == OperationStatusType.PROCESSING) || (OperationStatusType.STARTING == state)) {
			tmp = vnfm.getVnfLcmOpOccs(vnfLcmOpOccs.getId());
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
}
