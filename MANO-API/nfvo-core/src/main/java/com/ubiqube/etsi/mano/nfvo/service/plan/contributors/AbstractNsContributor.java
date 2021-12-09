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
package com.ubiqube.etsi.mano.nfvo.service.plan.contributors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Supplier;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.ToscaEntity;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.orchestrator.Bundle;
import com.ubiqube.etsi.mano.orchestrator.PlanContributor;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public abstract class AbstractNsContributor<U extends NsTask, T extends VirtualTask<U>> implements PlanContributor<U, T, NsBlueprint> {
	protected AbstractNsContributor() {
		// Nothing.
	}

	protected static <U extends NsTask> U createTask(final Supplier<U> newInstance, final ToscaEntity toscaEntity) {
		final U task = newInstance.get();
		task.setStartDate(LocalDateTime.now());
		task.setStatus(PlanStatusType.NOT_STARTED);
		task.setToscaName(toscaEntity.getToscaName());
		task.setAlias(toscaEntity.getToscaName());
		return task;
	}

	protected static <U extends NsTask> U createTask(final Supplier<U> newInstance) {
		final U task = newInstance.get();
		task.setStartDate(LocalDateTime.now());
		task.setStatus(PlanStatusType.NOT_STARTED);
		return task;
	}

	protected static <U extends NsTask> U createDeleteTask(final Supplier<U> newInstance, final NsLiveInstance vli) {
		final NsTask t = vli.getNsTask();
		final U task = newInstance.get();
		task.setStartDate(LocalDateTime.now());
		task.setChangeType(ChangeType.REMOVED);
		task.setStatus(PlanStatusType.NOT_STARTED);
		task.setToscaName(t.getToscaName());
		task.setAlias(t.getAlias());
		task.setVimResourceId(vli.getResourceId());
		task.setVimConnectionId(t.getVimConnectionId());
		task.setRemovedLiveInstance(vli.getId());
		return task;
	}

	@Override
	public final List<T> contribute(final Bundle bundle, final NsBlueprint blueprint) {
		final List<T> r = nsContribute((NsdPackage) bundle, blueprint);
		final List<U> rr = r.stream().map(VirtualTask::getParameters).toList();
		blueprint.getTasks().addAll(rr);
		return r;
	}

	protected abstract List<T> nsContribute(final NsdPackage bundle, final NsBlueprint blueprint);

}
