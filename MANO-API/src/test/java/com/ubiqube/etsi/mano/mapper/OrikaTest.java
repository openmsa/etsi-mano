package com.ubiqube.etsi.mano.mapper;

import java.util.HashMap;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.factory.VnfPackageFactory;
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

		final VnfPkgInfo vnf = VnfPackageFactory.createVnfPkgInfo(UUID.randomUUID().toString(), new HashMap<String, Object>());
		final VnfPackagesVnfPkgInfoAdditionalArtifacts additionalArtifactsItem = new VnfPackagesVnfPkgInfoAdditionalArtifacts();
		final VnfPackagesVnfPkgInfoChecksum checksum = new VnfPackagesVnfPkgInfoChecksum();
		checksum.algorithm("SHA-512").hash("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		additionalArtifactsItem.artifactPath("/path").checksum(checksum);
		vnf.addAdditionalArtifactsItem(additionalArtifactsItem);

		final VnfPackage cnv = mapper.map(vnf, VnfPackage.class);
		System.out.println("" + cnv);
	}
}
