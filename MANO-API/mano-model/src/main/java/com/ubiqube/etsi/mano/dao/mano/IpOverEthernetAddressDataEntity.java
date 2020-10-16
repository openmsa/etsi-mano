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
package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;

/**
 * TODO: maybe we can remove this table. USED:
 * IpOverEthernetAddressDataIpAddressesEntity
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
public class IpOverEthernetAddressDataEntity implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String macAddress = null;

	@Valid
	@OneToMany(mappedBy = "ipOverEthernetAddressDataEntity")
	private List<IpOverEthernetAddressDataIpAddressesEntity> ipAddresses = null;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(final String macAddress) {
		this.macAddress = macAddress;
	}

	public List<IpOverEthernetAddressDataIpAddressesEntity> getIpAddresses() {
		return ipAddresses;
	}

	public void setIpAddresses(final List<IpOverEthernetAddressDataIpAddressesEntity> ipAddresses) {
		this.ipAddresses = ipAddresses;
	}

}
