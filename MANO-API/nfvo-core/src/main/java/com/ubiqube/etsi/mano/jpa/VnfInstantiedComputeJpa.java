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
package com.ubiqube.etsi.mano.jpa;

import java.util.List;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;

public interface VnfInstantiedComputeJpa extends VnfInstantiedBaseJpa<VnfInstantiatedCompute> {

	int countByVnfLcmOpOccsVnfInstanceAndVduId(VnfInstance vnfInstance, UUID vduId);

	List<VnfInstantiatedCompute> findByVnfLcmOpOccsVnfInstanceAndVduId(VnfInstance vnfInstance, UUID id);

	List<VnfInstantiatedCompute> findAllByVnfLcmOpOccs_VnfInstance(VnfInstance vnfInstance);

}
