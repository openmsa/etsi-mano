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
package com.ubiqube.etsi.mano.dao.mano.dto.nsi;

import java.util.List;
import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.alarm.ResourceHandle;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
public class VnfcResourceInfoDto {
	private String id;

	private String vnfdId;

	private String vduId;

	private ResourceHandle computeResource;

	private List<String> storageResourceIds;

	private String reservationId;

	// private List<VnfcResourceInfoVnfcCpInfo> vnfcCpInfo ;

	private Map<String, String> metadata;

}
