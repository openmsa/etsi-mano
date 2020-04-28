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
		super(resourceHandleEntity);
		vnfStorage = x;
	}

	@Override
	public String getName() {
		return vnfStorage.getToscaName();
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UowType getType() {
		return UowType.VSTORAGE;
	}

}
