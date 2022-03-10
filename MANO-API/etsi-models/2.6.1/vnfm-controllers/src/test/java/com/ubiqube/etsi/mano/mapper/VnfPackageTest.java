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
package com.ubiqube.etsi.mano.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageSoftwareImageInfo;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;
import com.ubiqube.etsi.mano.dao.mano.Audit;
import com.ubiqube.etsi.mano.dao.mano.PkgChecksum;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.common.Checksum;
import com.ubiqube.etsi.mano.dao.mano.dto.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.factory.VnfPackageFactory;
import com.ubiqube.etsi.mano.vnfm.v261.OrikaMapperVnfm261;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.generator.EclipseJdtCompilerStrategy;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class VnfPackageTest {
	private final DefaultMapperFactory mapperFactory;
	private final PodamFactoryImpl podam;

	public VnfPackageTest() {
		final OrikaMapperVnfm261 orikaConfiguration = new OrikaMapperVnfm261();
		mapperFactory = new DefaultMapperFactory.Builder().compilerStrategy(new EclipseJdtCompilerStrategy()).build();
		orikaConfiguration.configure(mapperFactory);

		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
	}

	@Test
	void testJsonToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final Map<String, String> userData = new HashMap<>();
		userData.put("vimId", "TMA49");
		final VnfPackage vnf = VnfPackageFactory.createVnfPkgInfo(userData);
		final AdditionalArtifact additionalArtifactsItem = new AdditionalArtifact();
		final PkgChecksum checksum = new PkgChecksum();
		checksum.setAlgorithm("SHA-512");
		checksum.setHash("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		additionalArtifactsItem.setArtifactPath("/path");
		final Set<AdditionalArtifact> list = new HashSet<>();
		list.add(additionalArtifactsItem);
		vnf.setAdditionalArtifacts(list);
		vnf.setChecksum(checksum);
		final Set<SoftwareImage> softwareImages = new HashSet<>();
		// softwareImages.add(TestFactory.createVnfPackagesVnfPkgInfoSoftwareImages());

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
		softwareImage1.setAudit(new Audit());
		softwareImage1.setChecksum(checksum);
		softwareImage1.setContainerFormat("BARE");
		softwareImage1.setChecksum(checksum);
		compute.setSoftwareImage(softwareImage1);
		vnfCompute.add(compute);
		vnfDao.setVnfCompute(vnfCompute);

		final Set<VnfStorage> vnfStorage = new HashSet<>();
		final VnfStorage vnfStorage1 = new VnfStorage();
		final SoftwareImage softwareImage2 = new SoftwareImage();
		softwareImage2.setAudit(new Audit());
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

	void testVnfPackage2VnfInstance() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfPackage avcDb = podam.manufacturePojo(VnfPackage.class);
		final VnfInstance avc = mapper.map(avcDb, VnfInstance.class);
		assertEquals(avcDb.getId(), avc.getVnfPkg().getId());
	}

	void testVnfPackage2Lcm() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfPackage avcDb = podam.manufacturePojo(VnfPackage.class);
		final VnfLcmOpOccs avc = mapper.map(avcDb, VnfLcmOpOccs.class);
		System.out.println("" + avc);
	}
}
