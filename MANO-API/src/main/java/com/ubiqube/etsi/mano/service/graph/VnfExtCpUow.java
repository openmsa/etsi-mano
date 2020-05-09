package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class VnfExtCpUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfExtCp extCp;

	public VnfExtCpUow(final ResourceHandleEntity _resourceHandleEntity, final VnfExtCp _extCp) {
		super(_resourceHandleEntity, _extCp.getToscaName());
		extCp = _extCp;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		final String networkId = context.get(extCp.getInternalVirtualLink());
		return vim.createRouter(vimConnectionInformation, extCp.getToscaName(), networkId, extCp.getVimResource().getResourceId());
	}

	@Override
	public UowType getType() {
		return UowType.EXTCP;
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final String resourceId, final Map<String, String> context) {
		vim.deleteRouter(vimConnectionInformation, resourceId);
		return null;
	}

	@Override
	protected String getPrefix() {
		return "ext_cp";
	}
}
