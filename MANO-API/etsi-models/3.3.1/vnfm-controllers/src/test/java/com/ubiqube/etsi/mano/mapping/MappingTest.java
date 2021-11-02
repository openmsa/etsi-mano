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
package com.ubiqube.etsi.mano.mapping;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.test.TestHelper;
import com.ubiqube.etsi.mano.vnfm.v331.OrikaMapperVnfm331;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.GrantRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.VnfInstance;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.VnfLcmOpOcc;

public class MappingTest extends TestHelper {

	public MappingTest() {
		super(new OrikaMapperVnfm331());
	}

	@Test
	void testVnfPkgInfo() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getSoftwareImages");
		ignore.add("getLinks");
		ignore.add("getChecksum");
		doTest(VnfPkgInfo.class, VnfPackage.class, ignore);
	}

	@Test
	void testVnfInstance() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getLinks");
		ignore.add("getExtLinkPortId");
		doTest(VnfInstance.class, com.ubiqube.etsi.mano.dao.mano.VnfInstance.class, ignore);
	}

	@Test
	void testLcmOpOccs() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getLinks");
		ignore.add("getChangedInfo");
		ignore.add("getChangedExtConnectivity");
		ignore.add("getResourceChanges");
		doTest(VnfLcmOpOcc.class, VnfBlueprint.class, ignore);
	}

	@Test
	void testGrant() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getLinks");
		doTest(GrantRequest.class, GrantResponse.class, ignore);
	}
}