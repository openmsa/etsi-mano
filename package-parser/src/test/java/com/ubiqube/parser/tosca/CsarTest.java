package com.ubiqube.parser.tosca;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsarTest {
	private static final Logger LOG = LoggerFactory.getLogger(CsarTest.class);
	private static String META_FILENAME = "TOSCA-Metadata/TOSCA.meta";

	@Test
	void testName() throws Exception {
		final InputStream in = new FileInputStream("src/test/resources/csar_elk.csar");
		final ZipInputStream zis = new ZipInputStream(in);
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null) {
			LOG.info("entry: {}, size: {}", entry.getName(), entry.getSize());
			if (META_FILENAME.equals(entry.getName())) {
				//

				final Properties props = new Properties();
				props.load(zis);
				LOG.debug(">>>" + props);
				return;
			}
		}
	}

	@Test
	void testName2() throws Exception {
		final FileSystemManager fsManager = VFS.getManager();
		final FileObject csar = fsManager.resolveFile("zip:/home/olivier/eclipse-workspace/parser/src/test/resources/csar_elk.csar");
		final FileObject dir = csar.getChild("TOSCA-Metadata");
		final FileObject fil = dir.getChild("TOSCA.meta");
		LOG.info(">>> " + fil);
		final FileContent cont = fil.getContent();
		LOG.info("CONT>>> {}", cont.getString(Charset.defaultCharset()));
		final Properties props = new Properties();
		props.load(new ByteArrayInputStream(cont.getByteArray()));
		LOG.info("PROP>>> {}", props);
		final String entry = (String) props.get("Entry-Definitions");
		LOG.info("ED >>> {}", entry);
		final FileObject res = fsManager.resolveFile("zip:file:///home/olivier/eclipse-workspace/parser/src/test/resources/csar_elk.csar!/" + entry);
		LOG.info("RES>>> {}", res);
		final FileObject res2 = csar.resolveFile(entry);
		LOG.info("RES2>> {}", res2);
	}
}
