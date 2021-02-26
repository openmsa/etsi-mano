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
package com.ubiqube.etsi.mano.service.mon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import com.ubiqube.etsi.mano.dao.mano.pm.PmJob;
import com.ubiqube.etsi.mano.jpa.PmJobsJpa;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
//@Component
public class DataPoller {

	private static final Logger LOG = LoggerFactory.getLogger(DataPoller.class);

	private final PmJobsJpa pmJobsJpa;

	private final MonitoringEventManager monitoringEventManager;

	public DataPoller(final PmJobsJpa _pmJobsJpa, final MonitoringEventManager _monitoringEventManager) {
		pmJobsJpa = _pmJobsJpa;
		monitoringEventManager = _monitoringEventManager;
	}

	@Scheduled(fixedRate = 60000)
	public void run() {
		final Iterable<PmJob> ite = pmJobsJpa.findAll();
		LOG.debug("Polling data");
		for (final PmJob pmJob : ite) {
			LOG.debug(" - {}", pmJob);
			monitoringEventManager.sendGetDataEvent(pmJob);
		}

	}

}
