package com.ubiqube.etsi.mano.service.plan.contributors;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;

public abstract class AbstractPlanContributor implements PlanContributor {

	protected static <U> U createTask(final Supplier<Task> newInstance) {
		final Task task = newInstance.get();
		task.setStartDate(LocalDateTime.now());
		task.setStatus(PlanStatusType.NOT_STARTED);
		return (U) task;
	}
}
