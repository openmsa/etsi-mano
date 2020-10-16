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

import javax.persistence.Embeddable;

@Embeddable
public class L2Data implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private String name;

	private Boolean vlanTransparent = Boolean.FALSE;

	private String networkType;

	private Integer mtu;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Boolean getVlanTransparent() {
		return vlanTransparent;
	}

	public void setVlanTransparent(final Boolean vlanTransparent) {
		this.vlanTransparent = vlanTransparent;
	}

	public String getNetworkType() {
		return networkType;
	}

	public void setNetworkType(final String networkType) {
		this.networkType = networkType;
	}

	public Integer getMtu() {
		return mtu;
	}

	public void setMtu(final Integer mtu) {
		this.mtu = mtu;
	}

}
