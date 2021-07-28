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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.orchestrator.OrchExecutionListener;
import com.ubiqube.etsi.mano.orchestrator.Task;
import com.ubiqube.etsi.mano.orchestrator.uow.UnitOfWork;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class OrchListenetImpl implements OrchExecutionListener<VnfTask> {

	private static final Logger LOG = LoggerFactory.getLogger(OrchListenetImpl.class);

	@Override
	public void onSuccess(final Task<VnfTask> task) {
		LOG.info("Sucess {}", task);
		final VnfTask resource = task.getParameters();
		resource.setStatus(PlanStatusType.SUCCESS);
		resource.setEndDate(LocalDateTime.now());
	}

	@Override
	public void onError(final Task<VnfTask> task, final Exception exception) {
		LOG.warn("Error {}", task);
		final VnfTask resource = task.getParameters();
		resource.setStatus(PlanStatusType.FAILED);
		resource.setEndDate(LocalDateTime.now());
	}

	@Override
	public void onStart(final VirtualTask<VnfTask> task) {
		LOG.info("Starting {}", task);
		final VnfTask resource = task.getParameters();
		resource.setStatus(PlanStatusType.STARTED);
		resource.setEndDate(LocalDateTime.now());
	}

	@Override
	public void onTerminate(final UnitOfWork<VnfTask> uaow, final String res) {
		LOG.info("Terminate {} => {}", uaow.getTask(), res);
		uaow.getTask().getParameters().setVimResourceId(res);
	}

}
