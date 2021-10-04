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

import java.util.function.Function;

import com.github.dexecutor.core.task.Task;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWork;

public class DexecutorTask<P> extends Task<UnitOfWork, String> {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final UnitOfWork<?> uaow;

	private final transient Function<Context, String> function;

	private transient OrchExecutionListener listener;

	private Context context;

	public DexecutorTask(final OrchExecutionListener listener, final UnitOfWork<?> uaow, final Context context, final boolean create) {
		super();
		this.uaow = uaow;
		this.listener = listener;
		this.context = context;
		if (create) {
			function = x -> {
				final String res = uaow.execute(x);
				if (null != res) {
					x.add(uaow, res);
				}
				return res;
			};
		} else {
			function = uaow::rollback;
		}
	}

	@Override
	public String execute() {
		listener.onStart(uaow.getTask());
		final String res = function.apply(context);
		listener.onTerminate(uaow, res);
		return res;
	}

}
