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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.api.exceptions.ClientResponseException;
import org.openstack4j.api.exceptions.StatusCode;
import org.openstack4j.model.compute.Address;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.dns.v2.Recordset;
import org.openstack4j.model.dns.v2.Zone;
import org.openstack4j.model.dns.v2.ZoneType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OsDns implements Dns {

	private static final Logger LOG = LoggerFactory.getLogger(OsDns.class);

	private final OSClientV3 os;

	public OsDns(final OSClientV3 os) {
		this.os = os;
	}

	@Override
	public String createDnsZone(final String zoneName) {
		final Zone zone = Builders.zone().name(zoneName)
				.ttl(3600)
				.type(ZoneType.PRIMARY)
				.email("mano@ubqube.com")
				.build();
		try {
			final Zone res = os.dns().zones().create(zone);
			return res.getId();
		} catch (final ClientResponseException e) {
			if (e.getStatusCode() == StatusCode.CONFLICT) {
				LOG.warn("Conflict wile creating {}", zoneName);
				return null;
			}
			throw new VimException(e);
		}
	}

	@Override
	public void deleteDnsZone(final String resourceId) {
		os.dns().zones().delete(resourceId);
	}

	@Override
	public String createDnsRecordSet(final String zoneId, final String hostname, final String networkName) {
		final Server server = os.compute().servers().list().stream().filter(x -> x.getName().equals(hostname)).findFirst().orElseThrow();
		final List<String> addresses = server.getAddresses().getAddresses(networkName).stream().map(Address::getAddr).collect(Collectors.toList());
		final Optional<? extends Recordset> rropt = os.dns().recordsets().list().stream()
				.filter(x -> hostname.equals(x.getName()))
				.findFirst();
		Recordset rr;
		if (rropt.isPresent()) {
			rr = rropt.get();
			addresses.addAll(rr.getRecords());
			rr = rr.toBuilder().records(addresses).build();
		} else {
			rr = os.dns().recordsets().create(zoneId, hostname, "A", addresses);
		}
		os.dns().recordsets().update(zoneId, rr);
		return rr.getId();
	}

	@Override
	public void deleteDnsRecordSet(final String resourceId, final String zoneId, final Set<String> ips) {
		final Recordset rr = os.dns().recordsets().get(zoneId, resourceId);
		final List<String> recs = rr.getRecords().stream().filter(x -> !ips.contains(x)).toList();
		if (recs.isEmpty()) {
			os.dns().recordsets().delete(zoneId, resourceId);
		} else {
			os.dns().recordsets().update(zoneId, rr.toBuilder().records(recs).build());
		}
	}

}
