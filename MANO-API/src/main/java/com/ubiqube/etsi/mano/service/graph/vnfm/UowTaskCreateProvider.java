package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class UowTaskCreateProvider implements TaskProvider<UnitOfWork, String> {

	private static final Logger LOG = LoggerFactory.getLogger(UowTaskCreateProvider.class);
	private final VimConnectionInformation vimConnectionInformation;
	private final Vim vim;
	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;
	private final Map<String, String> context;

	public UowTaskCreateProvider(final VimConnectionInformation vimConnectionInformation, final Vim vim, final VnfLiveInstanceJpa _vnfLiveInstanceJpa, final Map<String, String> extVl) {
		super();
		this.vimConnectionInformation = vimConnectionInformation;
		this.vim = vim;
		vnfLiveInstanceJpa = _vnfLiveInstanceJpa;
		context = new ConcurrentHashMap<>(extVl);
	}

	@Override
	public Task<UnitOfWork, String> provideTask(final UnitOfWork uaow) {
		LOG.debug("Called with: {}", uaow);
		return new UowExecCreateTask(vimConnectionInformation, vim, uaow, vnfLiveInstanceJpa, context);
	}
}
