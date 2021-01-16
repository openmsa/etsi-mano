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
package com.ubiqube.etsi.mano.service.pkg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class PkgUtils {
	private PkgUtils() {
		// Nothing.
	}

	public static File fetchData(final byte[] data) {
		File tempFile;
		try {
			tempFile = File.createTempFile("tosca", ".zip");
		} catch (final IOException e) {
			throw new ToscaException(e);
		}
		try (final OutputStream os = new FileOutputStream(tempFile)) {
			os.write(data);
		} catch (final IOException e) {
			throw new ToscaException(e);
		}
		return tempFile;
	}

}
