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
package com.ubiqube.etsi.mano.vnfm.service.plan.contributors;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;
import com.ubiqube.etsi.mano.service.plan.contributors.PlanContributor;
import com.ubiqube.etsi.mano.vnfm.service.graph.vnfm.VnfParameters;

public abstract class AbstractVnfPlanContributor implements PlanContributor<VnfPackage, VnfBlueprint, VnfTask, VnfParameters> {

	protected static <U extends VnfTask> U createTask(final Supplier<U> newInstance) {
		final U task = newInstance.get();
		task.setStartDate(LocalDateTime.now());
		task.setStatus(PlanStatusType.NOT_STARTED);
		return task;
	}
}
