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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ubiqube.etsi.mano.dao.mano.pm.PmJob;
import com.ubiqube.etsi.mano.mon.MonGenericException;
import com.ubiqube.etsi.mano.service.mon.data.BatchPollingJob;
import com.ubiqube.etsi.mano.service.mon.data.Metric;
import com.ubiqube.etsi.mano.service.mon.data.MetricFunction;
import com.ubiqube.etsi.mano.service.mon.repository.MonPmJobJpa;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Component
public class ManoDataPoller {

	private static final Logger LOG = LoggerFactory.getLogger(ManoDataPoller.class);

	private final MonPmJobJpa pmJobsJpa;

	private final MonitoringEventManager monitoringEventManager;

	private Properties props;

	public ManoDataPoller(final MonPmJobJpa _pmJobsJpa, final MonitoringEventManager _monitoringEventManager) {
		pmJobsJpa = _pmJobsJpa;
		monitoringEventManager = _monitoringEventManager;
		try (InputStream mappting = this.getClass().getClassLoader().getResourceAsStream("gnocchi-mapping.properties")) {
			props = new Properties();
			props.load(mappting);
		} catch (final IOException e) {
			throw new MonGenericException(e);
		}
	}

	@Scheduled(fixedRate = 60_000)
	public void run() {
		final Iterable<PmJob> ite = pmJobsJpa.findAll();
		LOG.trace("Polling data");
		for (final PmJob pmJob : ite) {
			LOG.info(" - {}", pmJob);
			monitoringEventManager.sendGetDataEvent(map(pmJob));
		}

	}

	private BatchPollingJob map(final PmJob pmJob) {
		final List<Metric> mettrics = pmJob.getCriteria().getPerformanceMetric().stream().map(this::map).toList();
		return new BatchPollingJob(pmJob.getId(), pmJob.getObjectInstanceIds(), mettrics, pmJob.getVimConnectionInformation().getId());
	}

	private Metric map(final String x) {
		final String prop = props.getProperty(x);
		if (null == prop) {
			throw new MonGenericException("Unable to map monitoring key : " + x);
		}
		final String[] metric = prop.split(",");
		if (metric.length != 2) {
			throw new MonGenericException("bad mapping key : " + x + "/" + prop + ". Should have one ','");
		}
		return new Metric(metric[0], MetricFunction.fromValue(metric[1]));
	}

}
