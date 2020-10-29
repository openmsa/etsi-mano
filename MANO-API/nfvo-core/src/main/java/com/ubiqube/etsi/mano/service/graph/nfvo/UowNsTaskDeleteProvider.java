/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.service.graph.nfvo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.NsInstantiatedBaseJpa;
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
