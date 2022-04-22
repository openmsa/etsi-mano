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
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.sol004.builder.CertificateSigner;
import com.ubiqube.etsi.mano.sol004.builder.CsarBuilder;
import com.ubiqube.etsi.mano.sol004.builder.Pkcs7Certificate;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
class BuilderTest {

	@Test
	void testName() throws Exception {
		CsarBuilder.builder()
				.ofDoubleZip()
				.addEntry(new File("src/test/resources/manifest.mf"), "myFile")
				.build(new File("/tmp/tosca.zip"));
		assertTrue(Files.exists(Paths.get("/tmp/tosca.zip")));
	}

	@Test
	void testSignature() throws Exception {
		final File publicKey = new File("src/test/resources/mano-qa-sol004.pub.pem");
		final File privateKey = new File("src/test/resources/mano-qa-sol004.pem");
		final CertificateSigner certificateSigner = new Pkcs7Certificate(privateKey, publicKey);
		CsarBuilder.builder()
				.ofDoubleZip()
				.addEntry(new File("src/test/resources/manifest.mf"), "myFile")
				.certificate(certificateSigner)
				.build(new File("/tmp/tosca.zip"));
		assertTrue(Files.exists(Paths.get("/tmp/tosca.zip")));
	}

	@Test
	void testFulSol001() throws Exception {
		final File publicKey = new File("src/test/resources/mano-qa-sol004.pub.pem");
		final File privateKey = new File("src/test/resources/mano-qa-sol004.pem");
		final CertificateSigner certificateSigner = new Pkcs7Certificate(privateKey, publicKey);
		CsarBuilder.builder()
				.ofDoubleZip()
				.addEntry(new File("src/test/resources/scale-vnf/Definitions/etsi_nfv_sol001_vnfd_types.yaml"), "Definitions/etsi_nfv_sol001_vnfd_types.yaml")
				.addEntry(new File("src/test/resources/scale-vnf/Definitions/tosca_ubi_scale.yaml"), "Definitions/tosca_ubi_scale.yaml")
				.entryPoint("Definitions/tosca_ubi_scale.yaml")
				.certificate(certificateSigner)
				.build(new File("/tmp/tosca.zip"));
		assertTrue(Files.exists(Paths.get("/tmp/tosca.zip")));
	}
}
