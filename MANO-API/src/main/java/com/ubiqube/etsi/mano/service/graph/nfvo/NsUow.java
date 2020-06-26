package com.ubiqube.etsi.mano.service.graph.nfvo;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.controller.nslcm.NsInstanceControllerService;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedNs;
import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.InstantiateNsRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.TerminateNsRequest;
import com.ubiqube.etsi.mano.service.NsLcmOpOccsService;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class NsUow extends AbstractNsUnitOfWork {

	private static final Logger LOG = LoggerFactory.getLogger(NsUow.class);

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final NsInstantiatedNs resourceHandle;

	private final InstantiateNsRequest instantiateRequest;

	private final TerminateNsRequest terminateRequest;

	private final NsInstanceControllerService nsInstanceControllerService;

	private final NsLcmOpOccsService nsLcmOpOccsService;

	public NsUow(final NsInstantiatedNs _resourceHandleEntity, final InstantiateNsRequest req, final TerminateNsRequest terminateReq, final NsInstanceControllerService _nsInstanceControllerService, final NsLcmOpOccsService _nsLcmOpOccsService, final String _name) {
		super(_resourceHandleEntity, _name);
		resourceHandle = _resourceHandleEntity;
		instantiateRequest = req;
		terminateRequest = terminateReq;
		nsInstanceControllerService = _nsInstanceControllerService;
		nsLcmOpOccsService = _nsLcmOpOccsService;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final VnfmInterface vnfm, final Vim vim, final Map<String, String> context) {
		final NsLcmOpOccs lcm = nsInstanceControllerService.instantiate(UUID.fromString(resourceHandle.getNsInstanceId()), instantiateRequest);
		final NsLcmOpOccs result = waitLcmCompletion(lcm);
		if (InstantiationStatusType.COMPLETED != result.getOperationState()) {
			throw new GenericException("NSD LCM Failed: " + result.getError().getDetail());
		}
		return lcm.getId().toString();
	}

	@Override
	public NsUowType getType() {
		return NsUowType.NSD;
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final VnfmInterface vnfm, final Vim vim, final String resourceId, final Map<String, String> context) {
		final NsLcmOpOccs lcm = nsInstanceControllerService.terminate(UUID.fromString(resourceHandle.getNsInstanceId()), null);
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

}
