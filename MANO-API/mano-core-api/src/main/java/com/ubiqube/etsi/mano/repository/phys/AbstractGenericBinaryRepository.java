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
package com.ubiqube.etsi.mano.repository.phys;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.grammar.GrammarParser;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.grammar.Node;
import com.ubiqube.etsi.mano.repository.BinaryRepository;
import com.ubiqube.etsi.mano.repository.CrudRepository;
import com.ubiqube.etsi.mano.repository.Low;
import com.ubiqube.etsi.mano.repository.NamingStrategy;

/**
 *
 * @author Olivier
 *
 */
public abstract class AbstractGenericBinaryRepository<T> implements CrudRepository<T>, BinaryRepository {

	private final ObjectMapper objectMapper;
	private final JsonFilter jsonFilter;
	private final Low lowDriver;
	private final NamingStrategy namingStrategy;
	private final GrammarParser grammarParser;

	protected AbstractGenericBinaryRepository(final ObjectMapper objectMapper, final JsonFilter jsonFilter, final Low lowDriver, final NamingStrategy namingStrategy,
			final GrammarParser grammarParser) {
		this.objectMapper = objectMapper;
		this.jsonFilter = jsonFilter;
		this.lowDriver = lowDriver;
		this.namingStrategy = namingStrategy;
		this.grammarParser = grammarParser;
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
		final List<Node<String>> nodes = grammarParser.parse(filter);
		return listFilesInFolder.stream()
				.map(this::rawGetObject)
				.filter(x -> jsonFilter.apply(x, nodes))
				.toList();
	}

	@Override
	public final T get(final UUID id) {
		Path path = namingStrategy.getRoot(getClazz(), id);
		verifyPath(path);
		path = namingStrategy.getRoot(getClazz(), id, getFilename());
		try {
			final byte[] content = lowDriver.get(path.toString());
			return objectMapper.readValue(content, getClazz());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public final void delete(final UUID id) {
		final Path path = namingStrategy.getRoot(getClazz(), id);
		verifyPath(path);
		lowDriver.delete(path.toString());
	}

	@Override
	public T save(final T entity) {
		final UUID id = setId(entity);
		final Path path = namingStrategy.getRoot(getClazz(), id);
		lowDriver.mkdir(path.toString());
		storeObject(id, getFilename(), entity);
		return entity;
	}

	@Override
	public final void storeObject(final UUID id, final String filename, final Object object) {
		Path path = namingStrategy.getRoot(getClazz(), id);
		verifyPath(path);
		try {
			final String content = objectMapper.writeValueAsString(object);
			path = namingStrategy.getRoot(getClazz(), id, filename);
			lowDriver.add(path.toString(), content.getBytes(Charset.defaultCharset()));
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public final void storeBinary(final UUID id, final String filename, final InputStream stream) {
		Path path = namingStrategy.getRoot(getClazz(), id);
		verifyPath(path);
		path = namingStrategy.getRoot(getClazz(), id, filename);
		lowDriver.add(path.toString(), stream);
	}

	@Override
	public final byte[] getBinary(final UUID id, final String filename) {
		Path path = namingStrategy.getRoot(getClazz(), id);
		verifyPath(path);
		path = namingStrategy.getRoot(getClazz(), id, filename);
		return lowDriver.get(path.toString());
	}

	@Override
	public final byte[] getBinary(final UUID id, final String filename, final int min, final Long max) {
		Path path = namingStrategy.getRoot(getClazz(), id);
		verifyPath(path);
		path = namingStrategy.getRoot(getClazz(), id, filename);
		return lowDriver.get(path.toString(), min, max);
	}

	protected abstract @Nonnull UUID setId(T entity);

	protected abstract @Nonnull Class<T> getClazz();

	protected abstract @Nonnull String getFilename();

	private void verifyPath(final Path path) {
		if (!lowDriver.exist(path.toString())) {
			throw new NotFoundException("Unable to find " + path);
		}
	}

	private T rawGetObject(final String path) {
		final byte[] bytes = lowDriver.get(path);
		try {
			return objectMapper.readValue(bytes, getClazz());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public void delete(@NotNull final UUID id, @NotNull final String filename) {
		final Path path = namingStrategy.getRoot(getClazz(), id);
		lowDriver.delete(path.toString());
	}

}
