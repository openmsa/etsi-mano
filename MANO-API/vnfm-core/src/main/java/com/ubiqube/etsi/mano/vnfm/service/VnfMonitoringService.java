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

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.MonitoringParams;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.pm.PmJob;
import com.ubiqube.etsi.mano.dao.mano.pm.PmJobCriteria;
import com.ubiqube.etsi.mano.jpa.PmJobsJpa;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfMonitoringService {

	private static final Logger LOG = LoggerFactory.getLogger(VnfMonitoringService.class);
	private final PmJobsJpa pmJobsJpa;

	public VnfMonitoringService(final PmJobsJpa pmJobsJpa) {
		super();
		this.pmJobsJpa = pmJobsJpa;
	}

	public String registerMonitoring(final String instanceId, final MonitoringParams monitoringParams, final VimConnectionInformation vimConnectionId) {
		// We just need to convert monitoring params to PmJob entity..
		final PmJobCriteria criteria = PmJobCriteria.builder()
				.collectionPeriod(monitoringParams.getCollectionPeriod())
				.performanceMetric(Set.of(monitoringParams.getPerformanceMetric()))
				.build();
		final PmJob job = PmJob.builder()
				.objectInstanceIds(List.of(instanceId))
				.criteria(criteria)
				.vimConnectionInformation(vimConnectionId)
				.build();
		final PmJob nJob = pmJobsJpa.save(job);
		LOG.debug("Registering PmJob {}", nJob.getId());
		return nJob.getId().toString();
	}

	public void unregister(final String resourceId) {
		final PmJob entity = pmJobsJpa.findById(UUID.fromString(resourceId)).orElseThrow();
		pmJobsJpa.delete(entity);

	}

}
