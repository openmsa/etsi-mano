package com.ubiqube.etsi.mano.model.vnf;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

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
		final VnfPkgIndex vnfPkgIndex = mapper.readValue(str.getBytes(), VnfPkgIndex.class);
		final VnfPkgInstance instance = vnfPkgIndex.getVnfPkgInstance("DEADBEEF");
		assertNotNull(instance);
		final List<VnfPkgOperation> ope = instance.getOperations();
		assertEquals(1, ope.size());

	}
}
