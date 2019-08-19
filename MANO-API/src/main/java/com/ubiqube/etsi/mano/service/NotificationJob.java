package com.ubiqube.etsi.mano.service;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionFilter.NotificationTypesEnum;

public class NotificationJob extends QuartzJobBean {
	private final VnfEvent vnfEvent;

	public NotificationJob(final VnfEvent vnfEvent) {
		super();
		this.vnfEvent = vnfEvent;
	}

	@Override
	protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
		final JobDataMap jobDataMap = context.getMergedJobDataMap();
		final NotificationEvent eventType = NotificationEvent.valueOf(jobDataMap.getString("eventType"));
		final String objectIdId = jobDataMap.getString("objectId");

		dispatch(eventType, objectIdId);
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
			try {
				Thread.sleep(1000);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
	}

}
