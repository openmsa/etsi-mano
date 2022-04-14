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
package com.ubiqube.parser.tosca;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handle various problematic around SOL001 Zip / text file.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public final class Sol001FileFactory {
	private static final Pattern ZIP_MATCHER = Pattern.compile("\\.(zip|csar)$");

	private Sol001FileFactory() {
		// Nothing.
	}

	public static Sol001FileSystem of(final File filename) {
		if (isZip(filename)) {
			return new Sol001Zip(filename);
		}
		if (filename.isDirectory()) {
			return new Sol001Directory(filename);
		}
		return new Sol001SingleFile(filename);
	}

	private static boolean isZip(final File fileName) {
		final Matcher res = ZIP_MATCHER.matcher(fileName.toString());
		return res.find();
	}
}
