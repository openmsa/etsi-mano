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
package com.ubiqube.etsi.mano.dao.mano.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.CpProtocolDataEntity;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
public class VnfInstantiatedCompute extends VnfInstantiatedBase {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private UUID id = null;

	private List<String> storageResourceIds = null;

	private Set<CpProtocolDataEntity> vnfcCpInfo = null;

	private final Map<String, String> metadata = new HashMap<>();

	private String flavorId;

	private String imageId;

	private Set<String> affectedVnfcCpIds = null;

	private Set<String> addedStorageResourceIds = null;

	private Set<String> removedStorageResourceIds = null;

}
