package com.ubiqube.etsi.mano.service.graph;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.ResourceHandleEntityJpa;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class UowTaskCreateProvider implements TaskProvider<UnitOfWork, String> {

	private static final Logger LOG = LoggerFactory.getLogger(UowTaskCreateProvider.class);
	private final VimConnectionInformation vimConnectionInformation;
	private final Vim vim;
	private final ResourceHandleEntityJpa resourceHandleEntityJpa;
	private final Map<String, String> context = new HashMap<>();

	public UowTaskCreateProvider(final VimConnectionInformation vimConnectionInformation, final Vim vim, final ResourceHandleEntityJpa _resourceHandleEntityJpa) {
		super();
		this.vimConnectionInformation = vimConnectionInformation;
		this.vim = vim;
		resourceHandleEntityJpa = _resourceHandleEntityJpa;
	}

	@Override
	public Task<UnitOfWork, String> provideTask(final UnitOfWork uaow) {
		LOG.debug("Called with: {}", uaow);
		return new UowExecCreateTask(vimConnectionInformation, vim, uaow, resourceHandleEntityJpa, context);
	}
}
