package com.ubiqube.etsi.mano.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.AffectedVl;
import com.ubiqube.etsi.mano.model.nslcm.sol003.AffectedVirtualLink;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class AffectedVlTest {
	private final DefaultMapperFactory mapperFactory;
	private final PodamFactoryImpl podam;

	public AffectedVlTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);

		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
	}

	@Test
	void testJsonToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final AffectedVirtualLink avl = podam.manufacturePojo(AffectedVirtualLink.class);
		final AffectedVl avlDb = mapper.map(avl, AffectedVl.class);
		assertEquals(avl.getChangeType().toString(), avlDb.getChangeType().toString());
		assertEquals(avl.getId(), avlDb.getId().toString());
		assertEquals(avl.getVirtualLinkDescId(), avlDb.getVirtualLinkDesc().getId());
		assertEquals(avl.getNetworkResource().getResourceId(), avlDb.getNetworkResource().getResourceId());
		assertEquals(avl.getNetworkResource().getResourceProviderId(), avlDb.getNetworkResource().getResourceProviderId());
		assertEquals(avl.getNetworkResource().getVimConnectionId(), avlDb.getNetworkResource().getVimConnectionInformation().getId().toString());
		assertEquals(avl.getNetworkResource().getVimLevelResourceType(), avlDb.getNetworkResource().getVimLevelResourceType());
	}

	@Test
	void testDaoToJson() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final AffectedVl avlDb = podam.manufacturePojo(AffectedVl.class);
		final AffectedVirtualLink avl = mapper.map(avlDb, AffectedVirtualLink.class);
		assertEquals(avl.getChangeType().toString(), avlDb.getChangeType().toString());
		assertEquals(avl.getId(), avlDb.getId().toString());
		assertEquals(avl.getVirtualLinkDescId(), avlDb.getVirtualLinkDesc().getId());
		assertEquals(avl.getNetworkResource().getResourceId(), avlDb.getNetworkResource().getResourceId());
		assertEquals(avl.getNetworkResource().getResourceProviderId(), avlDb.getNetworkResource().getResourceProviderId());
		assertEquals(avl.getNetworkResource().getVimConnectionId(), avlDb.getNetworkResource().getVimConnectionInformation().getId().toString());
		assertEquals(avl.getNetworkResource().getVimLevelResourceType(), avlDb.getNetworkResource().getVimLevelResourceType());
	}
}
