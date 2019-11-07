package com.ubiqube.etsi.mano.repository.phys;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.grammar.AstBuilder;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.repository.BinaryRepository;
import com.ubiqube.etsi.mano.repository.CrudRepository;
import com.ubiqube.etsi.mano.repository.Low;
import com.ubiqube.etsi.mano.repository.NamingStrategy;

/**
 *
 * @author Olivier
 *
 */
public abstract class GenaricBinaryRepository<T> implements CrudRepository<T>, BinaryRepository {

	private final ObjectMapper objectMapper;
	private final JsonFilter jsonFilter;
	private final Low lowDriver;
	private final NamingStrategy namingStrategy;

	public GenaricBinaryRepository(final ObjectMapper _objectMapper, final JsonFilter _jsonFilter, final Low _lowDriver, final NamingStrategy _namingStrategy) {
		objectMapper = _objectMapper;
		jsonFilter = _jsonFilter;
		lowDriver = _lowDriver;
		namingStrategy = _namingStrategy;
		init();
	}

	private void init() {
		Path root = namingStrategy.getRoot();
		lowDriver.mkdir(root.toString());
		root = namingStrategy.getRoot(getClazz());
		lowDriver.mkdir(root.toString());
	}

	@Override
	public final List<T> query(final String filter) {
		final Path dir = namingStrategy.getRoot(getClazz());
		final List<String> listFilesInFolder = lowDriver.find(dir.toString(), getFilename());
		final AstBuilder astBuilder = new AstBuilder(filter);
		return listFilesInFolder.stream()
				.map(this::rawGetObject)
				.filter(x -> jsonFilter.apply(x, astBuilder))
				.collect(Collectors.toList());
	}

	@Override
	public final T get(final String _id) {
		Path path = namingStrategy.getRoot(getClazz(), _id);
		verifyPath(path);
		path = namingStrategy.getRoot(getClazz(), _id, getFilename());
		try {
			final byte[] content = lowDriver.get(path.toString());
			return objectMapper.readValue(content, getClazz());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public final void delete(final String _id) {
		final Path path = namingStrategy.getRoot(getClazz(), _id);
		verifyPath(path);
		lowDriver.delete(path.toString());
	}

	@Override
	public final T save(final T entity) {
		final String id = setId(entity);
		final Path path = namingStrategy.getRoot(getClazz(), id);
		lowDriver.mkdir(path.toString());
		storeObject(id, getFilename(), entity);
		return entity;
	}

	@Override
	public final void storeObject(final String _id, final String _filename, final Object _object) {
		Path path = namingStrategy.getRoot(getClazz(), _id);
		verifyPath(path);
		try {
			final String content = objectMapper.writeValueAsString(_object);
			path = namingStrategy.getRoot(getClazz(), _id, _filename);
			lowDriver.add(path.toString(), content.getBytes());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public final void storeBinary(final String _id, final String _filename, final InputStream _stream) {
		Path path = namingStrategy.getRoot(getClazz(), _id);
		verifyPath(path);
		path = namingStrategy.getRoot(getClazz(), _id, _filename);
		lowDriver.add(path.toString(), _stream);
	}

	@Override
	public final byte[] getBinary(final String _id, final String _filename) {
		Path path = namingStrategy.getRoot(getClazz(), _id);
		verifyPath(path);
		path = namingStrategy.getRoot(getClazz(), _id, _filename);
		return lowDriver.get(path.toString());
	}

	@Override
	public final byte[] getBinary(final String _id, final String _filename, final int min, final Long max) {
		Path path = namingStrategy.getRoot(getClazz(), _id);
		verifyPath(path);
		path = namingStrategy.getRoot(getClazz(), _id, _filename);
		return lowDriver.get(path.toString(), min, max);
	}

	@Override
	public <T, U extends Class> T loadObject(@NotNull final String _id, @NotNull final String _filename, final U t) {
		final byte[] bytes = getBinary(_id, _filename);
		try {
			return (T) objectMapper.readValue(bytes, t);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	protected abstract String setId(T entity);

	protected abstract Class<T> getClazz();

	protected abstract String getFilename();

	private void verifyPath(final Path path) {
		if (!lowDriver.exist(path.toString())) {
			throw new NotFoundException("Unable to find " + path);
		}
	}

	private T rawGetObject(final String _path) {
		final byte[] bytes = lowDriver.get(_path);
		try {
			return objectMapper.readValue(bytes, getClazz());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}
}
