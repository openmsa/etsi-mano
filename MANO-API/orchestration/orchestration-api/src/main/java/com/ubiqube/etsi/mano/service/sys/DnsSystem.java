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
package com.ubiqube.etsi.mano.service.sys;

import java.util.Set;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface DnsSystem extends System {
	String createDnsZone(final String zoneName);

	void deleteDnsZone(String resourceId);

	String createDnsRecordSet(final String zoneId, final String hostname, final String networkName);

	void deleteDnsRecordSet(final String resourceId, final String zoneId, final Set<String> ips);

}
