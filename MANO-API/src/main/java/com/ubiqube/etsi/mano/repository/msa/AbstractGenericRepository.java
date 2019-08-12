package com.ubiqube.etsi.mano.repository.msa;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;
import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.grammar.AstBuilder;
import com.ubiqube.etsi.mano.grammar.JsonFilter;

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
	private final JsonFilter jsonFilter;

	public AbstractGenericRepository(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter) {
		super(_repositoryService);
		mapper = _mapper;
		jsonFilter = _jsonFilter;
	}

	abstract String setId(T _entity);

	protected String makeRoot(final String _id) {
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
	public final T get(final String _id) {
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
	public final void delete(final String _id) {
		final String uri = makeRoot(_id);
		verify(uri);
		final RepositoryElement repositoryElement = repositoryService.getElement(uri);
		repositoryService.deleteRepositoryElement(repositoryElement, "ncroot");
	}

	@Override
	public final T save(final T _entity) {
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

	@Override
	public void storeObject(final String _id, final Object _object, final String _filename) {
		final StringBuilder path = new StringBuilder(makeRoot(_id));
		path.append('/').append(_filename);
		try {
			repositoryService.addFile(path.toString(), "", "etsi-mano", mapper.writeValueAsString(mapper.writeValueAsString(_object)), "ncroot");

		} catch (final ServiceException | JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public void storeBinary(final String _id, final InputStream _stream, final String _filename) {
		final StringBuilder path = new StringBuilder(makeRoot(_id));
		path.append('/').append(_filename);

		try {
			repositoryService.addFile(path.toString(), "", "etsi-mano", ByteStreams.toByteArray(_stream), "ncroot");
		} catch (ServiceException | IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public List<T> query(final String filter) {
		List<String> listFilesInFolder;
		try {
			listFilesInFolder = repositoryService.doSearch(getRoot(), getFilename());
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
		final AstBuilder astBuilder = new AstBuilder(filter);
		final ArrayList<T> ret = new ArrayList<>();
		for (final String entry : listFilesInFolder) {
			final String path = entry.substring((getRoot() + '/').length());
			final File file = new File(path);
			LOG.info("Retreiving: {}", file.getParent());
			final T repoObject = get(file.getParent());
			if (jsonFilter.apply(repoObject, astBuilder)) {
				ret.add(repoObject);
			}
		}
		return ret;
	}

	@Override
	public byte[] getBinary(final String _id, final String _filename) {
		final String uri = makeRoot(_id) + '/' + _filename;
		final RepositoryElement repositoryElement = repositoryService.getElement(uri);
		return repositoryService.getRepositoryElementContent(repositoryElement);
	}

	@Override
	public byte[] getBinary(final String _id, final String _filename, final int min, final Integer max) {
		final String uri = makeRoot(_id) + '/' + _filename;
		final byte[] repositoryContent = getBinary(_id, _filename);
		return Arrays.copyOfRange(repositoryContent, min, max == null ? repositoryContent.length - min : max);
	}

}
