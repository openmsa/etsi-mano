package com.ubiqube.etsi.mano.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.ubiqube.bean.TestFactory;
import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class VnfLcmOpOccsTest {
	private final DefaultMapperFactory mapperFactory;

	public VnfLcmOpOccsTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testJsonToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final VnfLcmOpOcc lcm = new VnfLcmOpOcc();
		lcm.setError(TestFactory.createProblemDetails());
		lcm.setStartTime(new Date());
		lcm.setVnfInstanceId("3bba2147-147a-41ab-a3ec-1a39e1b6d922");

		final VnfLcmOpOccs lcmP = mapper.map(lcm, VnfLcmOpOccs.class);
		assertNotNull(lcmP.getVnfInstanceId());
		assertNotNull(lcmP.getVnfInstanceId().getId());
		assertEquals("3bba2147-147a-41ab-a3ec-1a39e1b6d922", lcmP.getVnfInstanceId().getId().toString());

		assertNotNull(lcmP.getError());
		final FailureDetails error = lcmP.getError();
		assertEquals("detail", error.getDetail());
	}
}
