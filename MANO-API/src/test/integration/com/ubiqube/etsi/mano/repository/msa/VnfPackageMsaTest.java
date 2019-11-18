package com.ubiqube.etsi.mano.repository.msa;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.grammar.JsonBeanUtil;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.Grant;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesCreateNsRequest;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.DefaultNamingStrategy;
import com.ubiqube.etsi.mano.repository.Low;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;
import com.ubiqube.etsi.mano.service.Configuration;
import com.ubiqube.etsi.mano.service.PropertiesConfiguration;
import com.ubiqube.etsi.mano.service.rest.RepositoryServiceRest;
import com.ubiqube.etsi.mano.service.rest.UbiRest;

public class VnfPackageMsaTest {

	private final VnfPackageMsa vnfPackageMsa;

	public VnfPackageMsaTest() {
		final JsonFilter jsonFilter = new JsonFilter(new JsonBeanUtil());
		final ObjectMapper mapper = new ObjectMapper();
		final Configuration conf = new PropertiesConfiguration();
		final RepositoryService repositoryService = new RepositoryServiceRest(new UbiRest(conf));

		final Low low = new LowMsa(repositoryService);
		final NamingStrategy namingStrategy = new DefaultNamingStrategy(conf);
		vnfPackageMsa = new VnfPackageMsa(mapper, jsonFilter, low, namingStrategy);
		final VnfInstancesRepository _vnfInstancesRepository = new VnfInstancesMsa(mapper, repositoryService, jsonFilter, vnfPackageMsa);
		final VnfLcmOpOccsRepository _vnfLcmOpOccsRepository = new VnfLcmOpOccsMsa(mapper, repositoryService, jsonFilter, _vnfInstancesRepository, vnfPackageMsa);

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

	@Test
	public void testStoreError() {
		assertThrows(NotFoundException.class, () -> {
			vnfPackageMsa.storeObject("BAD", "grant", new Grant());
		});
	}

	@Test
	public void testStoreScenario() {
		final VnfPkgInfo entity = new VnfPkgInfo();
		vnfPackageMsa.save(entity);
		assertNotNull(entity.getId());

		vnfPackageMsa.storeObject(entity.getId(), "grant", new Grant());
		vnfPackageMsa.loadObject(entity.getId(), "grant", Grant.class);
		vnfPackageMsa.delete(entity.getId());
	}

	@Test
	public void testLoadObjectError() {
		assertThrows(NotFoundException.class, () -> {
			vnfPackageMsa.loadObject("BAD", "grant", NsInstancesCreateNsRequest.class);
		});
	}

	@Test
	public void testBinaryScenario() throws FileNotFoundException, NoSuchAlgorithmException {
		final VnfPkgInfo entity = new VnfPkgInfo();
		vnfPackageMsa.save(entity);
		assertNotNull(entity.getId());

		final InputStream stream = new FileInputStream("src/test/resources/pack.zip");
		vnfPackageMsa.storeBinary(entity.getId(), "file", stream);

		byte[] bytes = vnfPackageMsa.getBinary(entity.getId(), "file");
		final MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(bytes);
		assertEquals("4d251f6f44b12f8e6a0b2e9e7e69e603", DatatypeConverter.printHexBinary(md5.digest()).toLowerCase());

		bytes = vnfPackageMsa.getBinary(entity.getId(), "file", 0, 2L);

		assertEquals(2, bytes.length);
		assertEquals('P', bytes[0]);
		assertEquals('K', bytes[1]);
		vnfPackageMsa.delete(entity.getId());
	}

}
