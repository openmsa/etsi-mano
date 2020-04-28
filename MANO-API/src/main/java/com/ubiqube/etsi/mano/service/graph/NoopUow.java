package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class NoopUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	public NoopUow() {
		super(null);
	}

	@Override
	public String getName() {
		return "Noop operation.";
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		//
		return null;
	}

	@Override
	public UowType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
