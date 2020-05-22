package com.ubiqube.etsi.mano.service.graph;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedBaseJpa;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class UowTaskDeleteProvider implements TaskProvider<UnitOfWork, String> {

	private static final Logger LOG = LoggerFactory.getLogger(UowTaskDeleteProvider.class);

	private final VimConnectionInformation vimConnectionInformation;

	private final Vim vim;

	private final VnfInstantiedBaseJpa vnfInstantiedBaseJpa;

	private final Map<String, String> context = new HashMap<>();

	public UowTaskDeleteProvider(final VimConnectionInformation vimConnectionInformation, final Vim vim, final VnfInstantiedBaseJpa _vnfInstantiedBaseJpa) {
		super();
		this.vimConnectionInformation = vimConnectionInformation;
		this.vim = vim;
		vnfInstantiedBaseJpa = _vnfInstantiedBaseJpa;
	}

	@Override
	public Task<UnitOfWork, String> provideTask(final UnitOfWork uaow) {
		LOG.debug("Called with: {}", uaow);
		return new UowExecDeleteTask(vimConnectionInformation, vim, uaow, vnfInstantiedBaseJpa, context);
	}

}
