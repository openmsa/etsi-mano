package com.ubiqube.etsi.mano.mapping;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.model.v271.sol003.lcmgrant.GrantRequest;
import com.ubiqube.etsi.mano.model.v271.sol005.nsd.NsdInfo;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.NsInstance;
import com.ubiqube.etsi.mano.model.v271.sol005.nslcm.NsLcmOpOcc;
import com.ubiqube.etsi.mano.nfvo.v271.OrikaConfigurationNfvo271;
import com.ubiqube.etsi.mano.test.TestHelper;

public class MappingTest extends TestHelper {

	public MappingTest() {
		super(new OrikaConfigurationNfvo271());
	}

	@Test
	void testNsdInfo() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getLinks");
		doTest(NsdInfo.class, NsdPackage.class, ignore);
	}

	@Test
	void testNsLcmOpOcc() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getLinks");
		ignore.add("getResourceChanges");
		ignore.add("getStatusEnteredTime");
		ignore.add("getOperationParams");
		ignore.add("getCancelMode");
		doTest(NsLcmOpOcc.class, NsBlueprint.class, ignore);
	}

	@Test
	void testGrant() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getLinks");
		ignore.add("getInstantiationLevelId");
		ignore.add("getResource");
		ignore.add("getResourceTemplateId");
		ignore.add("getPlacementConstraints");
		ignore.add("getVimConstraints");
		doTest(GrantRequest.class, GrantResponse.class, ignore);
	}

	@Test
	void testNsdInstance() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getLinks");
		ignore.add("getVnfInstance");
		ignore.add("getVirtualLinkInfo");
		ignore.add("getPnfInfo");
		ignore.add("getSapInfo");
		ignore.add("getVnffgInfo");
		ignore.add("getAdditionalAffinityOrAntiAffinityRule");
		ignore.add("getMonitoringParameter");
		ignore.add("getNsScaleStatus");
		doTest(NsInstance.class, NsdInstance.class, ignore);
	}

}
