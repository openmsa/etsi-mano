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

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.sol004.ZipUtil.Entry;
import com.ubiqube.etsi.mano.sol004.vfs.DirectVfs;
import com.ubiqube.etsi.mano.sol004.vfs.DirectZip;
import com.ubiqube.etsi.mano.sol004.vfs.VirtualFileSystem;

@SuppressWarnings("static-method")
class ToscaTest {

	private static void buildCertCsar() throws IOException {
		ZipUtil.makeToscaZip("/tmp/tosca.csar", Entry.of("sol004-single/sample.cert", "sample.cert"),
				Entry.of("sol004-single/ubi-tm21-vnf.csar", "ubi-tm21-vnf.csar"),
				Entry.of("sol004-single/ubi-tm21-vnf.csar.sig.cms", "ubi-tm21-vnf.csar.sig.cms"));
	}

	private static void buildScaleCsar() throws IOException {
		ZipUtil.makeToscaZip("/tmp/tosca.csar", Entry.of("scale-vnf/Definitions/tosca_ubi_scale.yaml", "Definitions/tosca_ubi_scale.yaml"),
				Entry.of("scale-vnf/Definitions/etsi_nfv_sol001_vnfd_types.yaml", "Definitions/etsi_nfv_sol001_vnfd_types.yaml"),
				Entry.of("scale-vnf/TOSCA-Metadata/manifest.mf", "TOSCA-Metadata/manifest.mf"),
				Entry.of("scale-vnf/TOSCA-Metadata/metadata", "TOSCA-Metadata/metadata"),
				Entry.of("scale-vnf/TOSCA-Metadata/TOSCA.meta", "TOSCA-Metadata/TOSCA.meta"));
	}

	private static void buildVnffgNsdCsar() throws IOException {
		ZipUtil.makeToscaZip("/tmp/tosca.csar", Entry.of("vnffg-nsd/Definitions/nsd_ubi.yaml", "Definitions/nsd_ubi.yaml"),
				Entry.of("vnffg-nsd/Definitions/etsi_nfv_sol001_nsd_types.yaml", "Definitions/etsi_nfv_sol001_nsd_types.yaml"),
				Entry.of("vnffg-nsd/manifest.mf", "manifest.mf"),
				Entry.of("vnffg-nsd/TOSCA-Metadata/TOSCA.meta", "TOSCA-Metadata/TOSCA.meta"));
	}

	@Test
	void testName() throws Exception {
		buildVnffgNsdCsar();
		final Sol004Onboarding so = new Sol004Onboarding();
		so.getToscaMode("/tmp/tosca.csar");
	}

	@Test
	void testAll() {
		final VirtualFileSystem vfs = new DirectVfs(Paths.get("src/test/resources/vnffg-nsd/"));
		final CsarArchive ta = new CsarArchive(vfs, "test.zip");
	}

	@Test
	void testFlat() throws IOException {
		buildCertCsar();
		final VirtualFileSystem vfs = new DirectVfs(Paths.get("/tmp/tosca.csar"));
		final CsarArchive ta = new CsarArchive(vfs, "test.zip");
	}

	@Test
	void testZip01() throws IOException {
		buildScaleCsar();
		final VirtualFileSystem vfs = new DirectZip(Paths.get("/tmp/tosca.csar"));
		final CsarArchive ta = new CsarArchive(vfs, "ubi-vnffg-nsd.csar");
	}
}
