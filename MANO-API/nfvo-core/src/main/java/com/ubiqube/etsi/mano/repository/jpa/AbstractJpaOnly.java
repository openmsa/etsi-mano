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
