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
package com.ubiqube.etsi.mano.service.pkg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;
import com.ubiqube.etsi.mano.dao.mano.IpPool;
import com.ubiqube.etsi.mano.dao.mano.ScalingAspect;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.common.Checksum;
import com.ubiqube.etsi.mano.service.pkg.tosca.ToscaPackageProvider;

public class ToscaPackageProviderTest {
	private final ToscaPackageProvider tpp;

	public ToscaPackageProviderTest() throws IOException {
		final Path path = Paths.get("src/test/resources/ubi-tosca.csar");
		final byte[] data = Files.readAllBytes(path);
		tpp = new ToscaPackageProvider(data);
	}

	@Test
	void testSoftwareImage01() throws Exception {
		final Set<AdditionalArtifact> aa = tpp.getAdditionalArtefacts(new HashMap<String, String>());
		System.out.println("" + aa);
		assertNotNull(aa);
	}

	@Test
	void testComputeNode01() throws Exception {
		final Set<VnfCompute> vnfCn = tpp.getVnfComputeNodes(new HashMap<String, String>());
		System.out.println("" + vnfCn);
		assertEquals(2, vnfCn.size());
		final VnfCompute cn = vnfCn.iterator().next();
		// assertEquals("leftVdu01", cn.getToscaName());
		assertNotNull(cn);
	}

	@Test
	void testStorage01() throws Exception {
		final Set<VnfStorage> storages = tpp.getVnfStorages(new HashMap<String, String>());
		System.out.println("" + storages);
		assertEquals(2, storages.size());
		for (final VnfStorage vnfStorage : storages) {
			assertEquals(6000000000L, vnfStorage.getSize());
			assertNotNull(vnfStorage.getToscaName());
			if ("OBJECT".equals(vnfStorage.getType())) {
				//
			} else if ("BLOCK".equals(vnfStorage.getType())) {
				final SoftwareImage swImage = vnfStorage.getSoftwareImage();
				assertNotNull(swImage);
				final Checksum checksum = swImage.getChecksum();
				assertNotNull(checksum);
				assertEquals("SHA-256", checksum.getAlgorithm());
				assertEquals("01ba4719c80b6fe911b091a7c05124b64eeece964e09c058ef8f9805daca546b", checksum.getHash());

				assertEquals("BARE", swImage.getContainerFormat());
				assertEquals("QCOW2", swImage.getDiskFormat());
				assertEquals(6000000000L, swImage.getMinDisk());
				assertEquals(2000000000L, swImage.getMinRam());
				assertEquals("cirros", swImage.getName());
			}
		}
	}

	@Test
	void testVirtualLink01() throws Exception {
		final Set<VnfVl> vnfVl = tpp.getVnfVirtualLinks(new HashMap<String, String>());
		assertEquals(3, vnfVl.size());
		final VnfVl vl = vnfVl.iterator().next();
		// assertEquals("middleVl01", vl.getToscaName());
		final Set<IpPool> ipPool = vl.getVlProfileEntity().getVirtualLinkProtocolData().iterator().next().getIpAllocationPools();
		assertEquals(1, ipPool.size());
	}

	@Test
	void testVirtualCp01() throws Exception {
		final Set<VnfLinkPort> vnfCp = tpp.getVnfVduCp(new HashMap<String, String>());
		assertEquals(4, vnfCp.size());
		final VnfLinkPort cp = vnfCp.iterator().next();
		// assertEquals("cpLc02", cp.getToscaName());
		assertNotNull(cp);
	}

	@Test
	void testVnfExtCp() throws Exception {
		final Set<VnfExtCp> extCp = tpp.getVnfExtCp(new HashMap<String, String>());
		assertEquals(1, extCp.size());
	}

	@Test
	void testScalingAspect() throws Exception {
		final Set<ScalingAspect> list = tpp.getScalingAspects(new HashMap<String, String>());
		System.out.println("" + list);
		assertNotNull(list);
	}

	@Test
	void testVduScalingAspectDeltas() throws Exception {
		final List<com.ubiqube.etsi.mano.service.pkg.bean.VduScalingAspectDeltas> list = tpp.getVduScalingAspectDeltas(new HashMap<String, String>());
		System.out.println("" + list);
		assertNotNull(list);
	}

}
