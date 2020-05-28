package com.ubiqube.etsi.mano.service.graph;

import com.github.dexecutor.core.task.Task;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.VnfInstantiedBaseJpa;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class UowNsTaskDeleteProvider implements TaskProvider<NsUnitOfWork, String> {

	public UowNsTaskDeleteProvider(final VimConnectionInformation vimConnectionInformation, final Vim vim, final VnfInstantiedBaseJpa vnfInstantiedBaseJpa) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Task<NsUnitOfWork, String> provideTask(final NsUnitOfWork id) {
		// TODO Auto-generated method stub
		return null;
	}

}
