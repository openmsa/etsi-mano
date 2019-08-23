package com.ubiqube.etsi.mano.repository.msa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.grammar.JsonBeanUtil;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.service.RepositoryServiceRest;

public class VnfPackageMsaTest {

	private final VnfPackageMsa vnfPackageMsa;

	public VnfPackageMsaTest() {
		final JsonFilter jsonFilter = new JsonFilter(new JsonBeanUtil());
		final ObjectMapper mapper = new ObjectMapper();
		final RepositoryService repositoryService = new RepositoryServiceRest();
		vnfPackageMsa = new VnfPackageMsa(mapper, repositoryService, jsonFilter);
	}

	@Test
	void testName() throws Exception {

		VnfPkgInfo entity = new VnfPkgInfo();
		vnfPackageMsa.save(entity);
		assertNotNull(entity.getId());

		entity = vnfPackageMsa.get(entity.getId());
		assertNotNull(entity);

		List<VnfPkgInfo> res = vnfPackageMsa.query(null);
		assertNotNull(res);
		final int num = res.size();
		assertTrue(num >= 1);

		vnfPackageMsa.delete(entity.getId());

		res = vnfPackageMsa.query(null);
		assertNotNull(res);
		assertEquals(num - 1, res.size());
	}

	@Test
	void testNotFound() throws Exception {
		assertThrows(NotFoundException.class, () -> {
			vnfPackageMsa.get("DEADBEEF");
		});
	}
}
