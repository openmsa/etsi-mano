package com.ubiqube.etsi.mano.repository.jpa;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.grammar.AstBuilder;
import com.ubiqube.etsi.mano.repository.BinaryRepository;
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
public abstract class AbstractJpa<T, U> implements CrudRepository<T>, BinaryRepository {
	private final EntityManager em;
	private final org.springframework.data.repository.CrudRepository<U, UUID> repository;
	private final MapperFacade mapper;
	private final ContentManager contentManager;
	private final ObjectMapper jsonMapper;
	private final JpaQueryer queryer;

	public AbstractJpa(final EntityManager em, final org.springframework.data.repository.CrudRepository<U, UUID> repository, final MapperFacade mapper, final ContentManager contentManager, final ObjectMapper jsonMapper) {
		super();
		this.em = em;
		this.repository = repository;
		this.mapper = mapper;
		this.contentManager = contentManager;
		this.jsonMapper = jsonMapper;
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
		final AstBuilder astBuilder = new AstBuilder(filter);
		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<U> q = builder.createQuery(getDbClass());
		final Root<U> root = q.from(getDbClass());
		final Map<String, From<?, ?>> joins = getJoin(root);
		final Predicate p = queryer.getCriteria(astBuilder.getNodes(), VnfPackage.class, joins);
		if (null == p) {
			q.select(root);
		} else {
			q.select(root).where(p);
		}
		final List<U> res = em.createQuery(q).getResultList();
		return (List<T>) res.stream().map(x -> mapper.map(x, getFrontClass())).collect(Collectors.toList());
	}

	abstract Map<String, From<?, ?>> getJoin(Root<U> root);

	@Override
	public final void storeObject(final String _id, final String _filename, final Object _object) {
		try {
			final String str = jsonMapper.writeValueAsString(_object);
			storeBinary(_id, _filename, new ByteArrayInputStream(str.getBytes()));
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public final void storeBinary(final String _id, final String _filename, final InputStream _stream) {
		contentManager.store(_id, _filename, _stream);
	}

	@Override
	public final byte[] getBinary(final String _id, final String _filename) {
		return getBinary(_id, _filename, 0, null);
	}

	@Override
	public final byte[] getBinary(final String _id, final String _filename, final int min, final Integer max) {
		final InputStream os = contentManager.load(_id, _filename, 0, null);
		try {
			return IOUtils.toByteArray(os);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public final <T, U extends Class> T loadObject(@NotNull final String _id, final String _filename, final U t) {
		final byte[] content = getBinary(_id, _filename, 0, null);
		try {
			return (T) jsonMapper.readValue(content, t);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

}
