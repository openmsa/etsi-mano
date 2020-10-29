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
package com.ubiqube.etsi.mano.model;

public class ExternalManagedVirtualLink {
	private String extManagedVirtualLinkId = null;

	private String vmfVirtualLinkDescId = null;

	private String vimId = null;

	private String resourceProviderId = null;

	private String resourceId = null;

	public String getExtManagedVirtualLinkId() {
		return extManagedVirtualLinkId;
	}

	public void setExtManagedVirtualLinkId(final String extManagedVirtualLinkId) {
		this.extManagedVirtualLinkId = extManagedVirtualLinkId;
	}

	public String getVmfVirtualLinkDescId() {
		return vmfVirtualLinkDescId;
	}

	public void setVmfVirtualLinkDescId(final String vmfVirtualLinkDescId) {
		this.vmfVirtualLinkDescId = vmfVirtualLinkDescId;
	}

	public String getVimId() {
		return vimId;
	}

	public void setVimId(final String vimId) {
		this.vimId = vimId;
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

}
