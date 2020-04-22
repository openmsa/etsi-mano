package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class MonitoringUow implements UnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfCompute vnfCompute;
	private final String name;

	public MonitoringUow(final VnfCompute x, final String _name) {
		vnfCompute = x;
		name = _name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UowType getType() {
		return UowType.MONITORINGPARAM;
	}

}
