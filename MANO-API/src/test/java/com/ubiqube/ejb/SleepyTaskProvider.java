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
package com.ubiqube.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.ExecutionResults;
import com.github.dexecutor.core.task.Task;
import com.github.dexecutor.core.task.TaskProvider;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;

public class SleepyTaskProvider implements TaskProvider<UnitOfWork, Integer> {

	private static final Logger LOG = LoggerFactory.getLogger(SleepyTaskProvider.class);

	@Override
	public Task<UnitOfWork, Integer> provideTask(final UnitOfWork id) {
		LOG.debug("Called with: {}" + id);
		return new Task<UnitOfWork, Integer>() {

			/**
			 *
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Integer execute() {
				final ExecutionResults<UnitOfWork, Integer> res = getParentResults();
				LOG.debug("Execute {}", id);
				try {
					// id.exec(null, null);
				} catch (final Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		};
	}

}
