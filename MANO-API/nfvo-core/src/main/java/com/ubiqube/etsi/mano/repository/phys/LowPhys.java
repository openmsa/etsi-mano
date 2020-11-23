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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.repository.Low;

@Service
public class LowPhys implements Low {

	@Override
	public boolean exist(final String _path) {
		return new File(_path).exists();
	}

	@Override
	public void mkdir(final String _path) {
		new File(_path).mkdirs();
	}

	@Override
	public void add(final String _path, final byte[] _content) {
		try {
			Files.write(Paths.get(_path), _content);
		} catch (final IOException e) {
			throw new GenericException(e);
		}

	}

	@Override
	public void add(final String _path, final InputStream _stream) {
		try {
			Files.copy(_stream, Paths.get(_path), StandardCopyOption.REPLACE_EXISTING);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public byte[] get(final String _path) {
		try {
			return Files.readAllBytes(Paths.get(_path));
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public void delete(final String _path) {
		boolean result;
		try {
			result = FileSystemUtils.deleteRecursively(Paths.get(_path));
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		if (!result) {
			throw new GenericException("Unable to delete " + _path);
		}
	}

	@Override
	public List<String> find(final String _path, final String _pattern) {
		final Path path = Paths.get(_path);
		try (final Stream<Path> walk = Files.walk(path)) {
			return walk.filter(x -> x.toString().endsWith(_pattern))
					.map(Path::toString)
					.collect(Collectors.toList());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public boolean isDirectory(final String _path) {
		return new File(_path).isDirectory();
	}

	@Override
	public byte[] get(final String path, final int min, final Long max) {
		try {
			final byte[] bytes = Files.readAllBytes(Paths.get(path));
			if (null == max) {
				return bytes;
			}
			return Arrays.copyOfRange(bytes, min, max.intValue());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

}
