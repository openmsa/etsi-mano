package com.ubiqube.etsi.mano.service.graph.nfvo;

import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedBase;

public abstract class AbstractNsUnitOfWork implements NsUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final NsInstantiatedBase resourceHandleEntity;

	private final String name;

	// Nothing.
	public AbstractNsUnitOfWork(final NsInstantiatedBase _resourceHandleEntity, final String _name) {
		resourceHandleEntity = _resourceHandleEntity;
		name = _name;
	}

	@Override
	public final NsInstantiatedBase getResourceHandleEntity() {
		return resourceHandleEntity;
	}

	@Override
	public final String getName() {
		return getPrefix() + "_" + name;
	}

	protected abstract String getPrefix();

	@Override
	public final String getToscaName() {
		return name;
	}

}
