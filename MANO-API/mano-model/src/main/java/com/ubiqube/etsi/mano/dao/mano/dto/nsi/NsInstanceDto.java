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

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
public class NsInstanceDto {
	private String id = null;

	private String nsInstanceName = null;

	private String nsInstanceDescription = null;

	private String nsdId = null;

	private String nsdInfoId = null;

	private String flavourId = null;

	private List<VnfInstanceDto> vnfInstance = new ArrayList<>();

	// private final List<PnfInfo> pnfInfo = null;

	private List<NsVirtualLinkInfoDto> virtualLinkInfo = new ArrayList<>();

	// private final List<VnffgInfo> vnffgInfo = null;

	// private final List<SapInfo> sapInfo = null;

	// private final List<String> nestedNsInstanceId = null;

	// private final List<String> vnfSnapshotInfoIds = null;

}
