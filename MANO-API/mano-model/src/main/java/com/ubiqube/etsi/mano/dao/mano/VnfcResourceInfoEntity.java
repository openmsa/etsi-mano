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
import java.util.List;
import java.util.Map;

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
	private String id = null;

	private String vduId = null;

	// 3.3.1
	private String vnfdId;

	@Embedded
	private VimResource computeResource = null;

	// 2.7.1
	private String zoneId;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> storageResourceIds = null;

	private String reservationId = null;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<VnfcResourceInfoVnfcCpInfoEntity> vnfcCpInfo = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> metadata = null;

}
