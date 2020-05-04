package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class StorageUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfStorage vnfStorage;

	public StorageUow(final ResourceHandleEntity resourceHandleEntity, final VnfStorage x) {
		super(resourceHandleEntity, x.getToscaName());
		vnfStorage = x;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		return vim.createStorage(vimConnectionInformation, vnfStorage);
	}

	@Override
	public UowType getType() {
		return UowType.VSTORAGE;
	}

	@Override
	protected String getPrefix() {
		return "block_storage";
	}

	@Override
	public void rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		// TODO Auto-generated method stub

	}

}
