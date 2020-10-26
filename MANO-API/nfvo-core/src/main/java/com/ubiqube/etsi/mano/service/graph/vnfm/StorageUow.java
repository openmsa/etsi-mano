package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.Map;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.v2.StorageTask;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class StorageUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final StorageTask storageTask;

	private final VnfStorage vnfStorage;

	public StorageUow(final StorageTask _storageTask, final VnfStorage _vnfStorage) {
		super(_storageTask);
		vnfStorage = _vnfStorage;
		storageTask = _storageTask;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		return vim.createStorage(vimConnectionInformation, vnfStorage, storageTask.getAlias());
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

	@Override
	public void connect(final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g, final Map<String, UnitOfWork> cache) {
		g.addEdge(cache.get(storageTask.getParentAlias()), this);
	}

}
