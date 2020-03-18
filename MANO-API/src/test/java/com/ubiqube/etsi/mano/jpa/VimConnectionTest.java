package com.ubiqube.etsi.mano.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.model.VimConnectionInfo;

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
		assertEquals("https://10.18.54.42:13001/v3/", map.get("endpoint"));
	}
}
