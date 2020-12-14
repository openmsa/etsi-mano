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

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class NsInstantiatedPnf extends NsInstantiatedBase {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private UUID id = null;

	private String pnfId = null;

	private String pnfdId = null;

	private String pnfProfileId = null;

	private String pnfName = null;

	private Set<String> cpInstanceId = new HashSet<>();

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public String getPnfId() {
		return pnfId;
	}

	public void setPnfId(final String pnfId) {
		this.pnfId = pnfId;
	}

	public String getPnfdId() {
		return pnfdId;
	}

	public void setPnfdId(final String pnfdId) {
		this.pnfdId = pnfdId;
	}

	public String getPnfProfileId() {
		return pnfProfileId;
	}

	public void setPnfProfileId(final String pnfProfileId) {
		this.pnfProfileId = pnfProfileId;
	}

	public String getPnfName() {
		return pnfName;
	}

	public void setPnfName(final String pnfName) {
		this.pnfName = pnfName;
	}

	public Set<String> getCpInstanceId() {
		return cpInstanceId;
	}

	public void setCpInstanceId(final Set<String> cpInstanceId) {
		this.cpInstanceId = cpInstanceId;
	}

}
