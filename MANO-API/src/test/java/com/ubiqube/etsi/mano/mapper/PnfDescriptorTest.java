package com.ubiqube.etsi.mano.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfo;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class PnfDescriptorTest {
	private final DefaultMapperFactory mapperFactory;

	public PnfDescriptorTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testJsonToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final PnfDescriptorsPnfdInfo pnf = new PnfDescriptorsPnfdInfo();
		pnf.setPnfdersion("1.0.0");
		final PnfDescriptor pnfD = mapper.map(pnf, PnfDescriptor.class);
		assertEquals("1.0.0", pnfD.getPnfdersion());
	}
}
