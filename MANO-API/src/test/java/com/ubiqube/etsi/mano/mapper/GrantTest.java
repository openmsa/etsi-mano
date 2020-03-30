package com.ubiqube.etsi.mano.mapper;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.Grants;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class GrantTest {
	private final DefaultMapperFactory mapperFactory;
	private final ObjectMapper jsonMapper;

	public GrantTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
		jsonMapper = new ObjectMapper();
	}

	@Test
	void testName() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/grant-request.json"));
		final GrantRequest grantRequest = jsonMapper.readValue(bytes, GrantRequest.class);

		final Grants grants = mapper.map(grantRequest, Grants.class);
		System.out.println("" + grants);
	}
}
