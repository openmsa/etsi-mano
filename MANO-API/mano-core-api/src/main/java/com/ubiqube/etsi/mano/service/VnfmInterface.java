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

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.model.VnfInstantiate;

public interface VnfmInterface {

	VnfInstance createVnfInstance(Servers servers, String vnfdId, String vnfInstanceDescription, String vnfInstanceName);

	VnfBlueprint vnfInstatiate(Servers servers, @Nonnull String vnfInstanceId, VnfInstantiate instantiateVnfRequest);

	VnfBlueprint vnfLcmOpOccsGet(Servers servers, @NotNull UUID id);

	VnfBlueprint vnfTerminate(Servers servers, @Nonnull String nsInstanceId);

	VnfInstance getVnfInstance(Servers servers, String vnfInstance);

	void delete(Servers servers, String vnfInstance);

}
