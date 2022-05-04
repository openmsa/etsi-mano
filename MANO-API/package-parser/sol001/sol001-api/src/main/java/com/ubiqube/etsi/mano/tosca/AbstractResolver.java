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
package com.ubiqube.etsi.mano.tosca;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;

public abstract class AbstractResolver implements IResolver {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractResolver.class);

	private static final Pattern URL_MATCHER = Pattern.compile("(?<!\\\\):");
	private final Set<String> imported = new LinkedHashSet<>();
	private final File cacheDir = new File(".cache");
	private File parent;

	protected static final boolean isUrl(final String url) {
		final Matcher res = URL_MATCHER.matcher(url);
		return res.find();
	}

	protected final InputStream getUrlContent(final String url) {
		final String cacheFileName = getCacheName(url);
		final File cacheFile = new File(cacheDir, cacheFileName);
		if (cacheFile.exists()) {
			return cachedContent(cacheFile);
		}
		try {
			final URL realUrl = new URL(url);
			return realUrl.openStream();
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	private static InputStream cachedContent(final File cacheFile) {
		try {
			return new FileInputStream(cacheFile);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
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
			throw new Sol004Exception(e);
		}
	}

	@Override
	public final String resolvePath(final String path) {
		if (imported.contains(path) || isUrl(path)) {
			return path;
		}
		if (!path.startsWith("/") && null != parent) {
			final File f = new File(parent, path);
			if (exist(f.toString())) {
				return f.toString();
			}
		}
		return path;
	}

	protected abstract boolean exist(String string);

	@Override
	public final String getContent(final String url) {
		if (imported.contains(url)) {
			return null;
		}
		imported.add(url);
		LOG.info("Resolving: {} from: {}", url, parent);
		if (isUrl(url)) {
			try (InputStream is = getUrlContent(url)) {
				return new String(is.readAllBytes(), Charset.defaultCharset());
			} catch (final IOException e) {
				throw new Sol004Exception(e);
			}
		}
		if (!url.startsWith("/")) {
			final File f = new File(url);
			if (null != parent) {
				final File p = new File(parent, url);
				if (exist(p.toString())) {
					parent = f.getParentFile();
					return handleContent(p.toString());
				}
			}
			if (exist(f.toString())) {
				parent = f.getParentFile();
				return handleContent(url);
			}
		}
		return null;
	}

	protected abstract String handleContent(String url);
}
