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

import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;

public interface NsInstantiatedBaseJpa<T extends NsInstantiatedBase> extends CrudRepository<T, UUID> {
	@Query("SELECT nib from #{#entityName} nib\n" +
			"	left outer join NsLiveInstance nli  on nib.id = nli.nsInstantiatedBase \n" +
			"	where nli.nsInstance =?1")
	List<T> findByLiveInstanceOfVnfInstance(NsdInstance nsdInstance);
}
