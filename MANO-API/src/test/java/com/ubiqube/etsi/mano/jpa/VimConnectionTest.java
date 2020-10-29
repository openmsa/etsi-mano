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
package com.ubiqube.etsi.mano.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.common.v261.model.VimConnectionInfo;
import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class VimConnectionTest {
	private final DefaultMapperFactory mapperFactory;

	public VimConnectionTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
	}

	@Test
	void testJsonToDaoMapping() throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		final File src = new File("src/test/resources/vim-connection/openstack.json");
		final VimConnectionInfo vci = mapper.readValue(src, VimConnectionInfo.class);

		System.out.println("vci=" + vci);

		final MapperFacade oMapper = mapperFactory.getMapperFacade();
		final VimConnectionInformation res = oMapper.map(vci, VimConnectionInformation.class);
		System.out.println("res=" + res);
		assertEquals("OPENSTACK_V3", res.getVimType());
		Map<String, String> map = res.getAccessInfo();
		assertEquals("admin", map.get("username"));
		assertEquals("Default", map.get("projectDomain"));

		map = res.getInterfaceInfo();
		assertEquals("http://10.31.1.240:5000/v3", map.get("endpoint"));
	}
}
