package com.ubiqube.etsi.mano.service.event;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ubiqube.etsi.mano.model.vnf.sol005.PkgmNotificationsFilter.NotificationTypesEnum;

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

		dispatch(eventType, objectId);
	}

	private void dispatch(final NotificationEvent eventType, final String objectIdId) {
		switch (eventType) {
		case VNF_PKG_ONBOARDING:
			vnfEvent.onEvent(objectIdId, NotificationTypesEnum.VNFPACKAGEONBOARDINGNOTIFICATION);
			break;
		case VNF_PKG_ONCHANGE:
			vnfEvent.onEvent(objectIdId, NotificationTypesEnum.VNFPACKAGECHANGENOTIFICATION);
			break;
		default:
			LOG.warn("Could not find event: {}", eventType);
			break;
		}
	}

}
