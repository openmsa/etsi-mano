package com.ubiqube.etsi.mano.repository.jpa;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.repository.BinaryRepository;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;

public abstract class AbstractBinaryRepository implements BinaryRepository {
	private final ContentManager contentManager;
	private final ObjectMapper jsonMapper;

	private final NamingStrategy namingStrategy;

	public AbstractBinaryRepository(final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy _namingStrategy) {
		super();
		this.contentManager = contentManager;
		this.jsonMapper = jsonMapper;
		namingStrategy = _namingStrategy;
	}

	protected void mkdir(final UUID _id) {
		final Path path = namingStrategy.getRoot(getFrontClass(), _id);
		contentManager.mkdir(path);
	}

	@Override
	public final void storeObject(final UUID _id, final String _filename, final Object _object) {
		try {
			final String str = jsonMapper.writeValueAsString(_object);
			storeBinary(_id, _filename, new ByteArrayInputStream(str.getBytes()));
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public final void storeBinary(final UUID _id, final String _filename, final InputStream _stream) {
		final Path path = namingStrategy.getRoot(getFrontClass(), _id, _filename);
		contentManager.store(path, _stream);
	}

	@Override
	public final byte[] getBinary(final UUID _id, final String _filename) {
		return getBinary(_id, _filename, 0, null);
	}

	@Override
	public final byte[] getBinary(final UUID _id, final String _filename, final int min, final Long max) {
		final Path path = namingStrategy.getRoot(getFrontClass(), _id, _filename);
		try (InputStream os = contentManager.load(path, min, max)) {
			return StreamUtils.copyToByteArray(os);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public final <T, U extends Class> T loadObject(@NotNull final UUID _id, final String _filename, final U t) {
		final byte[] content = getBinary(_id, _filename, 0, null);
		try {
			return (T) jsonMapper.readValue(content, t);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public void delete(@NotNull final UUID _id, @NotNull final String _filename) {
		final Path path = namingStrategy.getRoot(getFrontClass(), _id, _filename);
		contentManager.delete(path);
	}

	@Override
	public void delete(@NotNull final UUID _id) {
		final Path path = namingStrategy.getRoot(getFrontClass(), _id);
		contentManager.delete(path);
	}

	protected abstract Class<?> getFrontClass();
}
