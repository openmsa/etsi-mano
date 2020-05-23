package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiedExtCp;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class VnfExtCpUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfExtCp extCp;
	private final VnfInstantiedExtCp vnfInstantiedExtCp;

	public VnfExtCpUow(final VnfInstantiedExtCp x, final VnfExtCp _extCp) {
		super(x, _extCp.getToscaName());
		extCp = _extCp;
		vnfInstantiedExtCp = x;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		final String networkId = context.get(extCp.getInternalVirtualLink());
		return vim.createRouter(vimConnectionInformation, vnfInstantiedExtCp.getAliasName(), networkId, vnfInstantiedExtCp.getResourceId());
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
