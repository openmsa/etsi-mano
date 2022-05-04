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
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.ubiqube.etsi.mano.repository.ManoResource;
import com.ubiqube.etsi.mano.sol004.Sol004Exception;
import com.ubiqube.etsi.mano.tosca.DoubleMrZipResolver;
import com.ubiqube.etsi.mano.tosca.IResolver;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@SuppressWarnings("resource")
public class DoubleZipMr extends AbstractZip implements VirtualFileSystem {

	private final ManoResource mr;

	private final List<String> innerList;

	private List<String> outerList;

	private final String csarEntry;

	private final DoubleMrZipResolver resolver;

	public DoubleZipMr(final ManoResource mr) {
		this.mr = mr;
		this.resolver = new DoubleMrZipResolver(this);
		try (InputStream is = mr.getInputStream()) {
			this.outerList = extractFileList(is);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
		csarEntry = outerList.stream().filter(x -> x.endsWith(".csar")).findFirst().orElseThrow();
		try (InputStream is = mr.getInputStream()) {
			final InputStream zis = getContentIs(is, csarEntry);
			this.innerList = extractFileList(zis);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	@Override
	public byte[] getFileContent(final String filename) {
		try {
			return getInputStream(filename).readAllBytes();
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	@Override
	public boolean exist(final String filename) {
		return outerList.contains(filename) || innerList.contains(filename);
	}

	@Override
	public List<String> getFileMatching(final String filenameWildcard) {
		final Pattern p = Pattern.compile(filenameWildcard);
		return Stream.of(innerList, outerList)
				.flatMap(Collection::stream)
				.filter(x -> p.matcher(x).find())
				.toList();
	}

	@Override
	public InputStream getInputStream(final String fileName) {
		if (outerList.contains(fileName)) {
			try {
				final InputStream is = mr.getInputStream();
				return getContentIs(is, fileName);
			} catch (final IOException e) {
				throw new Sol004Exception(e);
			}
		}
		try {
			final InputStream is = mr.getInputStream();
			final InputStream entry = getContentIs(is, csarEntry);
			return getContentIs(entry, fileName);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	@Override
	public IResolver getResolver() {
		return resolver;
	}

	@Override
	protected ZipEntry getZipEntry(final String fileName) throws IOException {
		if (innerList.contains(fileName)) {
			try (InputStream is = mr.getInputStream()) {
				final InputStream zentry = getContentIs(is, csarEntry);
				final ZipInputStream sub = new ZipInputStream(zentry);
				ZipEntry entry;
				while ((entry = sub.getNextEntry()) != null) {
					if (entry.isDirectory()) {
						continue;
					}
					if (entry.getName().equals(fileName)) {
						return entry;
					}
				}
			}
		}
		try (InputStream is = mr.getInputStream()) {
			final ZipInputStream sub = new ZipInputStream(is);
			ZipEntry entry;
			while ((entry = sub.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				if (entry.getName().equals(fileName)) {
					return entry;
				}
			}
		}
		return null;
	}

}
