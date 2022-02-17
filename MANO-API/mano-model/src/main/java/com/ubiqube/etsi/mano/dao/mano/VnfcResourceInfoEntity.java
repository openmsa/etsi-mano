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
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
@Entity
public class VnfcResourceInfoEntity implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	/**
	 * IdentifierInVnfd: Reference to the applicable VDU in the VNFD. See note 1.
	 */
	private String vduId;

	/**
	 * Identifier of the VNFD. Shall be present in case the value differs from the
	 * vnfdId attribute of the VnfInstance (e.g. during a "Change current VNF
	 * package" operation or due to its final failure).
	 *
	 * @since 3.3.1
	 */
	private String vnfdId;

	/**
	 * Reference to the VirtualCompute resource.
	 */
	@Embedded
	private VimResource computeResource;

	/**
	 * The identifier of the resource zone, as managed by the resource management
	 * layer (typically, the VIM), where the referenced VirtualCompute resource is
	 * placed. Shall be provided if this information is available from the VIM.
	 *
	 * @since 2.7.1
	 */
	private String zoneId;

	/**
	 * References to the VirtualStorage resources. The value refers to a
	 * VirtualStorageResourceInfo item in the VnfInstance.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> storageResourceIds;

	/**
	 * The reservation identifier applicable to the resource. It shall be present
	 * when an applicable reservation exists.
	 */
	private String reservationId;

	/**
	 * CPs of the VNFC instance. Shall be present when that particular CP of the
	 * VNFC instance is exposed as an external CP of the VNF instance or is
	 * connected to an external CP of the VNF instance. See note 2. May be present
	 * otherwise.
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Set<VnfcResourceInfoVnfcCpInfoEntity> vnfcCpInfo;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> metadata;

}
