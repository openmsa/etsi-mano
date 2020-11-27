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

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.lcmgrant.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.model.VnfInstantiate;

@Service
public class VnfmNfvo implements VnfmInterface {
	private final VnfInstanceLcm lcm;

	public VnfmNfvo(final VnfInstanceLcm _lcm) {
		lcm = _lcm;
	}

	@Override
	public VnfInstance createVnfInstance(final VnfPackage vnf, final String vnfInstanceDescription, final String vnfInstanceName) {
		return lcm.post(vnf.getId().toString(), vnfInstanceName, vnfInstanceDescription);
	}

	@Override
	public VnfBlueprint vnfInstatiate(final UUID vnfInstanceId, final VnfInstantiate instantiateVnfRequest, final UUID vnfId) {
		return lcm.instantiate(vnfInstanceId, instantiateVnfRequest);
	}

	@Override
	public VnfBlueprint vnfLcmOpOccsGet(@NotNull final UUID id) {
		return lcm.vnfLcmOpOccsGet(id);
	}

	@Override
	public VnfBlueprint vnfTerminate(final UUID nsInstanceId) {
		return lcm.terminate(nsInstanceId, CancelModeTypeEnum.FORCEFUL, null);
	}

}
