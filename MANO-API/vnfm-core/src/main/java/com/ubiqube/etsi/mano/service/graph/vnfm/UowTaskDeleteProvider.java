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
package com.ubiqube.etsi.mano.service.graph.vnfm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.graph.UowExecDeleteTask;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <U>
 * @param <P>
 */
public class UowTaskDeleteProvider<U extends com.ubiqube.etsi.mano.dao.mano.v2.Task, P extends GenericExecParams> implements TaskProvider<UnitOfWork<U, P>, String> {

	private static final Logger LOG = LoggerFactory.getLogger(UowTaskDeleteProvider.class);

	private final P params;

	public UowTaskDeleteProvider(final P params) {
		super();
		this.params = params;
	}

	@Override
	public Task<UnitOfWork<U, P>, String> provideTask(final UnitOfWork<U, P> uaow) {
		LOG.debug("Called with: {}", uaow);
		return new UowExecDeleteTask(uaow, params);
	}

}
