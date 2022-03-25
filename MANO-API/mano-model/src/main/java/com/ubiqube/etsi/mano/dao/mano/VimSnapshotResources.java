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

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Embeddable
@Getter
@Setter
public class VimSnapshotResources implements Serializable {

	/**
	 * Identifier of the VIM connection to access the software image referenced in
	 * this structure. The applicable "VimConnectionInfo" structure, which is
	 * referenced by vimConnectionId, can be obtained from the "vimConnectionInfo"
	 * attribute of the "VnfInstance" structure. This attribute shall only be
	 * supported and present if VNF-related resource management in direct mode is
	 * applicable.
	 */
	private String vimConnectionId;

	/**
	 * Identifies the entity responsible for the management of the virtualised
	 * resource. This attribute shall only be supported and present if VNF-related
	 * resource management in indirect mode is applicable. The identification scheme
	 * is outside the scope of the present document.
	 */
	private String resourceProviderId;

	/**
	 * Identifier of the VNF snapshot (referring to the "id" attribute in the
	 * "VnfSnapshot" data structure) related to this VIM snapshot resource.
	 */
	private String vnfSnapshotId;

	/**
	 * Identifier of the information about a specific VNFC snapshot (refer to
	 * "VnfcSnapshotInfo") of the VNF snapshot. The identifier is unique within the
	 * scope of a VNF snapshot, identified by the "vnfSnapshotId" attribute.
	 */
	private String vnfcSnapshotId;

	/**
	 * Identifier of the virtual storage resource that has been snapshotted as
	 * referred in the VNFC snapshot information. Shall only be present if the
	 * snapshot resource in the VIM is a storage resource (as indicated by
	 * "type=STORAGE" in the parent resource definition).
	 */
	private String storageSnapshotId;

	/**
	 * Identifier of the snapshot resource in the resource management layer (i.e.
	 * VIM).
	 */
	private String vimSnapshotResourceId;
}
