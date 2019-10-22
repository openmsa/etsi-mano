package com.ubiqube.etsi.mano.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.factory.NsdFactories;
import com.ubiqube.etsi.mano.factory.VnfPackageFactory;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoAdditionalArtifacts;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoChecksum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class OrikaTest {

	@Test
	void testName() throws Exception {
		final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		final ConverterFactory converterFactory = mapperFactory.getConverterFactory();
		converterFactory.registerConverter(new UuidConverter());
		final MapperFacade mapper = mapperFactory.getMapperFacade();

		final VnfPkgInfo vnf = VnfPackageFactory.createVnfPkgInfo(new HashMap<String, Object>());
		final VnfPackagesVnfPkgInfoAdditionalArtifacts additionalArtifactsItem = new VnfPackagesVnfPkgInfoAdditionalArtifacts();
		final VnfPackagesVnfPkgInfoChecksum checksum = new VnfPackagesVnfPkgInfoChecksum();
		checksum.algorithm("SHA-512").hash("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		additionalArtifactsItem.artifactPath("/path").checksum(checksum);
		vnf.addAdditionalArtifactsItem(additionalArtifactsItem);

		final VnfPackage cnv = mapper.map(vnf, VnfPackage.class);
		System.out.println("" + cnv);
	}

	@Test
	void testListIdObjectId() {
		final MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
				.build();
		final ConverterFactory converterFactory = mapperFactory.getConverterFactory();
		converterFactory.registerConverter(new UuidConverter());
		mapperFactory.classMap(NsDescriptorsNsdInfo.class, NsdPackage.class)
				.field("vnfPkgIds{}", "vnfPkgIds{id}")
				.byDefault()
				.register();

		final NsDescriptorsNsdInfo nsd = NsdFactories.createNsDescriptorsNsdInfo();
		nsd.addVnfPkgIdsItem("d5bbe3c1-23a2-4e72-8e00-66cc6ba2061f");
		nsd.addVnfPkgIdsItem("17372129-0590-4532-ace3-7c35eaf0c7c4");
		final NsdPackage nsdDao = mapperFactory.getMapperFacade().map(nsd, NsdPackage.class);
		final Set<VnfPackage> vnfPkgIds = nsdDao.getVnfPkgIds();
		assertEquals(2, vnfPkgIds.size());
		final VnfPackage[] vnf = vnfPkgIds.toArray(new VnfPackage[0]);
		assertEquals("d5bbe3c1-23a2-4e72-8e00-66cc6ba2061f", vnf[0].getId().toString());
		assertEquals("17372129-0590-4532-ace3-7c35eaf0c7c4", vnf[1].getId().toString());
	}
}
