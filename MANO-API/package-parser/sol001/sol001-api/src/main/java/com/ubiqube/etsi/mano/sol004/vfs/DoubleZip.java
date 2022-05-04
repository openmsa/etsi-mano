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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;
import com.ubiqube.etsi.mano.tosca.DoubleZipResolver;
import com.ubiqube.etsi.mano.tosca.IResolver;

public class DoubleZip implements VirtualFileSystem {

	private static final String UNABLE_TO_FIND = "Unable to find ";
	private ZipFile zip;
	private final String inZip;
	private final List<String> innerList;
	private final DoubleZipResolver resolver;

	public DoubleZip(final File filename, final String inZip) {
		try {
			zip = new ZipFile(filename);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
		this.inZip = inZip;
		final ZipEntry entry = zip.getEntry(inZip);
		try (InputStream is = zip.getInputStream(entry)) {
			this.innerList = extractFileList(is);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
		this.resolver = new DoubleZipResolver(this);
	}

	private static List<String> extractFileList(final InputStream inputStream) throws IOException {
		final List<String> ret = new ArrayList<>();
		final ZipInputStream zis = new ZipInputStream(inputStream);
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null) {
			if (entry.isDirectory()) {
				continue;
			}
			ret.add(entry.getName());
		}
		return ret;
	}

	private Optional<String> findInnerFile(final String filename) {
		return innerList.stream().filter(x -> x.equals(filename)).findFirst();
	}

	@Override
	public byte[] getFileContent(final String filename) {
		final Optional<String> innerElement = findInnerFile(filename);
		if (innerElement.isPresent()) {
			return readInner(filename);
		}
		final ZipEntry entry = zip.getEntry(filename);
		if (entry != null) {
			try (final InputStream is = zip.getInputStream(entry)) {
				return is.readAllBytes();
			} catch (final IOException e) {
				throw new Sol004Exception(e);
			}
		}
		throw new Sol004Exception(UNABLE_TO_FIND + filename);
	}

	private byte[] readInner(final String filename) {
		try (final InputStream is = getZipInputStream(filename)) {
			return is.readAllBytes();
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	private InputStream getZipInputStream(final String filename) {
		try {
			final InputStream is = zip.getInputStream(zip.getEntry(inZip));
			final ZipInputStream zis = new ZipInputStream(is);
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				if (filename.equals(entry.getName())) {
					return zis;
				}
			}
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
		throw new Sol004Exception(UNABLE_TO_FIND + filename);
	}

	private ZipEntry getZipEntry(final String filename) {
		try (final InputStream is = zip.getInputStream(zip.getEntry(inZip))) {
			final ZipInputStream zis = new ZipInputStream(is);
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				if (filename.equals(entry.getName())) {
					return entry;
				}
			}
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
		throw new Sol004Exception(UNABLE_TO_FIND + filename);
	}

	@Override
	public boolean exist(final String filename) {
		final Optional<String> innerElement = findInnerFile(filename);
		if (innerElement.isPresent()) {
			return true;
		}
		return zip.getEntry(filename) != null;
	}

	@Override
	public List<String> getFileMatching(final String filenameWildcard) {
		final Pattern p = Pattern.compile(filenameWildcard);
		final List<String> inner = innerList.stream().filter(x -> p.matcher(x).find()).toList();
		final List<String> outer = zip.stream().filter(x -> p.matcher(x.getName()).find()).map(ZipEntry::getName).toList();
		final ArrayList<String> ret = new ArrayList<>(inner);
		ret.addAll(outer);
		return ret;
	}

	@Override
	public InputStream getInputStream(final String fileName) {
		resolver.setLast(new File(fileName).getParentFile());
		final Optional<String> elem = findInnerFile(fileName);
		if (elem.isPresent()) {
			return getZipInputStream(fileName);
		}
		try {
			return zip.getInputStream(zip.getEntry(fileName));
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	@Override
	public IResolver getResolver() {
		return resolver;
	}

	@Override
	public Map<String, String> getMetaInfo(final String fileName) {
		final Optional<String> elem = findInnerFile(fileName);
		ZipEntry entry;
		if (elem.isPresent()) {
			entry = getZipEntry(fileName);
		} else {
			entry = zip.getEntry(fileName);
		}
		if (null == entry) {
			throw new Sol004Exception(UNABLE_TO_FIND + fileName);
		}
		final long crc = entry.getCrc();
		final long date = entry.getTime();
		final String dateStr = ZonedDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.of("UTC")).toString();
		final long size = entry.getSize();
		return Map.of("CRC", String.format("%d", crc), "SIZE", String.format("%d", size), "DATE", dateStr);
	}

}
