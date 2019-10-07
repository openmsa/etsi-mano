package com.ubiqube.etsi.mano.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.VnfInstanceInstantiatedVnfInfo;
import com.ubiqube.etsi.mano.model.nslcm.VnfOperationalStateType;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class VnfInstanceTest {
	private final DefaultMapperFactory mapperFactory;

	public VnfInstanceTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testJsonToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfInstance nsInstancesNsInstanceVnfInstance = new VnfInstance();
		nsInstancesNsInstanceVnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
		nsInstancesNsInstanceVnfInstance.setVimId("abcdef");
		nsInstancesNsInstanceVnfInstance.setVnfdId("1234");
		nsInstancesNsInstanceVnfInstance.setVnfInstanceDescription("description");
		nsInstancesNsInstanceVnfInstance.setVnfInstanceName("instance_name");
		nsInstancesNsInstanceVnfInstance.setVnfPkgId("3bba2147-147a-41ab-a3ec-1a39e1b6d922");
		final VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo = new VnfInstanceInstantiatedVnfInfo();
		instantiatedVnfInfo.setFlavourId("flavour");
		instantiatedVnfInfo.setVnfState(VnfOperationalStateType.STARTED);
		nsInstancesNsInstanceVnfInstance.setInstantiatedVnfInfo(instantiatedVnfInfo);

		final com.ubiqube.etsi.mano.dao.mano.VnfInstance o = mapper.map(nsInstancesNsInstanceVnfInstance, com.ubiqube.etsi.mano.dao.mano.VnfInstance.class);
		final VnfInstanceInstantiatedVnfInfo ivi = o.getInstantiatedVnfInfo();
		assertNotNull(ivi);
		assertEquals("flavour", ivi.getFlavourId());
		assertEquals("STARTED", ivi.getVnfState().toString());
		assertNotNull(o.getVnfPkg());
		assertNotNull(o.getVnfPkg().getId());
		assertEquals("3bba2147-147a-41ab-a3ec-1a39e1b6d922", o.getVnfPkg().getId().toString());
	}
}
