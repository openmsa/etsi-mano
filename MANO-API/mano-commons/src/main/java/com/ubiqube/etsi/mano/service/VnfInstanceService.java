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

import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedDnsZone;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;

public interface VnfInstanceService {

	List<ExtVirtualLinkDataEntity> getAllExtVirtualLinks(final VnfInstance vnfInstance);

	int getNumberOfLiveInstance(final VnfInstance vnfInstance, final VnfCompute vnfCompute);

	Deque<VnfLiveInstance> getLiveComputeInstanceOf(final VnfInstance vnfInstance, final VnfCompute vnfCompute);

	int getNumberOfLiveVl(final VnfInstance vnfInstance, final VnfVl x);

	int getNumberOfLiveExtCp(final VnfInstance vnfInstance, final VnfExtCp extCp);

	VnfInstantiatedCompute save(final VnfInstantiatedCompute vnfInstantiedCompute);

	VnfInstantiatedVirtualLink save(final VnfInstantiatedVirtualLink vnfInstantiedVirtualLink);

	VnfInstantiatedExtCp save(final VnfInstantiatedExtCp vnfInstantiedExtCp);

	VnfInstantiatedStorage save(final VnfInstantiatedStorage vs);

	int getNumberOfLiveStorage(final VnfInstance vnfInstance, final VnfStorage x);

	List<VnfInstantiatedCompute> getLiveComputeInstanceOf(final VnfInstance vnfInstance);

	List<VnfInstantiatedExtCp> getLiveExtCpInstanceOf(final VnfInstance vnfInstance);

	List<VnfInstantiatedStorage> getLiveStorageInstanceOf(final VnfInstance vnfInstance);

	List<VnfInstantiatedVirtualLink> getLiveVirtualLinkInstanceOf(final VnfInstance vnfInstance);

	List<VnfInstantiatedDnsZone> getLiveDnsZoneInstanceOf(final VnfInstance vnfInstance);

	VnfInstance findBVnfInstanceyVnfPackageId(final NsdInstance nsdInstance, final UUID vnfPackageId);

	VnfInstance findById(final UUID id);

	VnfInstance save(final VnfInstance vnfInstance);

	void delete(final UUID vnfInstanceId);

	VnfLiveInstance findLiveInstanceByInstantiated(final UUID id);

	VnfLiveInstance save(final VnfLiveInstance vli);

	Optional<VnfLiveInstance> findLiveInstanceById(final UUID removedInstantiated);

	void deleteLiveInstanceById(final UUID id);

	<T extends VnfInstantiatedBase> T save(final T y);

	Deque<VnfLiveInstance> getLiveComputeInstanceOf(Blueprint plan, VnfCompute x);
}
