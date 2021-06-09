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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.nfvo.v261.OrikaConfigurationNfvo261;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.InstantiateNsRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.NsInstance;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class NsdInstanceTest {
	private final DefaultMapperFactory mapperFactory;

	public NsdInstanceTest() {
		final OrikaConfigurationNfvo261 orikaConfiguration = new OrikaConfigurationNfvo261();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testJsonToDao() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final NsInstance nsInstance = new NsInstance();
		final List<UUID> nestedNsInstanceId = new ArrayList<>();
		nestedNsInstanceId.add(UUID.fromString("3bba2147-147a-41ab-a3ec-1a39e1b6d922"));
		nsInstance.setNestedNsInstanceId(nestedNsInstanceId);
		nsInstance.setNsdInfoId(UUID.fromString("3bba2147-147a-41ab-a3ec-1a39e1b6d922"));

		final NsdInstance nsd = mapper.map(nsInstance, NsdInstance.class);
		assertNotNull(nsd.getNestedNsInstance());
		final List<NsdInstance> l = nsd.getNestedNsInstance();
		assertEquals(1, l.size());
		assertNotNull(l.get(0).getId());
		assertEquals("3bba2147-147a-41ab-a3ec-1a39e1b6d922", l.get(0).getId().toString());

	}

	@Test
	void testInstantiateNsRequest() throws Exception {
		final InstantiateNsRequest req = new InstantiateNsRequest();
		req.setNsFlavourId("flav");
		req.setNsInstantiationLevelId("instlvl");
		final NsdInstance nsdi = new NsdInstance();
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		mapper.map(req, nsdi);
		assertEquals("flav", nsdi.getInstantiatedVnfInfo().getFlavourId());
		assertEquals("instlvl", nsdi.getNsInstantiationLevelId());
	}
}
