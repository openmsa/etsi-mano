package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedVnf;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class VnfUow extends AbstractNsUnitOfWork {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final NsInstantiatedVnf resourceHandleEntity;

	public VnfUow(final NsInstantiatedVnf _resourceHandleEntity, final String _name) {
		super(_resourceHandleEntity, _name);
		resourceHandleEntity = _resourceHandleEntity;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final VnfmInterface vnfm, final Vim vim, final Map<String, String> context) {
		final VnfLcmOpOccs res = vnfm.vnfInstatiate(resourceHandleEntity.getVnfInstance().getId(), null);
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
