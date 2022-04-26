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
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.validation.constraints.Null;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ZipResolver extends Resolver {

	private static final Logger LOG = LoggerFactory.getLogger(ZipResolver.class);
	private static final Pattern URL_MATCHER = Pattern.compile("(?<!\\\\):");

	private final Set<String> imported = new LinkedHashSet<>();
	private File parent;
	private final ZipFile zipFile;

	public ZipResolver(final String fileName) {
		super(null);
		try {
			zipFile = new ZipFile(fileName);
		} catch (final IOException e) {
			throw new Sol004Exception(e);
		}
	}

	@Override
	public String getContent(final String url) {
		if (imported.contains(url)) {
			return null;
		}
		imported.add(url);
		LOG.info("Resolving: {} from: {}", url, parent);
		if (isUrl(url)) {
			return super.getContent(url);
		}
		if (!url.startsWith("/")) {
			ZipEntry entry = zipFile.getEntry(url);
			if (entry == null) {
				entry = buildParent(url);
			}
			try (InputStream is = zipFile.getInputStream(entry)) {
				return new String(is.readAllBytes(), Charset.defaultCharset());
			} catch (final IOException e) {
				throw new Sol004Exception(e);
			}
		}
		return null;
	}

	@Null
	private ZipEntry buildParent(final String url) {
		final File f = new File(parent, url);
		return zipFile.getEntry(f.toString());
	}

	private static boolean isUrl(final String url) {
		final Matcher res = URL_MATCHER.matcher(url);
		return res.find();
	}

	public void setParent(final File parent) {
		this.parent = parent;
	}

	@Override
	public String resolvePath(final String path) {
		if (imported.contains(path) || isUrl(path)) {
			return path;
		}
		if (!path.startsWith("/")) {
			ZipEntry entry = zipFile.getEntry(path);
			if (null == entry) {
				entry = buildParent(path);
			}
			return entry.getName();
		}
		return path;
	}

}
