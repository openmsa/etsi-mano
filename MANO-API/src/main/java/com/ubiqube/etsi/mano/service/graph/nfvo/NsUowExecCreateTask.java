package com.ubiqube.etsi.mano.service.graph.nfvo;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.repository.jpa.NsInstantiatedBaseJpa;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class NsUowExecCreateTask extends AbstractNsTaskUow {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	public NsUowExecCreateTask(final VimConnectionInformation vimConnectionInformation, final Vim vim, final NsUnitOfWork uaow, final NsInstantiatedBaseJpa _resourceHandleEntityJpa, final Map<String, String> _context, final VnfmInterface vnfm) {
		super(vimConnectionInformation, vim, uaow, _resourceHandleEntityJpa, _context, true, vnfm);
	}

}
