package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class VirtualLinkUow implements UnitOfWork {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VlProtocolData vlProtocolData;
	private final String name;

	public VirtualLinkUow(final VlProtocolData _vlProtocolData, final String _name) {
		vlProtocolData = _vlProtocolData;
		name = _name;
	}

	@Override
	public String getName() {
		return name;
	}

	public VlProtocolData getVlProtocolData() {
		return vlProtocolData;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		return vim.createNetwork(vimConnectionInformation, vlProtocolData, name);
	}

	@Override
	public UowType getType() {
		return UowType.VL;
	}

}
