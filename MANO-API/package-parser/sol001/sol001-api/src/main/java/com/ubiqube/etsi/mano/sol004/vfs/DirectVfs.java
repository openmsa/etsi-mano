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
package com.ubiqube.etsi.mano.sol004.vfs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;
import com.ubiqube.etsi.mano.tosca.IResolver;
import com.ubiqube.etsi.mano.tosca.Resolver;

public class DirectVfs implements VirtualFileSystem {

	private final Path root;
	private final Resolver resolver;

	public DirectVfs(final Path path) {
		this.root = path;
		this.resolver = new Resolver(path.toFile());
	}

	@Override
	public byte[] getFileContent(final String filename) {
		try {
			final Path p = checkPath(root, Paths.get(filename));
			return Files.readAllBytes(p);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	@Override
	public boolean exist(final String filename) {
		final Path p = checkPath(root, Paths.get(filename));
		return Files.exists(p);
	}

	@Override
	public List<String> getFileMatching(final String filenameWildcard) {
		final Pattern p = Pattern.compile(filenameWildcard);
		try (Stream<Path> w = Files.walk(root)) {
			return w
					.filter(x -> p.matcher(x.getFileName().toString()).find())
					.map(root::relativize)
					.map(Path::toString)
					.toList();
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	/**
	 * Prevent directory traversal attack.
	 *
	 * @param root   The directory point where should not allow to goes backward.
	 * @param toTest The path to test.
	 * @return The normalized path.
	 */
	public static Path checkPath(final Path root, final Path toTest) {
		final Path target = root.resolve(toTest).normalize();
		if (!target.startsWith(root)) {
			throw new Sol004Exception("Could not resolve path for: " + toTest);
		}
		return target;
	}

	@Override
	public InputStream getInputStream(final String fileName) {
		final Path p = checkPath(root, Paths.get(fileName));
		try {
			return new FileInputStream(p.toFile());
		} catch (final FileNotFoundException e) {
			throw new Sol004Exception(e);
		}
	}

	@Override
	public IResolver getResolver() {
		return resolver;
	}

}
