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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Resolver implements IResolver {

	private static final Logger LOG = LoggerFactory.getLogger(Resolver.class);

	private final File cacheDir = new File(".cache");

	private String last;

	public Resolver(final File filename) {
		cacheDir.mkdir();
		if (null != filename) {
			last = filename.getParent();
		}
	}

	@Override
	public String getContent(final String url) {
		final String cacheFileName = getCacheName(url);
		final File cacheFile = new File(cacheDir, cacheFileName);
		if (cacheFile.exists()) {
			return getContents(cacheFile);
		}
		final URL rUrl = getUrl(url);
		// Maybe we probably need to store `last`
		final String content = getContent(rUrl);
		saveContent(cacheFile, content);
		return content;
	}

	private URL getUrl(final String url) {
		try {
			return new URL(url);
		} catch (final MalformedURLException e) {
			LOG.trace("Not an URL: " + url, e);
		}
		LOG.info("Opening: {}", url);
		final URL classpath = this.getClass().getClassLoader().getResource(url);
		if (classpath != null) {
			return classpath;
		}
		// Try as a normal file.
		if (last == null) {
			throw new ParseException("Could not find: " + url + " last:" + last);
		}
		final File f = new File(last, url);
		try {
			return f.toURI().toURL();
		} catch (final MalformedURLException e) {
			throw new ParseException(e);
		}
	}

	private static void saveContent(final File cacheFile, final String content) {
		try {
			Files.write(cacheFile.toPath(), content.getBytes(Charset.defaultCharset()));
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
			final byte[] messageDigest = md.digest(url.getBytes(Charset.defaultCharset()));
			final BigInteger no = new BigInteger(1, messageDigest);
			final String hashtext = no.toString(16);
			return new String(new char[40 - hashtext.length()]).replace('\0', '0') + hashtext;
		} catch (final NoSuchAlgorithmException e) {
			throw new ParseException(e);
		}
	}

	@Override
	public String resolvePath(final String path) {
		return path;
	}
}