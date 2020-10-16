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
public class VimComputeResourceFlavourEntity implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private String vimConnectionId = null;

	private String resourceProviderId = null;

	private String vnfdVirtualComputeDescId = null;

	private String vimFlavourId = null;

	public VimComputeResourceFlavourEntity() {
		// Nothing
	}

	public VimComputeResourceFlavourEntity(final VimComputeResourceFlavourEntity parent) {
		vimConnectionId = parent.getVimConnectionId();
		resourceProviderId = parent.getResourceProviderId();
		vnfdVirtualComputeDescId = parent.getVnfdVirtualComputeDescId();
		vimFlavourId = parent.getVimFlavourId();
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

	public String getVnfdVirtualComputeDescId() {
		return vnfdVirtualComputeDescId;
	}

	public void setVnfdVirtualComputeDescId(final String vnfdVirtualComputeDescId) {
		this.vnfdVirtualComputeDescId = vnfdVirtualComputeDescId;
	}

	public String getVimFlavourId() {
		return vimFlavourId;
	}

	public void setVimFlavourId(final String vimFlavourId) {
		this.vimFlavourId = vimFlavourId;
	}

}
