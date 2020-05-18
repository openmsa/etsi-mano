package com.ubiqube.etsi.mano.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.AffectedVs;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.AffectedVirtualStorage;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class AffectedVsTest {
	private final DefaultMapperFactory mapperFactory;
	private final PodamFactoryImpl podam;

	public AffectedVsTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);

		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());

	}

	@Test
	void testJsonToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final AffectedVirtualStorage avs = podam.manufacturePojo(AffectedVirtualStorage.class);
		final AffectedVs avsDb = mapper.map(avs, AffectedVs.class);
		assertEquals(avs.getChangeType().toString(), avsDb.getChangeType().toString());
		assertEquals(avs.getId(), avsDb.getId().toString());
		assertEquals(avs.getVirtualStorageDescId(), avsDb.getVirtualStorageDesc().getId());
		assertEquals(avs.getStorageResource().getResourceId(), avsDb.getStorageResource().getResourceId());
		assertEquals(avs.getStorageResource().getResourceProviderId(), avsDb.getStorageResource().getResourceProviderId());
		assertEquals(avs.getStorageResource().getVimConnectionId(), avsDb.getStorageResource().getVimConnectionInformation().getId().toString());
		assertEquals(avs.getStorageResource().getVimLevelResourceType(), avsDb.getStorageResource().getVimLevelResourceType());
	}

	@Test
	void testDaoToJson() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final AffectedVs avsDb = podam.manufacturePojo(AffectedVs.class);
		avsDb.setChangeType(ChangeType.ADDED);
		final AffectedVirtualStorage avs = mapper.map(avsDb, AffectedVirtualStorage.class);
		assertEquals(avs.getChangeType().toString(), avsDb.getChangeType().toString());
		assertEquals(avs.getId(), avsDb.getId().toString());
		assertEquals(avs.getVirtualStorageDescId(), avsDb.getVirtualStorageDesc().getId().toString());
		assertEquals(avs.getStorageResource().getResourceId(), avsDb.getStorageResource().getResourceId());
		assertEquals(avs.getStorageResource().getResourceProviderId(), avsDb.getStorageResource().getResourceProviderId());
		assertEquals(avs.getStorageResource().getVimConnectionId(), avsDb.getStorageResource().getVimConnectionInformation().getId().toString());
		assertEquals(avs.getStorageResource().getVimLevelResourceType(), avsDb.getStorageResource().getVimLevelResourceType());
	}
}
