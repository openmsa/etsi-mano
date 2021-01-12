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
package com.ubiqube.etsi.mano.service.vim;

import java.util.List;
import java.util.stream.Collectors;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.openstack.telemetry.domain.GnocchiResource;

import com.ubiqube.etsi.mano.service.vim.mon.Metric;

public class GnocchiTelemetry implements Telemetry {
	private final OSClientV3 os;

	public GnocchiTelemetry(final OSClientV3 _os) {
		os = _os;
	}

	@Override
	public List<Metric> getMetricsForVnfc(final String vnfcId) {
		final List<GnocchiResource> res = os.telemetry().gnocchi().search().resource("instance", "instance_id=" + vnfcId);
		return res.stream().map(this::map).collect(Collectors.toList());
	}

	private Metric map(final GnocchiResource gnocchiresource) {
		final Metric m = new Metric();
		m.setKey(gnocchiresource.getMetric(""));
		return null;
	}

}
