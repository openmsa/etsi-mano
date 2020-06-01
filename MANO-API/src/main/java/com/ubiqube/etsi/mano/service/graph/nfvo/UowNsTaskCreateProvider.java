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

public class UowNsTaskCreateProvider implements TaskProvider<NsUnitOfWork, String> {

	private static final Logger LOG = LoggerFactory.getLogger(UowNsTaskCreateProvider.class);

	private final VimConnectionInformation vimConnectionInformation;

	private final Vim vim;

	private final NsInstantiatedBaseJpa vnfInstantiedBaseJpa;

	private final Map<String, String> context;

	private final VnfmInterface vnfm;

	public UowNsTaskCreateProvider(final VimConnectionInformation vimConnectionInformation, final Vim vim, final NsInstantiatedBaseJpa _vnfInstantiedBaseJpa, final VnfmInterface _vnfm, final Map<String, String> baseContext) {
		super();
		this.vimConnectionInformation = vimConnectionInformation;
		this.vim = vim;
		vnfInstantiedBaseJpa = _vnfInstantiedBaseJpa;
		vnfm = _vnfm;
		context = new ConcurrentHashMap<>(baseContext);
	}

	@Override
	public Task<NsUnitOfWork, String> provideTask(final NsUnitOfWork uaow) {
		LOG.debug("Called with: {}", uaow);
		return new NsUowExecCreateTask(vimConnectionInformation, vim, uaow, vnfInstantiedBaseJpa, context, vnfm);
	}

}
