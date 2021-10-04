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

import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dexecutor.core.task.Task;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.vim.Vim;

public abstract class AbstractNsTaskUow extends Task<UnitOfWork<NsTask, NsParameters>, String> {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AbstractNsTaskUow.class);

	private final UnitOfWork<NsTask, NsParameters> uaow;

	private final transient Function<NsParameters, String> function;

	private NsParameters params;

	public AbstractNsTaskUow(final UnitOfWork<NsTask, NsParameters> uaow, final boolean _create, final NsParameters params) {
		super();
		this.uaow = uaow;
		this.params = params;
		if (_create) {
			function = x -> {
				final String res = uaow.exec(params);
				if (null != res) {
					params.getContext().put(uaow.getToscaName(), res);
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
		final com.ubiqube.etsi.mano.dao.mano.v2.Task resource = this.uaow.getTaskEntity();
		resource.setStartDate(LocalDateTime.now());
		resource.setStatus(PlanStatusType.STARTED);
		try {
			LOG.info("Task {} Started.", uaow.getName());
			final String res = function.apply(params);
			resource.setVimResourceId(res);
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
