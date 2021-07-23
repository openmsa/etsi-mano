/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.nfvo.v261.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;
import com.ubiqube.etsi.mano.nfvo.v261.OrikaConfigurationNfvo261;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.PnfdInfo;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.generator.EclipseJdtCompilerStrategy;

public class PnfDescriptorTest {
	private final DefaultMapperFactory mapperFactory;

	public PnfDescriptorTest() {
		final OrikaConfigurationNfvo261 orikaConfiguration = new OrikaConfigurationNfvo261();
		mapperFactory = new DefaultMapperFactory.Builder().compilerStrategy(new EclipseJdtCompilerStrategy()).build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testJsonToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final PnfdInfo pnf = new PnfdInfo();
		pnf.setPnfdersion("1.0.0");
		final PnfDescriptor pnfD = mapper.map(pnf, PnfDescriptor.class);
		assertEquals("1.0.0", pnfD.getPnfdersion());
	}
}
