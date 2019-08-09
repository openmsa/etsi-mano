package com.ubiqube.etsi.mano.factory;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.UsageStateEnum;

public class VnfPackageFactory {

	public static VnfPkgInfo createVnfPkgInfo(String vnfPkgId, Object userData) {
		final VnfPkgInfo vnfPkgInfo = new VnfPkgInfo();
		vnfPkgInfo.setId(vnfPkgId);
		vnfPkgInfo.setOnboardingState(OnboardingStateEnum.CREATED);
		vnfPkgInfo.setUserDefinedData(userData);
		vnfPkgInfo.setOperationalState(OperationalStateEnum.DISABLED);
		vnfPkgInfo.setUsageState(UsageStateEnum.NOT_IN_USE);

		return vnfPkgInfo;
	}
}
