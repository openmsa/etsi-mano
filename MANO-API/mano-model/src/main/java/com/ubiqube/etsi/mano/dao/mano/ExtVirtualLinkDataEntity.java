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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
public class ExtVirtualLinkDataEntity implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String vimConnectionId = null;

	private String resourceProviderId = null;

	private String resourceId = null;

	@Valid
	@OneToMany(mappedBy = "extVirtualLinkDataEntity")
	private List<VnfExtCpDataEntity> extCps = new ArrayList<>();

	@Valid
	@OneToMany(mappedBy = "extVirtualLinkDataEntity")
	private List<ExtLinkPortDataEntity> extLinkPorts = null;

	@ManyToOne
	private VnfInstance vnfInstance;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(final String resourceId) {
		this.resourceId = resourceId;
	}

	public List<VnfExtCpDataEntity> getExtCps() {
		return extCps;
	}

	public void setExtCps(final List<VnfExtCpDataEntity> extCps) {
		this.extCps = extCps;
	}

	public List<ExtLinkPortDataEntity> getExtLinkPorts() {
		return extLinkPorts;
	}

	public void setExtLinkPorts(final List<ExtLinkPortDataEntity> extLinkPorts) {
		this.extLinkPorts = extLinkPorts;
	}

	public VnfInstance getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(final VnfInstance vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

}
