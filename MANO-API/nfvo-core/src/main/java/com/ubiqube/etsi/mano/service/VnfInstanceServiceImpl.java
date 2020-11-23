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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.ExtVirtualLinkDataEntityJpa;
import com.ubiqube.etsi.mano.jpa.VnfInstanceJpa;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;

@Service
public class VnfInstanceServiceImpl implements VnfInstanceService {
	private static final Logger LOG = LoggerFactory.getLogger(VnfInstanceServiceImpl.class);

	private final ExtVirtualLinkDataEntityJpa extVirtualLinkDataEntityJpa;

	private final VnfInstanceJpa vnfInstanceJpa;

	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public VnfInstanceServiceImpl(final ExtVirtualLinkDataEntityJpa _extVirtualLinkDataEntityJpa, final VnfInstanceJpa _vnfInstanceJpa, final VnfLiveInstanceJpa _vnfLiveInstance) {
		extVirtualLinkDataEntityJpa = _extVirtualLinkDataEntityJpa;
		vnfInstanceJpa = _vnfInstanceJpa;
		vnfLiveInstanceJpa = _vnfLiveInstance;
	}

	@Override
	public List<ExtVirtualLinkDataEntity> getAllExtVirtualLinks(final VnfInstance vnfInstance) {
		return extVirtualLinkDataEntityJpa.findByVnfInstance(vnfInstance);
	}

	@Override
	public int getNumberOfLiveInstance(final VnfInstance vnfInstance, final VnfCompute vnfCompute) {
		return vnfLiveInstanceJpa.countByVnfInstanceAndTaskToscaName(vnfInstance, vnfCompute.getToscaName());
	}

	@Override
	public Deque<VnfLiveInstance> getLiveComputeInstanceOf(final VnfInstance vnfInstance, final VnfCompute vnfCompute) {
		return vnfLiveInstanceJpa.findByIdAndVnfInstance(vnfCompute.getId(), vnfInstance).stream().collect(Collectors.toCollection(ArrayDeque::new));
	}

	@Override
	public int getNumberOfLiveVl(final VnfInstance vnfInstance, final VnfVl x) {
		return vnfLiveInstanceJpa.countByVnfInstanceAndTaskToscaName(vnfInstance, x.getToscaName());
	}

	@Override
	public int getNumberOfLiveExtCp(final VnfInstance vnfInstance, final VnfExtCp extCp) {
		return vnfLiveInstanceJpa.countByVnfInstanceAndTaskToscaName(vnfInstance, extCp.getToscaName());
	}

	@Override
	public int getNumberOfLiveStorage(final VnfInstance vnfInstance, final VnfStorage x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public VnfInstance findBVnfInstanceyVnfPackageId(final NsdInstance nsdInstance, final UUID vnfPackageId) {
		return vnfInstanceJpa.findByVnfPkg_IdAndNsInstance_Id(vnfPackageId, nsdInstance.getId()).orElseThrow(() -> new NotFoundException("Could not find vnf=" + vnfPackageId + " nsInstance=" + nsdInstance.getId()));
	}

	@Override
	public VnfInstance findById(final UUID id) {
		final VnfInstance inst = vnfInstanceJpa.findById(id).orElseThrow(() -> new NotFoundException("Could not find " + id));
		inst.setInstantiationState(isLive(id));
		return inst;
	}

	private InstantiationState isLive(final UUID id) {
		return vnfLiveInstanceJpa.findByVnfInstanceId(id).isEmpty() ? InstantiationState.NOT_INSTANTIATED : InstantiationState.INSTANTIATED;
	}

	@Override
	public VnfInstance save(final VnfInstance vnfInstance) {
		return vnfInstanceJpa.save(vnfInstance);
	}

	@Override
	@Transactional
	public void delete(final UUID vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstanceJpa.findById(vnfInstanceId).orElseThrow(() -> new NotFoundException("Vnf Instance " + vnfInstanceId + " not found."));

		vnfInstanceJpa.deleteById(vnfInstanceId);
	}

	@Override
	public VnfLiveInstance findLiveInstanceByInstantiated(final UUID id) {
		return vnfLiveInstanceJpa.findByTaskId(id);
	}

	@Override
	public VnfLiveInstance save(final VnfLiveInstance vli) {
		return vnfLiveInstanceJpa.save(vli);
	}

	@Override
	public Optional<VnfLiveInstance> findLiveInstanceById(final UUID removedInstantiated) {
		return vnfLiveInstanceJpa.findById(removedInstantiated);
	}

	@Override
	public void deleteLiveInstanceById(final UUID id) {
		vnfLiveInstanceJpa.deleteById(id);
	}

	@Override
	public Deque<VnfLiveInstance> getLiveComputeInstanceOf(final Blueprint plan, final VnfCompute vnfCompute) {
		return vnfLiveInstanceJpa.findByTaskVnfInstanceAndVnfCompute(plan.getVnfInstance(), vnfCompute)
				.stream()
				.collect(Collectors.toCollection(ArrayDeque::new));
	}

	@Override
	public List<VnfLiveInstance> getLiveVirtualLinkInstanceOf(final VnfInstance vnfInstance) {
		return vnfLiveInstanceJpa.findByVnfInstanceAndTaskVnfVlNotNull(vnfInstance);
	}

	@Override
	public List<VnfLiveInstance> getLiveComputeInstanceOf(final VnfInstance vnfInstance) {
		return vnfLiveInstanceJpa.findByVnfInstanceAndTaskVnfComputeNotNull(vnfInstance);
	}

	@Override
	public List<VnfLiveInstance> getLiveExtCpInstanceOf(final VnfInstance vnfInstanceDb) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public List<VnfLiveInstance> getLiveStorageInstanceOf(final VnfInstance vnfInstance) {
		return vnfLiveInstanceJpa.findByVnfInstanceAndTaskVnfStorageIsNotNull(vnfInstance);
	}

}
