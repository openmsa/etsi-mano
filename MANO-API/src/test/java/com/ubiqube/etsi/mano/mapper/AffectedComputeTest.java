package com.ubiqube.etsi.mano.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.AffectedCompute;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.AffectedVnfc;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class AffectedComputeTest {
	private final DefaultMapperFactory mapperFactory;
	private final PodamFactoryImpl podam;

	public AffectedComputeTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);

		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
	}

	@Test
	void testJsonToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final AffectedVnfc avc = podam.manufacturePojo(AffectedVnfc.class);
		final AffectedCompute avcDb = mapper.map(avc, AffectedCompute.class);
		assertEquals(avc.getChangeType().toString(), avcDb.getChangeType().toString());
		assertEquals(avc.getId(), avcDb.getId().toString());
		assertEquals(avc.getVduId(), avcDb.getVduId().toString());
	}

	@Test
	void testDaoToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final AffectedCompute avcDb = podam.manufacturePojo(AffectedCompute.class);
		avcDb.setChangeType(ChangeType.ADDED);
		final AffectedVnfc avc = mapper.map(avcDb, AffectedVnfc.class);
		assertEquals(avc.getChangeType().toString(), avcDb.getChangeType().toString());
		assertEquals(avc.getId(), avcDb.getId().toString());
		assertEquals(avc.getVduId(), avcDb.getVduId().toString());
	}
}
