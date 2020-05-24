package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class StorageUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfStorage vnfStorage;

	VnfInstantiatedStorage vnfInstantiedStorage;

	public StorageUow(final VnfInstantiatedStorage _vnfInstantiedStorage, final VnfStorage x) {
		super(_vnfInstantiedStorage, x.getToscaName());
		vnfStorage = x;
		vnfInstantiedStorage = _vnfInstantiedStorage;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		return vim.createStorage(vimConnectionInformation, vnfStorage, vnfInstantiedStorage.getAliasName());
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
	public String rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final String resourceId, final Map<String, String> context) {
		vim.deleteStorage(vimConnectionInformation, resourceId);
		return null;
	}

}
