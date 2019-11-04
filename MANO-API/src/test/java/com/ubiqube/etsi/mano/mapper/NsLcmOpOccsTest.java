package com.ubiqube.etsi.mano.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.ubiqube.bean.TestFactory;
import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class NsLcmOpOccsTest {
	private final DefaultMapperFactory mapperFactory;

	public NsLcmOpOccsTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testJsonToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final NsLcmOpOccsNsLcmOpOcc nsLcmOpOcc = new NsLcmOpOccsNsLcmOpOcc();
		nsLcmOpOcc.setError(TestFactory.createProblemDetails());
		nsLcmOpOcc.setStartTime(new Date());
		nsLcmOpOcc.setStateEnteredTime(new Date());

		final NsLcmOpOccs nloo = mapper.map(nsLcmOpOcc, NsLcmOpOccs.class);
		assertNotNull(nloo.getError());
		assertEquals("detail", nloo.getError().getDetail());
		assertNotNull(nloo.getStartTime());
		assertNotNull(nloo.getStateEnteredTime());
	}
}
