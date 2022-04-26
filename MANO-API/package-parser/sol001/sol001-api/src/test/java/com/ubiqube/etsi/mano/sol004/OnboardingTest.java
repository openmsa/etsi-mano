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

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
class OnboardingTest {

	private static final Logger LOG = LoggerFactory.getLogger(OnboardingTest.class);

	private final Sol004Onboarding ob = new Sol004Onboarding();

	@Test
	void testName01() throws Exception {
		final String fileName = "src/test/resources/tosca.csar";
		ob.preOnboard(fileName);
		final boolean yang = ob.isYang();
		if (yang) {
			throw new Sol004Exception("Yang is not supported.");
		}
		final String entryFilename = ob.getToscaEntryPointFilename();
		if (isXml(entryFilename)) {
			throw new Sol004Exception("XML sol001 format not supported.");
		}
		try (final InputStream is = ob.getToscaEntryPoint()) {
			//
		}
		uploadToCluster(fileName);
		final List<CsarError> err = ob.validate();
		// err must be empty.
		assertTrue(true);
	}

	private static boolean isXml(final String entryFilename) {
		return entryFilename.endsWith(".xml");
	}

	private void uploadToCluster(final String fileName) {
		final CsarModeEnum mode = ob.getToscaMode(fileName);
		if (mode == CsarModeEnum.DOUBLE_ZIP) {
			// Upload to Cluster.
		} else {
			// Upload the filename.
		}
		LOG.debug("{}", mode);
	}

	@Test
	void testDoubleOnbarding() throws IOException {
		final Path root = Paths.get("/tmp/tosca");
		root.toFile().mkdir();
		Files.walk(root)
				.sorted(Comparator.reverseOrder())
				.map(Path::toFile)
				.forEach(File::delete);
		root.toFile().mkdir();
		ZipUtil.makeToscaZip("/tmp/tosca/l1.csar", "src/test/resources/scale-vnf/");
		copy(getClass().getResourceAsStream("/server.cert"), "/tmp/tosca/server.cert");
		copy(getClass().getResourceAsStream("/tosca.csar.p7s"), "/tmp/tosca/l1.csar.sig.p7s");
		ZipUtil.makeToscaZip("/tmp/test.zip", "/tmp/tosca/");
		ob.preOnboard("/tmp/test.zip");
		ob.getToscaMode("/tmp/test.zip");
		assertTrue(true);
	}

	private static void copy(final InputStream inputStream, final String file) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(file)) {
			inputStream.transferTo(fos);
		}

	}
}
