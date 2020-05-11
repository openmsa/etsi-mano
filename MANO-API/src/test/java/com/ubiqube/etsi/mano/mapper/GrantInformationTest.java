package com.ubiqube.etsi.mano.mapper;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.ResourceDefinition;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class GrantInformationTest {
	private final DefaultMapperFactory mapperFactory;
	private final PodamFactoryImpl podam;

	public GrantInformationTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);

		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
	}

	@Test
	void testName() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final ResourceDefinition avc = podam.manufacturePojo(ResourceDefinition.class);
		final GrantInformationExt avcDb = mapper.map(avc, GrantInformationExt.class);

		System.out.println("" + avcDb);
	}
}
