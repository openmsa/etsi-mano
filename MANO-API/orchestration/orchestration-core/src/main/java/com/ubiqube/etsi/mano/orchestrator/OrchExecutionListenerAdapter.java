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
package com.ubiqube.etsi.mano.orchestrator;

import com.github.dexecutor.core.ExecutionListener;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <U>
 */
public class OrchExecutionListenerAdapter<U> implements ExecutionListener<Task<U>, String> {
	private final OrchExecutionListener<U> listener;

	public OrchExecutionListenerAdapter(final OrchExecutionListener<U> listener) {
		super();
		this.listener = listener;
	}

	@Override
	public void onSuccess(final com.github.dexecutor.core.task.Task<Task<U>, String> task) {
		listener.onSuccess(task.getId());
	}

	@Override
	public void onError(final com.github.dexecutor.core.task.Task<Task<U>, String> task, final Exception exception) {
		listener.onError(task.getId(), exception);
	}

}
