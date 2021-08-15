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
package com.ubiqube.etsi.mano.service.rest;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.controller.vnflcm.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.vnfi.ChangeExtVnfConnRequest;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;
import com.ubiqube.etsi.mano.service.VersionManager;
import com.ubiqube.etsi.mano.service.VnfmService;

/**
 * This class Is preparing querying a VNFM using HHTP.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@ConditionalOnMissingBean(VnfmService.class)
@Service
public class NfvoVnfInstanceLcm implements VnfInstanceLcm {
	private final VersionManager versionService;

	public NfvoVnfInstanceLcm(final VersionManager _versionService) {
		versionService = _versionService;
	}

	@Override
	public List<VnfInstance> get(final MultiValueMap<String, String> requestParams) {
		return versionService.vnfInstanceGet(requestParams);
	}

	@Override
	public VnfInstance post(final String vnfdId, final String vnfInstanceName, final String vnfInstanceDescription) {
		return versionService.vnfInstancePost(vnfdId, vnfInstanceName, vnfInstanceDescription);
	}

	@Override
	public void delete(final UUID vnfInstanceId) {
		versionService.vnfInstanceDelete(vnfInstanceId);
	}

	@Override
	public VnfBlueprint instantiate(final UUID vnfInstanceId, final VnfInstantiate instantiateVnfRequest) {
		return versionService.vnfInstanceInstantiate(vnfInstanceId, instantiateVnfRequest);
	}

	@Override
	public VnfBlueprint terminate(final UUID vnfInstanceId, final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout) {
		return versionService.vnfInstanceTerminate(vnfInstanceId, terminationType, gracefulTerminationTimeout);
	}

	@Override
	public VnfBlueprint scaleToLevel(final UUID uuid, final VnfScaleToLevelRequest scaleVnfToLevelRequest) {
		return versionService.vnfInstanceScaleToLevel(uuid, scaleVnfToLevelRequest);
	}

	@Override
	public VnfBlueprint scale(final UUID uuid, final VnfScaleRequest scaleVnfRequest) {
		return versionService.vnfInstanceScale(uuid, scaleVnfRequest);
	}

	@Override
	public VnfBlueprint operate(final UUID uuid, final VnfOperateRequest operateVnfRequest) {
		return versionService.vnfInstanceOperate(uuid, operateVnfRequest);
	}

	@Override
	public VnfBlueprint vnfLcmOpOccsGet(@NotNull final UUID id) {
		return versionService.vnfLcmOpOccsGet(id);
	}

	@Override
	public VnfBlueprint changeExtConn(@NotNull final UUID vnfInstanceId, final ChangeExtVnfConnRequest cevcr) {
		// TODO Auto-generated method stub
		return null;
	}

}
