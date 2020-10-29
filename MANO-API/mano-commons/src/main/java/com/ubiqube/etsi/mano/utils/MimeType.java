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
package com.ubiqube.etsi.mano.utils;

import java.nio.charset.Charset;

import org.springframework.util.MimeTypeUtils;

/**
 * We are using simple idenfication. Basically we have only 2 mime types, Json
 * and Zip. If we need more types we can probably use compile group:
 * 'com.j256.simplemagic', name: 'simplemagic', version: '1.16'
 *
 * @author ovi@ubiqube.com
 *
 */
public class MimeType {

	private MimeType() {
		// Nothing.
	}

	public static String findMatch(final byte[] bytes) {
		if ((bytes.length > 2) && (bytes[0] == 'P') && (bytes[1] == 'K')) {
			return "application/zip";
		}
		if (bytes.length > 1) {
			String str = new String(bytes, Charset.defaultCharset());
			str = str.trim();
			if (str.startsWith("{")) {
				return MimeTypeUtils.APPLICATION_JSON_VALUE;
			}
		}

		return MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE;
	}
}