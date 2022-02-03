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

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.BaseEntity;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.grammar.GrammarParser;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.CrudRepositoryNg;
import com.ubiqube.etsi.mano.repository.NamingStrategy;

public abstract class AbstractDirectJpa<U extends BaseEntity> extends AbstractBinaryRepository implements CrudRepositoryNg<U> {

	private final EntityManager em;
	private final org.springframework.data.repository.CrudRepository<U, UUID> repository;
	private final GrammarParser grammarParser;

	protected AbstractDirectJpa(final EntityManager em, final org.springframework.data.repository.CrudRepository<U, UUID> repository,
			final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy, final GrammarParser grammarParser) {
		super(contentManager, jsonMapper, namingStrategy);
		this.em = em;
		this.repository = repository;
		this.grammarParser = grammarParser;
	}

	@Override
	@NotNull
	public final U get(@NotNull final UUID id) {
		final Optional<U> entity = repository.findById(id);
		return entity.orElseThrow(() -> new NotFoundException(getFrontClass().getSimpleName() + " entity " + id + " not found."));
	}

	@Override
	protected abstract Class<U> getFrontClass();

	@Override
	public final void delete(@NotNull final UUID id) {
		repository.deleteById(id);
		super.delete(id);
	}

	@Override
	@NotNull
	public final U save(@NotNull final U entity) {
		final U res = repository.save(entity);
		mkdir(res.getId());
		return res;
	}

	@Override
	@NotNull
	public final List<U> query(@Nullable final String filter) {
		final SearchQueryer sq = new SearchQueryer(em, grammarParser);
		return sq.getCriteria(filter, getFrontClass());
	}

}
