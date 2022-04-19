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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Sol004Onboarding {
	private static final Pattern ZIP_MATCHER = Pattern.compile(".*\\.(zip)");
	private static final Pattern CSAR_MATCHER = Pattern.compile(".*\\.(csar)");

	boolean isTosca(final String fileName) {
		if (isCsarExtension(fileName)) {
			return true;
		}
		if (!isZipExtension(fileName)) {
			return false;
		}
		final List<String> fileList = getFileList(fileName);
		return fileList.stream().anyMatch(x -> x.endsWith(".csar"));
	}

	private static List<String> getFileList(final String fileName) {
		try (final InputStream stream = new FileInputStream(fileName)) {
			return getZipFileList(stream);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	private static List<String> getZipFileList(final InputStream stream) {
		final List<String> ret = new ArrayList<>();
		final ZipInputStream zis = new ZipInputStream(stream);
		ZipEntry entry = null;
		try {
			while ((entry = zis.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					continue;
				}
				ret.add(entry.getName());
			}
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
		return ret;
	}

	private static boolean isCsarExtension(final String fileName) {
		return CSAR_MATCHER.matcher(fileName).find();
	}

	private static boolean isZipExtension(final String fileName) {
		return ZIP_MATCHER.matcher(fileName).find();
	}
}
