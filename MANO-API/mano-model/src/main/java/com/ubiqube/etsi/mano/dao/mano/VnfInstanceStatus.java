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
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Embeddable
public class VnfInstanceStatus implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<VnfInstanceScaleInfo> scaleStatus = null;

	private String instantiationLevelId = null;

	private String flavourId;

	// Not sure it's needed here.
	@Enumerated(EnumType.STRING)
	private OperationalStateType vnfState = null;

	public Set<VnfInstanceScaleInfo> getScaleStatus() {
		return scaleStatus;
	}

	public void setScaleStatus(final Set<VnfInstanceScaleInfo> scaleStatus) {
		this.scaleStatus = scaleStatus;
	}

	public String getInstantiationLevelId() {
		return instantiationLevelId;
	}

	public void setInstantiationLevelId(final String instantiationLevelId) {
		this.instantiationLevelId = instantiationLevelId;
	}

	public String getFlavourId() {
		return flavourId;
	}

	public void setFlavourId(final String flavourId) {
		this.flavourId = flavourId;
	}

	public OperationalStateType getVnfState() {
		return vnfState;
	}

	public void setVnfState(final OperationalStateType vnfState) {
		this.vnfState = vnfState;
	}

}
