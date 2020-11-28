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

import com.ubiqube.etsi.mano.dao.mano.NsSap;

public class NsInstantiatedSap extends NsInstantiatedBase {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private UUID id = null;

	private String sapInstanceId = null;

	private NsSap sapd = null;

	private String sapName = null;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public String getSapInstanceId() {
		return sapInstanceId;
	}

	public void setSapInstanceId(final String sapInstanceId) {
		this.sapInstanceId = sapInstanceId;
	}

	public NsSap getSapd() {
		return sapd;
	}

	public void setSapd(final NsSap sapd) {
		this.sapd = sapd;
	}

	public String getSapName() {
		return sapName;
	}

	public void setSapName(final String sapName) {
		this.sapName = sapName;
	}

}
