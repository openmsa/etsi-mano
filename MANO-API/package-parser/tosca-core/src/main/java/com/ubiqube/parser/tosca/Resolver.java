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
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Resolver implements IResolver {
	private final File cacheDir = new File(".cache");

	public Resolver() {
		cacheDir.mkdir();
	}

	@Override
	public String getContent(final String url) {
		final String cacheFileName = getCacheName(url);
		final File cacheFile = new File(cacheDir, cacheFileName);
		if (cacheFile.exists()) {
			return getContents(cacheFile);
		}
		try {
			final URL rUrl = new URL(url);
			final String content = getContent(rUrl);
			saveContent(cacheFile, content);
			return content;
		} catch (final MalformedURLException e) {
			throw new ParseException(e);
		}
	}

	private static void saveContent(final File cacheFile, final String content) {
		try {
			Files.write(cacheFile.toPath(), content.getBytes());
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	private static String getContent(final URL rUrl) {
		try (Scanner scanner = new Scanner(rUrl.openStream(),
				StandardCharsets.UTF_8.toString())) {
			scanner.useDelimiter("\\A");
			return scanner.hasNext() ? scanner.next() : "";
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	private static String getContents(final File cacheFile) {
		try {
			return new String(Files.readAllBytes(cacheFile.toPath()), Charset.defaultCharset());
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	private static String getCacheName(final String url) {
		try {
			final MessageDigest md = MessageDigest.getInstance("SHA-1");
			final byte[] messageDigest = md.digest(url.getBytes());
			final BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (final NoSuchAlgorithmException e) {
			throw new ParseException(e);
		}
	}
}