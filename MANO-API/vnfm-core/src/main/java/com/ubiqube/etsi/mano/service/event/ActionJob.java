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
package com.ubiqube.etsi.mano.service.event;

import java.util.UUID;

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

	private final VnfmActionController actionController;

	public ActionJob(final VnfmActionController _actionController) {
		super();
		actionController = _actionController;
	}

	@Override
	protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
		final JobDataMap jobDataMap = context.getMergedJobDataMap();
		final ActionType eventType = ActionType.valueOf(jobDataMap.getString("eventType"));
		final UUID objectId = UUID.fromString(jobDataMap.getString("objectId"));
		LOG.info("Quartz event start {} / {}", eventType, objectId);
		if (null == objectId) {
			throw new IllegalArgumentException("Event received With no ObjectId.");
		}
		actionController.dispatch(eventType, objectId, jobDataMap);
		LOG.info("Quartz event end {} / {}", eventType, objectId);
	}

}
