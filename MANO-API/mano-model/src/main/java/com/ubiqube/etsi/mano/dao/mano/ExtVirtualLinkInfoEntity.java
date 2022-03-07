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

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

/**
 * This type represents information about an external VL. It shall comply with
 * the provisions defined in table 5.5.3.2-1.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
@Entity
@EntityListeners(AuditListener.class)
public class ExtVirtualLinkInfoEntity implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Embedded
	private Audit audit;

	/**
	 * Reference to the resource realizing this VL.
	 */
	@Embedded
	private VimResource resourceHandle;

	/**
	 * Link ports of this VL.
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Set<ExtLinkPortInfoEntity> extLinkPorts;

	/**
	 * Allows the API consumer to read the current CP configuration information for
	 * the connection of external CPs to the external virtual link.
	 *
	 * @Since 3.3.1
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Set<VnfExtCpDataEntity> currentVnfExtCpData = new LinkedHashSet<>();
}
