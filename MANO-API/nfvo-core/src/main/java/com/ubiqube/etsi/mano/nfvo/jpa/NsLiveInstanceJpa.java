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
package com.ubiqube.etsi.mano.nfvo.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface NsLiveInstanceJpa extends CrudRepository<NsLiveInstance, UUID> {

	NsLiveInstance findByNsBlueprintId(String resourceId);

	@Query("select nli, t from NsLiveInstance nli join NsTask t on t.id = nli.nsTask where nli.nsInstance = ?1 AND ns_sap_id is not null AND t.toscaName = ?2 ORDER BY nli.audit.createdOn DESC")
	List<NsLiveInstance> findByVnfInstanceAndTaskSapIsNotNull(NsdInstance vnfInstance, String toscaName);

	@Query("select nli, t from NsLiveInstance nli join NsTask t on t.id = nli.nsTask where nli.nsInstance = ?1 AND t.nsVirtualLink is not null AND t.toscaName = ?2 ORDER BY nli.audit.createdOn DESC")
	List<NsLiveInstance> findByVnfInstanceAndTaskVlIsNotNull(NsdInstance vnfInstance, String toscaName);

	@Query("select nli, t from NsLiveInstance nli join NsTask t on t.id = nli.nsTask where nli.nsInstance = ?1 AND t.toscaName = ?2 AND t.class = ?3")
	List<NsLiveInstance> findByNsInstanceAndNsTaskToscaNameAndNsTaskClassGroupByNsTaskAlias(NsdInstance nsInstance, String toscaName, String simpleName);

	List<NsLiveInstance> findByNsInstanceId(UUID nsUuid);

	@Query("select nli, t from NsLiveInstance nli join NsTask t on t.id = nli.nsTask where nli.nsInstance = ?1 AND t.class = ?2")
	List<NsLiveInstance> findByNsdInstanceAndClass(NsdInstance instance, String simpleName);

	long countByNsInstance(NsdInstance nsInstance);

}
