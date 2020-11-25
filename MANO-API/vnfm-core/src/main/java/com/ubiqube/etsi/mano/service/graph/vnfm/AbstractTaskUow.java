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

import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.service.vim.Vim;

public abstract class AbstractTaskUow extends Task<UnitOfWork, String> {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AbstractTaskUow.class);

	private final VimConnectionInformation vimConnectionInformation;

	private final transient Vim vim;

	private final UnitOfWork uaow;

	private final Map<String, String> context;

	private final transient Function<Parameters, String> function;

	public AbstractTaskUow(final VimConnectionInformation vimConnectionInformation, final Vim vim, final UnitOfWork uaow, final Map<String, String> _context, final boolean _create) {
		super();
		this.vimConnectionInformation = vimConnectionInformation;
		this.vim = vim;
		this.uaow = uaow;
		context = _context;
		if (_create) {
			function = x -> {
				final String res = uaow.exec(vimConnectionInformation, vim, context);
				if (null != res) {
					context.put(uaow.getToscaName(), res);
					context.put(uaow.getTaskEntity().getAlias(), res);
					LOG.debug("Adding to context: {} => {}", uaow.getName(), res);
					uaow.getTaskEntity().setVimResourceId(res);
				}
				return res;
			};
		} else {
			function = x -> uaow.rollback(x.vimConnectionInformationLocal, x.vimLocal, x.resourceId, x.contextLocal);
		}
	}

	@Override
	public final String execute() {
		RuntimeException eRoot = null;
		final com.ubiqube.etsi.mano.dao.mano.v2.Task resource = this.uaow.getTaskEntity();
		resource.setStartDate(LocalDateTime.now());
		resource.setStatus(PlanStatusType.STARTED);
		try {
			LOG.info("Task {} Started.", uaow.getName());
			function.apply(new Parameters(vimConnectionInformation, vim, context, resource.getVimResourceId()));
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

	class Parameters {
		public Parameters(final VimConnectionInformation _vimConnectionInformation, final Vim _vim, final Map<String, String> _context, final String _resourceId) {
			vimConnectionInformationLocal = _vimConnectionInformation;
			vimLocal = _vim;
			contextLocal = _context;
			resourceId = _resourceId;
		}

		private final VimConnectionInformation vimConnectionInformationLocal;
		private final Vim vimLocal;
		private final Map<String, String> contextLocal;
		private final String resourceId;
	}
}
