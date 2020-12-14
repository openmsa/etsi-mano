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
package com.ubiqube.etsi.mano.dao.mano.v2;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Entity
public class DnsHostTask extends VnfTask {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private String hostname;

	private String zoneId;

	private String networkName;

	private String parentAlias;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> ips;

	public String getHostname() {
		return hostname;
	}

	public void setHostname(final String hostname) {
		this.hostname = hostname;
	}

	@Override
	public String getZoneId() {
		return zoneId;
	}

	@Override
	public void setZoneId(final String zoneId) {
		this.zoneId = zoneId;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(final String networkName) {
		this.networkName = networkName;
	}

	public String getParentAlias() {
		return parentAlias;
	}

	public void setParentAlias(final String parentAlias) {
		this.parentAlias = parentAlias;
	}

	public Set<String> getIps() {
		return ips;
	}

	public void setIps(final Set<String> ips) {
		this.ips = ips;
	}

}
