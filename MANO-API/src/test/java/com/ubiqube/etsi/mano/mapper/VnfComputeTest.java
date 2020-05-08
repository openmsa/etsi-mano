package com.ubiqube.etsi.mano.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.AffectedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiedCompute;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class VnfComputeTest {
	private final DefaultMapperFactory mapperFactory;
	private final PodamFactoryImpl podam;

	public VnfComputeTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);

		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
	}

	@Test
	void testVnfc2VnfInstantiedCompute() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfCompute avcDb = podam.manufacturePojo(VnfCompute.class);
		final VnfInstantiedCompute avc = mapper.map(avcDb, VnfInstantiedCompute.class);
		assertNull(avc.getId());
		assertNotNull(avc.getCompResource());
		assertEquals(avcDb.getId(), avc.getCompResource().getVduId());
		assertEquals(avcDb.getId(), avc.getComputeResource().getVduId());
		assertEquals(avcDb.getId(), avc.getVduId());
		assertEquals(avcDb.getStorages().size(), avc.getStorageResourceIds().size());
	}

	@Test
	void testVnfc2AffectedCompute() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfCompute avcDb = podam.manufacturePojo(VnfCompute.class);
		final AffectedCompute avc = mapper.map(avcDb, AffectedCompute.class);
		assertNull(avc.getId());
		assertNotNull(avc.getComputeResource());
		assertEquals(avcDb.getId(), avc.getComputeResource().getVduId());
		assertEquals(avcDb.getId(), avc.getVduId());
	}
}
