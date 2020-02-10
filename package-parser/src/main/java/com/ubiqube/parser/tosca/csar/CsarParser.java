package com.ubiqube.parser.tosca.csar;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;

import com.ubiqube.parser.tosca.IResolver;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.VfsResolver;

public class CsarParser {
	private final FileObject csar;
	private Properties props;
	private VfsResolver resolver;

	public CsarParser(final String filename) {
		FileSystemManager fsManager;
		try {
			fsManager = VFS.getManager();
			csar = fsManager.resolveFile("zip:" + filename);
			resolver = new VfsResolver();
			props = getMetaFileContent(csar);

		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	public String getEntryDefinition() {
		try {
			return innerGetEntryDefinition();
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}

	private String innerGetEntryDefinition() throws IOException {
		final String entry = (String) props.get("Entry-Definitions");
		final FileObject res2 = csar.resolveFile(entry);
		resolver.setParent(res2.getParent());
		return res2.getContent().getString(Charset.defaultCharset());
	}

	private static Properties getMetaFileContent(final FileObject fileObject) throws IOException {
		final Properties props = new Properties();
		final FileObject dir = fileObject.getChild("TOSCA-Metadata");
		final FileObject fil = dir.getChild("TOSCA.meta");
		final FileContent cont = fil.getContent();
		props.load(new ByteArrayInputStream(cont.getByteArray()));
		return props;
	}

	public IResolver getResolver() {
		return resolver;
	}
}
