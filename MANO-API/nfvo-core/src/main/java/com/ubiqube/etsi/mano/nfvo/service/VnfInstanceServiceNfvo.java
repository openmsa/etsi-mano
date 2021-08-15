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

import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.VnfInstanceJpa;
import com.ubiqube.etsi.mano.service.VnfInstanceService;

@Service
@ConditionalOnMissingBean(VnfInstanceService.class)
public class VnfInstanceServiceNfvo implements VnfInstanceService {

	private final VnfInstanceJpa vnfInstanceJpa;

	public VnfInstanceServiceNfvo(final VnfInstanceJpa _vnfInstanceJpa) {
		vnfInstanceJpa = _vnfInstanceJpa;
	}

	@Override
	public List<ExtVirtualLinkDataEntity> getAllExtVirtualLinks(final VnfInstance vnfInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfLiveInstance(final VnfInstance vnfInstance, final VnfCompute vnfCompute) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Deque<VnfLiveInstance> getLiveComputeInstanceOf(final VnfInstance vnfInstance, final VnfCompute vnfCompute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfLiveVl(final VnfInstance vnfInstance, final VnfVl x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfLiveExtCp(final VnfInstance vnfInstance, final VnfExtCp extCp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfLiveStorage(final VnfInstance vnfInstance, final VnfStorage x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public VnfInstance findBVnfInstanceyVnfPackageId(final NsdInstance nsdInstance, final UUID vnfPackageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfInstance findById(final UUID id) {
		return vnfInstanceJpa.findById(id).orElseThrow(() -> new NotFoundException("Could not find " + id));
	}

	@Override
	public VnfInstance save(final VnfInstance vnfInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(final UUID vnfInstanceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public VnfLiveInstance findLiveInstanceByInstantiated(final UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfLiveInstance save(final VnfLiveInstance vli) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<VnfLiveInstance> findLiveInstanceById(final UUID vnfLiveInstance) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void deleteLiveInstanceById(final UUID id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Deque<VnfLiveInstance> getLiveComputeInstanceOf(final VnfBlueprint plan, final VnfCompute vnfCompute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VnfLiveInstance> getLiveVirtualLinkInstanceOf(final VnfInstance vnfInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VnfLiveInstance> getLiveComputeInstanceOf(final VnfInstance vnfInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VnfLiveInstance> getLiveExtCpInstanceOf(final VnfInstance vnfInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VnfLiveInstance> getLiveStorageInstanceOf(final VnfInstance vnfInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VnfInstance> query(final String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInstantiate(final UUID id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VnfInstance vnfLcmPatch(final VnfInstance vnfInstance, final String body, final String ifMatch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final String excludeDefaults, final Set<String> mandatoryFields, final Consumer<U> makeLink) {
		// TODO Auto-generated method stub
		return null;
	}

}
