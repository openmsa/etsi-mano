package com.ubiqube.serialization;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.model.vnf.sol005.PackageOperationalStateType;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public class SerializationTest {
	ObjectMapper mapper = new ObjectMapper();

	@Test
	void testVnfPkgInfo() throws Exception {
		final VnfPkgInfo vnfPkg = new VnfPkgInfo();
		vnfPkg.setOperationalState(PackageOperationalStateType.ENABLED);

		final String res = mapper.writeValueAsString(vnfPkg);
		final VnfPkgInfo vnf2 = mapper.readValue(res.getBytes(), VnfPkgInfo.class);

		assertEquals(PackageOperationalStateType.ENABLED, vnf2.getOperationalState());

	}
}
