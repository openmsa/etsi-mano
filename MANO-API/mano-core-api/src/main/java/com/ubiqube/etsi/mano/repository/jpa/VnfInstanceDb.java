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

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.jpa.VnfInstanceJpa;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;

@Service
public class VnfInstanceDb extends AbstractJpaOnly<VnfInstance> implements VnfInstancesRepository {

	private final VnfInstanceJpa repository;

	public VnfInstanceDb(final EntityManager em, final VnfInstanceJpa _repository) {
		super(em, _repository);
		repository = _repository;
	}

	@Override
	protected Class<VnfInstance> getFrontClass() {
		return VnfInstance.class;
	}

	@Override
	public boolean isInstantiate(@NotNull final UUID vnfPkgId) {
		return 0 == repository.countByVnfPkgId(vnfPkgId);
	}

}
