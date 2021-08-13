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

import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.VirtualStorageResourceInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfMonitoringParameter;
import com.ubiqube.etsi.mano.dao.mano.VnfScaleInfo;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
public class VnfInstanceInstantiatedVnfInfoDto {
	private String flavourId;

	private OperationalStateType vnfState;

	private List<VnfScaleInfo> scaleStatus;

	private List<VnfScaleInfo> maxScaleLevels;

	// private List<VnfExtCpInfo> extCpInfo = new ArrayList<>();

	// private List<ExtVirtualLinkInfo> extVirtualLinkInfo ;

	// private List<ExtManagedVirtualLinkInfo> extManagedVirtualLinkInfo;

	private List<VnfMonitoringParameter> monitoringParameters;

	private String localizationLanguage;

	private List<VnfcResourceInfoDto> vnfcResourceInfo;

	// private List<VnfVirtualLinkResourceInfo> virtualLinkResourceInfo ;

	private List<VirtualStorageResourceInfo> virtualStorageResourceInfo;

}
