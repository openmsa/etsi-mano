package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class NsStartUow extends AbstractNsUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	public NsStartUow(final NsInstantiatedBase _resourceHandleEntity) {
		super(_resourceHandleEntity, "");
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final VnfmInterface vnfm, final Vim vim, final Map<String, String> context) {
		return null;
	}

	@Override
	public NsUowType getType() {
		return null;
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final VnfmInterface vnfm, final Vim vim, final String resourceId, final Map<String, String> context) {
		return null;
	}

	@Override
	protected String getPrefix() {
		return "ns_mano_start";
	}

}
