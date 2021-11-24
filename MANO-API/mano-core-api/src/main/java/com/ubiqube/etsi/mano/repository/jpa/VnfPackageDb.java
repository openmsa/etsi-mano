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
package com.ubiqube.etsi.mano.repository.jpa;

import java.nio.file.Path;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfPackageDb extends AbstractDirectJpa<VnfPackage> implements VnfPackageRepository {

	private final CrudRepository<VnfPackage, UUID> repository;

	private final NamingStrategy namingStrategy;

	public VnfPackageDb(final EntityManager em, final CrudRepository<VnfPackage, UUID> _repository, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, _repository, contentManager, jsonMapper, namingStrategy);
		repository = _repository;
		this.namingStrategy = namingStrategy;
	}

	@Override
	protected Class<VnfPackage> getFrontClass() {
		return VnfPackage.class;
	}

	@Override
	public Path getPathByVnfdId(final UUID fromString) {
		return namingStrategy.getRoot(VnfPackage.class, fromString, "vnfd");
	}

}
