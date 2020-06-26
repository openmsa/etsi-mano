package com.ubiqube.etsi.mano.mapper;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.VirtualLinkInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class VnfVlTest {
	private final DefaultMapperFactory mapperFactory;
	private final PodamFactoryImpl podam;

	public VnfVlTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);

		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
	}

	@Test
	void testVnfVl2VnfVirtualLinkInfo() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfVl avcDb = podam.manufacturePojo(VnfVl.class);
		final VirtualLinkInfo avc = mapper.map(avcDb, VirtualLinkInfo.class);
		assertNotNull(avc.getId());
		// assertEquals(avcDb.getId(), avc.getVnfVirtualLinkDescId());
	}

}
