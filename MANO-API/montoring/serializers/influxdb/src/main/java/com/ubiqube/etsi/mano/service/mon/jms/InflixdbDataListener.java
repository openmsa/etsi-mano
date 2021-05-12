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

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.ubiqube.etsi.mano.mon.dao.TelemetryMetricsResult;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class InflixdbDataListener {

	private static final Logger LOG = LoggerFactory.getLogger(InflixdbDataListener.class);

	private final InfluxDBClient influxClient;

	public InflixdbDataListener(final InfluxDBClient influxClient) {
		this.influxClient = influxClient;
	}

	@JmsListener(destination = "mano.monitoring.gnocchi.data", subscription = "mano.monitoring.gnocchi.data", concurrency = "1", containerFactory = "gnocchiDataFactory")
	public void onGnocchiData(final TelemetryMetricsResult action) {
		LOG.info("influxdb-Receive: {}", action);
		final Point point = Point.measurement(action.getMasterJobId())
				.addField("value", action.getValue())
				.addTag("key", action.getKey())
				.addTag("status", action.isStatus() ? "success" : "fail")
				.addTag("vnf-instance-id", action.getVnfInstanceId())
				.time(Instant.now(), WritePrecision.MS);
		try (WriteApi client = influxClient.getWriteApi()) {
			client.writePoint(point);
		}

	}
}
