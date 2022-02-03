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
package com.ubiqube.etsi.mano.repository.msa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.common.v261.model.lcmgrant.Grant;
import com.ubiqube.etsi.mano.config.properties.ManoRepositoryProperties;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.grammar.GrammarParser;
import com.ubiqube.etsi.mano.grammar.JsonBeanUtil;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.grammar.v1.Grammarv1Service;
import com.ubiqube.etsi.mano.repository.DefaultNamingStrategy;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.repository.phys.LowPhys;
import com.ubiqube.etsi.mano.repository.phys.VnfPackagePhys;
import com.ubiqube.etsi.mano.test.TestTools;

@Tag("Remote")
class VnfPackageGenTest {
	private final VnfPackageRepository vnfPackage;
	private final GrammarParser gp = new Grammarv1Service();

	public VnfPackageGenTest() {
		final JsonFilter jsonFilter = new JsonFilter(new JsonBeanUtil());
		final ObjectMapper mapper = new ObjectMapper();
		final ManoRepositoryProperties conf = new ManoRepositoryProperties();
		conf.setPhysRoot("/tmp/");
		final NamingStrategy namingStrategy = new DefaultNamingStrategy(conf);
		vnfPackage = new VnfPackagePhys(mapper, jsonFilter, new LowPhys(), namingStrategy, gp);
	}

	@Test
	void testName() throws Exception {

		VnfPackage entity = new VnfPackage();
		vnfPackage.save(entity);
		assertNotNull(entity.getId());

		entity = vnfPackage.get(entity.getId());
		assertNotNull(entity);

		List<VnfPackage> res = vnfPackage.query(null);
		assertNotNull(res);
		final int num = res.size();
		assertTrue(num >= 1);

		vnfPackage.delete(entity.getId());

		res = vnfPackage.query(null);
		assertNotNull(res);
		assertEquals(num - 1, res.size());
	}

	@Test
	public void testStoreScenario() {
		final VnfPackage entity = new VnfPackage();
		vnfPackage.save(entity);
		assertNotNull(entity.getId());

		vnfPackage.storeObject(entity.getId(), "grant", new Grant());
		vnfPackage.delete(entity.getId());
	}

	@Test
	public void testBinaryScenario() throws NoSuchAlgorithmException, IOException {
		final VnfPackage entity = new VnfPackage();
		vnfPackage.save(entity);
		assertNotNull(entity.getId());

		final String path = "/pack.zip";
		final URL url = TestTools.class.getResource(path);
		if (null == url) {
			throw new RuntimeException("Could not find path: " + path);
		}
		try (InputStream is = url.openStream()) {
			vnfPackage.storeBinary(entity.getId(), "file", is);
		}

		byte[] bytes = vnfPackage.getBinary(entity.getId(), "file");
		final MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(bytes);
		assertEquals("4d251f6f44b12f8e6a0b2e9e7e69e603", DatatypeConverter.printHexBinary(md5.digest()).toLowerCase());

		bytes = vnfPackage.getBinary(entity.getId(), "file", 0, Long.decode("2"));

		assertEquals(2, bytes.length);
		assertEquals('P', bytes[0]);
		assertEquals('K', bytes[1]);
		vnfPackage.delete(entity.getId());
	}

}
