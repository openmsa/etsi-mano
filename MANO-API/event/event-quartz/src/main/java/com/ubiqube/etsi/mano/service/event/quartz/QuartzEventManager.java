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
package com.ubiqube.etsi.mano.service.event.quartz;

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
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.NotificationEvent;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;

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
	public void sendNotification(final NotificationEvent notificationEvent, final UUID objectId, final Map<String, String> additionalParameters) {
		LOG.info("Starting notification : {}/{}", notificationEvent, objectId);
		createJob(additionalParameters, notificationEvent.value(), objectId, NotificationJob.class);
	}

	@Override
	public void sendActionVnfm(final ActionType actionType, final UUID objectId, final Map<String, Object> parameters) {
		LOG.info("Starting sendAction VNFM : {}/{}", actionType, objectId);
		createJob(parameters, actionType.value(), objectId, ActionJob.class);
	}

	@Override
	public void sendGrant(final UUID objectId, final Map<String, Object> parameters) {
		LOG.info("Starting send Grant : Grant/{}", objectId);
		createJob(parameters, "grant", objectId, GrantJob.class);
	}

	@Override
	public void sendActionNfvo(final ActionType actionType, final UUID objectId, final Map<String, Object> parameters) {
		LOG.info("Starting sendAction NFVO : {}/{}", actionType, objectId);
		createJob(parameters, actionType.value(), objectId, ActionNfvoJob.class);
	}

	@Override
	public void sendAction(final ActionType actionType, final UUID objectId) {
		LOG.info("Starting sendAction : {}/{}", actionType, objectId);
		createJob(Map.of(), actionType.value(), objectId, ActionJob.class);

	}

	private <U> void createJob(final Map<String, U> parameters, final String actionType, final UUID objectId, final Class<? extends QuartzJobBean> controllers) {
		final JobDataMap jobDataMap = QuartzEventUtils.createJobMap(actionType, objectId, parameters);
		final JobDetail jobDetail = JobBuilder.newJob(controllers)
				.withIdentity(UUID.randomUUID().toString(), actionType + "-jobs")
				.withDescription("Action ETSI-MANO")
				.usingJobData(jobDataMap)
				.build();

		final Trigger trigger = TriggerBuilder.newTrigger()
				.forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(), actionType + "-triggers")
				.withDescription("Send Action")
				.build();
		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (final SchedulerException e) {
			throw new GenericException(e);
		}
	}
}
