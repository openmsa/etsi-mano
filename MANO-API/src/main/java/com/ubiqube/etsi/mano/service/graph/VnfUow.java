package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedVnf;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.model.nslcm.sol003.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class VnfUow extends AbstractNsUnitOfWork {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final NsInstantiatedVnf resourceHandleEntity;

	private final InstantiateVnfRequest request;

	public VnfUow(final NsInstantiatedVnf _resourceHandleEntity, final InstantiateVnfRequest _request, final String _name) {
		super(_resourceHandleEntity, _name);
		resourceHandleEntity = _resourceHandleEntity;
		request = _request;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final VnfmInterface vnfm, final Vim vim, final Map<String, String> context) {
		final VnfLcmOpOccs res = vnfm.vnfInstatiate(resourceHandleEntity.getVnfInstance().getId(), request, null);
		// XXX poll the lcm or register for notifications.
		return resourceHandleEntity.getVnfInstance().getId().toString();
	}

	@Override
	public NsUowType getType() {
		return NsUowType.VNF;
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final VnfmInterface vnfm, final Vim vim, final String resourceId, final Map<String, String> context) {
		vnfm.vnfTerminate(resourceHandleEntity.getVnfInstance().getId(), null);
		return null;
	}

	@Override
	protected String getPrefix() {
		return "vnf";
	}

}
