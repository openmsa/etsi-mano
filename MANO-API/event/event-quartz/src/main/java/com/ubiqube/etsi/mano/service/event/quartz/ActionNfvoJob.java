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

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ubiqube.etsi.mano.service.event.ActionController;
import com.ubiqube.etsi.mano.service.event.ActionMessage;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ActionNfvoJob extends QuartzJobBean {
	private final ActionController actionController;

	public ActionNfvoJob(final ActionController actionController) {
		super();
		this.actionController = actionController;
	}

	@Override
	protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
		final JobDataMap jobDataMap = context.getMergedJobDataMap();
		final ActionMessage event = QuartzEventUtils.createActionMessage(jobDataMap);
		actionController.onEvent(event);
	}

}
