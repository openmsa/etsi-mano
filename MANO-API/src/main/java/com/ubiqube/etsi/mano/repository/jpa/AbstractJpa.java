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
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.CrudRepository;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <T> T is the Json object class.
 * @param <U> Is the Database class.
 */
public abstract class AbstractJpa<T, U> extends BinaryRepositoryImpl implements CrudRepository<T> {
	private final EntityManager em;
	private final org.springframework.data.repository.CrudRepository<U, UUID> repository;
	private final MapperFacade mapper;

	private final JpaQueryer queryer;

	public AbstractJpa(final EntityManager em, final org.springframework.data.repository.CrudRepository<U, UUID> repository, final MapperFacade mapper, final ContentManager contentManager, final ObjectMapper jsonMapper) {
		super(contentManager, jsonMapper);
		this.em = em;
		this.repository = repository;
		this.mapper = mapper;
		queryer = new JpaQueryer(em);
	}

	@Override
	public final T get(final String id) {
		final Optional<U> vnfPackage = repository.findById(UUID.fromString(id));
		return (T) mapper.map(vnfPackage.orElseThrow(() -> new NotFoundException("VNF Package " + id + " not found.")), getFrontClass());
	}

	protected abstract Class getFrontClass();

	protected abstract Class getDbClass();

	@Override
	public final void delete(final String id) {
		repository.deleteById(UUID.fromString(id));
	}

	@Override
	public final T save(final T entity) {
		final U vnf = (U) mapper.map(entity, getDbClass());
		repository.save(vnf);
		final T tmp = (T) mapper.map(vnf, getFrontClass());
		mapper.map(tmp, entity);
		return entity;
	}

	@Override
	public final List<T> query(final String filter) {
		final SearchQueryer sq = new SearchQueryer(em);
		return (List<T>) sq.getCriteria(filter, getDbClass())
				.getResultStream().map(x -> mapper.map(x, getFrontClass())).collect(Collectors.toList());
	}

	abstract Map<String, From<?, ?>> getJoin(Root<U> root);

}
