package com.ubiqube.etsi.mano.model.vnf;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	void testName() throws Exception {
		final VnfPkgIndex index = new VnfPkgIndex();
		final VnfPkgInstance vnfInstance = new VnfPkgInstance();
		vnfInstance.setInstanceId("DEADBEEF");
		final VnfPkgOperation operation = new VnfPkgOperation("ID", "processId");
		vnfInstance.getOperations().add(operation);
		index.addVnfPkgInstance(vnfInstance);

		final String str = mapper.writeValueAsString(index);
		System.out.println(str);
		final VnfPkgIndex vnfPkgIndex = mapper.readValue(str.getBytes(), VnfPkgIndex.class);
	}
}
