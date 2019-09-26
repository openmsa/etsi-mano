package com.ubiqube.serialization;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesCreateNsRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesPostQuery;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum;

public class SerializationTest {
	ObjectMapper mapper = new ObjectMapper();

	@Test
	void testName() throws Exception {
		final NsInstancesPostQuery nsp = new NsInstancesPostQuery();
		final NsInstancesCreateNsRequest createNsRequest = new NsInstancesCreateNsRequest();
		createNsRequest.setNsDescription("description");
		createNsRequest.setNsdId("nsd-id");
		createNsRequest.setNsName("name");
		nsp.createNsRequest(createNsRequest);

		System.out.println(mapper.writeValueAsString(nsp));
	}

	@Test
	void testVnfPkgInfo() throws Exception {
		final VnfPkgInfo vnfPkg = new VnfPkgInfo();
		vnfPkg.setOperationalState(OperationalStateEnum.ENABLED);

		final String res = mapper.writeValueAsString(vnfPkg);
		final VnfPkgInfo vnf2 = mapper.readValue(res.getBytes(), VnfPkgInfo.class);

		assertEquals("ENABLED", vnf2.getOperationalState().value());

	}
}
