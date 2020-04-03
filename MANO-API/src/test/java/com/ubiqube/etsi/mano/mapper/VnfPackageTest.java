package com.ubiqube.etsi.mano.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.bean.TestFactory;
import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.common.Checksum;
import com.ubiqube.etsi.mano.factory.VnfPackageFactory;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class VnfPackageTest {
	private final DefaultMapperFactory mapperFactory;

	public VnfPackageTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testJsonToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final Map<String, String> userData = new HashMap<>();
		userData.put("vimId", "TMA49");
		final VnfPackage vnf = VnfPackageFactory.createVnfPkgInfo(userData);
		final AdditionalArtifact additionalArtifactsItem = new AdditionalArtifact();
		final Checksum checksum = new Checksum();
		checksum.setAlgorithm("SHA-512");
		checksum.setHash("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		additionalArtifactsItem.setArtifactPath("/path");
		additionalArtifactsItem.setChecksum(checksum);
		final Set<AdditionalArtifact> list = new HashSet<>();
		list.add(additionalArtifactsItem);
		vnf.setAdditionalArtifacts(list);
		vnf.setChecksum(checksum);
		final Set<SoftwareImage> softwareImages = new HashSet<>();
		softwareImages.add(TestFactory.createVnfPackagesVnfPkgInfoSoftwareImages());
		vnf.setSoftwareImages(softwareImages);

		final VnfPackage vnfDao = mapper.map(vnf, VnfPackage.class);
		System.out.println("" + vnfDao);
		assertNotNull(vnfDao.getChecksum());
		assertEquals("SHA-512", vnfDao.getChecksum().getAlgorithm());
		assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", vnfDao.getChecksum().getHash());
		final Set<AdditionalArtifact> add = vnfDao.getAdditionalArtifacts();
		final AdditionalArtifact addP[] = add.toArray(new AdditionalArtifact[0]);
		assertEquals(1, add.size());
		assertEquals("/path", addP[0].getArtifactPath());
		final com.ubiqube.etsi.mano.dao.mano.common.Checksum check = addP[0].getChecksum();
		assertNotNull(check);
		assertEquals("SHA-512", check.getAlgorithm());
		assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", check.getHash());

		final Set<SoftwareImage> sil = vnfDao.getSoftwareImages();
		assertEquals(1, sil.size());
		final SoftwareImage si = sil.iterator().next();
		assertNotNull(si.getChecksum());
		assertEquals("SHA-512", si.getChecksum().getAlgorithm());
		assertEquals("e7c22b994c59d9cf2b48e549b1e24666636045930d3da7c1acb299d1c3b7f931f94aae41edda2c2b207a36e10f8bcb8d45223e54878f5b316e7ce3b6bc019629", si.getChecksum().getHash());
		assertNotNull(si.getContainerFormat());
		assertEquals("BARE", si.getContainerFormat().toString());
		assertEquals("/mnt/images/myimages.raw", si.getImagePath());
		assertEquals(12345, si.getSize());

		final Map<String, String> udd = vnfDao.getUserDefinedData();
		assertEquals(1, udd.size());
		assertEquals("TMA49", udd.get("vimId"));
	}
}
