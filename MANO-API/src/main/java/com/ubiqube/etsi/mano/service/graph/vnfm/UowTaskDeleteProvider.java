package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class UowTaskDeleteProvider implements TaskProvider<UnitOfWork, String> {

	private static final Logger LOG = LoggerFactory.getLogger(UowTaskDeleteProvider.class);

	private final VimConnectionInformation vimConnectionInformation;

	private final Vim vim;

	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	private final Map<String, String> context = new HashMap<>();

	public UowTaskDeleteProvider(final VimConnectionInformation vimConnectionInformation, final Vim vim, final VnfLiveInstanceJpa _vnfLiveInstanceJpa) {
		super();
		this.vimConnectionInformation = vimConnectionInformation;
		this.vim = vim;
		vnfLiveInstanceJpa = _vnfLiveInstanceJpa;
	}

	@Override
	public Task<UnitOfWork, String> provideTask(final UnitOfWork uaow) {
		LOG.debug("Called with: {}", uaow);
		return new UowExecDeleteTask(vimConnectionInformation, vim, uaow, vnfLiveInstanceJpa, context);
	}

}
