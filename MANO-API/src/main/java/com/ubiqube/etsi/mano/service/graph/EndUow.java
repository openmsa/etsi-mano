package com.ubiqube.etsi.mano.service.graph;

import java.io.Serializable;
import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class EndUow implements UnitOfWork, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "manoInternalEnd";
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UowType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
