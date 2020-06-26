package com.ubiqube.etsi.mano.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfLcmOpOcc;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class VnfLcmOpOccsTest {
	private final DefaultMapperFactory mapperFactory;
	private final PodamFactoryImpl podam;

	public VnfLcmOpOccsTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testJsonToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfLcmOpOcc lcm = podam.manufacturePojo(VnfLcmOpOcc.class);
		final VnfLcmOpOccs lcmP = mapper.map(lcm, VnfLcmOpOccs.class);
		assertNotNull(lcmP.getVnfInstance());
		assertNotNull(lcmP.getVnfInstance().getId());
		assertEquals(lcm.getVnfInstanceId(), lcmP.getVnfInstance().getId().toString());
		assertEquals(lcm.getCancelMode(), lcmP.getCancelMode());
		assertEquals(lcm.getGrantId(), lcmP.getGrantId());
		assertEquals(lcm.getId(), lcmP.getId().toString());
		assertEquals(lcm.getOperation(), lcmP.getOperation());
		assertEquals(lcm.getOperationState(), lcmP.getOperationState());
		assertNotNull(lcmP.getError());
		assertEquals(lcm.getStartTime(), lcmP.getStartTime());
		assertEquals(lcm.getStateEnteredTime(), lcmP.getStateEnteredTime());
		assertEquals(lcm.getResourceChanges().getAffectedVirtualLinks().size(), lcmP.getResourceChanges().getAffectedVirtualLinks().size());
		assertEquals(lcm.getResourceChanges().getAffectedVirtualStorages().size(), lcmP.getResourceChanges().getAffectedVirtualStorages().size());
		assertEquals(lcm.getResourceChanges().getAffectedVnfcs().size(), lcmP.getResourceChanges().getAffectedVnfcs().size());
	}

	/**
	 * Infinite loop in extCp
	 *
	 * @throws Exception
	 */
	void testDaoToJson() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfLcmOpOccs lcmP = podam.manufacturePojo(VnfLcmOpOccs.class);
		final VnfLcmOpOcc lcm = mapper.map(lcmP, VnfLcmOpOcc.class);
		assertNotNull(lcmP.getVnfInstance());
		assertNotNull(lcmP.getVnfInstance().getId());
		assertEquals(lcm.getVnfInstanceId(), lcmP.getVnfInstance().getId().toString());
		assertEquals(lcm.getCancelMode(), lcmP.getCancelMode());
		assertEquals(lcm.getGrantId(), lcmP.getGrantId());
		assertEquals(lcm.getId(), lcmP.getId().toString());
		assertEquals(lcm.getOperation(), lcmP.getOperation());
		assertEquals(lcm.getOperationState(), lcmP.getOperationState());
		assertNotNull(lcmP.getError());
		assertEquals(lcm.getStartTime(), lcmP.getStartTime());
		assertEquals(lcm.getStateEnteredTime(), lcmP.getStateEnteredTime());
		assertEquals(lcm.getResourceChanges().getAffectedVirtualLinks().size(), lcmP.getResourceChanges().getAffectedVirtualLinks().size());
		assertEquals(lcm.getResourceChanges().getAffectedVirtualStorages().size(), lcmP.getResourceChanges().getAffectedVirtualStorages().size());
		assertEquals(lcm.getResourceChanges().getAffectedVnfcs().size(), lcmP.getResourceChanges().getAffectedVnfcs().size());
	}

}
