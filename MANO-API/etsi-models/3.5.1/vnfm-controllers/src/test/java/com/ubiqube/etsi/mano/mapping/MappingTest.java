package com.ubiqube.etsi.mano.mapping;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.nfvo.v351.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.test.TestHelper;
import com.ubiqube.etsi.mano.vnfm.v351.OrikaMapperVnfm351;
import com.ubiqube.etsi.mano.vnfm.v351.model.grant.GrantRequest;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnflcm.VnfInstance;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnflcm.VnfLcmOpOcc;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class MappingTest extends TestHelper {

	public MappingTest() {
		super(new OrikaMapperVnfm351());
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
		ignore.add("getExtManagedVirtualLinkInfo");
		ignore.add("getInstantiatedVnfInfo");
		doTest(VnfInstance.class, com.ubiqube.etsi.mano.dao.mano.VnfInstance.class, ignore);
	}

	@Test
	void testLcmOpOccs() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getLinks");
		ignore.add("getChangedInfo");
		ignore.add("getChangedExtConnectivity");
		ignore.add("getResourceChanges");
		ignore.add("getAffectedVipCps");
		doTest(VnfLcmOpOcc.class, VnfBlueprint.class, ignore);
	}

	@Test
	void testGrant() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getLinks");
		doTest(GrantRequest.class, GrantResponse.class, ignore);
	}

}
