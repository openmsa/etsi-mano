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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.nfvo.v261.OrikaConfigurationNfvo261;
import com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant.GrantRequest;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class GrantTest {

	private final DefaultMapperFactory mapperFactory;
	private final PodamFactoryImpl podam;

	public GrantTest() {
		final OrikaConfigurationNfvo261 orikaConfiguration = new OrikaConfigurationNfvo261();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);

		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
	}

	@Test
	void testRequestJson() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final GrantRequest gr = podam.manufacturePojo(GrantRequest.class);
		final GrantResponse resp = mapper.map(gr, GrantResponse.class);
		assertEquals(5, resp.getAddResources().size());
		final GrantInformationExt res = resp.getAddResources().iterator().next();
		assertNull(res.getId());
		assertNotNull(res.getVduId());
		assertEquals(5, resp.getVimConnections().size());
		assertNotNull(resp.getVimConnections().iterator().next().getVimId());
	}

}
