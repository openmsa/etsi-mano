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
package com.ubiqube.etsi.mec.repositories;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mec.pkg.AppPkg;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.jpa.AbstractDirectJpa;

@Service
public class AppPackageRepository extends AbstractDirectJpa<AppPkg> {

	public AppPackageRepository(final EntityManager em, final CrudRepository<AppPkg, UUID> repository, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, repository, contentManager, jsonMapper, namingStrategy);
	}

	@Override
	protected Class<AppPkg> getFrontClass() {
		return AppPkg.class;
	}

}
