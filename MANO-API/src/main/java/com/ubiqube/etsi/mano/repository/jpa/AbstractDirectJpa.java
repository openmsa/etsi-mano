package com.ubiqube.etsi.mano.repository.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.BaseEntity;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.CrudRepositoryNg;
import com.ubiqube.etsi.mano.repository.NamingStrategy;

public abstract class AbstractDirectJpa<U extends BaseEntity> extends AbstractBinaryRepository implements CrudRepositoryNg<U> {

	private final EntityManager em;
	private final org.springframework.data.repository.CrudRepository<U, UUID> repository;

	public AbstractDirectJpa(final EntityManager em, final org.springframework.data.repository.CrudRepository<U, UUID> repository, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(contentManager, jsonMapper, namingStrategy);
		this.em = em;
		this.repository = repository;
	}

	@Deprecated
	public final U get(final String id) {
		return get(UUID.fromString(id));
	}

	@Override
	public final U get(final UUID id) {
		final Optional<U> entity = repository.findById(id);
		return entity.orElseThrow(() -> new NotFoundException(getFrontClass().getSimpleName() + " entity " + id + " not found."));
	}

	@Override
	protected abstract Class<U> getFrontClass();

	@Override
	public final void delete(final UUID id) {
		repository.deleteById(id);
		super.delete(id.toString());
	}

	@Override
	public final U save(final U entity) {
		final U res = repository.save(entity);
		mkdir(res.getId().toString());
		return res;
	}

	@Override
	public final List<U> query(final String filter) {
		final SearchQueryer sq = new SearchQueryer(em);
		return (List<U>) sq.getCriteria(filter, getFrontClass())
				.getResultStream().collect(Collectors.toList());
	}

}
