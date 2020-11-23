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

import java.util.UUID;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mano.service.event.VnfEvent;

public class NotificationJob extends QuartzJobBean {

	private static final Logger LOG = LoggerFactory.getLogger(NotificationJob.class);

	private final VnfEvent vnfEvent;

	public NotificationJob(final VnfEvent vnfEvent) {
		super();
		this.vnfEvent = vnfEvent;
	}

	@Override
	protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
		final JobDataMap jobDataMap = context.getMergedJobDataMap();
		final NotificationEvent eventType = NotificationEvent.valueOf(jobDataMap.getString("eventType"));
		final String objectId = jobDataMap.getString("objectId");

		dispatch(eventType, UUID.fromString(objectId));
	}

	private void dispatch(final NotificationEvent eventType, final UUID objectIdId) {
		switch (eventType) {
		case VNF_PKG_ONBOARDING:
			vnfEvent.onEvent(objectIdId, "VnfPackageOnboardingNotification");
			break;
		case VNF_PKG_ONCHANGE:
			vnfEvent.onEvent(objectIdId, "VnfPackageChangeNotification");
			break;
		default:
			LOG.warn("Could not find event: {}", eventType);
			break;
		}
	}

}
