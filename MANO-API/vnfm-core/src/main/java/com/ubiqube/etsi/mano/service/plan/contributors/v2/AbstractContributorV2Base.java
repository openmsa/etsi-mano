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
package com.ubiqube.etsi.mano.service.plan.contributors.v2;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.orchestrator.PlanContributor;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;

public abstract class AbstractContributorV2Base<U, T extends VirtualTask<U>, P> implements PlanContributor<U, T, P> {

	protected static <U extends VnfTask> U createTask(final Supplier<U> newInstance, final VnfTask t) {
		final U task = createTask(newInstance);
		task.setToscaName(t.getToscaName());
		return task;
	}

	protected static <U extends VnfTask> U createTask(final Supplier<U> newInstance) {
		final U task = newInstance.get();
		task.setStartDate(LocalDateTime.now());
		task.setChangeType(ChangeType.ADDED);
		task.setStatus(PlanStatusType.NOT_STARTED);
		return task;
	}

	protected static <U extends VnfTask> U createDeleteTask(final Supplier<U> newInstance, final VnfLiveInstance vli) {
		final VnfTask t = vli.getTask();
		final U task = newInstance.get();
		task.setStartDate(LocalDateTime.now());
		task.setChangeType(ChangeType.REMOVED);
		task.setStatus(PlanStatusType.NOT_STARTED);
		task.setToscaName(t.getToscaName());
		task.setAlias(t.getAlias());
		task.setVimResourceId(vli.getResourceId());
		task.setVimConnectionId(t.getVimConnectionId());
		task.setRemovedVnfLiveInstance(vli.getId());
		return task;
	}
}
