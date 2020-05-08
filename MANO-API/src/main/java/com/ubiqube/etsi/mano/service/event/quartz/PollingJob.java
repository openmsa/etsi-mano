package com.ubiqube.etsi.mano.service.event.quartz;

import java.util.Calendar;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ubiqube.api.entities.orchestration.ProcessInstance;
import com.ubiqube.api.interfaces.orchestration.OrchestrationService;
import com.ubiqube.etsi.mano.exception.GenericException;

/**
 * Polling job. This job poll orchestration service and re-schedule it self
 * while the MSA state is in RUNNING.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class PollingJob extends QuartzJobBean {
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(PollingJob.class);

	private final OrchestrationService orchestrationService;

	public PollingJob(final OrchestrationService _orchestrationService) {
		orchestrationService = _orchestrationService;
	}

	@Override
	protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
		final long processId = context.getJobDetail().getJobDataMap().getLong("processId");
		final ProcessInstance processInstance = orchestrationService.getProcessInstance(processId);
		LOG.debug("Polling task {} with status: {}", processId, processInstance.getStatus().getStatus());
		if ("RUNNING".equals(processInstance.getStatus().getStatus())) {

			final JobDetail currentJobDetail = context.getJobDetail();
			final Trigger currentTrigger = context.getTrigger();
			final Scheduler scheduler = context.getScheduler();
			LOG.info("Re-Scheduling the task {}", currentJobDetail.getKey());
			final Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.SECOND, 30);
			final Trigger newTrigger = TriggerBuilder.newTrigger()
					.startAt(calendar.getTime())
					.forJob(currentJobDetail)
					.build();
			try {
				scheduler.rescheduleJob(currentTrigger.getKey(), newTrigger);
			} catch (final SchedulerException e) {
				throw new GenericException(e);
			}
		}
	}

}
