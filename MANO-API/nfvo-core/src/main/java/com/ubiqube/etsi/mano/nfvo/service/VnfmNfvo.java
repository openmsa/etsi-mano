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
package com.ubiqube.etsi.mano.nfvo.service;

import static com.ubiqube.etsi.mano.Constants.getSafeUUID;

import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.vnflcm.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.jpa.GrantsResponseJpa;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.service.VnfmInterface;

@Service
public class VnfmNfvo implements VnfmInterface {
	private final VnfInstanceLcm lcm;
	private final GrantsResponseJpa grantsResponseJpa;

	public VnfmNfvo(final VnfInstanceLcm lcm, final GrantsResponseJpa grantsResponseJpa) {
		this.lcm = lcm;
		this.grantsResponseJpa = grantsResponseJpa;
	}

	@Override
	public VnfInstance createVnfInstance(final Servers servers, final String vnfdId, final String vnfInstanceDescription, final String vnfInstanceName) {
		return lcm.post(servers, vnfdId, vnfInstanceName, vnfInstanceDescription);
	}

	@Override
	public VnfBlueprint vnfInstatiate(final Servers servers, final String vnfInstanceId, final VnfInstantiate instantiateVnfRequest) {
		return lcm.instantiate(servers, UUID.fromString(vnfInstanceId), instantiateVnfRequest);
	}

	@Override
	public VnfBlueprint vnfLcmOpOccsGet(final Servers servers, @NotNull final UUID id) {
		return lcm.vnfLcmOpOccsGet(servers, id);
	}

	@Override
	public VnfBlueprint vnfTerminate(final Servers servers, final String nsInstanceId) {
		return lcm.terminate(servers, UUID.fromString(nsInstanceId), CancelModeTypeEnum.FORCEFUL, null);
	}

	@Override
	public VnfInstance getVnfInstance(final Servers servers, final String vnfInstance) {
		return lcm.findById(servers, vnfInstance);
	}

	@Transactional
	@Override
	public void delete(final Servers servers, final String vnfInstance) {
		lcm.delete(servers, getSafeUUID(vnfInstance));
		grantsResponseJpa.deleteByVnfInstanceId(vnfInstance);
	}

}
