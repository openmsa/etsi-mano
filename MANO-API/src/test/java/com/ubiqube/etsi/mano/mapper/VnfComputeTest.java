package com.ubiqube.etsi.mano.mapper;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;

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
		final VnfInstantiatedCompute avc = mapper.map(avcDb, VnfInstantiatedCompute.class);
		assertNull(avc.getId());
		assertEquals(avcDb.getId(), avc.getVduId());
		assertEquals(avcDb.getStorages().size(), avc.getStorageResourceIds().size());
	}

}
