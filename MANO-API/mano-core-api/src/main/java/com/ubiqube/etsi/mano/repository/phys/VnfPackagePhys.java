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
package com.ubiqube.etsi.mano.repository.phys;

import java.nio.file.Path;
import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.repository.Low;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

@Profile("phys")
@Service
public class VnfPackagePhys extends AbstractGenericBinaryRepository<VnfPackage> implements VnfPackageRepository {

	public VnfPackagePhys(final ObjectMapper objectMapper, final JsonFilter jsonFilter, final Low low, final NamingStrategy namingStrategy) {
		super(objectMapper, jsonFilter, low, namingStrategy);
	}

	@Override
	protected UUID setId(final VnfPackage entity) {
		final UUID id = entity.getId();
		if (null == id) {
			entity.setId(UUID.randomUUID());
		}

		return entity.getId();
	}

	@Override
	protected Class<VnfPackage> getClazz() {
		return VnfPackage.class;
	}

	@Override
	protected String getFilename() {
		return "vnfPkgInfo.json";
	}

	@Override
	public Path getPathByVnfdId(final UUID fromString) {
		// TODO Auto-generated method stub
		return null;
	}

}
