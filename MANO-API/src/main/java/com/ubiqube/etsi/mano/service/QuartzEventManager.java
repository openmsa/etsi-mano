package com.ubiqube.etsi.mano.service;

import java.util.UUID;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.exception.GenericException;

@Service
public class QuartzEventManager implements EventManager {

	private static final Logger LOG = LoggerFactory.getLogger(QuartzEventManager.class);

	private final Scheduler scheduler;

	public QuartzEventManager(final Scheduler scheduler) {
		super();
		this.scheduler = scheduler;
	}

	@Override
	public void sendEvent(final NotificationEvent notificationEvent, final String objectIdId) {
		LOG.info("Starting sendEvent : {}/{}", notificationEvent, objectIdId);
		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("eventType", notificationEvent.value());
		jobDataMap.put("objectId", objectIdId);
		final JobDetail jobDetail = JobBuilder.newJob(NotificationJob.class)
				.withIdentity(UUID.randomUUID().toString(), "notification-jobs")
				.withDescription("Notification ETSI-MANO")
				.usingJobData(jobDataMap)
				.build();

		final Trigger trigger = TriggerBuilder.newTrigger()
				.forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(), "notification-triggers")
				.withDescription("Send Notififactions")
				.build();

		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (final SchedulerException e) {
			throw new GenericException(e);
		}
	}

}
