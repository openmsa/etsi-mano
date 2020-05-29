package com.ubiqube.etsi.mano.service.graph.nfvo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.repository.jpa.NsInstantiatedBaseJpa;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class UowNsTaskDeleteProvider implements TaskProvider<NsUnitOfWork, String> {

	private static final Logger LOG = LoggerFactory.getLogger(UowNsTaskDeleteProvider.class);

	private final VimConnectionInformation vimConnectionInformation;

	private final Vim vim;

	private final NsInstantiatedBaseJpa vnfInstantiedBaseJpa;

	private final Map<String, String> context = new ConcurrentHashMap<>();

	private final VnfmInterface vnfn;

	public UowNsTaskDeleteProvider(final VimConnectionInformation vimConnectionInformation, final Vim vim, final NsInstantiatedBaseJpa _vnfInstantiedBaseJpa, final VnfmInterface _vnfn) {
		super();
		this.vimConnectionInformation = vimConnectionInformation;
		this.vim = vim;
		vnfn = _vnfn;
		vnfInstantiedBaseJpa = _vnfInstantiedBaseJpa;
	}

	@Override
	public Task<NsUnitOfWork, String> provideTask(final NsUnitOfWork uaow) {
		LOG.debug("Called with: {}", uaow);
		return new NsUowExecDeleteTask(vimConnectionInformation, vim, uaow, vnfInstantiedBaseJpa, context, vnfn);
	}

}
