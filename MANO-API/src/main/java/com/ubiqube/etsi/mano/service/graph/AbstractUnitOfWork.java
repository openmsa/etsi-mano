package com.ubiqube.etsi.mano.service.graph;

import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public abstract class AbstractUnitOfWork implements UnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final ResourceHandleEntity resourceHandleEntity;

	// Nothing.
	public AbstractUnitOfWork(final ResourceHandleEntity _resourceHandleEntity) {
		resourceHandleEntity = _resourceHandleEntity;
	}

	@Override
	public ResourceHandleEntity getResourceHandleEntity() {
		return resourceHandleEntity;
	}

}
