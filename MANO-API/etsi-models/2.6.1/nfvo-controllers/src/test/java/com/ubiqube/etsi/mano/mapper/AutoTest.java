package com.ubiqube.etsi.mano.mapper;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.nfvo.v261.OrikaConfigurationNfvo261;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.NsLcmOpOcc;
import com.ubiqube.etsi.mano.test.TestHelper;

public class AutoTest extends TestHelper {

	public AutoTest() {
		super(new OrikaConfigurationNfvo261());
	}

	@Test
	void testNsPackage() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getLinks");
		doTest(NsdInfo.class, NsdPackage.class, ignore);
	}

	@Test
	void testNsLcmOpOccs() throws Exception {
		final Set<String> ignore = new HashSet<>();
		ignore.add("getLinks");
		// XXX
		ignore.add("getResourceChanges");
		// XXX Same as lcmOperationType
		ignore.add("getOperationParams");
		doTest(NsLcmOpOcc.class, NsBlueprint.class, ignore);
	}

}
