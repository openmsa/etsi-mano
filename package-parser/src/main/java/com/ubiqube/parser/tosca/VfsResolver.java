package com.ubiqube.parser.tosca;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VfsResolver implements IResolver {
	private static final Logger LOG = LoggerFactory.getLogger(VfsResolver.class);
	private final FileSystemManager fsManager;
	final Pattern urlMatcher = Pattern.compile("(?<!\\\\):");
	private FileObject parent;

	public VfsResolver() {
		try {
			fsManager = VFS.getManager();
		} catch (final FileSystemException e) {
			throw new ParseException(e);
		}
	}

	@Override
	public String getContent(final String url) {
		LOG.info("Resolving: {} from: {}", url, parent.toString());
		if (isUrl(url)) {
			return handleUrl(url);
		}
		if (url.startsWith("/")) {
			// Handle absolute content
		} else {
			try {
				final FileObject child = parent.getChild(url);
				return child.getContent().getString(Charset.defaultCharset());
			} catch (final IOException e) {
				throw new ParseException(e);
			}
		}
		return null;
	}

	private static String handleUrl(final String url) {
		try {
			final URL urlObj = new URL(url);
			return urlObj.getContent().toString();
		} catch (final IOException e) {
			throw new ParseException(e);
		}

	}

	private boolean isUrl(final String url) {
		final Matcher res = urlMatcher.matcher(url);
		return res.find();
	}

	public void setParent(final FileObject _parent) {
		parent = _parent;
	}
}
