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
package com.ubiqube.etsi.mano.sol004;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.repository.ManoResource;
import com.ubiqube.etsi.mano.repository.phys.PhysResource;
import com.ubiqube.etsi.mano.sol004.vfs.DirectZipMr;
import com.ubiqube.etsi.mano.sol004.vfs.DoubleZipMr;
import com.ubiqube.etsi.mano.tosca.IResolver;

@SuppressWarnings("static-method")
class VfsManoResourceTest {

	public Path buildZip() throws IOException {
		final Path path = Files.createTempDirectory("tmr-");
		final File dest = new File(path.toFile(), "mano.zip");
		try (final FileOutputStream fos = new FileOutputStream(dest);
				final ZipOutputStream zipOut = new ZipOutputStream(fos);
				InputStream is = getClass().getResourceAsStream("/manifest.mf")) {
			ZipEntry zipEntry = new ZipEntry("directory/");
			zipOut.putNextEntry(zipEntry);
			zipOut.closeEntry();
			zipEntry = new ZipEntry("manifest.mf");
			zipOut.putNextEntry(zipEntry);
			is.transferTo(zipOut);
			zipOut.closeEntry();
		}
		return path;
	}

	public Path buildDoubleZip() throws FileNotFoundException, IOException {
		final Path p = buildZip();
		final File f = new File(p.toFile(), "mano.zip");
		final File dest = new File(p.toFile(), "mano-mano.zip");
		try (final FileOutputStream fos = new FileOutputStream(dest);
				final ZipOutputStream zipOut = new ZipOutputStream(fos);
				InputStream is = getClass().getResourceAsStream("/cert.cert");
				InputStream isf = new FileInputStream(f)) {
			ZipEntry zipEntry = new ZipEntry("directory2/");
			zipOut.putNextEntry(zipEntry);
			zipOut.closeEntry();
			zipEntry = new ZipEntry("cert.cert");
			zipOut.putNextEntry(zipEntry);
			is.transferTo(zipOut);
			zipOut.closeEntry();
			final ZipEntry zipEntry2 = new ZipEntry("mano.csar");
			zipOut.putNextEntry(zipEntry2);
			isf.transferTo(zipOut);
			zipOut.closeEntry();
		}
		return p;
	}

	@Test
	void testSingle() throws Exception {
		final Path p = buildZip();
		final ManoResource mr = new PhysResource(0, new File(p.toFile(), "mano.zip").toString());
		final DirectZipMr zmr = new DirectZipMr(mr);
		assertFalse(zmr.exist("test.zip"));
		assertTrue(zmr.exist("manifest.mf"));
		assertEquals(1628, zmr.getFileContent("manifest.mf").length);
		assertEquals(0, zmr.getFileMatching(".*.bad").size());
		assertEquals(1, zmr.getFileMatching(".*.mf").size());
		assertEquals(3, zmr.getMetaInfo("manifest.mf").size());
		final IResolver resolver = zmr.getResolver();
		assertNull(resolver.resolvePath("bad"));
	}

	@Test
	void testDouble() throws Exception {
		final Path p = buildDoubleZip();
		final ManoResource mr = new PhysResource(0, new File(p.toFile(), "mano-mano.zip").toString());
		final DoubleZipMr zmr = new DoubleZipMr(mr);
		assertFalse(zmr.exist("test.zip"));
		assertTrue(zmr.exist("manifest.mf"));
		assertEquals(1628, zmr.getFileContent("manifest.mf").length);
		assertEquals(1379, zmr.getFileContent("cert.cert").length);
		assertEquals(0, zmr.getFileMatching(".*.bad").size());
		assertEquals(1, zmr.getFileMatching(".*.mf").size());
		assertEquals(1, zmr.getFileMatching(".*.cert").size());
		assertEquals(3, zmr.getMetaInfo("manifest.mf").size());
		assertEquals(3, zmr.getMetaInfo("cert.cert").size());
	}
}
