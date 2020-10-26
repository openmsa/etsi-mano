package com.ubiqube.etsi.mano.service.graph.vnfm;

import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public abstract class AbstractUnitOfWork implements UnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private VnfInstantiatedBase resourceHandleEntity;

	private final String name;

	private final Task task;

	public AbstractUnitOfWork(final Task _task) {
		task = _task;
		name = task.getToscaName();
	}

	@Override
	public final VnfInstantiatedBase getResourceHandleEntity() {
		return resourceHandleEntity;
	}

	public Task getTask() {
		return task;
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
