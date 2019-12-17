package com.ubiqube.etsi.mano.repository.jpa;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.BaseEntity;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.CrudRepository;
import com.ubiqube.etsi.mano.repository.NamingStrategy;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <T> T is the Json object class.
 * @param <U> Is the Database class.
 */
public abstract class AbstractJpa<T, U extends BaseEntity> extends AbstractBinaryRepository implements CrudRepository<T> {
	private final EntityManager em;
	private final org.springframework.data.repository.CrudRepository<U, UUID> repository;
	private final MapperFacade mapper;

	public AbstractJpa(final EntityManager em, final org.springframework.data.repository.CrudRepository<U, UUID> repository, final MapperFacade mapper, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(contentManager, jsonMapper, namingStrategy);
		this.em = em;
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public final T get(final String id) {
		final Optional<U> vnfPackage = repository.findById(UUID.fromString(id));
		return mapper.map(vnfPackage.orElseThrow(() -> new NotFoundException(getDbClass().getSimpleName() + " entity " + id + " not found.")), getFrontClass());
	}

	@Override
	protected abstract Class<T> getFrontClass();

	protected abstract Class<U> getDbClass();

	@Override
	public final void delete(final String id) {
		repository.deleteById(UUID.fromString(id));
	}

	@Override
	public final T save(final T entity) {
		U vnf = mapper.map(entity, getDbClass());
		mapChild(vnf);
		vnf = repository.save(vnf);
		mkdir(vnf.getId().toString());
		mapper.map(vnf, getFrontClass());
		mapper.map(vnf, entity);
		return entity;
	}

	protected abstract void mapChild(U vnf);

	@Override
	public final List<T> query(final String filter) {
		final SearchQueryer sq = new SearchQueryer(em);
		return (List<T>) sq.getCriteria(filter, getDbClass())
				.getResultStream().map(x -> mapper.map(x, getFrontClass())).collect(Collectors.toList());
	}

	/** TODO Not used any more. */
	abstract Map<String, From<?, ?>> getJoin(Root<U> root);

}
