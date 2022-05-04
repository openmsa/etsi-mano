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

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.sol004.Sol004Exception;

public class VfsResolver extends Resolver {
	private static final Logger LOG = LoggerFactory.getLogger(VfsResolver.class);
	private final Pattern urlMatcher = Pattern.compile("(?<!\\\\):");
	private FileObject parent;
	private final Set<String> imported = new LinkedHashSet<>();

	public VfsResolver() {
		super(null);
		try {
			VFS.getManager();
		} catch (final FileSystemException e) {
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
			try {
				final FileObject child = parent.resolveFile(url);
				return child.getContent().getString(Charset.defaultCharset());
			} catch (final IOException e) {
				throw new Sol004Exception(e);
			}
		}
		return null;
	}

	private boolean isUrl(final String url) {
		final Matcher res = urlMatcher.matcher(url);
		return res.find();
	}

	public void setParent(final FileObject parent) {
		this.parent = parent;
	}

	@Override
	public String resolvePath(final String path) {
		if (imported.contains(path) || isUrl(path)) {
			return path;
		}
		if (!path.startsWith("/")) {
			FileObject child;
			try {
				child = parent.resolveFile(path);
				return child.getName().getPath();
			} catch (final FileSystemException e) {
				throw new Sol004Exception(e);
			}
		}
		return path;
	}
}
