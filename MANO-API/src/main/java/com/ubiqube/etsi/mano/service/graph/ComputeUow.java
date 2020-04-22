package com.ubiqube.etsi.mano.service.graph;

import java.io.Serializable;
import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class ComputeUow implements UnitOfWork, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfCompute vnfCompute;

	public ComputeUow(final VnfCompute x) {
		vnfCompute = x;
	}

	@Override
	public String getName() {
		return vnfCompute.getToscaName();
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UowType getType() {
		return UowType.COMPUTE;
	}

}
