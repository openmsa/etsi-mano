package com.ubiqube.etsi.mano.service.pkg;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

public class ToscaPackageProviderTest {
	private final ToscaPackageProvider tpp;

	public ToscaPackageProviderTest() throws IOException {
		final Path path = Paths.get("src/test/resources/ubi-tosca.csar");
		final byte[] data = Files.readAllBytes(path);
		tpp = new ToscaPackageProvider(data);
	}

	@Test
	void testSoftwareImage01() throws Exception {
		final Set<AdditionalArtifact> aa = tpp.getAdditionalArtefacts();
		System.out.println("" + aa);
	}

	@Test
	void testComputeNode01() throws Exception {
		final Set<VnfCompute> vnfCn = tpp.getVnfComputeNodes();
		System.out.println("" + vnfCn);
		assertEquals(2, vnfCn.size());
		final VnfCompute cn = vnfCn.iterator().next();
		// assertEquals("leftVdu01", cn.getToscaName());
	}

	@Test
	void testStorage01() throws Exception {
		final Set<VnfStorage> storages = tpp.getVnfStorages();
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
		final Set<VnfVl> vnfVl = tpp.getVnfVirtualLinks();
		assertEquals(3, vnfVl.size());
		final VnfVl vl = vnfVl.iterator().next();
		// assertEquals("middleVl01", vl.getToscaName());
		final Set<IpPool> ipPool = vl.getVlProfileEntity().getVirtualLinkProtocolData().iterator().next().getIpAllocationPools();
		assertEquals(1, ipPool.size());
	}

	@Test
	void testVirtualCp01() throws Exception {
		final Set<VnfLinkPort> vnfCp = tpp.getVnfVduCp();
		assertEquals(4, vnfCp.size());
		final VnfLinkPort cp = vnfCp.iterator().next();
		// assertEquals("cpLc02", cp.getToscaName());
	}

	@Test
	void testVnfExtCp() throws Exception {
		final Set<VnfExtCp> extCp = tpp.getVnfExtCp();
		assertEquals(4, extCp.size());
	}

	@Test
	void testScalingAspect() throws Exception {
		final Set<ScalingAspect> list = tpp.getScalingAspects();
		System.out.println("" + list);
	}
}
