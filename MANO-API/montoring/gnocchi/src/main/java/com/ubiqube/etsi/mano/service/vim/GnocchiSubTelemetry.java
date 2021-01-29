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
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.api.client.IOSClientBuilder.V3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.openstack.telemetry.domain.GnocchiResource;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.mon.GnocchiTelemetryMetrics;
import com.ubiqube.etsi.mano.repository.jpa.mon.GnocchiTelemetryMetricsJpa;

@Service
public class GnocchiSubTelemetry {
	private final GnocchiTelemetryMetricsJpa gnocchiTelemetryMetricsJpa;

	public GnocchiSubTelemetry(final GnocchiTelemetryMetricsJpa _gnocchiTelemetryMetricsJpa) {
		gnocchiTelemetryMetricsJpa = _gnocchiTelemetryMetricsJpa;
	}

	public List<GnocchiTelemetryMetrics> getMetricsForVnfc(final VimConnectionInformation vimConnectionInformation, final String vnfcId) {
		final OSClientV3 os = authenticate(vimConnectionInformation);
		return getMetrics(vnfcId, os);
	}

	private List<GnocchiTelemetryMetrics> getMetrics(final String vnfcId, final OSClientV3 os) {
		final List<GnocchiTelemetryMetrics> list = gnocchiTelemetryMetricsJpa.findByVnfInstanceId(vnfcId);
		if (!list.isEmpty()) {
			return list;
		}
		final List<GnocchiResource> res = os.telemetry().gnocchi().search().resource("instance", "instance_id=" + vnfcId);
		final List<GnocchiTelemetryMetrics> newList = res.stream().flatMap(x -> map(x, vnfcId)).collect(Collectors.toList());
		final Iterable<GnocchiTelemetryMetrics> ite = gnocchiTelemetryMetricsJpa.saveAll(newList);
		return StreamSupport.stream(ite.spliterator(), false).collect(Collectors.toList());
	}

	private static Stream<GnocchiTelemetryMetrics> map(final GnocchiResource gnocchiresource, final String vnfInstanceId) {
		return gnocchiresource.getMetrics().entrySet().stream().map(x -> new GnocchiTelemetryMetrics(vnfInstanceId, x.getKey(), x.getValue()));
	}

	private static OSClientV3 authenticate(final VimConnectionInformation vci) {
		vci.getInterfaceInfo().get("endpoint");
		final V3 base = OSFactory.builderV3()
				.endpoint(vci.getInterfaceInfo().get("endpoint"));
		final Map<String, String> ai = vci.getAccessInfo();
		final String userDomain = ai.get("userDomain");
		if (null != userDomain) {
			final Identifier domainIdentifier = Identifier.byName(userDomain);
			base.credentials(ai.get("username"), ai.get("password"), domainIdentifier);
		} else {
			base.credentials(ai.get("username"), ai.get("password"));
		}

		final String project = ai.get("project");
		final String projectId = ai.get("projectId");
		if (null != project) {
			base.scopeToProject(Identifier.byName(project));
		} else if (null != projectId) {
			base.scopeToProject(Identifier.byId(projectId));
		}
		return base.authenticate();
	}

}
