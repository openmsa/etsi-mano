package com.ubiqube.etsi.mano.service.event;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * this class handle job reception.
 *
 * TODO: I keep Package here for the momment.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Transactional
public class ActionJob extends QuartzJobBean {
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(ActionJob.class);

	private final ActionController actionController;

	public ActionJob(final ActionController _actionController) {
		super();
		actionController = _actionController;
	}

	@Override
	protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
		final JobDataMap jobDataMap = context.getMergedJobDataMap();
		final ActionType eventType = ActionType.valueOf(jobDataMap.getString("eventType"));
		final String objectId = jobDataMap.getString("objectId");
		LOG.info("Quartz event start {} / {}", eventType, objectId);
		if (null == objectId) {
			throw new IllegalArgumentException("Event received With no ObjectId.");
		}
		actionController.dispatch(eventType, objectId, jobDataMap);
		LOG.info("Quartz event end {} / {}", eventType, objectId);
	}

}
