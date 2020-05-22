package com.ubiqube.etsi.mano.mapper;

import com.ubiqube.etsi.mano.config.OrikaConfiguration;

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

}
