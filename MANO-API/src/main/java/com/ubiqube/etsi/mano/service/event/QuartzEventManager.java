package com.ubiqube.etsi.mano.service.event;

import java.util.Map;
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
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

/**
 * Simple implementation using Quartz.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class QuartzEventManager implements EventManager {
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(QuartzEventManager.class);
	/** Scheduler instance injection point. */
	private final Scheduler scheduler;

	public QuartzEventManager(final Scheduler scheduler, final VnfPackageRepository vnfPackageRepository) {
		super();
		this.scheduler = scheduler;
		try {
			this.scheduler.getListenerManager().addJobListener(new UriUploadListener(vnfPackageRepository));
		} catch (final SchedulerException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public void sendNotification(final NotificationEvent notificationEvent, final String objectId) {
		LOG.info("Starting sendEvent : {}/{}", notificationEvent, objectId);
		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("eventType", notificationEvent.value());
		jobDataMap.put("objectId", objectId);
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

	@Override
	public void sendAction(final ActionType actionType, final String objectId, final Map<String, Object> parameters) {
		LOG.info("Starting sendEvent : {}/{}", actionType, objectId);
		final JobDataMap jobDataMap = new JobDataMap(parameters);
		jobDataMap.put("eventType", actionType.value());
		jobDataMap.put("objectId", objectId);
		final JobDetail jobDetail = JobBuilder.newJob(ActionJob.class)
				.withIdentity(UUID.randomUUID().toString(), "action-jobs")
				.withDescription("Action ETSI-MANO")
				.usingJobData(jobDataMap)
				.build();

		final Trigger trigger = TriggerBuilder.newTrigger()
				.forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(), "action-triggers")
				.withDescription("Send Action")
				.build();

		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (final SchedulerException e) {
			throw new GenericException(e);
		}
	}

}
