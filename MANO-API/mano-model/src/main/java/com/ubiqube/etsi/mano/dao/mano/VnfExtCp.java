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
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class VnfExtCp implements Serializable, ToscaEntity {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String toscaId;

	private String toscaName;

	private String state;

	private String externalVirtualLink;

	private String internalVirtualLink;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn
	private Set<VirtualNicReq> virtualNetworkInterfaceRequirements;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	@Override
	public String getToscaId() {
		return toscaId;
	}

	@Override
	public void setToscaId(final String toscaId) {
		this.toscaId = toscaId;
	}

	@Override
	public String getToscaName() {
		return toscaName;
	}

	@Override
	public void setToscaName(final String toscaName) {
		this.toscaName = toscaName;
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
	public void setState(final String state) {
		this.state = state;
	}

	public String getExternalVirtualLink() {
		return externalVirtualLink;
	}

	public void setExternalVirtualLink(final String externalVirtualLink) {
		this.externalVirtualLink = externalVirtualLink;
	}

	public String getInternalVirtualLink() {
		return internalVirtualLink;
	}

	public void setInternalVirtualLink(final String internalVirtualLink) {
		this.internalVirtualLink = internalVirtualLink;
	}

	public Set<VirtualNicReq> getVirtualNetworkInterfaceRequirements() {
		return virtualNetworkInterfaceRequirements;
	}

	public void setVirtualNetworkInterfaceRequirements(final Set<VirtualNicReq> virtualNetworkInterfaceRequirements) {
		this.virtualNetworkInterfaceRequirements = virtualNetworkInterfaceRequirements;
	}

}
