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
package com.ubiqube.etsi.mano.dao.mano.dto;

import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVirtualLink;

public class NsInstantiatedVl extends NsInstantiatedBase {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private UUID id = null;

	private String nsVirtualLinkInstanceId = null;

	private NsVirtualLink nsVirtualLinkDesc = null;

	private UUID vlProfileId = null;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public String getNsVirtualLinkInstanceId() {
		return nsVirtualLinkInstanceId;
	}

	public void setNsVirtualLinkInstanceId(final String nsVirtualLinkInstanceId) {
		this.nsVirtualLinkInstanceId = nsVirtualLinkInstanceId;
	}

	public NsVirtualLink getNsVirtualLinkDesc() {
		return nsVirtualLinkDesc;
	}

	public void setNsVirtualLinkDesc(final NsVirtualLink nsVirtualLinkDesc) {
		this.nsVirtualLinkDesc = nsVirtualLinkDesc;
	}

	public UUID getVlProfileId() {
		return vlProfileId;
	}

	public void setVlProfileId(final UUID vlProfileId) {
		this.vlProfileId = vlProfileId;
	}

}
