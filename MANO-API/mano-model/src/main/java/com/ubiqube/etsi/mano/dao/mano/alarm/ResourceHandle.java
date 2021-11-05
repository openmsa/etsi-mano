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
package com.ubiqube.etsi.mano.dao.mano.alarm;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ResourceHandle implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;
	/**
	 * Identifier of the VIM connection to manage the resource. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable.
	 * NOTE: he information about the VIM connection referenced by the VIM connection id is known to the VNFM. Moreover, the identifier of the VIM connection provides scope to the resourceId.
	 */
	private UUID vimConnectionId;
	/**
	 * Identifier of the entity responsible for the management of the resource. This attribute shall only be supported and present when VNF-related resource management in indirect mode is applicable. The identification scheme is outside the scope of the present document.
	 */
	private String resourceProviderId;
	/**
	 * Identifier of the resource in the scope of the VIM or the resource provider.
	 */
	private UUID resourceId;
	/**
	 * Type of the resource in the scope of the VIM or the resource provider.
	 *
	 * NOTE: The value set of the "vimLevelResourceType" attribute is within the scope of the VIM or the resource provider and can be used as information that complements the ResourceHandle.
	 */
	private String vimLevelResourceType;
}
