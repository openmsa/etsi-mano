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

// @see ResourceHandle
@Embeddable
public class VimResource implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;
	/**
	 * Identifier of the VIM connection to manage the resource. This attribute shall
	 * only be supported and present if VNF- related resource management in direct
	 * mode is applicable. The applicable "VimConnectionInfo" structure, which is
	 * referenced by vimConnectionId, can be obtained from the "vimConnectionInfo"
	 * attribute of the "VnfInstance" structure.
	 */
	private String vimConnectionId;

	/**
	 * Identifier of the entity responsible for the management of the resource. This
	 * attribute shall only be supported and present when VNF-related resource
	 * management in indirect mode is applicable. The identification scheme is
	 * outside the scope of the present document.
	 */
	private String resourceProviderId;

	/**
	 * Identifier of the resource in the scope of the VIM or the resource provider.
	 */
	private String resourceId;

	/**
	 * Type of the resource in the scope of the VIM or the resource provider. The
	 * value set of the "vimLevelResourceType" attribute is within the scope of the
	 * VIM or the resource provider and can be used as information that complements
	 * the ResourceHandle. This value set is different from the value set of the
	 * "type" attribute in the ResourceDefinition (refer to clause 9.5.3.2).
	 * 
	 */
	private String vimLevelResourceType;

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

	public String getVimLevelResourceType() {
		return vimLevelResourceType;
	}

	public void setVimLevelResourceType(final String vimLevelResourceType) {
		this.vimLevelResourceType = vimLevelResourceType;
	}

}
