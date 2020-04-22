package com.ubiqube.etsi.mano.service.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class UowTaskProvider implements TaskProvider<UnitOfWork, String> {

	private static final Logger LOG = LoggerFactory.getLogger(UowTaskProvider.class);
	private final VimConnectionInformation vimConnectionInformation;
	private final Vim vim;

	public UowTaskProvider(final VimConnectionInformation vimConnectionInformation, final Vim vim) {
		super();
		this.vimConnectionInformation = vimConnectionInformation;
		this.vim = vim;
	}

	@Override
	public Task<UnitOfWork, String> provideTask(final UnitOfWork uaow) {
		LOG.debug("Called with: {}", uaow);
		return new UowTask(vimConnectionInformation, vim, uaow);
	}
}
