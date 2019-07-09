package com.ubiqube.etsi.mano.repository;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.GenericException;

/**
 * A Generic implementation of classical CRUD action around a repository.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <T>
 */
public abstract class AbstractGenericRepository<T> extends AbstractRepository<T> {
	private static final Logger LOG = LoggerFactory.getLogger(AbstractGenericRepository.class);
	private final ObjectMapper mapper;

	@Inject
	public AbstractGenericRepository(ObjectMapper _mapper, RepositoryService _repositoryService) {
		super(_repositoryService);
		mapper = _mapper;
	}

	abstract String getUriForId(String _id);

	abstract String setId(T _entity);

	@SuppressWarnings("unchecked")
	@Override
	public final T get(String _id) {
		final String uri = getUriForId(_id);
		LOG.debug("Loading ID: {}", _id);
		verify(uri);
		final RepositoryElement repositoryElement = repositoryService.getElement(uri);
		final byte[] repositoryContent = repositoryService.getRepositoryElementContent(repositoryElement);
		final String content = new String(repositoryContent);
		try {
			return (T) mapper.readValue(content, getClazz());
		} catch (final Exception e) {
			throw new GenericException(e);
		}
	}

	abstract Class<?> getClazz();

	@Override
	public final void delete(String _id) {
		final String uri = getUriForId(_id);
		verify(_id);
		final RepositoryElement repositoryElement = repositoryService.getElement(uri);
		repositoryService.deleteRepositoryElement(repositoryElement, "ncroot");
	}

	@Override
	public final T save(T _entity) {
		final String saveId = setId(_entity);

		final String uri = getUriForId(saveId);
		try {
			final String str = mapper.writeValueAsString(_entity);
			LOG.info("Creating entity @ {}", uri);
			repositoryService.addFile(uri, "SOL005", "", str, "ncroot");
		} catch (final Exception e) {
			throw new GenericException(e);
		}

		return _entity;
	}

}
