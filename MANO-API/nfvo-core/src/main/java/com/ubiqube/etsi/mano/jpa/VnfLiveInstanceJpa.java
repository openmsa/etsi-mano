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

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;

public interface VnfLiveInstanceJpa extends CrudRepository<VnfLiveInstance, UUID> {

	int countByVnfInstanceAndTaskToscaName(VnfInstance vnfInstance, String id);

	List<VnfLiveInstance> findByIdAndVnfInstance(UUID id, VnfInstance vnfInstance);

	VnfLiveInstance findByTaskId(UUID id);

	@Query("select vli, t from VnfLiveInstance vli join Task t on t.id = vli.task where vli.vnfInstance = ?1 AND vnf_vl_id is not null")
	List<VnfLiveInstance> findByVnfInstanceAndTaskVnfVlNotNull(VnfInstance vnfLiveInstance);

	@Query("select vli, t from VnfLiveInstance vli join Task t on t.id = vli.task where vli.vnfInstance = ?1 AND vnf_compute_id is not null")
	List<VnfLiveInstance> findByVnfInstanceAndTaskVnfComputeNotNull(VnfInstance vnfLiveInstance);

	@Query("select vli, t from VnfLiveInstance vli join Task t on t.id = vli.task where vli.vnfInstance = ?1 AND vnf_storage_id is not null")
	List<VnfLiveInstance> findByVnfInstanceAndTaskVnfStorageIsNotNull(VnfInstance vnfInstance);

	@Query("select vli, t from VnfLiveInstance vli join Task t on t.id = vli.task where vli.vnfInstance = ?1 AND vnf_compute_id is not null")
	List<VnfLiveInstance> findByTaskVnfInstanceAndVnfCompute(VnfInstance vnfInstance, VnfCompute vnfCompute);
}
