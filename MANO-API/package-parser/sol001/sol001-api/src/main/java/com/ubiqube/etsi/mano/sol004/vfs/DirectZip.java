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

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;
import com.ubiqube.etsi.mano.tosca.IResolver;
import com.ubiqube.etsi.mano.tosca.ZipResolver;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class DirectZip implements VirtualFileSystem {
	private final Path filename;
	private final ZipFile zip;
	private final ZipResolver resolver;

	public DirectZip(final Path p) {
		this.filename = p;
		this.resolver = new ZipResolver(p.toString());
		try {
			zip = new ZipFile(filename.toFile());
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	@Override
	public byte[] getFileContent(final String fileName) {
		try (InputStream is = zip.getInputStream(new ZipEntry(fileName))) {
			return is.readAllBytes();
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	@Override
	public boolean exist(final String fileName) {
		return null != zip.getEntry(fileName);
	}

	@Override
	public List<String> getFileMatching(final String filenameWildcard) {
		final Pattern p = Pattern.compile(filenameWildcard);
		final Enumeration<? extends ZipEntry> e = zip.entries();
		final Stream<? extends ZipEntry> stream = StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(e.asIterator(), Spliterator.ORDERED),
				false);
		return stream
				.filter(x -> !x.isDirectory())
				.filter(x -> p.matcher(x.getName()).find())
				.map(ZipEntry::getName)
				.toList();
	}

	@Override
	public InputStream getInputStream(final String fileName) {
		final ZipEntry entry = new ZipEntry(fileName);
		try {
			return zip.getInputStream(entry);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	@Override
	public IResolver getResolver() {
		return resolver;
	}

}
