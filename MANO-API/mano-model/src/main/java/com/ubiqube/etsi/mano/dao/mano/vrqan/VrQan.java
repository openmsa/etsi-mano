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
package com.ubiqube.etsi.mano.dao.mano.vrqan;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
public class VrQan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private UUID vimId;

	private int floatingIpUsed;

	private int floatingIpMax;

	private int floatingFree;

	private int securityGroupsUsed;

	private int securityGroupsMax;

	private int securityGroupsFree;

	private long ramUsed;

	private long ramMax;

	private long ramFree;

	private int keyPairsUsed;

	private int keyPairsMax;

	private int keyPairsFree;

	private int instanceUsed;

	private int instanceMax;

	private int instanceFree;

	private int vcpuUsed;

	private int vcpuMax;

	private int vcpuFree;

	private ZonedDateTime lastChange = ZonedDateTime.now();

	private ZonedDateTime lastCheck = ZonedDateTime.now();

	public VrQan(final UUID vimId) {
		this.vimId = vimId;
	}

	public boolean haveValue() {
		return floatingIpUsed != 0 ||
				floatingIpMax != 0 ||
				floatingFree != 0 ||
				securityGroupsUsed != 0 ||
				securityGroupsMax != 0 ||
				securityGroupsFree != 0 ||
				ramUsed != 0 ||
				ramMax != 0 ||
				ramFree != 0 ||
				keyPairsUsed != 0 ||
				keyPairsMax != 0 ||
				keyPairsFree != 0 ||
				instanceUsed != 0 ||
				instanceMax != 0 ||
				instanceFree != 0 ||
				vcpuUsed != 0 ||
				vcpuMax != 0 ||
				vcpuFree != 0;
	}

	@Override
	public String toString() {
		return "VrQan [id=" + id + ", vimId=" + vimId + ", floatingIpUsed=" + floatingIpUsed + ", floatingIpMax=" + floatingIpMax + ", floatingFree=" + floatingFree + ", securityGroupsUsed=" + securityGroupsUsed + ", securityGroupsMax=" + securityGroupsMax + ", securityGroupsFree=" + securityGroupsFree + ", ramUsed=" + ramUsed + ", ramMax=" + ramMax + ", ramFree=" + ramFree + ", keyPairsUsed=" + keyPairsUsed + ", keyPairsMax=" + keyPairsMax + ", keyPairsFree=" + keyPairsFree + ", instanceUsed=" + instanceUsed + ", instanceMax=" + instanceMax + ", instanceFree=" + instanceFree + ", vcpuUsed=" + vcpuUsed + ", vcpuMax=" + vcpuMax + ", vcpuFree=" + vcpuFree + ", lastChange=" + lastChange + "]";
	}

}
