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
package com.ubiqube.etsi.mano.nfvo.service.graph.nfvo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;

public class UowNsTaskDeleteProvider implements TaskProvider<UnitOfWork<NsTask, NsParameters>, String> {

	private static final Logger LOG = LoggerFactory.getLogger(UowNsTaskDeleteProvider.class);

	private final NsParameters params;

	public UowNsTaskDeleteProvider(final NsParameters params) {
		super();
		this.params = params;
	}

	@Override
	public Task<UnitOfWork<NsTask, NsParameters>, String> provideTask(final UnitOfWork<NsTask, NsParameters> uaow) {
		LOG.debug("Called with: {}", uaow);
		return new NsUowExecDeleteTask(uaow, params);
	}

}
