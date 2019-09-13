package com.ubiqube.etsi.mano.service.event;

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
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.orchestration.OrchestrationService;
import com.ubiqube.etsi.mano.exception.GenericException;

public class PollingJob extends QuartzJobBean {

	private static final Logger LOG = LoggerFactory.getLogger(PollingJob.class);

	private final OrchestrationService orchestrationService;

	public PollingJob(final OrchestrationService _orchestrationService) {
		orchestrationService = _orchestrationService;
	}

	@Override
	protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
		final long processId = context.getJobDetail().getJobDataMap().getLong("processId");
		ProcessInstance processInstance;
		try {
			processInstance = orchestrationService.getProcessInstance(processId);
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
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
