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
package com.ubiqube.etsi.mano.service.graph;

import java.time.LocalDateTime;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;

public abstract class AbstractTaskUow<U extends com.ubiqube.etsi.mano.dao.mano.v2.Task, P extends GenericExecParams> extends Task<UnitOfWork<U, P>, String> {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AbstractTaskUow.class);

	private final UnitOfWork<U, P> uaow;

	private final transient Function<P, String> function;

	private final transient P params;

	public AbstractTaskUow(final UnitOfWork<U, P> uaow, final P params, final boolean _create) {
		super();
		this.uaow = uaow;
		this.params = params;
		if (_create) {
			function = x -> {
				final String res = uaow.exec(params);
				if (null != res) {
					params.getWorkflowContext().put(uaow.getToscaName(), res);
					params.getWorkflowContext().put(uaow.getTaskEntity().getAlias(), res);
					LOG.debug("Adding to context: {} => {}", uaow.getName(), res);
					uaow.getTaskEntity().setVimResourceId(res);
				}
				return res;
			};
		} else {
			function = x -> uaow.rollback(params);
		}
	}

	@Override
	public final String execute() {
		RuntimeException eRoot = null;
		final U resource = this.uaow.getTaskEntity();
		resource.setStartDate(LocalDateTime.now());
		resource.setStatus(PlanStatusType.STARTED);
		try {
			LOG.info("Task {} Started.", uaow.getName());
			function.apply(params);
			resource.setStatus(PlanStatusType.SUCCESS);
		} catch (final RuntimeException e) {
			LOG.warn("Task {} failed.", uaow.getName(), e);
			eRoot = e;
			resource.setStatus(PlanStatusType.FAILED);
		}
		LOG.info("Task {} Finished.", uaow.getName());
		resource.setEndDate(LocalDateTime.now());
		if (eRoot != null) {
			throw eRoot;
		}
		return null;
	}

}
