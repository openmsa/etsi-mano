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
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
public class VnfExtCpDataEntity implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID cpdId = null;

	@Valid
	@OneToMany(mappedBy = "vnfExtCpDataEntity")
	private List<VnfExtCpConfiguration> cpConfig = null;

	@OneToOne
	private ExtVirtualLinkDataEntity extVirtualLinkDataEntity;

	public UUID getCpdId() {
		return cpdId;
	}

	public void setCpdId(final UUID cpdId) {
		this.cpdId = cpdId;
	}

	public List<VnfExtCpConfiguration> getCpConfig() {
		return cpConfig;
	}

	public void setCpConfig(final List<VnfExtCpConfiguration> cpConfig) {
		this.cpConfig = cpConfig;
	}

	public ExtVirtualLinkDataEntity getExtVirtualLinkDataEntity() {
		return extVirtualLinkDataEntity;
	}

	public void setExtVirtualLinkDataEntity(final ExtVirtualLinkDataEntity extVirtualLinkDataEntity) {
		this.extVirtualLinkDataEntity = extVirtualLinkDataEntity;
	}

}
