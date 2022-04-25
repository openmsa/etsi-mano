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
package com.ubiqube.etsi.mano.sol004;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public final class ZipUtil {

	private ZipUtil() {
		// Nothing.
	}

	public static void makeToscaZip(final String dest, final String folder) throws IOException {
		final Path root = Paths.get(folder);
		try (final FileOutputStream fos = new FileOutputStream(dest);
				final ZipOutputStream zipOut = new ZipOutputStream(fos)) {
			final List<Path> files = Files.walk(root).toList();
			for (final Path srcPath : files) {
				if (srcPath.toFile().isDirectory()) {
					continue;
				}
				final String entryPath = root.relativize(srcPath).toString();
				try (final InputStream is = new FileInputStream(srcPath.toFile())) {
					final ZipEntry zipEntry = new ZipEntry(entryPath);
					zipOut.putNextEntry(zipEntry);
					final byte[] bytes = new byte[1024];
					int length;
					while ((length = is.read(bytes)) >= 0) {
						zipOut.write(bytes, 0, length);
					}
				}
			}
		}
	}

	public static void makeToscaZip(final String dest, final Entry... toscaFile) throws IOException {
		try (final FileOutputStream fos = new FileOutputStream(dest);
				final ZipOutputStream zipOut = new ZipOutputStream(fos)) {
			for (final Entry srcFile : toscaFile) {
				try (final InputStream is = ZipUtil.class.getClassLoader().getResourceAsStream(srcFile.classPath)) {
					final ZipEntry zipEntry = new ZipEntry(srcFile.zipName);
					zipOut.putNextEntry(zipEntry);

					final byte[] bytes = new byte[1024];
					int length;
					while ((length = is.read(bytes)) >= 0) {
						zipOut.write(bytes, 0, length);
					}
				}
			}
		}
	}

	public static class Entry {
		public static Entry of(final String path, final String zip) {
			final Entry e = new Entry();
			e.classPath = path;
			e.zipName = zip;
			return e;
		}

		public String classPath;
		public String zipName;
	}
}
