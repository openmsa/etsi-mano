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
package com.ubiqube.grammar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.common.v261.model.vnf.Checksum;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageSoftwareImageInfo;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.grammar.JsonBeanProperty;
import com.ubiqube.etsi.mano.grammar.JsonBeanUtil;

public class JsonBeanUtilTest {

	@Test
	void testVnfPkg() throws Exception {
		final JsonBeanUtil jsonBeanUtil = new JsonBeanUtil();
		final Object _object = new VnfPkgInfo();
		final Map<String, JsonBeanProperty> res = jsonBeanUtil.getProperties(_object);

		JsonBeanProperty element = res.get("softwareImages.name");
		assertNotNull(element, "Could not find softwareImages.name");
		List<JsonBeanProperty> accessors = element.getListAccessors();
		assertNotNull(accessors, "List of accessors is null.");
		assertEquals(2, accessors.size(), "List of accessors should be 2");
		assertEquals("softwareImages", accessors.get(0).getJsonName(), "Sowtware Image not found.");

		element = res.get("_links.self.href");
		assertNotNull(element, "Could not find _links.self.href");
		accessors = element.getListAccessors();
		assertNotNull(accessors, "List of accessors is null.");
		assertEquals(3, accessors.size(), "List of accessors should be 2");
	}

	@Test
	void testSubscription() throws Exception {
		final JsonBeanUtil jsonBeanUtil = new JsonBeanUtil();
		final VnfPkgInfo _object = new VnfPkgInfo();
		final List<VnfPackageSoftwareImageInfo> softwareImages = new ArrayList<>();
		final VnfPackageSoftwareImageInfo vnfSoftwareImage = new VnfPackageSoftwareImageInfo();
		final Checksum checksum = new Checksum();
		checksum.setAlgorithm("SHA-512");
		vnfSoftwareImage.setChecksum(checksum);
		softwareImages.add(vnfSoftwareImage);
		_object.setSoftwareImages(softwareImages);
		final Map<String, JsonBeanProperty> res = jsonBeanUtil.getProperties(_object);

		System.out.println("" + res);
		final JsonBeanProperty element = res.get("softwareImages.checksum.algorithm");
		assertNotNull(element, "Could not find softwareImages.name");
		final List<JsonBeanProperty> accessors = element.getListAccessors();
		assertNotNull(accessors, "List of accessors is null.");
		assertEquals(3, accessors.size(), "List of accessors should be 2");
	}

	@Test
	void testCache() throws Exception {
		final JsonBeanUtil jsonBeanUtil = new JsonBeanUtil();
		final Object _object = new VnfPkgInfo();
		final Map<String, JsonBeanProperty> res1 = jsonBeanUtil.getProperties(_object);
		final Map<String, JsonBeanProperty> res2 = jsonBeanUtil.getProperties(_object);

		assertEquals(res1, res2, "Result should be the same.");
	}
}