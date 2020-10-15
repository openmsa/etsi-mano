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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
public class VnfExtCpConfiguration implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String cpInstanceId = null;

	private String linkPortId = null;

	@Valid
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vnfExtCpConfiguration")
	private List<CpProtocolDataEntity> cpProtocolData = null;

	@OneToOne
	private VnfExtCpDataEntity vnfExtCpDataEntity;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getCpInstanceId() {
		return cpInstanceId;
	}

	public void setCpInstanceId(final String cpInstanceId) {
		this.cpInstanceId = cpInstanceId;
	}

	public String getLinkPortId() {
		return linkPortId;
	}

	public void setLinkPortId(final String linkPortId) {
		this.linkPortId = linkPortId;
	}

	public List<CpProtocolDataEntity> getCpProtocolData() {
		return cpProtocolData;
	}

	public void setCpProtocolData(final List<CpProtocolDataEntity> cpProtocolData) {
		this.cpProtocolData = cpProtocolData;
	}

	public VnfExtCpDataEntity getVnfExtCpDataEntity() {
		return vnfExtCpDataEntity;
	}

	public void setVnfExtCpDataEntity(final VnfExtCpDataEntity vnfExtCpDataEntity) {
		this.vnfExtCpDataEntity = vnfExtCpDataEntity;
	}

}
