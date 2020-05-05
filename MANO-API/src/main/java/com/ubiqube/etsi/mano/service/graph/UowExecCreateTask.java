package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.ResourceHandleEntityJpa;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class UowExecCreateTask extends AbstractTaskUow {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	public UowExecCreateTask(final VimConnectionInformation vimConnectionInformation, final Vim vim, final UnitOfWork uaow, final ResourceHandleEntityJpa _resourceHandleEntityJpa, final Map<String, String> _context) {
		super(vimConnectionInformation, vim, uaow, _resourceHandleEntityJpa, _context, true);
	}

}
