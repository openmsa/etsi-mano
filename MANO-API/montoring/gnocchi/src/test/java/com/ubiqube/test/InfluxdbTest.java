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
package com.ubiqube.test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.ubiqube.etsi.mano.dao.mano.mon.TelemetryMetricsResult;

public class InfluxdbTest {

	@Test
	void testName() throws Exception {
		final char[] token = "3qmy3aPk7qVr58OV40GCGXUOPltsY1v8kecvgzLqG_fVe3ZjFPSWpCtmn1DkOxuAGSjRqcg7ULbnBBKX6zmlLQ==".toCharArray();
		final InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://10.31.1.29:8086", token, "mano", "mano");
		final TelemetryMetricsResult action = new TelemetryMetricsResult(UUID.randomUUID().toString(), UUID.randomUUID().toString(), "memory", 0.5d, OffsetDateTime.now(), true);
		final Point point = Point.measurement(action.getMasterJobId())
				.addField("value", action.getValue())
				.addField("vnf-instance-id", action.getVnfInstanceId())
				.addField("key", action.getKey())
				.addField("status", action.isStatus())
				.addTag("tag", "myTag")
				.time(Instant.now(), WritePrecision.MS);
		try (WriteApi client = influxDBClient.getWriteApi()) {
			client.writePoint(point);
		}
		influxDBClient.close();
	}
}
