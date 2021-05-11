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
package com.ubiqube.etsi.mano.service.mon.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.mon.TelemetryMetricsResult;
import com.ubiqube.etsi.mano.repository.jpa.mon.MonitoringDataJpa;
import com.ubiqube.etsi.mano.repository.jpa.mon.MonitoringData;

@Service
public class DataPostgresListener {
	private static final Logger LOG = LoggerFactory.getLogger(DataPostgresListener.class);

	private final MonitoringDataJpa monitoringDataJpa;

	public DataPostgresListener(final MonitoringDataJpa monitoringDataJpa) {
		super();
		this.monitoringDataJpa = monitoringDataJpa;
	}

	@JmsListener(destination = "mano.monitoring.gnocchi.data", subscription = "mano.monitoring.gnocchi.data", concurrency = "1", containerFactory = "gnocchiDataFactory")
	public void onGnocchiData(final TelemetryMetricsResult action) {
		LOG.info("Postgresql-Receive: {}", action);
		final MonitoringData entity = new MonitoringData(action.getKey(), action.getMasterJobId(), action.getTimestamp(), action.getValue(), action.getVnfInstanceId(), action.isStatus());
		monitoringDataJpa.save(entity);
	}
}
