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

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;

public interface NsdPackageJpa extends CrudRepository<NsdPackage, UUID> {
	Optional<NsdPackage> findByNsdInvariantId(String nsdInvariantId);

	@Query("select child \n" +
			" 	from NsdPackageNsdPackage nsdpackage0_\n" +
			" 	left outer join NsdPackage child on nsdpackage0_.child = child \n" +
			" 	where parent_id = ?1 ")
	Set<NsdPackage> findByNestedNsdInfoIds_Parent(NsdPackage nsdPackage);

	Optional<NsdPackage> findByNsdId(String nsdId);
}
