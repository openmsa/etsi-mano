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

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.repository.CrudRepositoryNg;

public abstract class AbstractJpaOnly<U> implements CrudRepositoryNg<U> {
	private final EntityManager em;
	private final org.springframework.data.repository.CrudRepository<U, UUID> repository;

	public AbstractJpaOnly(final EntityManager em, final org.springframework.data.repository.CrudRepository<U, UUID> repository) {
		this.em = em;
		this.repository = repository;
	}

	@Override
	public U get(final UUID id) {
		final Optional<U> entity = repository.findById(id);
		return entity.orElseThrow(() -> new NotFoundException(getFrontClass().getSimpleName() + " entity " + id + " not found."));
	}

	protected abstract Class<U> getFrontClass();

	@Override
	public final void delete(final UUID id) {
		repository.deleteById(id);
	}

	@Override
	public final U save(final U entity) {
		return repository.save(entity);
	}

	@Override
	public @NotNull List<U> query(final String filter) {
		final SearchQueryer sq = new SearchQueryer(em);
		return (List<U>) sq.getCriteria(filter, getFrontClass())
				.getResultStream().collect(Collectors.toList());
	}

}
