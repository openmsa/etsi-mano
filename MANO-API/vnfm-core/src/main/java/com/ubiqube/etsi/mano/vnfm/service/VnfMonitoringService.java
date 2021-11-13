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
package com.ubiqube.etsi.mano.vnfm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.MonitoringParams;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfMonitoringService {

	private static final Logger LOG = LoggerFactory.getLogger(VnfMonitoringService.class);

	public String registerMonitoring(final String instanceId, final MonitoringParams monitoringParams) {
//		/PmJob.
		return null;
	}

	public void unregister(final String resourceId) {
		// TODO Auto-generated method stub

	}

	// @Scheduled(fixedDelay = 30_000)
	public void poll() {
		LOG.debug("poll");
	}
}
