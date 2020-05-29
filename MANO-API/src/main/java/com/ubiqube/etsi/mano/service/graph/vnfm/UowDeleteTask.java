package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedBaseJpa;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class UowDeleteTask extends AbstractTaskUow {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	public UowDeleteTask(final VimConnectionInformation vimConnectionInformation, final Vim vim, final UnitOfWork uaow, final VnfInstantiedBaseJpa _resourceHandleEntityJpa, final Map<String, String> _context) {
		super(vimConnectionInformation, vim, uaow, _resourceHandleEntityJpa, _context, false);
	}

}
