package com.ubiqube.etsi.mano.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.factory.NsdFactories;
import com.ubiqube.etsi.mano.factory.VnfPackageFactory;
import com.ubiqube.etsi.mano.model.KeyValuePairs;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.Checksum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageArtifactInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class OrikaTest {

	private final DefaultMapperFactory mapperFactory;

	public OrikaTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testMapVnfArtifactChecksum() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();

		final VnfPkgInfo vnf = VnfPackageFactory.createVnfPkgInfo(new KeyValuePairs());
		final VnfPackageArtifactInfo additionalArtifactsItem = new VnfPackageArtifactInfo();
		final Checksum checksum = new Checksum();
		checksum.algorithm("SHA-512").hash("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		additionalArtifactsItem.artifactPath("/path").checksum(checksum);
		vnf.addAdditionalArtifactsItem(additionalArtifactsItem);

		final VnfPackage cnv = mapper.map(vnf, VnfPackage.class);
		System.out.println("" + cnv);
	}

	@Test
	void testListIdObjectId() {
		final NsdInfo nsd = NsdFactories.createNsdInfo();
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
