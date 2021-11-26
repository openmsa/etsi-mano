/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.repository.jpa;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
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

	protected AbstractBinaryRepository(final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super();
		this.contentManager = contentManager;
		this.jsonMapper = jsonMapper;
		this.namingStrategy = namingStrategy;
	}

	protected void mkdir(final UUID id) {
		final Path path = namingStrategy.getRoot(getFrontClass(), id);
		contentManager.mkdir(path);
	}

	@Override
	public final void storeObject(final UUID id, final String filename, final Object object) {
		try {
			final String str = jsonMapper.writeValueAsString(object);
			storeBinary(id, filename, new ByteArrayInputStream(str.getBytes(Charset.defaultCharset())));
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public final void storeBinary(final UUID id, final String filename, final InputStream stream) {
		final Path dir = namingStrategy.getRoot(getFrontClass(), id);
		dir.toFile().mkdirs();
		final Path path = namingStrategy.getRoot(getFrontClass(), id, filename);
		contentManager.store(path, stream);
	}

	@Override
	public final byte[] getBinary(final UUID id, final String filename) {
		return getBinary(id, filename, 0, null);
	}

	@Override
	public final byte[] getBinary(final UUID id, final String filename, final int min, final Long max) {
		final Path path = namingStrategy.getRoot(getFrontClass(), id, filename);
		try (InputStream os = contentManager.load(path, min, max)) {
			return StreamUtils.copyToByteArray(os);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public void delete(@NotNull final UUID id, @NotNull final String filename) {
		final Path path = namingStrategy.getRoot(getFrontClass(), id, filename);
		contentManager.delete(path);
	}

	@Override
	public void delete(@NotNull final UUID id) {
		final Path path = namingStrategy.getRoot(getFrontClass(), id);
		contentManager.delete(path);
	}

	protected abstract Class<?> getFrontClass();
}
