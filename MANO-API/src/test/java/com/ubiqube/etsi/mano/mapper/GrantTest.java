package com.ubiqube.etsi.mano.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.config.OrikaConfiguration;
import com.ubiqube.etsi.mano.dao.mano.Grants;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class GrantTest {
	private final DefaultMapperFactory mapperFactory;
	private final ObjectMapper jsonMapper;
	private final PodamFactoryImpl podam;

	public GrantTest() {
		final OrikaConfiguration orikaConfiguration = new OrikaConfiguration();
		mapperFactory = new DefaultMapperFactory.Builder().build();
		orikaConfiguration.configure(mapperFactory);
		jsonMapper = new ObjectMapper();

		podam = new PodamFactoryImpl();
		podam.getStrategy().addOrReplaceTypeManufacturer(String.class, new UUIDManufacturer());
	}

	@Test
	void testName() throws Exception {
		final MapperFacade mapper = mapperFactory.getMapperFacade();
		final byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/grant-request.json"));
		// final GrantRequest grantRequest = jsonMapper.readValue(bytes,
		// GrantRequest.class);
		final GrantRequest grantRequest = podam.manufacturePojo(GrantRequest.class);
		final Grants grants = mapper.map(grantRequest, Grants.class);
		System.out.println("" + grants);
		assertEquals(grantRequest.getAdditionalParams().size(), grants.getAdditionalParams().size());
		assertEquals(grantRequest.getAddResources().size(), grants.getAddResources().size());
		// assertEquals(grantRequest.isAutomaticInvocation(),
		// grants.isAutomaticInvocation());
		assertEquals(grantRequest.getFlavourId(), grants.getFlavourId());
		assertEquals(grantRequest.getOperation().toString(), grants.getOperation().toString());
		assertEquals(grantRequest.getRemoveResources().size(), grants.getRemoveResources().size());
		assertEquals(grantRequest.getTempResources().size(), grants.getTempResources().size());
		assertEquals(grantRequest.getUpdateResources().size(), grants.getUpdateResources().size());
		assertEquals(grantRequest.getVnfdId(), grants.getVnfdId());
		assertEquals(grantRequest.getVnfInstanceId(), grants.getVnfInstanceId());
		assertEquals(grantRequest.getVnfLcmOpOccId(), grants.getVnfLcmOpOccId());
	}
}
