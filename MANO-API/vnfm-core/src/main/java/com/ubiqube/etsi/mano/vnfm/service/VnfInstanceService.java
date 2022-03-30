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
package com.ubiqube.etsi.mano.vnfm.service;

import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;

public interface VnfInstanceService {

	List<ExtVirtualLinkDataEntity> getAllExtVirtualLinks(final VnfInstance vnfInstance);

	int getNumberOfLiveInstance(final VnfInstance vnfInstance, final VnfCompute vnfCompute);

	Deque<VnfLiveInstance> getLiveComputeInstanceOf(final VnfInstance vnfInstance, final VnfCompute vnfCompute);

	int getNumberOfLiveVl(final VnfInstance vnfInstance, final VnfVl x);

	int getNumberOfLiveExtCp(final VnfInstance vnfInstance, final VnfExtCp extCp);

	int getNumberOfLiveStorage(final VnfInstance vnfInstance, final VnfStorage x);

	// @Nonnull
	VnfInstance findBVnfInstanceyVnfPackageId(final NsdInstance nsdInstance, final UUID vnfPackageId);

	VnfInstance save(final VnfInstance vnfInstance);

	void delete(final UUID vnfInstanceId);

	// @Nonnull
	VnfLiveInstance findLiveInstanceByInstantiated(final UUID id);

	// @Nonnull
	VnfLiveInstance save(final VnfLiveInstance vli);

	Optional<VnfLiveInstance> findLiveInstanceById(final UUID vnfLiveInstance);

	void deleteLiveInstanceById(final UUID id);

	Deque<VnfLiveInstance> getLiveComputeInstanceOf(VnfBlueprint plan, VnfCompute vnfCompute);

	List<VnfLiveInstance> getLiveVirtualLinkInstanceOf(VnfInstance vnfInstance);

	List<VnfLiveInstance> getLiveComputeInstanceOf(VnfInstance vnfInstance);

	List<VnfLiveInstance> getLiveExtCpInstanceOf(VnfInstance vnfInstance);

	List<VnfLiveInstance> getLiveStorageInstanceOf(VnfInstance vnfInstance);

	List<VnfInstance> query(final String filter);

	boolean isInstantiate(UUID id);

	@Nonnull
	VnfInstance vnfLcmPatch(VnfInstance vnfInstance, String body, String ifMatch);

	<U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final String excludeDefaults, final Set<String> mandatoryFields, final Consumer<U> makeLink);

	List<VnfLiveInstance> findByResourceIdIn(List<String> objectInstanceIds);
}
