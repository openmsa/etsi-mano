package com.ubiqube.etsi.mano.factory;

import java.util.Map;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;

public class VnfPackageFactory {
	private VnfPackageFactory() {
		// Nothing.
	}

	@Nonnull
	public static VnfPackage createVnfPkgInfo(final Map<String, String> userData) {
		final VnfPackage vnfPkgInfo = new VnfPackage();
		vnfPkgInfo.setOnboardingState(OnboardingStateType.CREATED);
		vnfPkgInfo.setUserDefinedData(userData);
		vnfPkgInfo.setOperationalState(PackageOperationalState.DISABLED);
		vnfPkgInfo.setUsageState(PackageUsageState.NOT_IN_USE);

		return vnfPkgInfo;
	}

}
