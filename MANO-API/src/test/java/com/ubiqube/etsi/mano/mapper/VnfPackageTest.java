package com.ubiqube.etsi.mano.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.bean.TestFactory;
import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.common.Checksum;
import com.ubiqube.etsi.mano.factory.VnfPackageFactory;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageSoftwareImageInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

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

		final Map<String, String> udd = vnfDao.getUserDefinedData();
		assertEquals(1, udd.size());
		assertEquals("TMA49", udd.get("vimId"));
	}

	@Test
	void testDaoToJson() throws Exception {
		final VnfPackage vnfDao = new VnfPackage();
		final Set<VnfCompute> vnfCompute = new HashSet<>();
		final VnfCompute compute = new VnfCompute();
		final SoftwareImage softwareImage1 = new SoftwareImage();

		final Checksum checksum = new Checksum();
		checksum.setAlgorithm("SHA-512");
		checksum.setHash("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		softwareImage1.setChecksum(checksum);
		softwareImage1.setContainerFormat("BARE");
		softwareImage1.setChecksum(checksum);
		compute.setSoftwareImage(softwareImage1);
		vnfCompute.add(compute);
		vnfDao.setVnfCompute(vnfCompute);

		final Set<VnfStorage> vnfStorage = new HashSet<>();
		final VnfStorage vnfStorage1 = new VnfStorage();
		final SoftwareImage softwareImage2 = new SoftwareImage();

		final Checksum checksum2 = new Checksum();
		checksum2.setAlgorithm("SHA-512");
		checksum2.setHash("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		softwareImage2.setChecksum(checksum2);
		vnfStorage1.setSoftwareImage(softwareImage2);
		vnfStorage.add(vnfStorage1);
		vnfDao.setVnfStorage(vnfStorage);

		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfPkgInfo vnfPkgInfo = mapper.map(vnfDao, VnfPkgInfo.class);
		final List<VnfPackageSoftwareImageInfo> swImages = vnfPkgInfo.getSoftwareImages();
		assertEquals(2, swImages.size());

	}
}
