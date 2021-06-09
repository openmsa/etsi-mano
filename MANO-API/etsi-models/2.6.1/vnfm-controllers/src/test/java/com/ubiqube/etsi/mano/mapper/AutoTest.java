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
package com.ubiqube.etsi.mano.mapper;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.dto.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant.GrantRequest;
import com.ubiqube.etsi.mano.test.TestHelper;
import com.ubiqube.etsi.mano.vnfm.v261.OrikaMapperVnfm261;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfLcmOpOcc;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class AutoTest extends TestHelper {

	public AutoTest() {
		super(new OrikaMapperVnfm261());
	}

	@Test
	void testVnfPkgInfo() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getSoftwareImages");
		ignore.add("getLinks");
		doTest(VnfPkgInfo.class, VnfPackage.class, ignore);
	}

	@Test
	void testVnfInstance() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getExtManagedVirtualLinkInfo");
		ignore.add("getVnfcResourceInfo");
		ignore.add("getLinks");
		ignore.add("getLocalizationLanguage");
		ignore.add("getVirtualLinkResourceInfo");
		ignore.add("getMonitoringParameters");
		ignore.add("getExtCpInfo");
		ignore.add("getExtVirtualLinkInfo");
		ignore.add("getVirtualStorageResourceInfo");
		ignore.add("getVimId");
		doTest(VnfInstance.class, com.ubiqube.etsi.mano.dao.mano.VnfInstance.class, ignore);
	}

	@Test
	void testLcmOpOccs() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getChangedInfo");
		ignore.add("getChangedExtConnectivity");
		ignore.add("getLinks");
		ignore.add("getVirtualStorageDescId");
		ignore.add("getStorageResource");
		doTest(VnfLcmOpOcc.class, VnfLcmOpOccs.class, ignore);
	}

	@Test
	void testGrant() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getInstantiationLevelId");
		ignore.add("getLinks");
		ignore.add("getVimLevelResourceType");
		ignore.add("getResourceId");
		ignore.add("getResourceTemplateId");
		ignore.add("getPlacementConstraints");
		ignore.add("getVimConstraints");
		doTest(GrantRequest.class, GrantResponse.class, ignore);
	}
}
