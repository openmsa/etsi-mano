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

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class VnfcResourceInfoVnfcCpInfoEntity implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	/**
	 * IdentifierInVnf: Identifier of this VNFC CP instance and the associated array
	 * entry.
	 */
	@Id
	private String id;

	/**
	 * IdentifierInVnfd: Identifier of the VDU CPD, cpdId, in the VNFD. ETSI GS
	 * NFV-SOL 001 [i.4] specifies the structure and format of the VNFD based on
	 * TOSCA specifications.
	 */
	private String cpdId;

	/**
	 * IdentifierInVnf: Identifier of the related external CP. Shall be present when
	 * the VNFC CP is exposed as an external CP of the VNF instance or connected to
	 * an external CP of the VNF instance (see note 2) and shall be absent
	 * otherwise.
	 */
	private String vnfExtCpId;

	/**
	 * Network protocol information for this CP. May be omitted if the VNFC CP is
	 * exposed as an external CP. The information can be omitted because it is
	 * already available as part of the external CP information.
	 */
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<CpProtocolInfoEntity> cpProtocolInfo;

	/**
	 * IdentifierInVnf: Identifier of the "VnfLinkPortInfo" structure in the
	 * "VnfVirtualLinkResourceInfo" or "ExtManagedVirtualLinkInfo" structure. Shall
	 * be present if the CP is associated to a link port on an internal VL
	 * (including externally-managed internal VL) of the VNF instance and shall be
	 * absent otherwise.
	 */
	private String vnfLinkPortId;

	/**
	 * IdentifierInVnf: Identifier of another VNFC CP instance that corresponds to
	 * the parent port of a trunk that the present VNFC CP instance participates in.
	 * Shall be provided if the present CP instance participates in a trunk as
	 * subport, and the referred VNFC CP instances are also present in the
	 * vnfcCpInfo attribute.
	 */
	private String parentCpId;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> metadata;

}
