package com.ubiqube.etsi.mano.service.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class ObjectStorageUow extends AbstractUnitOfWork {

	private final VnfStorage vnfStorage;

	public ObjectStorageUow(final ResourceHandleEntity _resourceHandleEntity, final VnfStorage _vnfStorage, final String _name) {
		super(_resourceHandleEntity, _name);
		vnfStorage = _vnfStorage;
	}

	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		return vim.createObjectStorage(vimConnectionInformation, vnfStorage);
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
	public String rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final String resourceId, final Map<String, String> context) {
		vim.deleteObjectStorage(vimConnectionInformation, resourceId);
		return null;
	}

}
