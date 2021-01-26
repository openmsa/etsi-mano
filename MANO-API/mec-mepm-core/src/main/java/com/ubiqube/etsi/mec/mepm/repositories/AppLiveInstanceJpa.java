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
package com.ubiqube.etsi.mec.mepm.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ubiqube.etsi.mano.dao.mec.lcm.AppInstance;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppLiveInstance;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Repository
public interface AppLiveInstanceJpa extends CrudRepository<AppLiveInstance, UUID> {

	@Query("select ali, t from AppLiveInstance ali join AppTask t on t.id = ali.task where ali.appInstance = ?1 AND t.class = ?2")
	List<AppLiveInstance> findByAppInstanceAndClass(AppInstance vnfInstance, String clazz);

}
