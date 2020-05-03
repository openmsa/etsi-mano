package com.ubiqube.etsi.mano.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.AffectedVs;
import com.ubiqube.etsi.mano.dao.mano.VirtualStorageInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class VnfStorageTest {
	private final DefaultMapperFactory mapperFactory;
	private final PodamFactoryImpl podam;

	public VnfStorageTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);

		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
	}

	@Test
	void testVnfStorage2VirtualStorageInfo() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfStorage avcDb = podam.manufacturePojo(VnfStorage.class);
		final VirtualStorageInfo avc = mapper.map(avcDb, VirtualStorageInfo.class);
		assertNull(avc.getId());
		assertNotNull(avc.getStorageResource());
		assertEquals(avcDb.getId(), avc.getStorageResource().getVduId());
		assertEquals(avcDb.getId(), avc.getVirtualStorageDescId());
	}

	@Test
	void testVnfStorage2AffectedVs() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfStorage avcDb = podam.manufacturePojo(VnfStorage.class);
		final AffectedVs avc = mapper.map(avcDb, AffectedVs.class);
		assertNull(avc.getId());
		assertNotNull(avc.getStorageResource());
		assertEquals(avcDb.getId(), avc.getStorageResource().getVduId());
		assertEquals(avcDb.getId(), avc.getVirtualStorageDescId());
	}
}
