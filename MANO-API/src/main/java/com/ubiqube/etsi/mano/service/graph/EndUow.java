package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class EndUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	public EndUow(final ResourceHandleEntity resourceHandleEntity) {
		super(resourceHandleEntity, "");
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

	@Override
	protected String getPrefix() {
		return "mano_end";
	}

	@Override
	public void rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		// TODO Auto-generated method stub

	}

}
