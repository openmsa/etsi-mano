package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.Map;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.v2.ObjectStorageTask;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class ObjectStorageUow extends AbstractUnitOfWork {

	private final VnfStorage vnfStorage;

	public ObjectStorageUow(final ObjectStorageTask objectStorageTask, final VnfStorage _vnfStorage) {
		super(objectStorageTask);
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

	@Override
	public void connect(final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g, final Map<String, UnitOfWork> cache) {
		// Nothing to do.
	}

}
