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
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public abstract class AbstractZip {
	protected static List<String> extractFileList(final InputStream is) throws IOException {
		final List<String> ret = new ArrayList<>();
		final ZipInputStream zis = new ZipInputStream(is);
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null) {
			if (entry.isDirectory()) {
				continue;
			}
			ret.add(entry.getName());
		}
		return ret;
	}

	protected static InputStream getContentIs(final InputStream is, final String filename) throws IOException {
		final ZipInputStream zis = new ZipInputStream(is);
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null) {
			if (entry.isDirectory()) {
				continue;
			}
			if (entry.getName().equals(filename)) {
				return zis;
			}
		}
		throw new Sol004Exception("Unable to find entry: " + filename);
	}

	public final Map<String, String> getMetaInfo(final String fileName) {
		ZipEntry entry;
		try {
			entry = getZipEntry(fileName);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
		if (null == entry) {
			throw new Sol004Exception("Unable to find: " + fileName);
		}
		final long crc = entry.getCrc();
		final long date = entry.getTime();
		final String dateStr = ZonedDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.of("UTC")).toString();
		final long size = entry.getSize();
		return Map.of("CRC", String.format("%d", crc), "SIZE", String.format("%d", size), "DATE", dateStr);
	}

	protected abstract ZipEntry getZipEntry(String fileName) throws IOException;

}
