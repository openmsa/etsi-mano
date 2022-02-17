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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class ExtCpInfo implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	/**
	 * IdentifierInVnf: Identifier of the external CP instance and the related
	 * information instance.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	/**
	 * IdentifierInVnfd: Identifier of the external CPD, VnfExtCpd, in the VNFD.
	 */
	private String cpdId;

	/**
	 * IdentifierInVnf: Identifier that references the applied "VnfExtCpConfig"
	 * entry in the "cpConfig" map of the "currentVnfExtCpData" in the
	 * "ExtVirtualLinkInfo" structure.
	 *
	 * @since 3.3.1
	 */
	private String cpConfigId;
	/**
	 * Identifier: Identifier of the VNFD. Shall be present in case the value
	 * differs from the vnfdId attribute of the VnfInstance (e.g. during a "Change
	 * current VNF package" operation or due to its final failure).
	 *
	 * @since 3.3.1
	 */
	private String vnfdId;

	/**
	 * Network protocol information for this CP.
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<CpProtocolDataEntity> cpProtocolInfo;

	/**
	 * Identifier of the "ExtLinkPortInfo" structure inside the "ExtVirtualLinkInfo"
	 * structure. Shall be present if the CP is associated to a link port. See note
	 * 2.
	 *
	 * @deprecated Duplicate with 271.
	 */
	@Deprecated(forRemoval = false)
	@OneToOne
	private CpProtocolDataEntity extLinkPortId;
	// 2.7.1 Type change
	private CpProtocolDataEntity extLinkPortId271;

	@ElementCollection
	private Map<String, String> metadata = new HashMap<>();

	/**
	 * IdentifierInVnf: Identifier of the "vnfcCpInfo" structure in
	 * "VnfcResourceInfo" structure that represents the VNFC CP which is exposed by
	 * this external CP instance, either directly or via a floating IP address.
	 * Shall be present in case this CP instance maps to a VNFC CP. See note 1.
	 */
	private String associatedVnfcCpId;

	/**
	 * IdentifierInVnf: Identifier of the VIP CP instance that is exposed as this
	 * VnfExtCp instance, either directly or via a floating IP address, and the
	 * related "VipCpInfo" structure in "VnfInstance". Shall be present if the cpdId
	 * of this VnfExtCp has a vipCpd attribute.
	 */
	private String associatedVipCpId;

	/**
	 * IdentifierInVnf: Identifier of the "VnfVirtualLinkResourceInfo" structure
	 * that represents the internal VL or of the "ExtManagedVirtualLinkInfo"
	 * structure that represents the externally-managed internal VL which is exposed
	 * by this external CP instance. Shall be present in case this CP instance maps
	 * to an internal VL (including externally-managed internal VL).
	 */
	private String associatedVnfVirtualLinkId;

}
