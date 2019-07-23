package com.ubiqube.etsi.mano.repository.msa;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.exception.ServiceException;
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

	@Autowired
	public AbstractGenericRepository(ObjectMapper _mapper, RepositoryService _repositoryService) {
		super(_repositoryService);
		mapper = _mapper;
	}

	abstract String setId(T _entity);

	protected String makeRoot(String _id) {
		final StringBuilder sb = new StringBuilder(getRoot());
		sb.append('/').append(_id);
		final String uri = sb.toString();
		try {
			if (!repositoryService.exists(uri)) {
				repositoryService.addDirectory(uri, "", "etsi-mano", "ncroot");
			}
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
		return uri;
	}

	abstract String getRoot();

	@SuppressWarnings("unchecked")
	@Override
	public final T get(String _id) {
		final String uri = makeRoot(_id) + '/' + getFilename();
		LOG.debug("Loading ID: {}", _id);
		verify(uri);
		final RepositoryElement repositoryElement = repositoryService.getElement(uri);
		final byte[] repositoryContent = repositoryService.getRepositoryElementContent(repositoryElement);

		try {
			final String content = new String(repositoryContent, StandardCharsets.UTF_8);
			return (T) mapper.readValue(content, getClazz());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	abstract String getFilename();

	abstract Class<?> getClazz();

	@Override
	public final void delete(String _id) {
		final String uri = makeRoot(_id);
		verify(_id);
		final RepositoryElement repositoryElement = repositoryService.getElement(uri);
		repositoryService.deleteRepositoryElement(repositoryElement, "ncroot");
	}

	@Override
	public final T save(T _entity) {
		final String saveId = setId(_entity);

		final String uri = makeRoot(saveId) + '/' + getFilename();
		try {
			final String str = mapper.writeValueAsString(_entity);
			LOG.info("Creating entity @ {}", uri);
			repositoryService.addFile(uri, "SOL005", "", str, "ncroot");
		} catch (IOException | ServiceException e) {
			throw new GenericException(e);
		}

		return _entity;
	}

	public void storeObject(String _vnfPkgId, Object _object, String _filename) {
		final StringBuilder path = new StringBuilder(makeRoot(_vnfPkgId));
		path.append('/').append(_filename);
		try {
			repositoryService.addFile(path.toString(), "", "etsi-mano", mapper.writeValueAsString(mapper.writeValueAsString(_object)), "ncroot");

		} catch (final ServiceException | JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	public void storeObject(String _vnfPkgId, InputStream _stream, String _filename) {
		final StringBuilder path = new StringBuilder(makeRoot(_vnfPkgId));
		path.append('/').append(_filename);

		try {
			repositoryService.addFile(path.toString(), "", "etsi-mano", ByteStreams.toByteArray(_stream), "ncroot");
		} catch (ServiceException | IOException e) {
			throw new GenericException(e);
		}
	}

}
