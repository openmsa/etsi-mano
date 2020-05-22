package com.ubiqube.etsi.mano.service.graph;

import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedBase;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public abstract class AbstractUnitOfWork implements UnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfInstantiatedBase resourceHandleEntity;

	private final String name;

	// Nothing.
	public AbstractUnitOfWork(final VnfInstantiatedBase _resourceHandleEntity, final String _name) {
		resourceHandleEntity = _resourceHandleEntity;
		name = _name;
	}

	@Override
	public final VnfInstantiatedBase getResourceHandleEntity() {
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
