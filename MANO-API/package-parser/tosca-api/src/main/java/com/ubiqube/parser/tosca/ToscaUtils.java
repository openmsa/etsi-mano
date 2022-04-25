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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public final class ToscaUtils {
	public static final String SHA_512 = "SHA-512";

	private ToscaUtils() {
		// Nothing.
	}

	public static String computeChecksum(final InputStream data) {
		try (DigestInputStream dis = new DigestInputStream(data, MessageDigest.getInstance(SHA_512))) {
			while (data.read() != -1) {
				// Read all.
			}
			return getChecksum(dis);
		} catch (final IOException | NoSuchAlgorithmException e) {
			throw new ParseException(e);
		}
	}

	private static String getChecksum(final DigestInputStream digest) {
		final byte[] hashbytes = digest.getMessageDigest().digest();
		return bytesToHex(hashbytes);
	}

	private static String bytesToHex(final byte[] hash) {
		final StringBuilder hexString = new StringBuilder();
		for (final byte element : hash) {
			final String hex = Integer.toHexString(0xff & element);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public static Path checkPath(final Path root, final Path toTest) {
		final Path target = root.resolve(toTest).normalize();
		if (!target.startsWith(root)) {
			throw new ParseException("Could not resolve path for: " + toTest);
		}
		return target;
	}

	public static byte[] readAllBytes(final FileInputStream fis) throws IOException {
		final byte[] targetArray = new byte[fis.available()];
		final int nb = fis.read(targetArray);
		if (nb != targetArray.length) {
			throw new ParseException("Number of bytes read mismatch: read " + nb + ", expected: " + targetArray.length);
		}
		return targetArray;
	}
}
