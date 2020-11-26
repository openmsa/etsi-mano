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
package com.ubiqube.etsi.mano.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.lcmgrant.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;

@ConditionalOnMissingBean(VnfmService.class)
@Service
public class NfvoVnfInstanceLcm implements VnfInstanceLcm {

	@Override
	public List<VnfInstance> get(final Map<String, String> queryParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blueprint get(final UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfInstance post(final String vnfdId, final String vnfInstanceName, final String vnfInstanceDescription) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(final UUID vnfInstanceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Blueprint instantiate(final UUID vnfInstanceId, final VnfInstantiate instantiateVnfRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blueprint terminate(final UUID vnfInstanceId, final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blueprint scaleToLevel(final UUID uuid, final VnfScaleToLevelRequest scaleVnfToLevelRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blueprint scale(final UUID uuid, final VnfScaleRequest scaleVnfRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blueprint operate(final UUID uuid, final VnfOperateRequest operateVnfRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
