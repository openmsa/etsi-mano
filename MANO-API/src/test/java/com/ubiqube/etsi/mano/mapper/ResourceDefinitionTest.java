package com.ubiqube.etsi.mano.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.ResourceDefinition;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class ResourceDefinitionTest {

	private final DefaultMapperFactory mapperFactory;
	private final PodamFactoryImpl podam;

	public ResourceDefinitionTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		podam = new PodamFactoryImpl();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testJson2Db() throws Exception {
		final ResourceDefinition resourceDefinition = podam.manufacturePojo(ResourceDefinition.class);
		resourceDefinition.setId("a59cad85-ea40-46c2-9189-5cd38ec3d170");
		resourceDefinition.setVduId("a59cad85-ea40-46c2-9189-5cd38ec3d170");
		resourceDefinition.getResource().setVimConnectionId("a59cad85-ea40-46c2-9189-5cd38ec3d170");
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final ResourceHandleEntity res = mapper.map(resourceDefinition, ResourceHandleEntity.class);
		assertNull(res.getId());
		assertNotNull(res.getResourceId());
		assertNotNull(res.getResourceProviderId());
		assertNotNull(res.getVduId());
		assertNotNull(res.getVimConnectionInformation());
		assertNotNull(res.getVimConnectionInformation().getId());
		assertNotNull(res.getVimLevelResourceType());
	}

	@Test
	void testDb2Json() throws Exception {
		final ResourceHandleEntity resourceHandleEntity = podam.manufacturePojo(ResourceHandleEntity.class);
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final ResourceDefinition res = mapper.map(resourceHandleEntity, ResourceDefinition.class);
		assertEquals(resourceHandleEntity.getResourceId(), res.getResource().getResourceId());
		assertEquals(resourceHandleEntity.getResourceProviderId(), res.getResource().getResourceProviderId());
		assertEquals(resourceHandleEntity.getVimConnectionInformation().getId().toString(), res.getResource().getVimConnectionId());
		assertEquals(resourceHandleEntity.getVimLevelResourceType(), res.getResource().getVimLevelResourceType());
		assertEquals(resourceHandleEntity.getVduId().toString(), res.getVduId());
		assertEquals(resourceHandleEntity.getResourceId(), res.getResource().getResourceId());
		assertEquals(resourceHandleEntity.getId().toString(), res.getId());
	}
}
