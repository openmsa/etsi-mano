package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class ObjectStorageUow extends AbstractUnitOfWork {

	public ObjectStorageUow(final ResourceHandleEntity _resourceHandleEntity, final String _name) {
		super(_resourceHandleEntity, _name);
	}

	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UowType getType() {
		return UowType.VSTORAGE;
	}

	@Override
	protected String getPrefix() {
		return "object_storage";
	}

	@Override
	public void rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		// TODO Auto-generated method stub

	}

}
