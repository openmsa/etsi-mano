package com.ubiqube.etsi.mano.service.graph.nfvo;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedNs;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsUnitOfWork.NsUowType;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class NsUow extends AbstractNsUnitOfWork {

	/** Serial. */
	private static final long serialVersionUID = 1L;
	private final NsInstantiatedNs resourceHandle;

	public NsUow(final NsInstantiatedNs _resourceHandleEntity, final String _name) {
		super(_resourceHandleEntity, _name);
		resourceHandle = _resourceHandleEntity;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final VnfmInterface vnfm, final Vim vim, final Map<String, String> context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NsUowType getType() {
		return NsUowType.NSD;
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final VnfmInterface vnfm, final Vim vim, final String resourceId, final Map<String, String> context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPrefix() {
		return "nsd";
	}
}
